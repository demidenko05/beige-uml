package org.beigesoft.uml.service.persist.xmllight;

import java.util.List;

import org.beigesoft.uml.assembly.ShapeFull;
import org.beigesoft.uml.model.EJointSide;
import org.beigesoft.uml.pojo.RectangleRelationship;
import org.beigesoft.uml.pojo.ShapeUml;

public class SaxRectangleRelationshipFiller<P extends RectangleRelationship<SHF, SH>, SHF extends ShapeFull<SH>, SH extends ShapeUml> 
    extends SaxShapeRelationshipFiller<P, SHF, SH> {

  public SaxRectangleRelationshipFiller(String namePersistable,
      List<String> pathCurrent) {
    super(namePersistable, pathCurrent);
  }

  @Override
  public boolean fillModel(String elementName, String elementValue) {
    if(isConsumableForElement(elementName)) {
      if(super.fillModel(elementName, elementValue)) {
        return true;
      }
      if(SrvSaveXmlRectangleRelationship.NAMEXML_SIDEJOINT.equals(elementName)) {
        getModel().setSideJoint(EJointSide.valueOf(elementValue));
        return true;
      }
      if(SrvSaveXmlRectangleRelationship.NAMEXML_SIDELENGTH.equals(elementName)) {
        getModel().setSideLength(Double.valueOf(elementValue));
        return true;
      }
    }
    return false;
  }
}
