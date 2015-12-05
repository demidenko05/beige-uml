package org.beigesoft.uml.assembly;

import java.util.Set;

import org.beigesoft.graphic.ISrvInteractiveGraphicElement;
import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.graphic.pojo.ColorRgb;
import org.beigesoft.graphic.service.ISrvGraphicElement;
import org.beigesoft.service.ISrvPersist;
import org.beigesoft.uml.model.IElementUml;

public class AsmElementUmlInteractive<EU extends IElementUml, DRI, DS extends ISettingsDraw, PRI>
    extends AsmElementUml<EU, DRI, DS, PRI> implements IAsmElementUmlInteractive<EU, DRI, DS, PRI> {
  
  private final ISrvInteractiveGraphicElement<EU> interactiveElementUmlSrv;

  public AsmElementUmlInteractive(EU elementUml, DS drawSettings,
      ISrvGraphicElement<EU, DRI, DS> graphicElementSrv,
      ISrvPersist<EU, PRI> persistSrv,
      ISrvInteractiveGraphicElement<EU> interactiveElementUmlSrv) {
    super(elementUml, drawSettings, graphicElementSrv, persistSrv);
    this.interactiveElementUmlSrv = interactiveElementUmlSrv;
  }

  @Override
  public String toString() {
    return getElementUml().toString();
  }

  @Override
  public void makeIsSelected(boolean isSelected) {
    if(getElementUml().getIsSelected() != isSelected) {
      getElementUml().setIsSelected(isSelected);
      if(isSelected) {
        getSettingsDraw().setColor(ColorRgb.BLUE);
      }
      else {
        getSettingsDraw().setColor(ColorRgb.BLACK);
      }
    }
  }

  @Override
  public void move(double deltaX, double deltaY) {
    interactiveElementUmlSrv.move(getElementUml(), deltaX, deltaY);
  }
  
  @Override
  public boolean isContainsScreenForManipulate(int screenX, int screenY) {
    return interactiveElementUmlSrv.isContainsScreenPointForManipulate(getElementUml(), screenX, screenY);
  }

  @Override
  public boolean move(int screenWasX, int screenWasY, int screenX, int screenY) {
    return interactiveElementUmlSrv.move(getElementUml(), screenWasX, screenWasY, screenX, screenY);
  }

  @Override
  public boolean resize(int screenWasX, int screenWasY, int screenX, int screenY) {
    return interactiveElementUmlSrv.resize(getElementUml(), screenWasX, screenWasY, screenX, screenY);
  }

  @Override
  public void startEdit() {
    interactiveElementUmlSrv.startEdit(getElementUml());
  }

  @Override
  public boolean getIsSelected() {
    return getElementUml().getIsSelected();
  }

  @Override
  public boolean handleStopDraggingAt(int mouseWasAtX, int mouseWasAtY) {
    return interactiveElementUmlSrv.handleStopDraggingAt(getElementUml(), mouseWasAtX, mouseWasAtY);
  }
  
  @Override
  public void validate(Set<String> result) {
    interactiveElementUmlSrv.validate(getElementUml(), result);
  }

  public ISrvInteractiveGraphicElement<EU> getInteractiveElementUmlSrv() {
    return interactiveElementUmlSrv;
  }
}
