package org.beigesoft.uml.diagram.assembly;

import java.util.List;
import java.util.UUID;

import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.uml.assembly.IAsmElementUml;
import org.beigesoft.uml.container.IContainerAppUml;
import org.beigesoft.uml.model.IElementUml;

public interface IAsmListElementsUml<AEU extends IAsmElementUml<EU, DRI, SD, PRI>, DRI, 
    SD extends ISettingsDraw, IMG, PRI, EU extends IElementUml> {
  
  public double getWeight();
  
  public void setWeight(double weight);

  public List<AEU> getListElementsUml();

  public void addElementUml(AEU elementUml);

  public void removeElementUml(AEU elementUml);
  
  public boolean tryToRemoveElementUmlById(UUID idElementUml);

  public void persist(PRI persistInstrument) throws Exception;
  
  public void restore(PRI persistInstrument) throws Exception;

  public IContainerAppUml<DRI, SD, IMG> getHolderApp();

  public EU getElementUmlById(UUID id);

  public long getVersionElementsUml();
}
