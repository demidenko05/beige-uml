package org.beigesoft.ui.widget;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import org.beigesoft.handler.IHandlerConfirm;
import org.beigesoft.handler.IObserverModelChanged;
import org.beigesoft.ui.IWindowInstrument;
import org.beigesoft.ui.service.edit.ISrvEdit;

/**
 * 
 * @author Yury Demidenko
 *
 * @param <DLI> dialog instrument (e.g. Frame for SWING, Activity for Android)
 * @param <AEI> action event instrument
 * @param <M> model to edit
 */
public abstract class AEditor<M, DLI, AEI> implements IEditor<M> {

  private IButton<AEI> btApply;
  
  private IButton<AEI> btOk;
  
  private IButton<AEI> btClose;
  
  private M model;
  
  private M modelClone;
  
  private final String modelName;
  
  private final ISrvEdit<M, DLI> srvEdit;
  
  private IWindowInstrument windowInstrument;
  
  private final Set<IObserverModelChanged> observersModelChanged;
  
  private final DLI dialogInstrument;
  
  public AEditor(DLI dialogInstrument, ISrvEdit<M, DLI> srvEdit, String modelName) {
    this.dialogInstrument = dialogInstrument;
    this.modelName = modelName;
    this.srvEdit = srvEdit;
    observersModelChanged = new HashSet<IObserverModelChanged>();
  }

  /**
   * post constructor
   */
  public void doPostConstruct() {
    btOk.setText(srvEdit.getSrvI18n().getMsg("ok"));
    btApply.setText(srvEdit.getSrvI18n().getMsg("apply"));
    btClose.setText(srvEdit.getSrvI18n().getMsg("close"));
  }
  
  public boolean makeAction(AEI eventInstrument) {
    if(btOk.isPushed(eventInstrument)) {
      doOk();
      return true;
    }
    if(btApply.isPushed(eventInstrument)) {
      doApply();
      return true;
    }
    if(btClose.isPushed(eventInstrument)) {
      doClose();
      return true;
    }
    return false;
  }

  public boolean doApply() {
    refreshModelClone();
    if(srvEdit.isEquals(model,modelClone)) {
      srvEdit.getSrvDialog().warningMessage(dialogInstrument, srvEdit.getSrvI18n().getMsg("nothing_to_save"),
          srvEdit.getSrvI18n().getMsg("warning")+"!");
      return false;
    }
    Set<String> validResult = new LinkedHashSet<String>();
    srvEdit.validate(modelClone, validResult);
    if(validResult.size() == 0) {
      try {
        refreshModel();
        return true;
      } catch(Exception e) {
        getSrvEdit().getSrvDialog().errorMessage(getDialogInstrument(), e.getMessage(), "Error!");
        e.printStackTrace();
        return false;
      }
    }
    else {
      String strRez = "";
      for(String str : validResult)
        strRez += str + "\n";
      srvEdit.getSrvDialog().errorMessage(dialogInstrument, strRez, srvEdit.getSrvI18n().getMsg("error"));
      return false;
    }
  }

  public void doOk() {
    if(doApply()) {
      windowInstrument.doClose();
    }
  }
  
  public void doClose() {
    refreshModelClone();
    if(!srvEdit.isEquals(model, modelClone)) {
      srvEdit.getSrvDialog().confirm(dialogInstrument, srvEdit.getSrvI18n().getMsg("changes_will_be_lost_msg"),
          srvEdit.getSrvI18n().getMsg("are_you_sure"), new IHandlerConfirm() {
        
            @Override
            public void handleConfirm(boolean isConfirmed) {
              if(isConfirmed) {
                windowInstrument.doClose();
              }
            }
          });
    }
    else {
      windowInstrument.doClose();
    }
  }
  
  @Override
  public void startEdit(M model) {
    this.model = model;
    this.modelClone = srvEdit.createClone(model);
    if(srvEdit.getIsNew(model)) {
      windowInstrument.setTitle(srvEdit.getSrvI18n().getMsg("new")+" "+modelName);
    }
    else {
      windowInstrument.setTitle(srvEdit.getSrvI18n().getMsg("edit")+" "+modelName);
    }
    if(btOk != null) {
      refreshGui();
    }
    windowInstrument.doShow();
  }
  
  public void addObserverModelChanged(IObserverModelChanged obsv) {
    observersModelChanged.add(obsv);
  }

  public void refreshModel() throws Exception {
    for(IObserverModelChanged obsv : observersModelChanged) {
      obsv.notifyModelChanged();
    }
    if(srvEdit.getIsNew(model)) {
      srvEdit.setIsNew(model, false);
    }
    if(srvEdit.getIsNew(modelClone)) {
      srvEdit.setIsNew(modelClone, false);
    }
  }
  
  //to override:
  public abstract void refreshModelClone();

  public abstract void refreshGui();
  
  //SGS:
  public IButton<AEI> getBtApply() {
    return btApply;
  }

  public void setBtApply(IButton<AEI> btApply) {
    this.btApply = btApply;
  }

  public IButton<AEI> getBtOk() {
    return btOk;
  }

  public void setBtOk(IButton<AEI> btOk) {
    this.btOk = btOk;
  }

  public IButton<AEI> getBtClose() {
    return btClose;
  }

  public void setBtClose(IButton<AEI> btClose) {
    this.btClose = btClose;
  }

  public M getModel() {
    return model;
  }

  public void setModel(M model) {
    this.model = model;
  }

  public M getModelClone() {
    return modelClone;
  }

  public void setModelClone(M modelClone) {
    this.modelClone = modelClone;
  }

  public IWindowInstrument getWindowInstrument() {
    return windowInstrument;
  }

  public void setWindowInstrument(IWindowInstrument windowInstrument) {
    this.windowInstrument = windowInstrument;
  }

  public String getModelName() {
    return modelName;
  }

  public ISrvEdit<M, DLI> getSrvEdit() {
    return srvEdit;
  }

  public DLI getDialogInstrument() {
    return dialogInstrument;
  }
}
