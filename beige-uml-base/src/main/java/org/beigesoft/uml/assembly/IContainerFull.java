package org.beigesoft.uml.assembly;

import java.util.List;

import org.beigesoft.uml.model.IContainerUml;
import org.beigesoft.uml.model.IElementUml;

public interface IContainerFull<CNT extends IContainerUml> extends IElementUml {

  public CNT getContainer();

  public void setContainer(CNT container);

  public List<IAsmElementUmlInteractive<?, ?, ?, ?>> getElements();
}
