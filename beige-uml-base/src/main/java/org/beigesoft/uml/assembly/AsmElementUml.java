package org.beigesoft.uml.assembly;

import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.graphic.service.ISrvGraphicElement;
import org.beigesoft.service.ISrvPersist;
import org.beigesoft.uml.model.IElementUml;

public class AsmElementUml<EU extends IElementUml, DRI, SD extends ISettingsDraw, PRI> 
    implements IAsmElementUml<EU, DRI, SD, PRI> {

  private final EU elementUml;

  private final SD drawSettings;

  private final ISrvGraphicElement<EU, DRI, SD> graphicElementSrv;
  
  private final ISrvPersist <EU, PRI> persistSrv;
  
  private boolean isVisible = true;
  
  public AsmElementUml(EU elementUml, SD drawSettings,
      ISrvGraphicElement<EU, DRI, SD> graphicElementSrv,
      ISrvPersist<EU, PRI> persistSrv) {
    this.elementUml = elementUml;
    this.drawSettings = drawSettings;
    this.graphicElementSrv = graphicElementSrv;
    this.persistSrv = persistSrv;
  }

  @Override
  public Integer getIndexZ() {
    return elementUml.getIndexZ();
  }

  @Override
  public void setIndexZ(Integer indexZ) {
    elementUml.setIndexZ(indexZ);
  }

  @Override
  public void draw(DRI drawHelper) {
    graphicElementSrv.draw(elementUml, drawHelper, drawSettings);
  }

  @Override
  public void recalculate(double coefficient) {
    graphicElementSrv.recalculate(elementUml, coefficient);
  }

  @Override
  public Point2D evalMinimumScreenPoint() {
    return graphicElementSrv.evalMinimumScreenPoint(elementUml);
  }

  @Override
  public Point2D evalMaximumScreenPoint() {
    return graphicElementSrv.evalMaximumScreenPoint(elementUml);
  }

  @Override
  public boolean isContainsScreenPoint(int x, int y) {
    return graphicElementSrv.isContainsScreenPoint(elementUml, x, y);
  }

  @Override
  public EU getElementUml() {
    return elementUml;
  }

  @Override
  public void persist(PRI persistInstrument) throws Exception {
    persistSrv.persist(elementUml, persistInstrument);
  }

  @Override
  public void restore(PRI persistInstrument) throws Exception {
    persistSrv.restore(elementUml, persistInstrument);
  }

  @Override
  public SD getSettingsDraw() {
    return drawSettings;
  }

  @Override
  public boolean getIsVisible() {
    return isVisible;
  }

  @Override
  public void setIsVisible(boolean isVisible) {
    this.isVisible = isVisible;
  }
}
