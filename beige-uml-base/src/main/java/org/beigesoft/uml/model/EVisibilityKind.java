package org.beigesoft.uml.model;

public enum EVisibilityKind {
  PRIVATE("private"), PUBLIC("public"), PROTECTED("protected"), PACKAGE("package");
  private String value;
  private EVisibilityKind() {}
  private EVisibilityKind(String value) {
    this.value = value;
  }
  @Override
  public String toString() {
    return value;
  }
}
