package org.beigesoft.uml.controller;

public interface IControllerMainUml<ITP> {

  public void newDiagramClass();
    
  /**
   * Set diagram class editor and open diagram
   * @param pi
   */
  public void openDiagramClass(ITP pi);

  public void openDiagramPackage(ITP pi);

  public void openDiagramUseCase(ITP pi);

  public void newUseCaseDiagram();

  public void newPackageDiagram();

  public void newDiagramClassFromJavaSource();
  
  public void closeForcedDiagram();

  public void newDiagramObject();

  public void openDiagramObject(ITP pi);

  public void newDiagramSequence();

  public void openDiagramSequence(ITP pi);
}
