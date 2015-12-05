package org.beigesoft.uml.ui;

import org.beigesoft.factory.IFactorySimple;
import org.beigesoft.graphic.model.EJoint2DType;
import org.beigesoft.ui.service.edit.ISrvEdit;
import org.beigesoft.ui.widget.AEditor;
import org.beigesoft.ui.widget.IButton;
import org.beigesoft.ui.widget.IComboBox;
import org.beigesoft.ui.widget.IListWithEditor;
import org.beigesoft.uml.assembly.ShapeFull;
import org.beigesoft.uml.model.ERelationshipKind;
import org.beigesoft.uml.pojo.ShapeRelationship;
import org.beigesoft.uml.pojo.RelationshipPoly;
import org.beigesoft.uml.pojo.ShapeUml;

public class EditorRelationshipPoly<M extends RelationshipPoly<SHR, SHF, SH>, DLI, AEI,
  SHR extends ShapeRelationship<SHF, SH>, SHF extends ShapeFull<SH>, SH extends ShapeUml> 
    extends AEditor<M, DLI, AEI> {
  
  private IComboBox<ERelationshipKind> cmbRelationshipKind;
  
  private IComboBox<EJoint2DType> cmbJointType;
  
  private IListWithEditor<SHR> listShapeRelationships;
  
  private IButton<AEI> btAddShapeRelationship;

  private IButton<AEI> btEditShapeRelationship;
  
  private IButton<AEI> btDelShapeRelationship;
  
  private final IFactorySimple<SHR> factoryShapeRelationship;

  public EditorRelationshipPoly(DLI dli, ISrvEdit<M, DLI> srvEdit, String modelName, 
      IFactorySimple<SHR> factoryShapeRelationship) {
    super(dli, srvEdit, modelName);
    this.factoryShapeRelationship = factoryShapeRelationship;
  }

  @Override
  public void doPostConstruct() {
    btAddShapeRelationship.setText(getSrvEdit().getSrvI18n().getMsg("add"));
    btEditShapeRelationship.setText(getSrvEdit().getSrvI18n().getMsg("edit"));
    btDelShapeRelationship.setText(getSrvEdit().getSrvI18n().getMsg("delete"));
    super.doPostConstruct();
  }

  @Override
  public boolean makeAction(AEI eventInstrument) {
    if(super.makeAction(eventInstrument)) {
      return true;
    }
    if(btAddShapeRelationship.isPushed(eventInstrument)) {
      SHR shr = factoryShapeRelationship.create();
      listShapeRelationships.add(shr);
      return true;
    }
    if(btEditShapeRelationship.isPushed(eventInstrument)) {
      listShapeRelationships.editSelectedRow();
      return true;
    }
    if(btDelShapeRelationship.isPushed(eventInstrument)) {
      listShapeRelationships.removeSelectedRow();
      return true;
    }
    return false;
  }
  
  @Override
  public void refreshModel() throws Exception {
    getModel().setKind(getModelClone().getItsKind());
    getModel().setSharedJoint(getModelClone().getSharedJoint());
    getModel().getShapesRelationship().clear();
    for(SHR shr : getModelClone().getShapesRelationship()) {
      getModel().getShapesRelationship().add(shr);
    }
    super.refreshModel();
  }

  @Override
  public void refreshModelClone() {
    getModelClone().setKind(cmbRelationshipKind.getSelectedItem());
    getModelClone().getSharedJoint().setTypeJoint(cmbJointType.getSelectedItem());
  }

  @Override
  public void refreshGui() {
    listShapeRelationships.replaceDataSource(getModelClone().getShapesRelationship());
    cmbRelationshipKind.setSelectedItem(getModelClone().getItsKind());
    cmbJointType.setSelectedItem(getModelClone().getSharedJoint().getTypeJoint());
  }

  //SGS:
  public IComboBox<ERelationshipKind> getCmbRelationshipKind() {
    return cmbRelationshipKind;
  }

  public IComboBox<EJoint2DType> getCmbJointType() {
    return cmbJointType;
  }

  public void setCmbJointType(IComboBox<EJoint2DType> cmbJointType) {
    this.cmbJointType = cmbJointType;
  }

  public IListWithEditor<SHR> getListShapeRelationships() {
    return listShapeRelationships;
  }

  public void setListShapeRelationships(
      IListWithEditor<SHR> listShapeRelationships) {
    this.listShapeRelationships = listShapeRelationships;
  }

  public IButton<AEI> getBtAddShapeRelationship() {
    return btAddShapeRelationship;
  }

  public void setBtAddShapeRelationship(IButton<AEI> btAddShapeRelationship) {
    this.btAddShapeRelationship = btAddShapeRelationship;
  }

  public IButton<AEI> getBtEditShapeRelationship() {
    return btEditShapeRelationship;
  }

  public void setBtEditShapeRelationship(IButton<AEI> btEditShapeRelationship) {
    this.btEditShapeRelationship = btEditShapeRelationship;
  }

  public IButton<AEI> getBtDelShapeRelationship() {
    return btDelShapeRelationship;
  }

  public void setBtDelShapeRelationship(IButton<AEI> btDelShapeRelationship) {
    this.btDelShapeRelationship = btDelShapeRelationship;
  }

  public IFactorySimple<SHR> getFactoryShapeRelationship() {
    return factoryShapeRelationship;
  }

  public void setCmbRelationshipKind(
      IComboBox<ERelationshipKind> cmbRelationshipKind) {
    this.cmbRelationshipKind = cmbRelationshipKind;
  }
}
