package org.beigesoft.uml.ui;

import org.beigesoft.handler.IConsumer;
import org.beigesoft.ui.widget.IButton;
import org.beigesoft.ui.widget.IChooserWithDataSource;
import org.beigesoft.ui.widget.IList;
import org.beigesoft.ui.widget.ITextField;
import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.assembly.MessageFull;
import org.beigesoft.uml.assembly.CoregionFull;
import org.beigesoft.uml.container.IContainerAsmMessagesFull;
import org.beigesoft.uml.service.edit.SrvEditCoregionFull;

public class EditorCoregionFull<M extends CoregionFull, DLI, AEI> extends AEditorElementUml<M, DLI, AEI> {

  private ITextField tfLifeLine;
  
  private IList<IAsmElementUmlInteractive<MessageFull,?,?,?>> listAsmMessagesFull;
  
  private IButton<AEI> btAddAsmMessageFull;

  private IButton<AEI> btDelAsmMessageFull;

  private IChooserWithDataSource<IAsmElementUmlInteractive<MessageFull,?,?,?>> chooserAsmMessagesFull;
  
  private IContainerAsmMessagesFull containerAsmMessagesFull;
  
  private long versionAsmMessagesFull;

  public EditorCoregionFull(DLI dialogInstrument, SrvEditCoregionFull<M, DLI> srvEdit,
      String modelName) {
    super(dialogInstrument, srvEdit, modelName);
  }

  @Override
  public boolean makeAction(AEI eventInstrument) {
    if(super.makeAction(eventInstrument)) {
      return true;
    }
    if(btAddAsmMessageFull.isPushed(eventInstrument)) {
      chooserAsmMessagesFull.showAndChoose(new IConsumer<IAsmElementUmlInteractive<MessageFull,?,?,?>>() {
        
        @Override
        public void consume(IAsmElementUmlInteractive<MessageFull,?,?,?> asmElm) {
          if(asmElm != null) {
            listAsmMessagesFull.add(asmElm);
          }
        }
      });
      return true;
    }
    if(btDelAsmMessageFull.isPushed(eventInstrument)) {
      listAsmMessagesFull.removeSelectedRow();
      return true;
    }
    return false;
  }

  @Override
  public void startEdit(M rel) {
    if(versionAsmMessagesFull != containerAsmMessagesFull.getVersionAsmMessagesFull()) {
      chooserAsmMessagesFull.refillDataSource(containerAsmMessagesFull.getMessagesFullForLifeLine(rel.getAsmLifeLineFull().getElementUml().getId()));
      versionAsmMessagesFull = containerAsmMessagesFull.getVersionAsmMessagesFull();
    }
    super.startEdit(rel);
  }

  @Override
  public void refreshModel() throws Exception {
    getModel().setAsmLifeLineFull(getModelClone().getAsmLifeLineFull());
    getModel().getAsmMessages().clear();
    for(IAsmElementUmlInteractive<MessageFull,?,?,?> asmElm : getModelClone().getAsmMessages()) {
      getModel().getAsmMessages().add(asmElm);
    }
    super.refreshModel();
  }

  @Override
  public void refreshGui() {
    tfLifeLine.setText(getModelClone().getAsmLifeLineFull() == null ? "" : 
      getModelClone().getAsmLifeLineFull().toString());
    listAsmMessagesFull.replaceDataSource(getModelClone().getAsmMessages());
    super.refreshGui();
  }

  //SGS:
  public ITextField getTfLifeLine() {
    return tfLifeLine;
  }

  public void setTfLifeLine(ITextField tfLifeLine) {
    this.tfLifeLine = tfLifeLine;
  }

  public IList<IAsmElementUmlInteractive<MessageFull, ?, ?, ?>> getListAsmMessagesFull() {
    return listAsmMessagesFull;
  }

  public void setListAsmMessagesFull(IList<IAsmElementUmlInteractive<MessageFull, ?, ?, ?>> listAsmMessagesFull) {
    this.listAsmMessagesFull = listAsmMessagesFull;
  }

  public IButton<AEI> getBtAddAsmMessageFull() {
    return btAddAsmMessageFull;
  }

  public void setBtAddAsmMessageFull(IButton<AEI> btAddAsmMessageFull) {
    this.btAddAsmMessageFull = btAddAsmMessageFull;
  }

  public IButton<AEI> getBtDelAsmMessageFull() {
    return btDelAsmMessageFull;
  }

  public void setBtDelAsmMessageFull(IButton<AEI> btDelAsmMessageFull) {
    this.btDelAsmMessageFull = btDelAsmMessageFull;
  }

  public IChooserWithDataSource<IAsmElementUmlInteractive<MessageFull, ?, ?, ?>> getChooserAsmMessagesFull() {
    return chooserAsmMessagesFull;
  }

  public void setChooserAsmMessagesFull(
      IChooserWithDataSource<IAsmElementUmlInteractive<MessageFull, ?, ?, ?>> chooserAsmMessagesFull) {
    this.chooserAsmMessagesFull = chooserAsmMessagesFull;
  }

  public IContainerAsmMessagesFull getContainerAsmMessagesFull() {
    return containerAsmMessagesFull;
  }

  public void setContainerAsmMessagesFull(IContainerAsmMessagesFull containerAsmMessagesFull) {
    this.containerAsmMessagesFull = containerAsmMessagesFull;
  }

  public long getVersionAsmMessagesFull() {
    return versionAsmMessagesFull;
  }

  public void setVersionAsmMessagesFull(long versionAsmMessagesFull) {
    this.versionAsmMessagesFull = versionAsmMessagesFull;
  }
}
