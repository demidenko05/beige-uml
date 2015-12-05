package org.beigesoft.uml.ui;

import java.io.File;
import java.util.UUID;

import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.model.NodeTree;
import org.beigesoft.ui.container.IContainerSrvsGui;
import org.beigesoft.ui.pojo.NodeJavaClass;
import org.beigesoft.ui.widget.IChooserWithDataSource;
import org.beigesoft.uml.container.IContainerAppUml;
import org.beigesoft.uml.controller.IControllerDiagramClass;
import org.beigesoft.uml.controller.IControllerDiagramUml;
import org.beigesoft.uml.controller.IControllerMainUml;

public interface IGuiMainUml<DRI, SD extends ISettingsDraw, IMG, PRI, DLI> extends 
    IControllerMainUml<PRI>, IContainerAppUml<DRI, SD, IMG> {

  public IControllerDiagramUml<?, PRI> getActiveControllerDiagramUml();

  public void refreshGui();
  
  public void setTitle(String title);
  
  public boolean evalIsCurrentDiagramHasNeverSaved();
  
  public IMenuUml getMenuMain();

  public IChooserWithDataSource<NodeTree<UUID, NodeJavaClass>> lazyGetAndPrepareJavaClassChooser();

  public IContainerSrvsGui<DLI> getGuiSrvs();
  
  public DLI getDialogInstrument();

  public IControllerDiagramClass<?, PRI> getControllerDiagramClass();
  
  public IControllerDiagramUml<?, PRI> getControllerDiagramUseCase();

  public IControllerDiagramUml<?, PRI> getControllerDiagramPackage();

  public IControllerDiagramUml<?, PRI> getControllerDiagramObject();

  public IControllerDiagramUml<?, PRI> getControllerDiagramSequence();

  public void refreshProjectTreeAndShowFile(File diagramFile);

  public String getSelectedFolderPathProjectTree();

  public void addFileIntoSelectedFolderProjectTree(File file);

  public void newProjectUml();

  public void openProjectUml();

  public void addFolderIntoSelectedFolderProjectTree();
}
