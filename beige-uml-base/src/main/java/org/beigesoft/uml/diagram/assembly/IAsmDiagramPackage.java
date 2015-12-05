package org.beigesoft.uml.diagram.assembly;

import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.uml.assembly.ClassFull;
import org.beigesoft.uml.container.IContainerShapesForTie;
import org.beigesoft.uml.container.IContainerShapesFullInteractive;
import org.beigesoft.uml.diagram.pojo.DiagramUml;
import org.beigesoft.uml.model.EClassKind;
import org.beigesoft.uml.model.ERelationshipKind;
import org.beigesoft.uml.pojo.ClassUml;

public interface IAsmDiagramPackage<DUML extends DiagramUml, DRI, SD extends ISettingsDraw, IMG, PRI,
    CL extends ClassUml> 
    extends IAsmDiagramUmlInteractive<DUML, DRI, SD, IMG, PRI>, IContainerShapesFullInteractive<ClassFull<CL>, CL>,
    IContainerShapesForTie {

  public void createClassAt(EClassKind classKind, int screenX, int screenY)
      throws Exception;

  public void createPackageAt(int screenX, int screenY)
      throws Exception;

  public void tryToCreateRelationshipBinaryAt(ERelationshipKind relationKind, int screenX, int screenY)
      throws Exception;

  public void tryToCreateRelationshipSelfAt(int screenX, int screenY);

  public boolean tryCreatePackageImportAt(int screenX, int screenY);

  public boolean tryCreatePackageMergeAt(int screenX, int screenY);

  public boolean tryCreatePackageAccessAt(int screenX, int screenY);

  public void tryToCreateRelationshipPolyAt(int screenX, int screenY);
}
