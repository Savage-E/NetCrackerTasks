package com.netcracker.reflection;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Set;
import java.util.List;
import org.reflections.Reflections;


/**
 * Provides Dependency Injection.
 */
@Configuration(packages = {"com.netcracker.validators", "com.netcracker.util"})
public class Injector {

  private List<Object> list = new ArrayList<>();


  /**
   * Injects dependencies into a specified object.
   *
   * @param object the object to inject dependencies
   * @param <T>    the type of the class
   * @return object  with injected dependencies
   * @throws IllegalAccessException
   */
  public static <T> T inject(T object) throws IllegalAccessException {

    List<Object> classInject = new ArrayList<>();
    Class<T> clazz = (Class<T>) object.getClass();
    Field[] fields = clazz.getDeclaredFields();

    ArrayList<Object> objects = new ArrayList<>();

    for (Field f : fields) {
      if (f.isAnnotationPresent(AutoInjectable.class)) {
        f.setAccessible(true);
        if (f.getType().getName().contains("java.util.List")) {
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
          getClasses(objects, f.getType());
          for (Object o : objects) {
            if (o != null && f.getType().isAssignableFrom(o.getClass())) {
              classInject.add(o);
            }
            if (classInject.size() == 1) {
              f.set(object, classInject.get(0));
            } else {
              throw new RuntimeException("Amount of classes more than 1 (only 1 valid)");
            }
          }
        }
      }
    }
    return object;
  }


  private static void getClasses(ArrayList<Object> objects, Class<?> type) {
    Configuration packages = Injector.class.getAnnotation(Configuration.class);
    Reflections reflections;
    for (String pack : packages.packages()) {
      reflections = new Reflections(pack);
      Set<Class<?>> classes = reflections.getSubTypesOf((Class<Object>) type);
      for (Class<?> o : classes) {
        try {
          objects.add(o.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
          e.printStackTrace();
        }
      }
    }

  }
}

