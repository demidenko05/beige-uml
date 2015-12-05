package org.beigesoft.uml.model;

public enum EInteractionOperator {
  ALTTERNATIVES("alt"), OPTION("opt"), BREAK("break"), PARALLEL("par"), WEEK_SEQUENCING("seq"), 
  STRICT_SEQUENCING("strict"), NEGATIVE("neg"), CRITICAL_REGION("critical"),
  ASSERTION("assert") ,LOOP("loop"), IGNORE("ignore"), CONSIDER("consider");
  
  private String value;
  
  private EInteractionOperator() {}
  
  private EInteractionOperator(String value) {
    this.value = value;
  }
  
  @Override
  public String toString() {
    return value;
  }
}
