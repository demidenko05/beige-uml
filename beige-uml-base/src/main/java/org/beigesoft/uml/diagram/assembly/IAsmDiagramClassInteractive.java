package org.beigesoft.uml.diagram.assembly;

import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.uml.assembly.ClassFull;
import org.beigesoft.uml.container.IContainerShapesForTie;
import org.beigesoft.uml.container.IContainerShapesFullInteractive;
import org.beigesoft.uml.diagram.pojo.DiagramClass;
import org.beigesoft.uml.model.EClassKind;
import org.beigesoft.uml.model.ERelationshipKind;
import org.beigesoft.uml.pojo.ClassUml;

public interface IAsmDiagramClassInteractive<DUML extends DiagramClass, DRI, SD extends ISettingsDraw, IMG, PRI,
    CL extends ClassUml> 
    extends IAsmDiagramUmlInteractive<DUML, DRI, SD, IMG, PRI>, IContainerShapesFullInteractive<ClassFull<CL>, CL>,
    IContainerShapesForTie {

  public void addClassUmlForClass(Class<?> clazz);
  
  public void rearrange();

  public void createClassAt(EClassKind classKind, int screenX, int screenY)
      throws Exception;

  public void addClassesUmlForClass(Class<?> clazz) throws Exception;

  public void adjustRelationsFor90Degree() throws Exception;

  public void tryToCreateRelationshipAt(ERelationshipKind relationKind, int screenX, int screenY)
      throws Exception;

  public void tryToCreateRelationshipSelfAt(int screenX, int screenY);

  public void tryToCreateRelationshipPolyAt(int screenX, int screenY);
}
