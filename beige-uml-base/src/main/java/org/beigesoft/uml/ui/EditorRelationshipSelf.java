package org.beigesoft.uml.ui;

import org.beigesoft.ui.widget.AEditor;
import org.beigesoft.ui.widget.IButton;
import org.beigesoft.ui.widget.IComboBox;
import org.beigesoft.ui.widget.IRadioButton;
import org.beigesoft.ui.widget.ITextField;
import org.beigesoft.uml.assembly.ShapeFull;
import org.beigesoft.uml.model.EJointSide;
import org.beigesoft.uml.model.ERelationshipEndType;
import org.beigesoft.uml.model.ERelationshipKind;
import org.beigesoft.uml.pojo.RectangleRelationship;
import org.beigesoft.uml.pojo.RelationshipSelf;
import org.beigesoft.uml.pojo.ShapeUml;
import org.beigesoft.uml.service.UtilsRectangleRelationship;
import org.beigesoft.uml.service.edit.SrvEditRelationshipSelf;

public class EditorRelationshipSelf<M extends RelationshipSelf<SHR, SHF, SH>, DLI, AEI,
  SHR extends RectangleRelationship<SHF, SH>, SHF extends ShapeFull<SH>, SH extends ShapeUml> 
    extends AEditor<M, DLI, AEI> {
  
  private IComboBox<ERelationshipKind> cmbRelationshipKind;

  private ITextField tfShape;

  private IComboBox<ERelationshipEndType> cmbRelationshipEnd1;
  
  private IComboBox<ERelationshipEndType> cmbRelationshipEnd2;
  
  private IRadioButton<AEI> btLeftTop;

  private IRadioButton<AEI> btTopRight;

  private IRadioButton<AEI> btRightBottom;

  private IRadioButton<AEI> btBottomLeft;

  public EditorRelationshipSelf(DLI dli, SrvEditRelationshipSelf<M, DLI, SHR, SHF, SH> srvEdit, String modelName) {
    super(dli, srvEdit, modelName);
  }

  @Override
  public void doPostConstruct() {
    btLeftTop.setText(getSrvEdit().getSrvI18n().getMsg("left_top"));
    btTopRight.setText(getSrvEdit().getSrvI18n().getMsg("top_right"));
    btRightBottom.setText(getSrvEdit().getSrvI18n().getMsg("right_bottom"));
    btBottomLeft.setText(getSrvEdit().getSrvI18n().getMsg("bottom_left"));
    super.doPostConstruct();
  }

  @Override
  public boolean makeAction(AEI eventInstrument) {
    if(super.makeAction(eventInstrument)) {
      return true;
    }
    if(btLeftTop.isPushed(eventInstrument)) {
      if(getModelClone().getShapeRelationshipStart().getSideJoint() != EJointSide.LEFT) {
        getModelClone().getShapeRelationshipStart().setSideJoint(EJointSide.LEFT);
        getModelClone().getShapeRelationshipStart().setSideLength(getModelClone().getShapeRelationshipStart().getShape().getHeight()/2);
        getModelClone().getShapeRelationshipEnd().setSideJoint(EJointSide.TOP);
        getModelClone().getShapeRelationshipEnd().setSideLength(getModelClone().getShapeRelationshipStart().getShape().getWidth()/2);
        getModelClone().getSharedJoint().setX(getModelClone().getShapeRelationshipStart().getShape().getPointStart().getX() - getModelClone().getShapeRelationshipStart().getShape().getWidth()/4);
        getModelClone().getSharedJoint().setY(getModelClone().getShapeRelationshipStart().getShape().getPointStart().getY() - getModelClone().getShapeRelationshipStart().getShape().getHeight()/4);
        UtilsRectangleRelationship.evalPointsJointAndShared(getModelClone());
      }
      return true;
    }
    if(btTopRight.isPushed(eventInstrument)) {
      if(getModelClone().getShapeRelationshipStart().getSideJoint() != EJointSide.TOP) {
        getModelClone().getShapeRelationshipStart().setSideJoint(EJointSide.TOP);
        getModelClone().getShapeRelationshipStart().setSideLength(getModelClone().getShapeRelationshipStart().getShape().getWidth()/2);
        getModelClone().getShapeRelationshipEnd().setSideJoint(EJointSide.RIGHT);
        getModelClone().getShapeRelationshipEnd().setSideLength(getModelClone().getShapeRelationshipStart().getShape().getHeight()/2);
        getModelClone().getSharedJoint().setX(getModelClone().getShapeRelationshipStart().getShape().getPointStart().getX() + getModelClone().getShapeRelationshipStart().getShape().getWidth()*1.4);
        getModelClone().getSharedJoint().setY(getModelClone().getShapeRelationshipStart().getShape().getPointStart().getY() - getModelClone().getShapeRelationshipStart().getShape().getHeight()/4);
        UtilsRectangleRelationship.evalPointsJointAndShared(getModelClone());
      }
      return true;
    }
    if(btRightBottom.isPushed(eventInstrument)) {
      if(getModelClone().getShapeRelationshipStart().getSideJoint() != EJointSide.RIGHT) {
        getModelClone().getShapeRelationshipStart().setSideJoint(EJointSide.RIGHT);
        getModelClone().getShapeRelationshipStart().setSideLength(getModelClone().getShapeRelationshipStart().getShape().getHeight()/2);
        getModelClone().getShapeRelationshipEnd().setSideJoint(EJointSide.BOTTOM);
        getModelClone().getShapeRelationshipEnd().setSideLength(getModelClone().getShapeRelationshipStart().getShape().getWidth()/2);
        getModelClone().getSharedJoint().setX(getModelClone().getShapeRelationshipStart().getShape().getPointStart().getX() + getModelClone().getShapeRelationshipStart().getShape().getWidth()*1.4);
        getModelClone().getSharedJoint().setY(getModelClone().getShapeRelationshipStart().getShape().getPointStart().getY() + getModelClone().getShapeRelationshipStart().getShape().getHeight()*1.4);
        UtilsRectangleRelationship.evalPointsJointAndShared(getModelClone());
      }
      return true;
    }
    if(btBottomLeft.isPushed(eventInstrument)) {
      if(getModelClone().getShapeRelationshipStart().getSideJoint() != EJointSide.BOTTOM) {
        getModelClone().getShapeRelationshipStart().setSideJoint(EJointSide.BOTTOM);
        getModelClone().getShapeRelationshipStart().setSideLength(getModelClone().getShapeRelationshipStart().getShape().getWidth()/2);
        getModelClone().getShapeRelationshipEnd().setSideJoint(EJointSide.LEFT);
        getModelClone().getShapeRelationshipEnd().setSideLength(getModelClone().getShapeRelationshipStart().getShape().getHeight()/2);
        getModelClone().getSharedJoint().setX(getModelClone().getShapeRelationshipStart().getShape().getPointStart().getX() - getModelClone().getShapeRelationshipStart().getShape().getWidth()/4);
        getModelClone().getSharedJoint().setY(getModelClone().getShapeRelationshipStart().getShape().getPointStart().getY() + getModelClone().getShapeRelationshipStart().getShape().getHeight()*1.4);
        UtilsRectangleRelationship.evalPointsJointAndShared(getModelClone());
      }
      return true;
    }
    return false;
  }
  
  @Override
  public void refreshModel() throws Exception {
    getModel().setKind(getModelClone().getItsKind());
    getModel().setSharedJoint(getModelClone().getSharedJoint());
    getModel().getShapeRelationshipStart().setEndType(getModelClone().getShapeRelationshipStart().getEndType());
    getModel().getShapeRelationshipStart().setSideJoint(getModelClone().getShapeRelationshipStart().getSideJoint());
    getModel().getShapeRelationshipStart().setSideLength(getModelClone().getShapeRelationshipStart().getSideLength());
    getModel().getShapeRelationshipEnd().setEndType(getModelClone().getShapeRelationshipEnd().getEndType());
    getModel().getShapeRelationshipEnd().setSideJoint(getModelClone().getShapeRelationshipEnd().getSideJoint());
    getModel().getShapeRelationshipEnd().setSideLength(getModelClone().getShapeRelationshipEnd().getSideLength());
    super.refreshModel();
  }

  @Override
  public void refreshModelClone() {
    getModelClone().setKind((ERelationshipKind) cmbRelationshipKind.getSelectedItem());
    getModelClone().getShapeRelationshipStart().setEndType((ERelationshipEndType) cmbRelationshipEnd1.getSelectedItem());
    getModelClone().getShapeRelationshipEnd().setEndType((ERelationshipEndType) cmbRelationshipEnd2.getSelectedItem());
  }

  @Override
  public void refreshGui() {
    cmbRelationshipKind.setSelectedItem(getModelClone().getItsKind());
    cmbRelationshipEnd1.setSelectedItem(getModelClone().getShapeRelationshipStart().getEndType());
    cmbRelationshipEnd2.setSelectedItem(getModelClone().getShapeRelationshipEnd().getEndType());
    tfShape.setText(getModelClone().getShapeRelationshipStart().getShape() == null ? "" :
      getModelClone().getShapeRelationshipStart().getShape().toString());
    if(getModelClone().getShapeRelationshipStart().getSideJoint() == EJointSide.LEFT) {
      btLeftTop.setIsSelected(true);
    }
    else if(getModelClone().getShapeRelationshipStart().getSideJoint() == EJointSide.TOP) {
      btTopRight.setIsSelected(true);
    }
    else if(getModelClone().getShapeRelationshipStart().getSideJoint() == EJointSide.RIGHT) {
      btRightBottom.setIsSelected(true);
    }
    else if(getModelClone().getShapeRelationshipStart().getSideJoint() == EJointSide.BOTTOM) {
      btBottomLeft.setIsSelected(true);
    }
  }

  //SGS:
  public IComboBox<ERelationshipKind> getCmbRelationshipKind() {
    return cmbRelationshipKind;
  }

  public void setCmbRelationshipKind(
      IComboBox<ERelationshipKind> cmbRelationshipKind) {
    this.cmbRelationshipKind = cmbRelationshipKind;
  }

  public IComboBox<ERelationshipEndType> getCmbRelationshipEnd1() {
    return cmbRelationshipEnd1;
  }

  public void setCmbRelationshipEnd1(
      IComboBox<ERelationshipEndType> cmbRelationshipEnd1) {
    this.cmbRelationshipEnd1 = cmbRelationshipEnd1;
  }

  public IComboBox<ERelationshipEndType> getCmbRelationshipEnd2() {
    return cmbRelationshipEnd2;
  }

  public void setCmbRelationshipEnd2(
      IComboBox<ERelationshipEndType> cmbRelationshipEnd2) {
    this.cmbRelationshipEnd2 = cmbRelationshipEnd2;
  }

  public IButton<AEI> getBtLeftTop() {
    return btLeftTop;
  }

  public void setBtLeftTop(IRadioButton<AEI> btLeftTop) {
    this.btLeftTop = btLeftTop;
  }

  public IButton<AEI> getBtTopRight() {
    return btTopRight;
  }

  public void setBtTopRight(IRadioButton<AEI> btTopRight) {
    this.btTopRight = btTopRight;
  }

  public IButton<AEI> getBtRightBottom() {
    return btRightBottom;
  }

  public void setBtRightBottom(IRadioButton<AEI> btRightBottom) {
    this.btRightBottom = btRightBottom;
  }

  public IButton<AEI> getBtBottomLeft() {
    return btBottomLeft;
  }

  public void setBtBottomLeft(IRadioButton<AEI> btBottomLeft) {
    this.btBottomLeft = btBottomLeft;
  }

  public ITextField getTfShape() {
    return tfShape;
  }

  public void setTfShape(ITextField tfShape) {
    this.tfShape = tfShape;
  }
}
