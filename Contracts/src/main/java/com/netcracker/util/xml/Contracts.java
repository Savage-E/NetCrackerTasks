package com.netcracker.util.xml;

import com.netcracker.entities.Contract;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vlad Kotov
 * Date: 02.май2021
 * Time:  10:48
 * Project: Contracts
 * Description:
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Contracts {
  @XmlElement
  List<Contract> contractList = new ArrayList<>();

  public Contracts() {

  }

  public List<Contract> getContractList() {
    return contractList;
  }

  public void setContractList(List<Contract> contractList) {
    this.contractList = contractList;
  }
}
