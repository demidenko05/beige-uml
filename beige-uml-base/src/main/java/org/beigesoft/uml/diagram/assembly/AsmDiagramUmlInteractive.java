package org.beigesoft.uml.diagram.assembly;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.beigesoft.graphic.ComparatorDrawbleIndexZDesc;
import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.graphic.service.UtilsGraphMath;
import org.beigesoft.uml.assembly.ContainerFull;
import org.beigesoft.uml.assembly.IAsmElementUml;
import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.container.IContainerInteractiveElementsUml;
import org.beigesoft.uml.container.IContainerTextComment;
import org.beigesoft.uml.diagram.pojo.DiagramUml;
import org.beigesoft.uml.factory.IFactoryAsmElementUml;
import org.beigesoft.uml.pojo.CommentUml;
import org.beigesoft.uml.pojo.FrameUml;
import org.beigesoft.uml.pojo.LineUml;
import org.beigesoft.uml.pojo.RectangleUml;
import org.beigesoft.uml.pojo.TextUml;
import org.beigesoft.uml.service.persist.xmllight.ISrvPersistAsmDiagramUml;
import org.beigesoft.uml.service.persist.xmllight.ISrvPersistListElementsUml;
import org.beigesoft.uml.ui.IGuiMainUml;

public class AsmDiagramUmlInteractive<DUML extends DiagramUml, DRI, SD extends ISettingsDraw, IMG, PRI, DLI,
    CM extends CommentUml, ACM extends IAsmElementUmlInteractive<CM, DRI, SD, PRI>,
    TX extends TextUml, ATX extends IAsmElementUmlInteractive<TX, DRI, SD, PRI>>
    extends AsmDiagramUml<DUML, IAsmElementUmlInteractive<?, DRI, ?, PRI>, DRI, SD, IMG, PRI> 
    implements IAsmDiagramUmlInteractive<DUML, DRI, SD, IMG, PRI>,
    IContainerTextComment, IContainerInteractiveElementsUml {

  private IAsmElementUmlInteractive<?, ?, ?, ?> selectedElementUml;
  
  private int mouseWasAtX = -1;

  private int mouseWasAtY = -1;

  private boolean isChanged;
  
  private final IGuiMainUml<DRI, SD, IMG, PRI, DLI> guiApp;
  
  private final IAsmListElementsUml<ACM, DRI, SD, IMG, PRI, CM> assemblyListCommentsUml;

  private final IAsmListElementsUml<ATX, DRI, SD, IMG, PRI, TX> assemblyListTextsUml;
  
  private final IFactoryAsmElementUml<ACM, DRI, SD, PRI, CM> factoryCommentUml;

  private final IFactoryAsmElementUml<ATX, DRI, SD, PRI, TX> factoryTextUml;

  private final IFactoryAsmElementUml<IAsmElementUmlInteractive<ContainerFull<FrameUml>, DRI, SD, PRI>, DRI, SD, PRI, ContainerFull<FrameUml>> factoryFrameFullFull;

  private final IAsmListElementsUml<IAsmElementUmlInteractive<ContainerFull<FrameUml>, DRI, SD, PRI>, DRI, SD, IMG, PRI, ContainerFull<FrameUml>> asmListAsmFramesFull;

  private final IAsmListElementsUml<IAsmElementUmlInteractive<RectangleUml, DRI, SD, PRI>, DRI, SD, IMG, PRI, RectangleUml> asmListAsmRectangles;

  private final IFactoryAsmElementUml<IAsmElementUmlInteractive<RectangleUml, DRI, SD, PRI>, DRI, SD, PRI, RectangleUml> factoryAsmRectangle;

  private final IAsmListElementsUml<IAsmElementUmlInteractive<LineUml, DRI, SD, PRI>, DRI, SD, IMG, PRI, LineUml> asmListAsmLines;

  private final IFactoryAsmElementUml<IAsmElementUmlInteractive<LineUml, DRI, SD, PRI>, DRI, SD, PRI, LineUml> factoryAsmLine;

  public AsmDiagramUmlInteractive(DUML diagramUml,
      IGuiMainUml<DRI, SD, IMG, PRI, DLI> guiApp,
      ISrvPersistAsmDiagramUml<DUML, PRI> persistDiagramUmlSrv,
      ISrvPersistListElementsUml<ACM, DRI, SD, PRI, CM> persistListCommentsUmlSrv,
      ISrvPersistListElementsUml<ATX, DRI, SD, PRI, TX> persistListTextsUmlSrv,
      IFactoryAsmElementUml<ACM, DRI, SD, PRI, CM> factoryCommentUml,
      IFactoryAsmElementUml<ATX, DRI, SD, PRI, TX> factoryTextUml,
      ISrvPersistListElementsUml<IAsmElementUmlInteractive<ContainerFull<FrameUml>, DRI, SD, PRI>, DRI, SD, PRI, ContainerFull<FrameUml>> srvPersistXmlListFrames,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<ContainerFull<FrameUml>, DRI, SD, PRI>, DRI, SD, PRI, ContainerFull<FrameUml>> factoryFrameFullFull,
      ISrvPersistListElementsUml<IAsmElementUmlInteractive<RectangleUml, DRI, SD, PRI>, DRI, SD, PRI, RectangleUml> srvPersistXmlListRectangles,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<RectangleUml, DRI, SD, PRI>, DRI, SD, PRI, RectangleUml> factoryAsmRectangle,
      ISrvPersistListElementsUml<IAsmElementUmlInteractive<LineUml, DRI, SD, PRI>, DRI, SD, PRI, LineUml> srvPersistXmlListLines,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<LineUml, DRI, SD, PRI>, DRI, SD, PRI, LineUml> factoryAsmLine) {
    super(diagramUml, guiApp, persistDiagramUmlSrv);
    this.guiApp = guiApp;
    this.factoryCommentUml = factoryCommentUml;
    this.factoryTextUml = factoryTextUml;
    this.factoryFrameFullFull = factoryFrameFullFull;
    this.factoryAsmRectangle = factoryAsmRectangle;
    this.factoryAsmLine = factoryAsmLine;
    this.assemblyListCommentsUml = new AsmListElementsUml<ACM, DRI, SD, IMG, PRI, CM>(guiApp, persistListCommentsUmlSrv);
    getAssembliesListElementsUml().add(assemblyListCommentsUml);
    assemblyListCommentsUml.setWeight(99);
    this.assemblyListTextsUml = new AsmListElementsUml<ATX, DRI, SD, IMG, PRI, TX>(guiApp, persistListTextsUmlSrv);
    getAssembliesListElementsUml().add(assemblyListTextsUml);
    assemblyListTextsUml.setWeight(100);
    asmListAsmFramesFull = new AsmListElementsUml<IAsmElementUmlInteractive<ContainerFull<FrameUml>, DRI, SD, PRI>, DRI, SD, IMG, PRI, ContainerFull<FrameUml>>(guiApp, srvPersistXmlListFrames);
    getAssembliesListElementsUml().add(asmListAsmFramesFull);
    asmListAsmFramesFull.setWeight(501);
    asmListAsmRectangles = new AsmListElementsUml<IAsmElementUmlInteractive<RectangleUml, DRI, SD, PRI>, DRI, SD, IMG, PRI, RectangleUml>(guiApp, srvPersistXmlListRectangles);
    getAssembliesListElementsUml().add(asmListAsmRectangles);
    asmListAsmRectangles.setWeight(202);
    asmListAsmLines = new AsmListElementsUml<IAsmElementUmlInteractive<LineUml, DRI, SD, PRI>, DRI, SD, IMG, PRI, LineUml>(guiApp, srvPersistXmlListLines);
    getAssembliesListElementsUml().add(asmListAsmLines);
    asmListAsmLines.setWeight(202);
  }
  
  @Override
  public void openDiagram(PRI pi) throws Exception {
    unselectElementIfNeed();
    super.openDiagram(pi);
    setIsChanged(false);
    refreshGui();
  }

  @Override
  public void saveDiagram() throws Exception {
    unselectElementIfNeed();
    Collections.sort(getAsmListFrames().getListElementsUml(), new ComparatorDrawbleIndexZDesc());
    super.saveDiagram();
    setIsChanged(false);
    refreshGui();
  }

  @Override
  public void newDiagramUml(PRI pi) {
    unselectElementIfNeed();
    super.newDiagramUml(pi);
    setIsChanged(false);
    refreshGui();
  }

  @Override
  public void clearContent() {
    super.clearContent();
    unselectElementIfNeed();
    setIsChanged(false);
    refreshGui();
  }

  @Override
  public void tryToEditSelectedElement(int x, int y) {
    if(selectedElementUml != null && selectedElementUml.isContainsScreenForManipulate(x, y)) {
      selectedElementUml.startEdit();
    }
  }

  @Override
  public boolean tryToDragging(int x, int y) {
    if(selectedElementUml != null) {
      boolean wasDragging = selectedElementUml.resize(mouseWasAtX, mouseWasAtY, x, y);
      if(!wasDragging) {
        wasDragging = selectedElementUml.move(mouseWasAtX, mouseWasAtY, x, y);
      }
      if(wasDragging) {
        mouseWasAtX = x;
        mouseWasAtY = y;  
        setIsChanged(true);
        refreshGui();
        return true;
      }
    }
    return false;
  }

  @Override
  public void unselectElementIfNeed() {
    if(selectedElementUml != null) {
      selectedElementUml.makeIsSelected(false);
      selectedElementUml = null;
    }
  }

  @Override
  public void makeElementSelected(IAsmElementUmlInteractive<?, ?, ?, ?> umlElement) {
    if(selectedElementUml != null) {
      selectedElementUml.makeIsSelected(false);
    }
    umlElement.makeIsSelected(true);
    selectedElementUml = umlElement;
    refreshGui();
  }

  @Override
  public void setIsChanged(boolean isChanged) {
    this.isChanged = isChanged;
  }

  @Override
  public boolean getIsChanged() {
    return isChanged;
  }

  @Override
  public void refreshGui() {
    guiApp.refreshGui();
  }
  
  @Override
  public void deleteSelectedElement() {
    if(selectedElementUml != null) {
      for(IAsmListElementsUml<?, ?, ?, ?, ?, ?> aleu : getAssembliesListElementsUml()) {
        if(aleu.tryToRemoveElementUmlById(selectedElementUml.getElementUml().getId())) {
          break;
        }
      }
      setIsChanged(true);
      refreshGui();
    }
  }
  
  @Override
  public void registerMouseReleasedAfterDragging() {
    if(selectedElementUml != null && selectedElementUml.handleStopDraggingAt(mouseWasAtX, mouseWasAtY)) {
      setIsChanged(true);
      refreshGui();
    }
    mouseWasAtX = -1;
    mouseWasAtY = -1;
  }

  @Override
  public void registerMousePressedAt(int x, int y) {
    mouseWasAtX = x;
    mouseWasAtY = y;  
  }
  
  @Override
  public void selectAt(int screenX, int screenY) {
    if(selectedElementUml != null && selectedElementUml.isContainsScreenForManipulate(screenX, screenY)) {
      return;
    }
    for(IAsmListElementsUml<? extends IAsmElementUmlInteractive<?, DRI, ?, PRI>, DRI, ?, ?, PRI, ?> aleu : getAssembliesListElementsUml()) {
      for(IAsmElementUmlInteractive<?, DRI, ?, PRI> aeu : aleu.getListElementsUml()) {
        if(aeu.isContainsScreenForManipulate(screenX, screenY)) {
          makeElementSelected(aeu);
          return;
        }
      }
    }
  }
  
  @Override
  public void createFrameAt(int screenX, int screenY) {
    IAsmElementUmlInteractive<ContainerFull<FrameUml>, DRI, SD, PRI> asmFrame = getFactoryFrameFull().createAsmElementUml();
    asmFrame.getElementUml().getContainer().getPointStart().setX(UtilsGraphMath.toRealX(guiApp.getSettingsGraphicUml(), screenX));
    asmFrame.getElementUml().getContainer().getPointStart().setY(UtilsGraphMath.toRealY(guiApp.getSettingsGraphicUml(), screenY));
    makeElementSelected(asmFrame);
    if(tryToAddIntoContainer(asmFrame, screenX, screenY)) {
      asmFrame.setIndexZ(1000);
    }
    asmListAsmFramesFull.addElementUml(asmFrame);
    asmFrame.startEdit();
    setIsChanged(true);
    refreshGui();
  }
  
  @Override
  public void createRectangleAt(int screenX, int screenY) {
    IAsmElementUmlInteractive<RectangleUml, DRI, SD, PRI> asmRectangle = getFactoryAsmRectangle().createAsmElementUml();
    asmRectangle.getElementUml().getPointStart().setX(UtilsGraphMath.toRealX(guiApp.getSettingsGraphicUml(), screenX));
    asmRectangle.getElementUml().getPointStart().setY(UtilsGraphMath.toRealY(guiApp.getSettingsGraphicUml(), screenY));
    asmRectangle.getElementUml().setWidth(guiApp.getSettingsGraphicUml().getWidthMinClass());
    asmRectangle.getElementUml().setHeight(guiApp.getSettingsGraphicUml().getWidthMinClass());
    makeElementSelected(asmRectangle);
    tryToAddIntoContainer(asmRectangle, screenX, screenY);
    asmListAsmRectangles.addElementUml(asmRectangle);
    asmRectangle.startEdit();
    setIsChanged(true);
    refreshGui();
  }

  @Override
  public void createLineAt(int screenX, int screenY) {
    IAsmElementUmlInteractive<LineUml, DRI, SD, PRI> asmLine = getFactoryAsmLine().createAsmElementUml();
    asmLine.getElementUml().getPoint1().setX(UtilsGraphMath.toRealX(guiApp.getSettingsGraphicUml(), screenX));
    asmLine.getElementUml().getPoint1().setY(UtilsGraphMath.toRealY(guiApp.getSettingsGraphicUml(), screenY));
    asmLine.getElementUml().getPoint2().setX(asmLine.getElementUml().getPoint1().getX() + guiApp.getSettingsGraphicUml().fromInchToCurrentMeasure(0.5));
    asmLine.getElementUml().getPoint2().setY(asmLine.getElementUml().getPoint1().getY());
    makeElementSelected(asmLine);
    tryToAddIntoContainer(asmLine, screenX, screenY);
    asmListAsmLines.addElementUml(asmLine);
    asmLine.startEdit();
    setIsChanged(true);
    refreshGui();
  }

  @Override
  public void createTextUmlAt(int screenX, int screenY) {
    ATX asmTextUml = getFactoryAsmText().createAsmElementUml();
    asmTextUml.getElementUml().getPointStart().setX(UtilsGraphMath.toRealX(guiApp.getSettingsGraphicUml(), screenX));
    asmTextUml.getElementUml().getPointStart().setY(UtilsGraphMath.toRealY(guiApp.getSettingsGraphicUml(), screenY));
    asmTextUml.getElementUml().setItsText("«*»");
    makeElementSelected(asmTextUml);
    tryToAddIntoContainer(asmTextUml, screenX, screenY);
    assemblyListTextsUml.addElementUml(asmTextUml);
    asmTextUml.startEdit();
    setIsChanged(true);
    refreshGui();
  }

  @Override
  public void createCommentUmlAt(int screenX, int screenY) {
    ACM asmCommentUml = getFactoryCommentUml().createAsmElementUml();
    asmCommentUml.getElementUml().getPointStart().setX(UtilsGraphMath.toRealX(guiApp.getSettingsGraphicUml(), screenX));
    asmCommentUml.getElementUml().getPointStart().setY(UtilsGraphMath.toRealY(guiApp.getSettingsGraphicUml(), screenY));
    makeElementSelected(asmCommentUml);
    tryToAddIntoContainer(asmCommentUml, screenX, screenY);
    assemblyListCommentsUml.addElementUml(asmCommentUml);
    asmCommentUml.startEdit();
    setIsChanged(true);
    refreshGui();
  }

  @Override
  public IAsmElementUmlInteractive<?, ?, ?, ?> getSelectedElementUml() {
    return selectedElementUml;
  }

  @Override
  public Collection<? extends IAsmElementUml<? extends CommentUml, ?, ?, ?>> getCommentUmlList() {
    return assemblyListCommentsUml.getListElementsUml();
  }

  @Override
  public Collection<? extends IAsmElementUml<? extends TextUml, ?, ?, ?>> getTextUmlList() {
    return assemblyListTextsUml.getListElementsUml();
  }

  @Override
  public IAsmElementUmlInteractive<?, ?, ?, ?> getInteractiveAsmElementUml(
      UUID id) {
    for(IAsmListElementsUml<? extends IAsmElementUmlInteractive<?, DRI, ?, PRI>, DRI, ?, IMG, PRI, ?> asmList : getAssembliesListElementsUml()) {
      for(IAsmElementUmlInteractive<?, DRI, ?, PRI> asmElem : asmList.getListElementsUml()) {
        if(asmElem.getElementUml().getId().equals(id)) {
          return asmElem;
        }
      }
    }
    return null;
  }

  @Override
  public Collection<IAsmElementUmlInteractive<?, ?, ?, ?>> getInteractiveAsmElementsUmlExcept(UUID id) {
    List<IAsmElementUmlInteractive<?, ?, ?, ?>> result = new ArrayList<IAsmElementUmlInteractive<?,?,?,?>>();
    for(IAsmListElementsUml<? extends IAsmElementUmlInteractive<?, DRI, ?, PRI>, DRI, ?, IMG, PRI, ?> asmList : getAssembliesListElementsUml()) {
      for(IAsmElementUmlInteractive<?, DRI, ?, PRI> asmElem : asmList.getListElementsUml()) {
        if(!asmElem.getElementUml().getId().equals(id)) {
          result.add(asmElem);
        }
      }
    }
    return result;
  }

  @Override
  public long getVersionInteractiveAsmElementsUml() {
    long result = 0;
    for(IAsmListElementsUml<? extends IAsmElementUmlInteractive<?, DRI, ?, PRI>, DRI, ?, IMG, PRI, ?> asmList : getAssembliesListElementsUml()) {
      result = Math.max(result, asmList.getVersionElementsUml());
    }
    return result;
  }

  @Override
  public IAsmElementUmlInteractive<? extends ContainerFull<?>, ?, ?, ?> findContainerAtExcept(
      int screenX, int screenY, UUID id) {
    for(IAsmElementUmlInteractive<ContainerFull<FrameUml>, DRI, SD, PRI> asmFrameFull : asmListAsmFramesFull.getListElementsUml()) {
      if(!asmFrameFull.getElementUml().getId().equals(id) &&
          asmFrameFull.isContainsScreenPoint(screenX, screenY)) {
        return asmFrameFull;
      }
    }
    return null;
  }

  @Override
  public IAsmElementUmlInteractive<ContainerFull<FrameUml>, ?, ?, ?> findFrameFullById(UUID id) {
    for(IAsmElementUmlInteractive<ContainerFull<FrameUml>, DRI, SD, PRI> asmFrameFull : asmListAsmFramesFull.getListElementsUml()) {
      if(asmFrameFull.getElementUml().getId().equals(id)) {
        return asmFrameFull;
      }
    }
    return null;
  }

  @Override
  public IAsmElementUmlInteractive<ContainerFull<FrameUml>, ?, ?, ?> findFrameAt(int screenX, int screenY) {
    for(IAsmElementUmlInteractive<ContainerFull<FrameUml>, DRI, SD, PRI> asmFrameFull : asmListAsmFramesFull.getListElementsUml()) {
      if(asmFrameFull.isContainsScreenPoint(screenX, screenY)) {
        return asmFrameFull;
      }
    }
    return null;
  }

  @Override
  public Collection<IAsmElementUmlInteractive<ContainerFull<FrameUml>, ?, ?, ?>> getFramesFullExcept(
      UUID id) {
    List<IAsmElementUmlInteractive<ContainerFull<FrameUml>, ?, ?, ?>> result = new ArrayList<IAsmElementUmlInteractive<ContainerFull<FrameUml>,?,?,?>>();
    for(IAsmElementUmlInteractive<ContainerFull<FrameUml>, DRI, SD, PRI> asmFrameFull : asmListAsmFramesFull.getListElementsUml()) {
      if(!asmFrameFull.getElementUml().getId().equals(id)) {
        result.add(asmFrameFull);
      }
    }
    return result;
  }

  @Override
  public long getVersionAsmFramesFull() {
    return asmListAsmFramesFull.getVersionElementsUml();
  }

  @Override
  public void notifyModelChanged() {
    getGuiApp().getPaneDrawing().getSrvPaneDrawing().handleChangesIndexZ();
    setIsChanged(true);
    refreshGui();
  }

  //Utils:
  protected boolean tryToAddIntoContainer(IAsmElementUmlInteractive<?, ?, ?, ?> asmElm, int screenX, int screenY) {
    IAsmElementUmlInteractive<? extends ContainerFull<?>, ?, ?, ?> container = findContainerAtExcept(screenX, screenY, asmElm.getElementUml().getId());
    if(container != null) {
      container.getElementUml().getElements().add(asmElm);
      return true;
    }
    return false;
  }

  //SGS:
  public int getMouseWasAtX() {
    return mouseWasAtX;
  }

  public void setMouseWasAtX(int mouseWasAtX) {
    this.mouseWasAtX = mouseWasAtX;
  }

  public int getMouseWasAtY() {
    return mouseWasAtY;
  }

  public void setMouseWasAtY(int mouseWasAtY) {
    this.mouseWasAtY = mouseWasAtY;
  }

  public IGuiMainUml<DRI, SD, IMG, PRI, DLI> getGuiApp() {
    return guiApp;
  }

  public IAsmListElementsUml<ACM, DRI, SD, IMG, PRI, CM> getAssemblyListCommentsUml() {
    return assemblyListCommentsUml;
  }

  public IAsmListElementsUml<ATX, DRI, SD, IMG, PRI, TX> getAsmListAsmTexts() {
    return assemblyListTextsUml;
  }

  public IFactoryAsmElementUml<ACM, DRI, SD, PRI, CM> getFactoryCommentUml() {
    return factoryCommentUml;
  }

  public IFactoryAsmElementUml<ATX, DRI, SD, PRI, TX> getFactoryAsmText() {
    return factoryTextUml;
  }

  public void setSelectedElementUml(
      IAsmElementUmlInteractive<?, ?, ?, ?> selectedElementUml) {
    this.selectedElementUml = selectedElementUml;
  }

  public void setChanged(boolean isChanged) {
    this.isChanged = isChanged;
  }
  
  public IAsmListElementsUml<IAsmElementUmlInteractive<ContainerFull<FrameUml>, DRI, SD, PRI>, DRI, SD, IMG, PRI, ContainerFull<FrameUml>> getAsmListFrames() {
    return asmListAsmFramesFull;
  }

  public IFactoryAsmElementUml<IAsmElementUmlInteractive<ContainerFull<FrameUml>, DRI, SD, PRI>, DRI, SD, PRI, ContainerFull<FrameUml>> getFactoryFrameFull() {
    return factoryFrameFullFull;
  }
  
  public IAsmListElementsUml<IAsmElementUmlInteractive<RectangleUml, DRI, SD, PRI>, DRI, SD, IMG, PRI, RectangleUml> getAsmListRectangles() {
    return asmListAsmRectangles;
  }
  
  public IFactoryAsmElementUml<IAsmElementUmlInteractive<RectangleUml, DRI, SD, PRI>, DRI, SD, PRI, RectangleUml> getFactoryAsmRectangle() {
    return factoryAsmRectangle;
  }

  public IAsmListElementsUml<ATX, DRI, SD, IMG, PRI, TX> getAssemblyListTextsUml() {
    return assemblyListTextsUml;
  }

  public IFactoryAsmElementUml<ATX, DRI, SD, PRI, TX> getFactoryTextUml() {
    return factoryTextUml;
  }

  public IAsmListElementsUml<IAsmElementUmlInteractive<LineUml, DRI, SD, PRI>, DRI, SD, IMG, PRI, LineUml> getAsmListAsmLines() {
    return asmListAsmLines;
  }

  public IFactoryAsmElementUml<IAsmElementUmlInteractive<LineUml, DRI, SD, PRI>, DRI, SD, PRI, LineUml> getFactoryAsmLine() {
    return factoryAsmLine;
  }
}
