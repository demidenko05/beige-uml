package org.beigesoft.uml.model;

public enum EClassKind {
  CLASS("Class"), INTERFACE("«interface»"), ENUM("«enumeration»");
  private String value;
  private EClassKind() {}
  private EClassKind(String value) {
    this.value = value;
  }
  @Override
  public String toString() {
    return value;
  }
}
