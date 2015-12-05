package org.beigesoft.uml.ui;

import org.beigesoft.handler.IConsumer;
import org.beigesoft.ui.widget.IButton;
import org.beigesoft.ui.widget.IChooserWithDataSource;
import org.beigesoft.ui.widget.IList;
import org.beigesoft.ui.widget.ITextField;
import org.beigesoft.uml.assembly.ContainerFull;
import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.container.IContainerInteractiveElementsUml;
import org.beigesoft.uml.pojo.FrameUml;
import org.beigesoft.uml.service.edit.SrvEditContainerFull;
import org.beigesoft.util.UtilsMisc;

public class EditorFrameFull<M extends FrameUml, DLI, AEI> extends AEditorElementUml<ContainerFull<M>, DLI, AEI> {

  private ITextField tfItsName;

  private ITextField tfItsKind;

  private ITextField tfParameters;

  private IList<IAsmElementUmlInteractive<?,?,?,?>> listElements;
  
  private IButton<AEI> btAddElement;

  private IButton<AEI> btDelElement;

  private IChooserWithDataSource<IAsmElementUmlInteractive<?,?,?,?>> chooserAsmElementUml;
  
  private IContainerInteractiveElementsUml containerInteractiveElementsUml;
  
  private long versionAsmElementsUml;
  
  public EditorFrameFull(DLI dialogInstrument, SrvEditContainerFull<M, DLI> srvEdit,
      String modelName) {
    super(dialogInstrument, srvEdit, modelName);
  }

  @Override
  public void doPostConstruct() {
    super.doPostConstruct();
    btAddElement.setText(getSrvEdit().getSrvI18n().getMsg("add"));
    btDelElement.setText(getSrvEdit().getSrvI18n().getMsg("delete"));
  }

  @Override
  public boolean makeAction(AEI eventInstrument) {
    if(super.makeAction(eventInstrument)) {
      return true;
    }
    if(btAddElement.isPushed(eventInstrument)) {
      chooserAsmElementUml.showAndChoose(new IConsumer<IAsmElementUmlInteractive<?,?,?,?>>() {
        
        @Override
        public void consume(IAsmElementUmlInteractive<?,?,?,?> asmElm) {
          if(asmElm != null) {
            listElements.add(asmElm);
          }
        }
      });
      return true;
    }
    if(btDelElement.isPushed(eventInstrument)) {
      listElements.removeSelectedRow();
      return true;
    }
    return false;
  }

  @Override
  public void startEdit(ContainerFull<M> frf) {
    if(versionAsmElementsUml != containerInteractiveElementsUml.getVersionInteractiveAsmElementsUml()) {
      chooserAsmElementUml.refillDataSource(containerInteractiveElementsUml.getInteractiveAsmElementsUmlExcept(frf.getId()));
      versionAsmElementsUml = containerInteractiveElementsUml.getVersionInteractiveAsmElementsUml();
    }
    super.startEdit(frf);
  }

  @Override
  public void refreshModel() throws Exception {
    getModel().getContainer().setItsName(getModelClone().getContainer().getItsName());
    getModel().getContainer().setItsKind(getModelClone().getContainer().getItsKind());
    getModel().getContainer().setParameters(getModelClone().getContainer().getParameters());
    getModel().getElements().clear();
    for(IAsmElementUmlInteractive<?,?,?,?> asmElm : getModelClone().getElements()) {
      getModel().getElements().add(asmElm);
    }
    super.refreshModel();
  }

  @Override
  public void refreshModelClone() {
    getModelClone().getContainer().setParameters(UtilsMisc.evalTextValue(tfParameters.getText()));
    getModelClone().getContainer().setItsName(UtilsMisc.evalTextValue(tfItsName.getText()));
    getModelClone().getContainer().setItsKind(UtilsMisc.evalTextValue(tfItsKind.getText()));
    super.refreshModelClone();
  }

  @Override
  public void refreshGui() {
    tfParameters.setText(getModelClone().getContainer().getParameters());
    tfItsName.setText(getModelClone().getContainer().getItsName());
    tfItsKind.setText(getModelClone().getContainer().getItsKind());
    listElements.replaceDataSource(getModelClone().getElements());
    super.refreshGui();
  }

  //SGS:
  public ITextField getTfItsName() {
    return tfItsName;
  }

  public void setTfItsName(ITextField tfItsName) {
    this.tfItsName = tfItsName;
  }

  public ITextField getTfItsKind() {
    return tfItsKind;
  }

  public void setTfItsKind(ITextField tfItsKind) {
    this.tfItsKind = tfItsKind;
  }

  public ITextField getTfParameters() {
    return tfParameters;
  }

  public void setTfParameters(ITextField tfParameters) {
    this.tfParameters = tfParameters;
  }

  public IList<IAsmElementUmlInteractive<?, ?, ?, ?>> getListElements() {
    return listElements;
  }

  public void setListElements(
      IList<IAsmElementUmlInteractive<?, ?, ?, ?>> listElements) {
    this.listElements = listElements;
  }

  public IButton<AEI> getBtAddElement() {
    return btAddElement;
  }

  public void setBtAddElement(IButton<AEI> btAddElement) {
    this.btAddElement = btAddElement;
  }

  public IButton<AEI> getBtDelElement() {
    return btDelElement;
  }

  public void setBtDelElement(IButton<AEI> btDelElement) {
    this.btDelElement = btDelElement;
  }

  public IChooserWithDataSource<IAsmElementUmlInteractive<?, ?, ?, ?>> getChooserAsmElementUml() {
    return chooserAsmElementUml;
  }

  public void setChooserAsmElementUml(
      IChooserWithDataSource<IAsmElementUmlInteractive<?, ?, ?, ?>> chooserAsmElementUml) {
    this.chooserAsmElementUml = chooserAsmElementUml;
  }

  public IContainerInteractiveElementsUml getContainerInteractiveElementsUml() {
    return containerInteractiveElementsUml;
  }

  public void setContainerInteractiveElementsUml(
      IContainerInteractiveElementsUml containerInteractiveElementsUml) {
    this.containerInteractiveElementsUml = containerInteractiveElementsUml;
  }

  public long getVersionAsmElementsUml() {
    return versionAsmElementsUml;
  }

  public void setVersionAsmElementsUml(long versionAsmElementsUml) {
    this.versionAsmElementsUml = versionAsmElementsUml;
  }
}
