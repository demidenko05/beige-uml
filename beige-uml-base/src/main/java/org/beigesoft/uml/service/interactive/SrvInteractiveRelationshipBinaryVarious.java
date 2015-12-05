package org.beigesoft.uml.service.interactive;

import java.util.Set;

import org.beigesoft.graphic.ISrvInteractiveGraphicElement;
import org.beigesoft.graphic.service.UtilsGraphMath;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.ShapeFullVarious;
import org.beigesoft.uml.container.IContainerShapesFullVariousInteractive;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.pojo.RelationshipBinaryVarious;
import org.beigesoft.uml.service.graphic.SrvGraphicRelationshipBinary;

public class SrvInteractiveRelationshipBinaryVarious <RE extends RelationshipBinaryVarious, DLI> 
    implements ISrvInteractiveGraphicElement<RE> {

  private final IFactoryEditorElementUml<RE, DLI> factoryEditor;
  
  private final SettingsGraphicUml sg;
  
  private final SrvGraphicRelationshipBinary<RE, ?, ?> srvGraphicRelationshipBinary;
  
  private IContainerShapesFullVariousInteractive<ShapeFullVarious<?>> containerShapesFull;
  
  public SrvInteractiveRelationshipBinaryVarious(IFactoryEditorElementUml<RE, DLI> factoryEditor, 
      SettingsGraphicUml sg, SrvGraphicRelationshipBinary<RE, ?, ?> srvGraphicRelationshipBinary) {
    this.factoryEditor = factoryEditor;
    this.srvGraphicRelationshipBinary = srvGraphicRelationshipBinary;
    this.sg = sg;
  }
  
  @Override
  public void move(RE eu, double deltaX, double deltaY) {
    eu.getShapeRelationshipStart().getPointJoint().setX(eu.getShapeRelationshipStart().getPointJoint().getX() + deltaX);
    eu.getShapeRelationshipStart().getPointJoint().setY(eu.getShapeRelationshipStart().getPointJoint().getY() + deltaY);
    eu.getShapeRelationshipEnd().getPointJoint().setX(eu.getShapeRelationshipEnd().getPointJoint().getX() + deltaX);
    eu.getShapeRelationshipEnd().getPointJoint().setY(eu.getShapeRelationshipEnd().getPointJoint().getY() + deltaY);
  }

  @Override
  public boolean move(RE eu, int screenWasX, int screenWasY, int screenX,
      int screenY) {
    if(Math.abs(screenX - screenWasX) >= sg.getDraggingStep() 
        || Math.abs(screenY - screenWasY) >= sg.getDraggingStep()) {
      double deltaX = UtilsGraphMath.toRealLenghtX(sg, screenX - screenWasX);
      double deltaY = UtilsGraphMath.toRealLenghtY(sg, screenY - screenWasY);
      if(UtilsGraphMath.dragRentangleContainsOf(sg, eu.getShapeRelationshipStart().getPointJoint(), screenWasX, screenWasY)) {
        eu.getShapeRelationshipStart().getPointJoint().setX(eu.getShapeRelationshipStart().getPointJoint().getX() + deltaX);
        eu.getShapeRelationshipStart().getPointJoint().setY(eu.getShapeRelationshipStart().getPointJoint().getY() + deltaY);
        return true;
      }
      else if(UtilsGraphMath.dragRentangleContainsOf(sg, eu.getShapeRelationshipEnd().getPointJoint(), screenWasX, screenWasY)) {
        eu.getShapeRelationshipEnd().getPointJoint().setX(eu.getShapeRelationshipEnd().getPointJoint().getX() + deltaX);
        eu.getShapeRelationshipEnd().getPointJoint().setY(eu.getShapeRelationshipEnd().getPointJoint().getY() + deltaY);
        return true;
      }
    }
    return false;
  }

  @Override
  public void startEdit(RE eu) {
    factoryEditor.lazyGetEditorElementUml().startEdit(eu);
  }

  @Override
  public void validate(RE eu, Set<String> result) {
    factoryEditor.lazyGetSrvEditElementUml().validate(eu, result);
  }

  @Override
  public boolean handleStopDraggingAt(RE eu, int screenX, int screenY) {
    boolean itWasSticking = false;
    ShapeFullVarious<?> shape = null;
    if(eu.getShapeRelationshipStart().getShapeFull() == null && UtilsGraphMath.dragRentangleContainsOf(sg, eu.getShapeRelationshipStart().getPointJoint(), screenX, screenY)) {
      shape = containerShapesFull.getShapeFullAt(screenX, screenY);
      if(shape != null) {
        eu.getShapeRelationshipStart().setShapeFull(shape);
        shape.getRelationshipsVariousStart().add(eu.getShapeRelationshipStart());
        itWasSticking = true;
      }
    }
    if(eu.getShapeRelationshipEnd().getShapeFull() == null && UtilsGraphMath.dragRentangleContainsOf(sg, eu.getShapeRelationshipEnd().getPointJoint(), screenX, screenY)) {
      shape = containerShapesFull.getShapeFullAt(screenX, screenY);
      if(shape != null) {
        eu.getShapeRelationshipEnd().setShapeFull(shape);
        shape.getRelationshipsVariousEnd().add(eu.getShapeRelationshipEnd());
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

  //SGS:
  public IContainerShapesFullVariousInteractive<ShapeFullVarious<?>> getContainerShapesFull() {
    return containerShapesFull;
  }

  public void setContainerShapesFull(
      IContainerShapesFullVariousInteractive<ShapeFullVarious<?>> containerShapesFull) {
    this.containerShapesFull = containerShapesFull;
  }
}
