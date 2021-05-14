package com.netcracker.util.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

/**
 * Created by Vlad Kotov
 * Date: 02.май2021
 * Time:  10:53
 * Project: Contracts
 * Description:
 */
public class DateXmlAdapter extends XmlAdapter<String, org.joda.time.LocalDate> {

  @Override
  public org.joda.time.LocalDate unmarshal(String v) throws Exception {
    return org.joda.time.LocalDate.parse(v);
  }

  @Override
  public String marshal(org.joda.time.LocalDate v) {
    return v.toString();
  }
}
