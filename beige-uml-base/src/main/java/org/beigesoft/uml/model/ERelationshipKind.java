package org.beigesoft.uml.model;

public enum ERelationshipKind {
  GENERALIZATION("Generalization"), ASSOCIATION("Association"), INTERFACE_REALIZATION("interface realization"),
  AGGREGATION("Aggregation"), COMPOSITION("Composition"),
  REALIZATION("realization"), SUBSTITUTION("substitute"), USAGE("use"), IMPORT("import"), MERGE("merge"), EXTEND("extend"), INCLUDE("include"), 
  PACKAGE_IMPORT("Package import"), PACKAGE_MERGE("Package merge"), PACKAGE_ACCESS("Package access");

  private String value;
  private ERelationshipKind() {}
  private ERelationshipKind(String value) {
    this.value = value;
  }
  @Override
  public String toString() {
    return value;
  }
}
