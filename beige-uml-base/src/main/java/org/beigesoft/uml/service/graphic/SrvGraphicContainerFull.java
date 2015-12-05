package org.beigesoft.uml.service.graphic;

import org.beigesoft.graphic.SettingsGraphic;
import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.graphic.service.ISrvGraphicElement;
import org.beigesoft.uml.assembly.ContainerFull;
import org.beigesoft.uml.model.IContainerUml;

public class SrvGraphicContainerFull<CNT extends IContainerUml, DRI, SD extends ISettingsDraw> 
    implements ISrvGraphicElement<ContainerFull<CNT>, DRI, SD> {

  private final ASrvGraphicShapeUml<CNT, DRI, SD> srvGraphicContainer; 
  
  public SrvGraphicContainerFull(
      ASrvGraphicShapeUml<CNT, DRI, SD> srvGraphicContainer) {
    this.srvGraphicContainer = srvGraphicContainer;
  }

  @Override
  public void draw(ContainerFull<CNT> graphicElement, DRI drawInstrument,
      SD drawSettings) {
    srvGraphicContainer.draw(graphicElement.getContainer(), drawInstrument, drawSettings);
  }

  @Override
  public void recalculate(ContainerFull<CNT> graphicElement, double coefficient) {
    srvGraphicContainer.recalculate(graphicElement.getContainer(), coefficient);
  }

  @Override
  public Point2D evalMinimumScreenPoint(ContainerFull<CNT> graphicElement) {
    return srvGraphicContainer.evalMinimumScreenPoint(graphicElement.getContainer());
  }

  @Override
  public Point2D evalMaximumScreenPoint(ContainerFull<CNT> graphicElement) {
    return srvGraphicContainer.evalMaximumScreenPoint(graphicElement.getContainer());
  }

  @Override
  public boolean isContainsScreenPoint(ContainerFull<CNT> graphicElement,
      int x, int y) {
    return srvGraphicContainer.isContainsScreenPoint(graphicElement.getContainer(), x, y);
  }

  @Override
  public SettingsGraphic getSettingsGraphic() {
    return srvGraphicContainer.getSettingsGraphic();
  }

  //SGS:
  public ASrvGraphicShapeUml<CNT, DRI, SD> getSrvGraphicContainer() {
    return srvGraphicContainer;
  }
}
