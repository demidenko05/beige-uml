package org.beigesoft.uml.container;

import java.util.Collection;

import org.beigesoft.uml.assembly.IShapeFullUml;
import org.beigesoft.uml.pojo.ShapeUml;

public interface IContainerShapesFullInteractive<SHF extends IShapeFullUml<SH>, SH extends ShapeUml> 
    extends IContainerShapesFull<SHF, SH>{
  
  public SHF getShapeAt(int screenX, int screenY);
  
  public Collection<SHF> getShapesUml();
  
  public long getVersionShapesUml();
}
