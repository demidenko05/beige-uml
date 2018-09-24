package org.beigesoft.uml.diagram.assembly;

import java.util.ArrayList;
import java.util.List;

import org.beigesoft.graphic.SettingsGraphic;
import org.beigesoft.graphic.IDrawable;
import org.beigesoft.graphic.model.EMeasurementUnit;
import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.IAsmElementUml;
import org.beigesoft.uml.container.IContainerAppUml;
import org.beigesoft.uml.diagram.pojo.DiagramUml;
import org.beigesoft.uml.service.persist.xmllight.ISrvPersistAsmDiagramUml;

public class AsmDiagramUml<DUML extends DiagramUml, AEU extends IAsmElementUml<?, DRI, ?, PRI>, 
    DRI, SD extends ISettingsDraw, IMG, PRI> 
    implements IAsmDiagramUml<DUML, AEU, DRI, SD, IMG, PRI> {

  private final DUML diagramUml;
  
  private PRI persistInstrument;

  private final List<IAsmListElementsUml<? extends AEU, DRI, ?, IMG, PRI, ?>> assembliesListElementsUml = 
      new ArrayList<IAsmListElementsUml<? extends AEU, DRI, ?, IMG, PRI, ?>>();
  
  private final IContainerAppUml<DRI, SD, IMG> holderApp;
    
  private final ISrvPersistAsmDiagramUml<DUML, PRI> persistDiagramUmlSrv;
  
  public AsmDiagramUml(DUML diagramUml, IContainerAppUml<DRI, SD, IMG> holderApp, ISrvPersistAsmDiagramUml<DUML, PRI> persistDiagramUmlSrv) {
    this.holderApp = holderApp;
    this.diagramUml = diagramUml;
    this.persistDiagramUmlSrv = persistDiagramUmlSrv;
    holderApp.getPaneDrawing().setMargin(holderApp.getSettingsGraphicUml().calculateDiagramMarginInPixel());
  }

  @Override
  public void clearContent() {
    for(IAsmListElementsUml<? extends AEU, DRI, ?, IMG, PRI, ?> holderList : assembliesListElementsUml) {
      for(IDrawable<DRI> dr : holderList.getListElementsUml()) {
        synchronized (holderApp.getPaneDrawing().getDrawableList()) {
          holderApp.getPaneDrawing().getDrawableList().remove(dr);
        }        
      }
      holderList.getListElementsUml().clear();
    }
    this.holderApp.getSrvZoom().makeZoom11(this.holderApp.getSettingsGraphicUml());
  }
  
  @Override
  public boolean getIsEmpty() {
    boolean result = true;
    for(IAsmListElementsUml<?, DRI, ?, ?, ?, ?> holderList : assembliesListElementsUml) {
      if(holderList.getListElementsUml().size() > 0) {
        result = false;
        break;
      }
    }
    return result;
  }
  
  @Override
  public void handleModelChanged(SettingsGraphicUml model) {
    if(!diagramUml.getMeasurementUnit().equals(model.getMeasurementUnit())) {
      double coef;
      if(diagramUml.getMeasurementUnit().equals(EMeasurementUnit.INCH)) {
        coef = SettingsGraphic.CENTEMETERS_IN_INCH;
      }
      else {
        coef = 1 / SettingsGraphic.CENTEMETERS_IN_INCH;
      }
      for(IAsmListElementsUml<?, ?, ?, ?, ?, ?> holderList : assembliesListElementsUml) {
        for(IDrawable<?> dr : holderList.getListElementsUml()) {
          dr.recalculate(coef);
        }
      }
      diagramUml.setMeasurementUnit(holderApp.getSettingsGraphicUml().getMeasurementUnit());
    }    
  }

  @Override
  public void openDiagram(PRI pi) throws Exception {
    clearContent();
    setPersistInstrument(pi);
    persistDiagramUmlSrv.restore(this);
    handleModelChanged(holderApp.getSettingsGraphicUml());
  }
  
  @Override
  public void saveDiagram() throws Exception {
    persistDiagramUmlSrv.persist(this);
  }
  
  @Override
  public List<IAsmListElementsUml<? extends AEU, DRI, ?, IMG, PRI, ?>> getAssembliesListElementsUml() {
    return assembliesListElementsUml;
  }

  @Override
  public IContainerAppUml<DRI, SD, IMG> getHolderApp() {
    return holderApp;
  }

  @Override
  public DUML getDiagramUml() {
    return diagramUml;
  }

  @Override
  public void newDiagramUml(PRI pi) {
    clearContent();
    setPersistInstrument(pi);
    diagramUml.setMeasurementUnit(holderApp.getSettingsGraphicUml().getMeasurementUnit());
  }

  @Override
  public PRI getPersistInstrument() {
    return persistInstrument;
  }

  public void setPersistInstrument(PRI pi) {
    this.persistInstrument = pi;
  }
}
