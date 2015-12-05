package org.beigesoft.uml.diagram.assembly;

import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.uml.assembly.ClassFull;
import org.beigesoft.uml.assembly.IAsmElementUml;
import org.beigesoft.uml.container.IContainerShapesFull;
import org.beigesoft.uml.diagram.pojo.DiagramClass;
import org.beigesoft.uml.pojo.ClassUml;

public interface IAsmDiagramClass<DUML extends DiagramClass, AEU extends IAsmElementUml<?, DRI, ?, PRI>, //kind of assembly in generic list
    DRI, SD extends ISettingsDraw, IMG, PRI, CL extends ClassUml>  
    extends IAsmDiagramUml<DUML, AEU, DRI, SD, IMG, PRI>, IContainerShapesFull<ClassFull<CL>, CL> {

  public void rearrange();
}
