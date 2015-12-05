package org.beigesoft.uml.ui;

import org.beigesoft.pojo.HasNameEditable;
import org.beigesoft.ui.widget.IButton;
import org.beigesoft.ui.widget.IListWithEditor;
import org.beigesoft.uml.pojo.UseCaseExtended;
import org.beigesoft.uml.service.edit.SrvEditUseCaseExtendedFull;

public class EditorUseCaseExtendedFull<UC extends UseCaseExtended, DLI, AEI> extends EditorUseCaseFull<UC, DLI, AEI> {
  
  private IButton<AEI> btAddExtPoint;
  
  private IButton<AEI> btEditExtPoint;
  
  private IButton<AEI> btDelExtPoint;
  
  private IListWithEditor<HasNameEditable> listExtentionPoints;
  
  public EditorUseCaseExtendedFull(DLI dli, SrvEditUseCaseExtendedFull<UC, DLI> srvEdit) {
    super(dli, srvEdit);
  }

  @Override
  public void doPostConstruct() {
    super.doPostConstruct();
    btAddExtPoint.setText(getSrvEdit().getSrvI18n().getMsg("add"));
    btEditExtPoint.setText(getSrvEdit().getSrvI18n().getMsg("edit"));
    btDelExtPoint.setText(getSrvEdit().getSrvI18n().getMsg("delete"));
  }

  @Override
  public boolean makeAction(AEI eventInstrument) {
    if(super.makeAction(eventInstrument)) {
      return true;
    }
    if(btAddExtPoint.isPushed(eventInstrument)) {
      listExtentionPoints.add(new HasNameEditable());
      return true;
    }
    if(btEditExtPoint.isPushed(eventInstrument)) {
      listExtentionPoints.editSelectedRow();
      return true;
    }
    if(btDelExtPoint.isPushed(eventInstrument)) {
      listExtentionPoints.removeSelectedRow();
      return true;
    }
    return false;
  }

  @Override
  public void refreshModel() throws Exception {
    super.refreshModel();
    getModel().getShape().getExtentionPoints().clear();
    for(HasNameEditable ep : getModelClone().getShape().getExtentionPoints()) {
      getModel().getShape().getExtentionPoints().add(ep.clone());
    }
  }

  @Override
  public void refreshGui() {
    super.refreshGui();
    listExtentionPoints.replaceDataSource(getModelClone().getShape().getExtentionPoints());
  }
  //SGS:
  public IButton<AEI> getBtAddExtPoint() {
    return btAddExtPoint;
  }

  public void setBtAddExtPoint(IButton<AEI> btAddExtPoint) {
    this.btAddExtPoint = btAddExtPoint;
  }

  public IButton<AEI> getBtDelExtPoint() {
    return btDelExtPoint;
  }

  public void setBtDelExtPoint(IButton<AEI> btDelExtPoint) {
    this.btDelExtPoint = btDelExtPoint;
  }

  public IButton<AEI> getBtEditExtPoint() {
    return btEditExtPoint;
  }

  public void setBtEditExtPoint(IButton<AEI> btEditExtPoint) {
    this.btEditExtPoint = btEditExtPoint;
  }

  public IListWithEditor<HasNameEditable> getListExtentionPoints() {
    return listExtentionPoints;
  }

  public void setListExtentionPoints(IListWithEditor<HasNameEditable> listExtentionPoints) {
    this.listExtentionPoints = listExtentionPoints;
  }
}
