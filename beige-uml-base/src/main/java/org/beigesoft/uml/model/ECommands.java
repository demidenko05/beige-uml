package org.beigesoft.uml.model;

public enum ECommands {
  SELECT("Select"), CLASS("Class"), INTERFACE("Interface"), ENUMERATION("Enumeration"), GENERALIZATION("Generalization"), GENERALIZATION_SIMPLE("Generalization"),
  REALIZATION("Realization"), COMMENT("Comment+"), TEXT("Text"), ASSOCIATION("Association"), ASSOCIATION_SIMPLE("Association"),
  AGGREGATION("Aggregation"), COMPOSITION("Composition"), DEPENDENCY("Dependency"), ACTOR("Actor"), ACTOR_ICON("Actor icon"),
  USE_CASE("Use case"), EXTEND_SIMPLE("Extend"), INCLUDE_SIMPLE("Include"), USE_CASEEXTENDED("Extension point"), RELATIONSHIP_SELF("Self-relationship"), 
  RELATIONSHIP_POLY("N-ary relationship"), ZOOM_IN("Zoom in"), ZOOM_OUT("Zoom out"), ZOOM_11("Zoom 1:1"), FRAME("Frame"), RECTANGLE("Rectangle"), 
  PACKAGE_IMPORT("Package import"), PACKAGE_MERGE("Package merge"), PACKAGE_ACCESS("Package access"), PACKAGE("Package"),
  INSTANCE("Instance"), LIFELINE("Lifeline"), MESSAGE("Message"), STATEINVCONTIN("State invariant+"), INTERACTIONUSE("Interaction use"), 
  COMBINEDFRAGMENT("Combined fragment"), COREGION_MESSAGES("Coregion messages"), LINE("Line");
  private String value;
  private ECommands() {}
  private ECommands(String value) {
    this.value = value;
  }
  @Override
  public String toString() {
    return value;
  }
}
