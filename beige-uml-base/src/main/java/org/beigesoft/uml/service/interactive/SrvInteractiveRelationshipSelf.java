package org.beigesoft.uml.service.interactive;

import java.util.Set;

import org.beigesoft.graphic.ISrvInteractiveGraphicElement;
import org.beigesoft.graphic.service.UtilsGraphMath;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.ShapeFull;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.pojo.RelationshipSelf;
import org.beigesoft.uml.pojo.RectangleRelationship;
import org.beigesoft.uml.pojo.ClassUml;
import org.beigesoft.uml.service.UtilsRectangleRelationship;
import org.beigesoft.uml.service.graphic.ASrvGraphicRelationship;

public class SrvInteractiveRelationshipSelf <RE extends RelationshipSelf<SHR, SHF, SH>, DLI, SHR extends RectangleRelationship<SHF, SH>, SHF extends ShapeFull<SH>, SH extends ClassUml> 
    implements ISrvInteractiveGraphicElement<RE> {

  private final IFactoryEditorElementUml<RE, DLI> factoryEditorRelationship;
  
  private final SettingsGraphicUml sg;
  
  private final ASrvGraphicRelationship<RE, ?, ?> srvGraphicRelationship;
    
  public SrvInteractiveRelationshipSelf(IFactoryEditorElementUml<RE, DLI> factoryEditorRelationship, SettingsGraphicUml sg,
      ASrvGraphicRelationship<RE, ?, ?> srvGraphicRelationship) {
    this.factoryEditorRelationship = factoryEditorRelationship;
    this.sg = sg;
    this.srvGraphicRelationship = srvGraphicRelationship;
  }

  @Override
  public void move(RE eu, double deltaX, double deltaY) {
    eu.getSharedJoint().setX(eu.getSharedJoint().getX() + deltaX);
    eu.getSharedJoint().setY(eu.getSharedJoint().getY() + deltaY);
    UtilsRectangleRelationship.evalPointsJointAndShared(eu);
  }

  @Override
  public boolean move(RE eu, int screenWasX, int screenWasY, int screenX,
      int screenY) {
    if(Math.abs(screenX - screenWasX) >= sg.getDraggingStep() 
        || Math.abs(screenY - screenWasY) >= sg.getDraggingStep()) {
      double deltaX = UtilsGraphMath.toRealLenghtX(sg, screenX - screenWasX);
      double deltaY = UtilsGraphMath.toRealLenghtY(sg, screenY - screenWasY);
      if(UtilsGraphMath.dragRentangleContainsOf(sg, eu.getSharedJoint(), screenWasX, screenWasY)) {
        eu.getSharedJoint().setX(eu.getSharedJoint().getX() + deltaX);
        eu.getSharedJoint().setY(eu.getSharedJoint().getY() + deltaY);
        UtilsRectangleRelationship.evalPointsJointAndShared(eu);
        return true;
      }
      if(UtilsGraphMath.dragRentangleContainsOf(getSettingsgraphic(), eu.getShapeRelationshipStart().getPointJoint(), screenWasX, screenWasY)) {
        UtilsRectangleRelationship.movePointJointAlongSide(eu.getShapeRelationshipStart(), getSettingsgraphic(), deltaX, deltaY);
        UtilsRectangleRelationship.evalPointsJointAndShared(eu);
        return true;
      }
      else if(UtilsGraphMath.dragRentangleContainsOf(getSettingsgraphic(), eu.getShapeRelationshipEnd().getPointJoint(), screenWasX, screenWasY)) {
        UtilsRectangleRelationship.movePointJointAlongSide(eu.getShapeRelationshipEnd(), getSettingsgraphic(), deltaX, deltaY);
        UtilsRectangleRelationship.evalPointsJointAndShared(eu);
        return true;
      }
    }
    return false;
  }

  @Override
  public void startEdit(RE eu) {
    factoryEditorRelationship.lazyGetEditorElementUml().startEdit(eu);
  }

  @Override
  public void validate(RE eu, Set<String> result) {
    factoryEditorRelationship.lazyGetSrvEditElementUml().validate(eu, result);
  }

  @Override
  public boolean handleStopDraggingAt(RE eu, int screenX, int screenY) {
    // nothing
    return false;
  }

  @Override
  public boolean resize(RE eu, int screenWasX, int screenWasY, int screenX,
      int screenY) {
    return false;
  }

  @Override
  public boolean isContainsScreenPointForManipulate(RE eu, int screenX, int screenY) {
    return srvGraphicRelationship.isContainsScreenPoint(eu, screenX, screenY);
  }

  //SGS:
  public IFactoryEditorElementUml<RE, DLI> getFactoryEditorRelationship() {
    return factoryEditorRelationship;
  }

  public SettingsGraphicUml getSettingsgraphic() {
    return sg;
  }

  public ASrvGraphicRelationship<RE, ?, ?> getSrvGraphicRelationship() {
    return srvGraphicRelationship;
  }
}
