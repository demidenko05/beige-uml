package org.beigesoft.uml.service.interactive;

import java.util.Set;

import org.beigesoft.graphic.ISrvInteractiveGraphicElement;
import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.graphic.service.UtilsGraphMath;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.pojo.TextUml;
import org.beigesoft.uml.service.graphic.SrvGraphicText;

public class SrvInteractiveText<TX extends TextUml, DRI, DS extends ISettingsDraw, DLI> 
    implements ISrvInteractiveGraphicElement<TX> {
  
  private final SrvGraphicText<TX, DRI, DS> graphicTextumlSrv;
  
  private final IFactoryEditorElementUml<TX, DLI> factoryEditorTextUml;
  
  public SrvInteractiveText(SrvGraphicText<TX, DRI, DS> graphicTextumlSrv,
      IFactoryEditorElementUml<TX, DLI> factoryEditorTextUml) {
     this.graphicTextumlSrv = graphicTextumlSrv;
     this.factoryEditorTextUml = factoryEditorTextUml;
  }

  @Override
  public void move(TX ge, double deltaX, double deltaY) {
    ge.getPointStart().setX(ge.getPointStart().getX() + deltaX);
    ge.getPointStart().setY(ge.getPointStart().getY() + deltaY);
  }

  @Override
  public boolean move(TX ge, int screenWasX, int screenWasY, int screenX,
      int screenY) {
    if(Math.abs(screenX - screenWasX) >= graphicTextumlSrv.getSettingsGraphic().getDraggingStep() 
        || Math.abs(screenY - screenWasY) >= graphicTextumlSrv.getSettingsGraphic().getDraggingStep()) {
      double deltaX = UtilsGraphMath.toRealLenghtX(graphicTextumlSrv.getSettingsGraphic(), screenX - screenWasX);
      double deltaY = UtilsGraphMath.toRealLenghtY(graphicTextumlSrv.getSettingsGraphic(), screenY - screenWasY);
      if(graphicTextumlSrv.isContainsScreenPoint(ge, screenWasX, screenWasY)) {
        ge.getPointStart().setX(ge.getPointStart().getX() + deltaX);
        ge.getPointStart().setY(ge.getPointStart().getY() + deltaY);
        return true;
      }
    }
    return false;
  }

  @Override
  public void startEdit(TX ge) {
    factoryEditorTextUml.lazyGetEditorElementUml().startEdit(ge);
  }

  @Override
  public void validate(TX eu, Set<String> result) {
    factoryEditorTextUml.lazyGetSrvEditElementUml().validate(eu, result);
  }

  @Override
  public boolean handleStopDraggingAt(TX ge, int mouseWasAtX, int mouseWasAtY) {
    // nothing
    return false;
  }

  @Override
  public boolean resize(TX eu, int screenWasX, int screenWasY, int screenX,
      int screenY) {
    // nothing
    return false;
  }

  @Override
  public boolean isContainsScreenPointForManipulate(TX eu, int screenX, int screenY) {
    return graphicTextumlSrv.isContainsScreenPoint(eu, screenX, screenY);
  }

  //SGS:
  public SrvGraphicText<TX, DRI, DS> getGraphicTextumlSrv() {
    return graphicTextumlSrv;
  }
  
  public IFactoryEditorElementUml<TX, DLI> getFactoryEditorTextUml() {
    return factoryEditorTextUml;
  }
}
