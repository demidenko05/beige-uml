package org.beigesoft.uml.diagram.assembly;

import java.util.List;

import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.handler.IHandlerModelChanged;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.IAsmElementUml;
import org.beigesoft.uml.container.IContainerAppUml;
import org.beigesoft.uml.diagram.pojo.DiagramUml;

/**
 * 
 * @author yury
 *
 * @param <DRI>
 * @param <PRI> abstraction of "persist instrument". E.g. id and table name for JDBC storage, file name and XMLWriter for XML storage
 * @param <DUML>
 * @param <AEU>
 */
public interface IAsmDiagramUml<DUML extends DiagramUml, AEU extends IAsmElementUml<?, DRI, ?, PRI>, //kind of assembly in the list
    DRI, SD extends ISettingsDraw, IMG, PRI> extends IHandlerModelChanged<SettingsGraphicUml> {

  public List<IAsmListElementsUml<? extends AEU, DRI, ?, IMG, PRI, ?>> getAssembliesListElementsUml();

  public IContainerAppUml<DRI, SD, IMG> getHolderApp();

  public DUML getDiagramUml();

  public void saveDiagram() throws Exception;

  public void openDiagram(PRI persistMethod) throws Exception;

  public boolean getIsEmpty();

  public void clearContent();
  
  public PRI getPersistInstrument();
    
  public void newDiagramUml(PRI pi);
}
