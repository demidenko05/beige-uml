package org.beigesoft.uml.service.graphic;

import org.beigesoft.graphic.SettingsGraphic;
import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.graphic.service.ISrvGraphicElement;
import org.beigesoft.uml.assembly.ClassFull;
import org.beigesoft.uml.pojo.ClassUml;

public class SrvGraphicClassFull<CL extends ClassUml, DRI, SD extends ISettingsDraw> 
    implements ISrvGraphicElement<ClassFull<CL>, DRI, SD> {
  
  private final SrvGraphicClass<CL, DRI, SD> graphicClassUmlSrv;
    
  public SrvGraphicClassFull(SrvGraphicClass<CL, DRI, SD> graphicClassUmlSrv) {
    this.graphicClassUmlSrv = graphicClassUmlSrv;
  }


  @Override
  public void draw(ClassFull<CL> ge,
      DRI di, SD ds) {
    graphicClassUmlSrv.draw(ge.getShape(), di, ds);
  }


  @Override
  public void recalculate(ClassFull<CL> ge,
      double coefficient) {
    graphicClassUmlSrv.recalculate(ge.getShape(), coefficient);
  }


  @Override
  public Point2D evalMinimumScreenPoint(
      ClassFull<CL> ge) {
    return graphicClassUmlSrv.evalMinimumScreenPoint(ge.getShape());
  }


  @Override
  public Point2D evalMaximumScreenPoint(
      ClassFull<CL> ge) {
    return graphicClassUmlSrv.evalMaximumScreenPoint(ge.getShape());
  }


  @Override
  public boolean isContainsScreenPoint(
      ClassFull<CL> ge, int x, int y) {
    return graphicClassUmlSrv.isContainsScreenPoint(ge.getShape(), x, y);
  }


  @Override
  public SettingsGraphic getSettingsGraphic() {
     return graphicClassUmlSrv.getSettingsGraphic();
  }
  
  //SGS:
  public SrvGraphicClass<CL, DRI, SD> getGraphicClassUmlSrv() {
    return graphicClassUmlSrv;
  }
}
