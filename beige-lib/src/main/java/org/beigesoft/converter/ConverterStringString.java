package org.beigesoft.converter;

public class ConverterStringString implements IConverter<String, String> {

  @Override
  public String convert(String from) {
    return from;
  }
}
