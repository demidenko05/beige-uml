package org.beigesoft.uml.service.graphic;

import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.graphic.service.ISrvDraw;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.pojo.RelationshipBinary;
import org.beigesoft.uml.pojo.RectangleRelationship;
import org.beigesoft.uml.service.UtilsRectangleRelationship;

public class SrvGraphicRelationshipBinaryRectangle<RE extends RelationshipBinary<? extends RectangleRelationship<?, ?>, ?, ?>, DRI, SD extends ISettingsDraw>
    extends SrvGraphicRelationshipBinary<RE, DRI, SD> {

  public SrvGraphicRelationshipBinaryRectangle(ISrvDraw<DRI, SD, ?> srvDraw, SettingsGraphicUml sg) {
    super(srvDraw, sg);
  }

  @Override
  public void recalculate(RE re, double coefficient) {
    super.recalculate(re, coefficient);
    UtilsRectangleRelationship.recalculate(re.getShapeRelationshipStart(), coefficient);
    UtilsRectangleRelationship.recalculate(re.getShapeRelationshipEnd(), coefficient);
  }
}
