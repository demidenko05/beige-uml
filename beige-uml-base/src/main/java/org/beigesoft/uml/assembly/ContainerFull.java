package org.beigesoft.uml.assembly;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.beigesoft.uml.model.IContainerUml;

public class ContainerFull<CNT extends IContainerUml> implements Cloneable, IContainerFull<CNT> {

  private CNT container;
  
  private List<IAsmElementUmlInteractive<?, ?, ?, ?>> elements = new ArrayList<IAsmElementUmlInteractive<?,?,?,?>>();

  public ContainerFull(CNT cnt) {
    this.container = cnt;
  }

  @Override
  public String toString() {
    return container.toString();
  }

  @Override
  public ContainerFull<CNT> clone() {
    try {
      @SuppressWarnings("unchecked")
      ContainerFull<CNT> clone = (ContainerFull<CNT>) super.clone();
      clone.setElements(new ArrayList<IAsmElementUmlInteractive<?,?,?,?>>());
      for(IAsmElementUmlInteractive<?, ?, ?, ?> asmElm : elements) {
        clone.getElements().add(asmElm);
      }
      return clone;
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public UUID getId() {
    return container.getId();
  }

  @Override
  public void setId(UUID id) {
    container.setId(id);
  }

  @Override
  public boolean getIsNew() {
    return container.getIsNew();
  }

  @Override
  public void setIsNew(boolean isNew) {
    container.setIsNew(isNew);
  }

  @Override
  public boolean getIsSelected() {
    return container.getIsSelected();
  }

  @Override
  public void setIsSelected(boolean isSelected) {
    container.setIsSelected(isSelected);
  }

  @Override
  public CNT getContainer() {
    return container;
  }

  @Override
  public void setContainer(CNT container) {
    this.container = container;
  }

  /**
   * 
   * @return elements belong to this container to be moved together
   */
  @Override
  public List<IAsmElementUmlInteractive<?, ?, ?, ?>> getElements() {
    return elements;
  }

  public void setElements(List<IAsmElementUmlInteractive<?, ?, ?, ?>> elements) {
    this.elements = elements;
  }

  @Override
  public Integer getIndexZ() {
    return container.getIndexZ();
  }

  @Override
  public void setIndexZ(Integer indexZ) {
    container.setIndexZ(indexZ);
  }
}
