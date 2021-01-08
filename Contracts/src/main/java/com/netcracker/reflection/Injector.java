package com.netcracker.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reflections.Reflections;


/**
 * Provides Dependency Injection.
 */
@Configuration(packages = {"com.netcracker.validators", "com.netcracker.util"})
public class Injector {

  private List<Object> list = new ArrayList<>();
  private static final Logger logger = LogManager.getLogger(Injector.class);

  /**
   * Injects dependencies into a specified object.
   *
   * @param object the object to inject dependencies
   * @param <T>    the type of the class
   * @return object  with injected dependencies
   * @throws IllegalAccessException if application does not have access to the definition of the specified class, field, method or constructor.
   */
  public static <T> T inject(T object) throws IllegalAccessException {
    logger.debug("Starting method inject");
    List<Object> classInject = new ArrayList<>();
    Class<T> clazz = (Class<T>) object.getClass();
    Field[] fields = clazz.getDeclaredFields();

    ArrayList<Object> objects = new ArrayList<>();

    for (Field f : fields) {
      if (f.isAnnotationPresent(AutoInjectable.class)) {
        f.setAccessible(true);
        if (f.getType().getName().contains("java.util.List")) {
          logger.debug("Starting injection to collection");
          ParameterizedType fieldListType = (ParameterizedType) f.getGenericType();
          Class<?> fieldGenericType = (Class<?>) fieldListType.getActualTypeArguments()[0];
          getClasses(objects, fieldGenericType);

          for (Object o : objects) {
            if (o != null && fieldGenericType.isAssignableFrom(o.getClass())) {
              classInject.add(o);
            }
            f.set(object, classInject);
          }
        } else {
          logger.debug("Starting injection to single object");
          getClasses(objects, f.getType());
          for (Object o : objects) {
            if (o != null && f.getType().isAssignableFrom(o.getClass())) {
              classInject.add(o);
            }
            if (classInject.size() == 1) {
              f.set(object, classInject.get(0));
            } else {
              logger.info("Error to inject the object");
              throw new RuntimeException("Amount of classes more than 1 (only 1 valid)");
            }
          }
        }
      }
    }
    logger.debug("Exiting method readFrom");
    return object;
  }


  private static void getClasses(ArrayList<Object> objects, Class<?> type) {
    logger.debug("Exiting method getClasses");
    Configuration packages = Injector.class.getAnnotation(Configuration.class);
    Reflections reflections;
    for (String pack : packages.packages()) {
      reflections = new Reflections(pack);
      Set<Class<?>> classes = reflections.getSubTypesOf((Class<Object>) type);
      for (Class<?> o : classes) {
        try {
          logger.debug("Injecting the object");
          objects.add(o.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
          logger.info("Error to inject the object");
          logger.error("Exception:",e);
        }
      }
    }
    logger.debug("Exiting method getClasses");
  }
}

