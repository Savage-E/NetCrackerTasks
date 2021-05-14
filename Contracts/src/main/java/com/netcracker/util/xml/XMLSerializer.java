package com.netcracker.util.xml;

import com.netcracker.Repository;
import com.netcracker.entities.Contract;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Class provides serialization.
 */
public class XMLSerializer {
  private static final Logger logger = LogManager.getLogger(XMLSerializer.class);

  /**
   * Serializes repository to xml format.
   *
   * @param repository the repository to serialize.
   */
  public void toXml(Repository repository) {
    try {
      JAXBContext context = JAXBContext.newInstance(Contracts.class);
      Marshaller marshaller = context.createMarshaller();
      marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
      File output = new File("contracts.xml");
      Contracts contracts = new Contracts();
      List<Contract> contractList = repository.toArrayList();
      contracts.setContractList(contractList);
      marshaller.marshal(contracts, output);
    } catch (JAXBException e) {
      logger.error(e);
    }
  }

  /**
   * Reads xml file and extract it to repository.
   *
   * @param file the file to read from.
   * @return the repository
   */
  public Repository fromXml(String file) {
    File xmlFile = new File(file);
    Repository repository = new Repository();
    Contracts contracts = null;
    JAXBContext jaxbContext;
    try {
      jaxbContext = JAXBContext.newInstance(Contracts.class);
      Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
      if (!xmlFile.exists()) System.out.println("File does not exist");
      assert jaxbUnmarshaller != null;
      contracts = (Contracts) jaxbUnmarshaller.unmarshal(xmlFile);
    } catch (JAXBException e) {
      e.printStackTrace();
    }
    assert contracts != null;
    repository.setArrayList((ArrayList<Contract>) contracts.getContractList());

    return repository;

  }

}
