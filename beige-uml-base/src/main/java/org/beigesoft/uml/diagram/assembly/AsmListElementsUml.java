/*
 * Beigesoft ™
 * 
 * Licensed under the Apache License, Version 2.0
 * 
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package org.beigesoft.uml.diagram.assembly;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.uml.assembly.IAsmElementUml;
import org.beigesoft.uml.container.IContainerAppUml;
import org.beigesoft.uml.model.IElementUml;
import org.beigesoft.uml.service.persist.xmllight.ISrvPersistListElementsUml;

/**
 * 
 * <p>Represent controller of list of a UML-element to be draw, persist...</p>
 * 
 * @author Yury Demidenko
 * 
 * @param <DRI> draw instrument, e.g. awt.Graphics2D
 * @param <SD> draw settings (color...)
 * @param <EU> UML element
 * @param <PRI> persist instrument (XML file, ORM, etc.)
 * @param <AEU> assembly of UML element to be draw, persist...
 */
public class AsmListElementsUml <AEU extends IAsmElementUml<EU, DRI, SD, PRI>,
    DRI, SD extends ISettingsDraw, IMG, PRI, EU extends IElementUml> 
    implements IAsmListElementsUml<AEU, DRI, SD, IMG, PRI, EU> {

  private final List<AEU> listElementsUml = new ArrayList<AEU>();
  
  private final IContainerAppUml<DRI, SD, IMG> holderApp;
  
  private final ISrvPersistListElementsUml<AEU, DRI, SD, PRI, EU> persistListElementsUmlSrv;

  private long versionElementsUml;

  private double weight;
  

  public AsmListElementsUml(IContainerAppUml<DRI, SD, IMG> holderApp, 
      ISrvPersistListElementsUml<AEU, DRI, SD, PRI, EU> persistListElementsUmlSrv) {
    this.holderApp = holderApp;
    this.persistListElementsUmlSrv = persistListElementsUmlSrv;
  }

  @Override
  public void addElementUml(AEU elementUml) {
    listElementsUml.add(elementUml);
    synchronized (holderApp.getPaneDrawing().getDrawableList()) {
      holderApp.getPaneDrawing().getDrawableList().add(elementUml);
    }
    versionElementsUml = new Date().getTime();
  }
  
  @Override
  public void removeElementUml(AEU elementUml) {
    listElementsUml.remove(elementUml);
    synchronized (holderApp.getPaneDrawing().getDrawableList()) {
      holderApp.getPaneDrawing().getDrawableList().remove(elementUml);
    }
    versionElementsUml = new Date().getTime();
  }

  @Override
  public void persist(PRI persistInstrument) throws Exception {
    persistListElementsUmlSrv.persist(this, persistInstrument); 
  }

  @Override
  public void restore(PRI persistInstrument) throws Exception {
    persistListElementsUmlSrv.restore(this, persistInstrument);
  }
  
  @Override
  public List<AEU> getListElementsUml() {
    return listElementsUml;
  }

  @Override
  public IContainerAppUml<DRI, SD, IMG> getHolderApp() {
    return holderApp;
  }

  @Override
  public EU getElementUmlById(UUID id) {
    EU result = null;
    for(AEU asmElementUml : getListElementsUml())
      if(asmElementUml.getElementUml().getId().equals(id)) {
        result = asmElementUml.getElementUml();
        break;
      }
    return result;
  }

  //SGS:
  public ISrvPersistListElementsUml<AEU, DRI, SD, PRI, EU> getPersistListElementsUmlSrv() {
    return persistListElementsUmlSrv;
  }

  @Override
  public boolean tryToRemoveElementUmlById(UUID idElementUml) {
    for(AEU aeu : listElementsUml) {
      if(aeu.getElementUml().getId().equals(idElementUml)) {
        removeElementUml(aeu);
        return true;
      }
    }
    return false;
  }

  @Override
  public long getVersionElementsUml() {
    return versionElementsUml;
  }

  @Override
  public double getWeight() {
    return weight;
  }

  @Override
  public void setWeight(double weight) {
    this.weight = weight;
  }
}
