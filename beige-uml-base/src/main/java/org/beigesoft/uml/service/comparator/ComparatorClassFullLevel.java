package org.beigesoft.uml.service.comparator;

import java.util.Comparator;

import org.beigesoft.uml.assembly.ClassFull;
import org.beigesoft.uml.pojo.ClassUml;

public class ComparatorClassFullLevel<CL extends ClassUml> 
    implements Comparator<ClassFull<CL>> {

  @Override
  public int compare(ClassFull<CL> umlClass0, ClassFull<CL> umlClass1) {
    int compareLevelY = umlClass0.getShape().getLevelYOnDiagram().compareTo(umlClass1.getShape().getLevelYOnDiagram());
    return compareLevelY != 0 ? compareLevelY : 
      umlClass0.getShape().getLevelXOnDiagram().compareTo(umlClass1.getShape().getLevelXOnDiagram());
  }
}
