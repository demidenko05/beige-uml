package org.beigesoft.uml.ui;

import org.beigesoft.ui.widget.IButton;
import org.beigesoft.ui.widget.IListWithEditor;
import org.beigesoft.uml.pojo.MethodClass;
import org.beigesoft.uml.pojo.ParameterMethod;
import org.beigesoft.uml.service.edit.SrvEditMethodClass;

public class EditorMethodClass<M extends MethodClass, DLI, AEI> extends EditorMemberClass<M, DLI, AEI> {

  private IListWithEditor<ParameterMethod> listParameters;
  
  private IButton<AEI> btAddParameter;

  private IButton<AEI> btEditParameter;
  
  private IButton<AEI> btDelParameter;
  
  public EditorMethodClass(DLI dli, SrvEditMethodClass<M, DLI> srvEdit, String modelName) {
    super(dli, srvEdit, modelName);
  }

  @Override
  public void doPostConstruct() {
    super.doPostConstruct();
    btAddParameter.setText(getSrvEdit().getSrvI18n().getMsg("add"));
    btEditParameter.setText(getSrvEdit().getSrvI18n().getMsg("edit"));
    btDelParameter.setText(getSrvEdit().getSrvI18n().getMsg("delete"));
  }

  @Override
  public boolean makeAction(AEI eventInstrument) {
    if(super.makeAction(eventInstrument)) {
      return true;
    }
    if(btAddParameter.isPushed(eventInstrument)) {
      listParameters.add(new ParameterMethod());
      return true;
    }
    if(btEditParameter.isPushed(eventInstrument)) {
      listParameters.editSelectedRow();
      return true;
    }
    if(btDelParameter.isPushed(eventInstrument)) {
      listParameters.removeSelectedRow();
      return true;
    }
    return false;
  }

  @Override
  public void refreshModel() throws Exception {
    getModel().getParameters().clear();
    for(ParameterMethod param : getModelClone().getParameters()) {
      getModel().getParameters().add(param.clone());
    }
    super.refreshModel();
  }

  @Override
  public void refreshGui() {
    listParameters.replaceDataSource(getModelClone().getParameters());
    super.refreshGui();
  }

  //SGS:
  public IListWithEditor<ParameterMethod> getListParameters() {
    return listParameters;
  }

  public void setListParameters(IListWithEditor<ParameterMethod> listParameters) {
    this.listParameters = listParameters;
  }

  public IButton<AEI> getBtAddParameter() {
    return btAddParameter;
  }

  public void setBtAddParameter(IButton<AEI> btAddParameter) {
    this.btAddParameter = btAddParameter;
  }

  public IButton<AEI> getBtEditParameter() {
    return btEditParameter;
  }

  public void setBtEditParameter(IButton<AEI> btEditParameter) {
    this.btEditParameter = btEditParameter;
  }

  public IButton<AEI> getBtDelParameter() {
    return btDelParameter;
  }

  public void setBtDelParameter(IButton<AEI> btDelParameter) {
    this.btDelParameter = btDelParameter;
  }
}
