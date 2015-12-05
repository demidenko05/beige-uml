package org.beigesoft.uml.service.comparator;

import java.util.Comparator;

import org.beigesoft.uml.pojo.ClassUml;

public class ComparatorClassLevel implements Comparator<ClassUml> {

  @Override
  public int compare(ClassUml umlClass0, ClassUml umlClass1) {
    int compareLevelY = umlClass0.getLevelYOnDiagram().compareTo(umlClass1.getLevelYOnDiagram());
    return compareLevelY != 0 ? compareLevelY : 
      umlClass0.getLevelXOnDiagram().compareTo(umlClass1.getLevelXOnDiagram());
  }
}
