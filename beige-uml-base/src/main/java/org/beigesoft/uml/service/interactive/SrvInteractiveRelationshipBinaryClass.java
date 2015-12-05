package org.beigesoft.uml.service.interactive;

import org.beigesoft.graphic.service.UtilsGraphMath;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.ClassFull;
import org.beigesoft.uml.assembly.ClassRelationFull;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.pojo.RelationshipBinary;
import org.beigesoft.uml.pojo.RectangleRelationship;
import org.beigesoft.uml.pojo.ClassUml;
import org.beigesoft.uml.service.UtilsRectangleRelationship;
import org.beigesoft.uml.service.graphic.SrvGraphicRelationshipBinary;

public class SrvInteractiveRelationshipBinaryClass<RE extends RelationshipBinary<RectangleRelationship<ClassFull<SH>, SH>, ClassFull<SH>, SH>, DLI, SH extends ClassUml> 
    extends SrvInteractiveRelationshipBinaryRectangle<RE, DLI, RectangleRelationship<ClassFull<SH>, SH>, ClassFull<SH>, SH> {
  
  public SrvInteractiveRelationshipBinaryClass(
      IFactoryEditorElementUml<RE, DLI> factoryEditorRelationship, SettingsGraphicUml sg,
      SrvGraphicRelationshipBinary<RE, ?, ?> srvGraphicRelationshipBinary) {
    super(factoryEditorRelationship, sg, srvGraphicRelationshipBinary);
  }

  @Override
  public boolean handleStopDraggingAt(RE eu, int screenX, int screenY) {
    boolean itWasSticking = false;
    ClassFull<SH> shape = null;
    if(eu.getShapeRelationshipStart().getShapeFull() == null && UtilsGraphMath.dragRentangleContainsOf(getSettingsgraphic(), eu.getShapeRelationshipStart().getPointJoint(), screenX, screenY)) {
      shape = getContainerShapesFull().getShapeAt(screenX, screenY);
      if(shape != null) {
        eu.getShapeRelationshipStart().setShapeFull(shape);
        shape.getRelationshipsBinary().add(new ClassRelationFull<SH>(eu.getShapeRelationshipStart(), eu));
        UtilsRectangleRelationship.setPointJointAt(eu.getShapeRelationshipStart(), getSettingsgraphic(), screenX, screenY);
        itWasSticking = true;
      }
    }
    if(eu.getShapeRelationshipEnd().getShapeFull() == null && UtilsGraphMath.dragRentangleContainsOf(getSettingsgraphic(), eu.getShapeRelationshipEnd().getPointJoint(), screenX, screenY)) {
      shape = getContainerShapesFull().getShapeAt(screenX, screenY);
      if(shape != null) {
        eu.getShapeRelationshipEnd().setShapeFull(shape);
        shape.getRelationshipsBinary().add(new ClassRelationFull<SH>(eu.getShapeRelationshipEnd(), eu));
        UtilsRectangleRelationship.setPointJointAt(eu.getShapeRelationshipEnd(), getSettingsgraphic(), screenX, screenY);
        itWasSticking = true;
      }
    }
    return itWasSticking;
  }
}
