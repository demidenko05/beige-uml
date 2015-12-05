package org.beigesoft.uml.ui;

import org.beigesoft.ui.widget.IButton;
import org.beigesoft.ui.widget.ICheckBox;
import org.beigesoft.ui.widget.IList;
import org.beigesoft.ui.widget.ITextField;
import org.beigesoft.uml.assembly.LifeLineFull;
import org.beigesoft.uml.pojo.Execution;
import org.beigesoft.uml.pojo.ShapeUmlWithName;
import org.beigesoft.uml.service.edit.SrvEditLifeLineFull;
import org.beigesoft.util.UtilsMisc;

public class EditorLifeLineFull<M extends LifeLineFull<ShapeUmlWithName>, DLI, AEI> extends AEditorElementUml<M, DLI, AEI> {

  private ITextField tfItsName;

  private ITextField tfItsFrame;
  
  private ICheckBox cbIsDestructionOccurence;
  
  private IList<Execution> listExecutions;
  
  private IButton<AEI> btAddExecution;

  private IButton<AEI> btDelExecution;
  
  public EditorLifeLineFull(DLI dialogInstrument, SrvEditLifeLineFull<M, DLI> srvEdit,
      String modelName) {
    super(dialogInstrument, srvEdit, modelName);
  }

  @Override
  public void doPostConstruct() {
    super.doPostConstruct();
    btAddExecution.setText(getSrvEdit().getSrvI18n().getMsg("add"));
    btDelExecution.setText(getSrvEdit().getSrvI18n().getMsg("delete"));
  }

  @Override
  public boolean makeAction(AEI eventInstrument) {
    if(super.makeAction(eventInstrument)) {
      return true;
    }
    if(btAddExecution.isPushed(eventInstrument)) {
      double execStart = (getModel().getLineEndY() - getModel().getHeight() - getModel().getPointStart().getY()) / 4;
      double execEnd = (getModel().getLineEndY() - getModel().getHeight() - getModel().getPointStart().getY()) / 2;
      for(Execution ex : getModelClone().getExecutions()) {
        if(ex.getEndY() >= getModel().getLineEndY() - getModel().getHeight() - getModel().getPointStart().getY()
            - getSrvEdit().getSettingsGraphic().getWidthDragRectangle()*2) {
          execStart = getModel().getLineEndY();
          break;
        }
        execStart = ex.getEndY() + getSrvEdit().getSettingsGraphic().getWidthDragRectangle();
        execEnd = getModel().getLineEndY() - getModel().getHeight() - getModel().getPointStart().getY()
            - getSrvEdit().getSettingsGraphic().getWidthDragRectangle();
      }
      if(execStart == getModel().getLineEndY()) {
        String msg = getSrvEdit().getSrvI18n().getMsg("make_some_free_space_first");
        String title = getSrvEdit().getSrvI18n().getMsg("error");
        getSrvEdit().getSrvDialog().errorMessage(getDialogInstrument(), msg, title);
      }
      else {
        Execution exec = new Execution();
        exec.setStartY(execStart);
        exec.setEndY(execEnd);
        listExecutions.add(exec);
      }
      return true;
    }
    if(btDelExecution.isPushed(eventInstrument)) {
      listExecutions.removeSelectedRow();
      return true;
    }
    return false;
  }

  @Override
  public void refreshModel() throws Exception {
    getModel().getLifeLine().setItsName(getModelClone().getLifeLine().getItsName());
    getModel().setDestructionOccurenceY(getModelClone().getDestructionOccurenceY());
    getModel().getExecutions().clear();
    for(Execution rel : getModelClone().getExecutions()) {
      getModel().getExecutions().add(rel.clone());
    }
    super.refreshModel();
  }

  @Override
  public void refreshModelClone() {
    getModelClone().getLifeLine().setItsName(UtilsMisc.evalTextValue(tfItsName.getText()));
    if(cbIsDestructionOccurence.getIsSelected()) {
      getModelClone().setDestructionOccurenceY(getModel().getLineEndY());
    }
    else {
      getModelClone().setDestructionOccurenceY(null);
    }
    super.refreshModelClone();
  }

  @Override
  public void refreshGui() {
    tfItsName.setText(getModelClone().getLifeLine().getItsName());
    String nameFrame = getModelClone().getAsmFrameFull() == null ? "-" : 
      getModelClone().getAsmFrameFull().toString();
    tfItsFrame.setText(nameFrame);
    cbIsDestructionOccurence.setIsSelected(getModelClone().getDestructionOccurenceY() != null);
    listExecutions.replaceDataSource(getModelClone().getExecutions());
    super.refreshGui();
  }

  //SGS:
  public ITextField getTfItsName() {
    return tfItsName;
  }

  public void setTfItsName(ITextField tfItsName) {
    this.tfItsName = tfItsName;
  }

  public ITextField getTfItsFrame() {
    return tfItsFrame;
  }

  public void setTfItsFrame(ITextField tfItsFrame) {
    this.tfItsFrame = tfItsFrame;
  }

  public ICheckBox getCbIsDestructionOccurence() {
    return cbIsDestructionOccurence;
  }

  public void setCbIsDestructionOccurence(ICheckBox cbIsDestructionOccurence) {
    this.cbIsDestructionOccurence = cbIsDestructionOccurence;
  }

  public IList<Execution> getListExecutions() {
    return listExecutions;
  }

  public void setListExecutions(IList<Execution> listExecutions) {
    this.listExecutions = listExecutions;
  }

  public IButton<AEI> getBtAddExecution() {
    return btAddExecution;
  }

  public void setBtAddExecution(IButton<AEI> btAddExecution) {
    this.btAddExecution = btAddExecution;
  }

  public IButton<AEI> getBtDelExecution() {
    return btDelExecution;
  }

  public void setBtDelExecution(IButton<AEI> btDelExecution) {
    this.btDelExecution = btDelExecution;
  }
}
