package org.beigesoft.uml.service.graphic;

import org.beigesoft.graphic.service.ISrvDraw;
import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.assembly.ShapeFull;
import org.beigesoft.uml.pojo.RectangleRelationship;
import org.beigesoft.uml.pojo.RelationshipPoly;
import org.beigesoft.uml.pojo.ShapeUml;
import org.beigesoft.uml.service.UtilsRectangleRelationship;

public class SrvGraphicRelationshipPolyRectangle<RE extends RelationshipPoly<SHR, SHF, SH>, DRI, SD extends ISettingsDraw, SHR extends RectangleRelationship<SHF, SH>, SHF extends ShapeFull<SH>, SH extends ShapeUml> 
    extends SrvGraphicRelationshipPoly<RE, DRI, SD, SHR, SHF, SH> {
  
  public SrvGraphicRelationshipPolyRectangle(ISrvDraw<DRI, SD, ?> srvDraw, SettingsGraphicUml sg) {
    super(srvDraw, sg);
  }

  @Override
  public void recalculate(RE re, double coefficient) {
    re.getSharedJoint().setX(re.getSharedJoint().getX() * coefficient);
    re.getSharedJoint().setY(re.getSharedJoint().getY() * coefficient);
    for(SHR shr : re.getShapesRelationship()) {
      UtilsRectangleRelationship.recalculate(shr, coefficient);
    }
  }
}
