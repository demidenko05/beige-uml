package org.beigesoft.uml.service.interactive;

import org.beigesoft.graphic.service.UtilsGraphMath;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.ShapeFull;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.pojo.RelationshipBinary;
import org.beigesoft.uml.pojo.RectangleRelationship;
import org.beigesoft.uml.pojo.ClassUml;
import org.beigesoft.uml.service.UtilsRectangleRelationship;
import org.beigesoft.uml.service.graphic.SrvGraphicRelationshipBinary;

public class SrvInteractiveRelationshipBinaryRectangle <RE extends RelationshipBinary<SHR, SHF, SH>, DLI, SHR extends RectangleRelationship<SHF, SH>, SHF extends ShapeFull<SH>, SH extends ClassUml> 
    extends SrvInteractiveRelationshipBinary<RE, DLI, SHR, SHF, SH> {

  public SrvInteractiveRelationshipBinaryRectangle(
      IFactoryEditorElementUml<RE, DLI> factoryEditorRelationship, SettingsGraphicUml sg,
      SrvGraphicRelationshipBinary<RE, ?, ?> srvGraphicRelationshipBinary) {
    super(factoryEditorRelationship, sg, srvGraphicRelationshipBinary);
  }

  @Override
  public boolean move(RE eu, int screenWasX, int screenWasY, int screenX,
      int screenY) {
    if(Math.abs(screenX - screenWasX) >= getSg().getDraggingStep() 
        || Math.abs(screenY - screenWasY) >= getSg().getDraggingStep()) {
      double deltaX = UtilsGraphMath.toRealLenghtX(getSettingsgraphic(), screenX - screenWasX);
      double deltaY = UtilsGraphMath.toRealLenghtY(getSettingsgraphic(), screenY - screenWasY);
      if(tryToMovePointJoint(eu.getShapeRelationshipStart(), screenWasX, screenWasY, deltaX, deltaY)) {
        return true;
      }
      else if(tryToMovePointJoint(eu.getShapeRelationshipEnd(), screenWasX, screenWasY, deltaX, deltaY)) {
        return true;
      }
    }
    return false;
  }
  
  @Override
  public boolean handleStopDraggingAt(RE eu, int screenX, int screenY) {
    boolean itWasSticking = false;
    SHF shape = null;
    if(eu.getShapeRelationshipStart().getShapeFull() == null && UtilsGraphMath.dragRentangleContainsOf(getSettingsgraphic(), eu.getShapeRelationshipStart().getPointJoint(), screenX, screenY)) {
      shape = getContainerShapesFull().getShapeAt(screenX, screenY);
      if(shape != null) {
        eu.getShapeRelationshipStart().setShapeFull(shape);
        UtilsRectangleRelationship.setPointJointAt(eu.getShapeRelationshipStart(), getSettingsgraphic(), screenX, screenY);
        itWasSticking = true;
      }
    }
    if(eu.getShapeRelationshipEnd().getShapeFull() == null && UtilsGraphMath.dragRentangleContainsOf(getSettingsgraphic(), eu.getShapeRelationshipEnd().getPointJoint(), screenX, screenY)) {
      shape = getContainerShapesFull().getShapeAt(screenX, screenY);
      if(shape != null) {
        eu.getShapeRelationshipEnd().setShapeFull(shape);
        UtilsRectangleRelationship.setPointJointAt(eu.getShapeRelationshipEnd(), getSettingsgraphic(), screenX, screenY);
        itWasSticking = true;
      }
    }
    return itWasSticking;
  }

  protected boolean tryToMovePointJoint(SHR shr, int screenWasX, int screenWasY, double deltaX, double deltaY) {
    if(UtilsGraphMath.dragRentangleContainsOf(getSettingsgraphic(), shr.getPointJoint(), screenWasX, screenWasY)) {
      if(shr.getShape() != null) {
        UtilsRectangleRelationship.movePointJoint(shr, getSettingsgraphic(), deltaX, deltaY);
      }
      else {
        shr.getPointJoint().setX(shr.getPointJoint().getX() + deltaX);
        shr.getPointJoint().setY(shr.getPointJoint().getY() + deltaY);
      }
      return true;
    }
    return false;
  }
}
