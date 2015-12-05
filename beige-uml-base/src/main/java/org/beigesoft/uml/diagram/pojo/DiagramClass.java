package org.beigesoft.uml.diagram.pojo;

public class DiagramClass extends DiagramUml {

  private boolean isAbleToChangeByDoclet = true;

  private int algorithmId = 1;
  
  public boolean getIsAbleToChangeByDoclet() {
    return isAbleToChangeByDoclet;
  }
  
  public void setIsAbleToChangeByDoclet(boolean isAbleToChangeByDoclet) {
    this.isAbleToChangeByDoclet = isAbleToChangeByDoclet;
  }
  public int getAlgorithmAd() {
    return algorithmId;
  }

  public void setAlgorithmId(int algorithmId) {
    this.algorithmId = algorithmId;
  }
}
