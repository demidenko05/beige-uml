package org.beigesoft.uml.service.interactive;

import java.util.Set;

import org.beigesoft.graphic.ISrvInteractiveGraphicElement;
import org.beigesoft.graphic.model.EJoint2DType;
import org.beigesoft.graphic.service.UtilsGraphMath;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.ShapeFull;
import org.beigesoft.uml.container.IContainerShapesFullInteractive;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.pojo.RelationshipBinary;
import org.beigesoft.uml.pojo.ShapeRelationship;
import org.beigesoft.uml.pojo.ClassUml;
import org.beigesoft.uml.service.graphic.SrvGraphicRelationshipBinary;

public class SrvInteractiveRelationshipBinary <RE extends RelationshipBinary<SHR, SHF, SH>, DLI, SHR extends ShapeRelationship<SHF, SH>, SHF extends ShapeFull<SH>, SH extends ClassUml> 
    implements ISrvInteractiveGraphicElement<RE> {

  private final IFactoryEditorElementUml<RE, DLI> factoryEditorRelationship;
  
  private final SettingsGraphicUml sg;
  
  private final SrvGraphicRelationshipBinary<RE, ?, ?> srvGraphicRelationshipBinary;
  
  private IContainerShapesFullInteractive<SHF, SH> containerShapesFull;
  
  public SrvInteractiveRelationshipBinary(IFactoryEditorElementUml<RE, DLI> factoryEditorRelationship, SettingsGraphicUml sg,
      SrvGraphicRelationshipBinary<RE, ?, ?> srvGraphicRelationshipBinary) {
    this.factoryEditorRelationship = factoryEditorRelationship;
    this.sg = sg;
    this.srvGraphicRelationshipBinary = srvGraphicRelationshipBinary;
  }

  @Override
  public void move(RE eu, double deltaX, double deltaY) {
    if(eu.getSharedJoint() != null && eu.getSharedJoint().getTypeJoint() == EJoint2DType.POINT) {
      eu.getSharedJoint().setX(eu.getSharedJoint().getX() + deltaX);
      eu.getSharedJoint().setY(eu.getSharedJoint().getY() + deltaY);
    }
    eu.getShapeRelationshipStart().getPointJoint().setX(eu.getShapeRelationshipStart().getPointJoint().getX() + deltaX);
    eu.getShapeRelationshipStart().getPointJoint().setY(eu.getShapeRelationshipStart().getPointJoint().getY() + deltaY);
    eu.getShapeRelationshipEnd().getPointJoint().setX(eu.getShapeRelationshipEnd().getPointJoint().getX() + deltaX);
    eu.getShapeRelationshipEnd().getPointJoint().setY(eu.getShapeRelationshipEnd().getPointJoint().getY() + deltaY);
  }

  @Override
  public boolean move(RE eu, int screenWasX, int screenWasY, int screenX,
      int screenY) {
    if(Math.abs(screenX - screenWasX) >= getSg().getDraggingStep() 
        || Math.abs(screenY - screenWasY) >= getSg().getDraggingStep()) {
      double deltaX = UtilsGraphMath.toRealLenghtX(getSg(), screenX - screenWasX);
      double deltaY = UtilsGraphMath.toRealLenghtY(getSg(), screenY - screenWasY);
      if(eu.getSharedJoint() != null && eu.getSharedJoint().getTypeJoint() == EJoint2DType.POINT) {
        if(UtilsGraphMath.dragRentangleContainsOf(getSg(), eu.getSharedJoint(), screenWasX, screenWasY)) {
          eu.getSharedJoint().setX(eu.getSharedJoint().getX() + deltaX);
          eu.getSharedJoint().setY(eu.getSharedJoint().getY() + deltaY);
          return true;
        }
      }
      if(UtilsGraphMath.dragRentangleContainsOf(getSg(), eu.getShapeRelationshipStart().getPointJoint(), screenWasX, screenWasY)) {
        eu.getShapeRelationshipStart().getPointJoint().setX(eu.getShapeRelationshipStart().getPointJoint().getX() + deltaX);
        eu.getShapeRelationshipStart().getPointJoint().setY(eu.getShapeRelationshipStart().getPointJoint().getY() + deltaY);
        return true;
      }
      else if(UtilsGraphMath.dragRentangleContainsOf(getSg(), eu.getShapeRelationshipEnd().getPointJoint(), screenWasX, screenWasY)) {
        eu.getShapeRelationshipEnd().getPointJoint().setX(eu.getShapeRelationshipEnd().getPointJoint().getX() + deltaX);
        eu.getShapeRelationshipEnd().getPointJoint().setY(eu.getShapeRelationshipEnd().getPointJoint().getY() + deltaY);
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
    boolean itWasSticking = false;
    SHF shape = null;
    if(eu.getShapeRelationshipStart().getShapeFull() == null && UtilsGraphMath.dragRentangleContainsOf(getSg(), eu.getShapeRelationshipStart().getPointJoint(), screenX, screenY)) {
      shape = containerShapesFull.getShapeAt(screenX, screenY);
      if(shape != null) {
        eu.getShapeRelationshipStart().setShapeFull(shape);
        itWasSticking = true;
      }
    }
    if(eu.getShapeRelationshipEnd().getShapeFull() == null && UtilsGraphMath.dragRentangleContainsOf(getSg(), eu.getShapeRelationshipEnd().getPointJoint(), screenX, screenY)) {
      shape = containerShapesFull.getShapeAt(screenX, screenY);
      if(shape != null) {
        eu.getShapeRelationshipEnd().setShapeFull(shape);
        itWasSticking = true;
      }
    }
    return itWasSticking;
  }

  @Override
  public boolean resize(RE eu, int screenWasX, int screenWasY, int screenX,
      int screenY) {
    return false;
  }

  @Override
  public boolean isContainsScreenPointForManipulate(RE eu, int screenX, int screenY) {
    return srvGraphicRelationshipBinary.isContainsScreenPoint(eu, screenX, screenY);
  }

  public IContainerShapesFullInteractive<SHF, SH> getContainerShapesFull() {
    return containerShapesFull;
  }

  public void setContainerShapesFull(
      IContainerShapesFullInteractive<SHF, SH> containerShapesFull) {
    this.containerShapesFull = containerShapesFull;
  }

  public IFactoryEditorElementUml<RE, DLI> getFactoryEditorRelationship() {
    return factoryEditorRelationship;
  }

  public SettingsGraphicUml getSettingsgraphic() {
    return getSg();
  }

  public SettingsGraphicUml getSg() {
    return sg;
  }

  public SrvGraphicRelationshipBinary<RE, ?, ?> getSrvGraphicRelationshipBinary() {
    return srvGraphicRelationshipBinary;
  }
}
