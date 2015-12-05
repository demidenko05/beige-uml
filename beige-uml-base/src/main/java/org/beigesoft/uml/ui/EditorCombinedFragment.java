package org.beigesoft.uml.ui;

import org.beigesoft.ui.widget.IButton;
import org.beigesoft.ui.widget.IComboBox;
import org.beigesoft.ui.widget.IList;
import org.beigesoft.uml.model.EInteractionOperator;
import org.beigesoft.uml.pojo.CombinedFragment;
import org.beigesoft.uml.service.edit.SrvEditCombinedFragment;

public class EditorCombinedFragment<M extends CombinedFragment, DLI, AEI> extends EditorInteractionUse<M, DLI, AEI> {

  private IComboBox<EInteractionOperator> cmbInteractionOperator;

  private IList<Double> listTracesY;
  
  private IButton<AEI> btAddTraceY;

  private IButton<AEI> btDelTraceY;
  
  public EditorCombinedFragment(DLI dialogInstrument, SrvEditCombinedFragment<M, DLI> srvEdit,
      String modelName) {
    super(dialogInstrument, srvEdit, modelName);
  }

  @Override
  public void doPostConstruct() {
    super.doPostConstruct();
    btAddTraceY.setText(getSrvEdit().getSrvI18n().getMsg("add"));
    btDelTraceY.setText(getSrvEdit().getSrvI18n().getMsg("delete"));
  }

  @Override
  public boolean makeAction(AEI eventInstrument) {
    if(super.makeAction(eventInstrument)) {
      return true;
    }
    if(btAddTraceY.isPushed(eventInstrument)) {
      listTracesY.add(new Double(getModelClone().getHeight() - 
          getModelClone().getHeight()/(2 + getModelClone().getTracesY().size())));
      return true;
    }
    if(btDelTraceY.isPushed(eventInstrument)) {
      listTracesY.removeSelectedRow();
      return true;
    }
    return false;
  }

  @Override
  public void refreshModel() throws Exception {
    getModel().setInteractionOperator(getModelClone().getInteractionOperator());
    getModel().getTracesY().clear();
    for(Double tr : getModelClone().getTracesY()) {
      getModel().getTracesY().add(new Double(tr));
    }
    super.refreshModel();
  }

  @Override
  public void refreshModelClone() {
    getModelClone().setInteractionOperator(cmbInteractionOperator.getSelectedItem());
    super.refreshModelClone();
  }

  @Override
  public void refreshGui() {
    cmbInteractionOperator.setSelectedItem(getModelClone().getInteractionOperator());
    listTracesY.replaceDataSource(getModelClone().getTracesY());
    super.refreshGui();
  }


  //SGS:
  public IComboBox<EInteractionOperator> getCmbInteractionOperator() {
    return cmbInteractionOperator;
  }

  public void setCmbInteractionOperator(
      IComboBox<EInteractionOperator> cmbInteractionOperator) {
    this.cmbInteractionOperator = cmbInteractionOperator;
  }

  public IList<Double> getListTracesY() {
    return listTracesY;
  }

  public void setListTracesY(IList<Double> listTracesY) {
    this.listTracesY = listTracesY;
  }

  public IButton<AEI> getBtAddTraceY() {
    return btAddTraceY;
  }

  public void setBtAddTraceY(IButton<AEI> btAddTrace) {
    this.btAddTraceY = btAddTrace;
  }

  public IButton<AEI> getBtDelTraceY() {
    return btDelTraceY;
  }

  public void setBtDelTraceY(IButton<AEI> btDelTrace) {
    this.btDelTraceY = btDelTrace;
  }  
}
