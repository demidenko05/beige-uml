package org.beigesoft.converter;

public interface IConverter<FR, TO> {

  public TO convert(FR from);
}
