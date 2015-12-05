package org.beigesoft.uml.service.comparator;

import java.util.Comparator;

import org.beigesoft.uml.diagram.assembly.IAsmListElementsUml;

public class ComparatorAsmListElementsUml implements Comparator<IAsmListElementsUml<?, ?, ?, ?, ?, ?>> {

  @Override
  public int compare(IAsmListElementsUml<?, ?, ?, ?, ?, ?> o1,
      IAsmListElementsUml<?, ?, ?, ?, ?, ?> o2) {
    return Double.compare(o1.getWeight(), o2.getWeight());
  }
}
