package org.beigesoft.uml.ui;

import org.beigesoft.ui.widget.IButton;
import org.beigesoft.ui.widget.ICheckBox;
import org.beigesoft.ui.widget.IList;
import org.beigesoft.ui.widget.ITextField;
import org.beigesoft.uml.pojo.CommentUml;
import org.beigesoft.uml.pojo.CommentRelationship;
import org.beigesoft.uml.service.edit.SrvEditComment;
import org.beigesoft.util.UtilsMisc;

public class EditorComment<M extends CommentUml, DLI, AEI> extends AEditorElementUml<M, DLI, AEI> {
  
  private ITextField taItsText;
  
  private ICheckBox cbHasBorder;

  private ICheckBox cbIsDashedRelations;

  private IList<CommentRelationship> listRelationships;
  
  private IButton<AEI> btAddRelationship;

  private IButton<AEI> btDelRelationship;
  
  public EditorComment(DLI dli, SrvEditComment<M, DLI> srvEdit, String modelName) {
    super(dli, srvEdit, modelName);
  }

  @Override
  public void doPostConstruct() {
    super.doPostConstruct();
    btAddRelationship.setText(getSrvEdit().getSrvI18n().getMsg("add"));
    btDelRelationship.setText(getSrvEdit().getSrvI18n().getMsg("delete"));
    cbHasBorder.setLabel(getSrvEdit().getSrvI18n().getMsg("hasBorder"));
    cbIsDashedRelations.setLabel(getSrvEdit().getSrvI18n().getMsg("isDashedRelations"));
  }

  @Override
  public boolean makeAction(AEI eventInstrument) {
    if(super.makeAction(eventInstrument)) {
      return true;
    }
    if(btAddRelationship.isPushed(eventInstrument)) {
      listRelationships.add(new CommentRelationship(getModel()));
      return true;
    }
    if(btDelRelationship.isPushed(eventInstrument)) {
      listRelationships.removeSelectedRow();
      return true;
    }
    return false;
  }

  @Override
  public void refreshModel() throws Exception {
    getModel().setItsText(getModelClone().getItsText());
    getModel().setHasBorder(getModelClone().getHasBorder());
    getModel().setIsDashedRelationships(getModelClone().getIsDashedRelationships());
    getModel().getRelationships().clear();
    for(CommentRelationship rel : getModelClone().getRelationships()) {
      getModel().getRelationships().add(rel.clone());
    }
    super.refreshModel();
  }

  @Override
  public void refreshGui() {
    taItsText.setText(getModelClone().getItsText());
    cbHasBorder.setIsSelected(getModelClone().getHasBorder());
    cbIsDashedRelations.setIsSelected(getModelClone().getIsSelected());
    listRelationships.replaceDataSource(getModelClone().getRelationships());
    super.refreshGui();
  }

  @Override
  public void refreshModelClone() {
    getModelClone().setItsText(UtilsMisc.evalTextValue(taItsText.getText()));
    getModelClone().setHasBorder(cbHasBorder.getIsSelected());
    getModelClone().setIsSelected(cbIsDashedRelations.getIsSelected());
    super.refreshModelClone();
  }
  
  //SGS:
  public ITextField getTaItsText() {
    return taItsText;
  }

  public void setTaItsText(ITextField taItsText) {
    this.taItsText = taItsText;
  }

  public ICheckBox getCbHasBorder() {
    return cbHasBorder;
  }

  public void setCbHasBorder(ICheckBox cbHasBorder) {
    this.cbHasBorder = cbHasBorder;
  }

  public ICheckBox getCbIsDashedRelations() {
    return cbIsDashedRelations;
  }

  public void setCbIsDashedRelations(ICheckBox cbIsDashedRelations) {
    this.cbIsDashedRelations = cbIsDashedRelations;
  }

  public IList<CommentRelationship> getListRelationships() {
    return listRelationships;
  }

  public void setListRelationships(IList<CommentRelationship> listRelationships) {
    this.listRelationships = listRelationships;
  }

  public IButton<AEI> getBtAddRelationship() {
    return btAddRelationship;
  }

  public void setBtAddRelationship(IButton<AEI> btAddRelationship) {
    this.btAddRelationship = btAddRelationship;
  }

  public IButton<AEI> getBtDelRelationship() {
    return btDelRelationship;
  }

  public void setBtDelRelationship(IButton<AEI> btDelRelationship) {
    this.btDelRelationship = btDelRelationship;
  }
}
