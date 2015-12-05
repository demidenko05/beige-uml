package org.beigesoft.uml.service.interactive;

import java.util.Set;

import org.beigesoft.graphic.ISrvInteractiveGraphicElement;
import org.beigesoft.graphic.model.EJoint2DType;
import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.graphic.service.UtilsGraphMath;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.ClassFull;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.pojo.RectangleRelationship;
import org.beigesoft.uml.pojo.RelationshipPoly;
import org.beigesoft.uml.pojo.ClassUml;
import org.beigesoft.uml.service.UtilsRectangleRelationship;
import org.beigesoft.uml.service.graphic.SrvGraphicRelationshipPoly;

public class SrvInteractiveRelationshipPoly <RE extends RelationshipPoly<RectangleRelationship<ClassFull<SH>, SH>, ClassFull<SH>, SH>, DLI, SH extends ClassUml> 
    implements ISrvInteractiveGraphicElement<RE> {

  private final IFactoryEditorElementUml<RE, DLI> factoryEditorRelationship;
  
  private final SettingsGraphicUml sg;
  
  private final SrvGraphicRelationshipPoly<RE, ?, ?, ?, ?, ?> srvGraphicRelationshipPoly;
    
  public SrvInteractiveRelationshipPoly(IFactoryEditorElementUml<RE, DLI> factoryEditorRelationship, 
      SettingsGraphicUml sg, SrvGraphicRelationshipPoly<RE, ?, ?, ?, ?, ?> srvGraphicRelationshipPoly) {
    this.factoryEditorRelationship = factoryEditorRelationship;
    this.sg = sg;
    this.srvGraphicRelationshipPoly = srvGraphicRelationshipPoly;
  }

  @Override
  public void move(RE eu, double deltaX, double deltaY) {
    if(eu.getSharedJoint().getTypeJoint() == EJoint2DType.POINT) {
      eu.getSharedJoint().setX(eu.getSharedJoint().getX() + deltaX);
      eu.getSharedJoint().setY(eu.getSharedJoint().getY() + deltaY);
    }
    else if(eu.getSharedJoint().getTypeJoint() == EJoint2DType.BUS_X) {
      eu.getSharedJoint().setY(eu.getSharedJoint().getY() + deltaY);
    }
    else if(eu.getSharedJoint().getTypeJoint() == EJoint2DType.BUS_Y) {
      eu.getSharedJoint().setX(eu.getSharedJoint().getX() + deltaX);
    }
    for(RectangleRelationship<ClassFull<SH>, SH> shr : eu.getShapesRelationship()) {
      UtilsRectangleRelationship.movePointJoint(shr, sg, deltaX, deltaY);
    }
  }

  @Override
  public boolean move(RE eu, int screenWasX, int screenWasY, int screenX,
      int screenY) {
    if(Math.abs(screenX - screenWasX) >= sg.getDraggingStep() 
        || Math.abs(screenY - screenWasY) >= sg.getDraggingStep()) {
      double deltaX = UtilsGraphMath.toRealLenghtX(sg, screenX - screenWasX);
      double deltaY = UtilsGraphMath.toRealLenghtY(sg, screenY - screenWasY);
      if(eu.getSharedJoint().getTypeJoint() == EJoint2DType.POINT) {
        if(UtilsGraphMath.dragRentangleContainsOf(sg, eu.getSharedJoint(), screenWasX, screenWasY)) {
          eu.getSharedJoint().setX(eu.getSharedJoint().getX() + deltaX);
          eu.getSharedJoint().setY(eu.getSharedJoint().getY() + deltaY);
          return true;
        }
      }
      else if(eu.getSharedJoint().getTypeJoint() == EJoint2DType.BUS_X) {
        Point2D dragPoint = new Point2D(eu.getMaxBusCoord() - sg.getWidthDragRectangle()*1.5,
            eu.getSharedJoint().getY());
        if(UtilsGraphMath.dragRentangleContainsOf(sg, dragPoint, screenWasX, screenWasY)) {
          eu.getSharedJoint().setY(eu.getSharedJoint().getY() + deltaY);
          return true;
        }
      }
      else if(eu.getSharedJoint().getTypeJoint() == EJoint2DType.BUS_Y) {
        Point2D dragPoint = new Point2D(eu.getSharedJoint().getX(), eu.getMaxBusCoord() - sg.getWidthDragRectangle()*1.5);
        if(UtilsGraphMath.dragRentangleContainsOf(sg, dragPoint, screenWasX, screenWasY)) {
          eu.getSharedJoint().setX(eu.getSharedJoint().getX() + deltaX);
          return true;
        }
      }
      for(RectangleRelationship<ClassFull<SH>, SH> shr : eu.getShapesRelationship()) {
        if(UtilsGraphMath.dragRentangleContainsOf(sg, shr.getPointJoint(), screenWasX, screenWasY)) {
          UtilsRectangleRelationship.movePointJoint(shr, sg, deltaX, deltaY);
          return true;
        }
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
    // nothing
    return false;
  }

  @Override
  public boolean isContainsScreenPointForManipulate(RE eu, int screenX, int screenY) {
    return srvGraphicRelationshipPoly.isContainsScreenPoint(eu, screenX, screenY);
  }
  
  //SGS:
  public IFactoryEditorElementUml<RE, DLI> getFactoryEditorRelationship() {
    return factoryEditorRelationship;
  }

  public SettingsGraphicUml getSettingsgraphic() {
    return sg;
  }

  public SrvGraphicRelationshipPoly<RE, ?, ?, ?, ?, ?> getSrvGraphicRelationshipPoly() {
    return srvGraphicRelationshipPoly;
  }
}
