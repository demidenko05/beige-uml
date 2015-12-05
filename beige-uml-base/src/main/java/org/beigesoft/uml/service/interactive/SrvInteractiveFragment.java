package org.beigesoft.uml.service.interactive;

import java.util.Set;

import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.graphic.service.UtilsGraphMath;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.model.IFragment;
import org.beigesoft.uml.service.graphic.ASrvGraphicShapeUml;

public class SrvInteractiveFragment<M extends IFragment, DRI, SD extends ISettingsDraw, DLI> 
    extends SrvInteractiveShapeUml<M, DRI, SD> {

  private final IFactoryEditorElementUml<M, DLI> factoryEditorElementUml;
  
  public SrvInteractiveFragment(ASrvGraphicShapeUml<M, DRI, SD> srvGraphicShape, 
      IFactoryEditorElementUml<M, DLI> factoryEditorElementUml) {
    super(srvGraphicShape);
    this.factoryEditorElementUml = factoryEditorElementUml;
  }

  @Override
  public boolean isContainsScreenPointForManipulate(M ge, int x, int y) {
    double realX = UtilsGraphMath.toRealX(getSrvGraphicShape().getSettingsGraphic(), x);
    double realY = UtilsGraphMath.toRealY(getSrvGraphicShape().getSettingsGraphic(), y);
    if(realX >= ge.getPointStart().getX() && realX <= ge.getPointStart().getX() + ge.getWidthHead() && 
        realY >= ge.getPointStart().getY() && realY <= ge.getPointStart().getY() + ge.getHeightHead()) {
      return true;
    }
    if(UtilsGraphMath.isLineContainsPoint(getSrvGraphicShape().getSettingsGraphic(), ge.getPointStart().getX(), 
        ge.getPointStart().getY(), ge.getPointStart().getX() + ge.getWidth(), 
        ge.getPointStart().getY(), realX, realY)) {
      return true;
    }
    if(UtilsGraphMath.isLineContainsPoint(getSrvGraphicShape().getSettingsGraphic(), ge.getPointStart().getX(), 
        ge.getPointStart().getY() + ge.getHeight(), ge.getPointStart().getX() + ge.getWidth(), 
        ge.getPointStart().getY() + ge.getHeight(), realX, realY)) {
      return true;
    }
    if(UtilsGraphMath.isLineContainsPoint(getSrvGraphicShape().getSettingsGraphic(), ge.getPointStart().getX(), 
        ge.getPointStart().getY(), ge.getPointStart().getX(), 
        ge.getPointStart().getY() + ge.getHeight(), realX, realY)) {
      return true;
    }
    if(UtilsGraphMath.isLineContainsPoint(getSrvGraphicShape().getSettingsGraphic(), ge.getPointStart().getX() + ge.getWidth(), 
        ge.getPointStart().getY(), ge.getPointStart().getX() + ge.getWidth(), 
        ge.getPointStart().getY() + ge.getHeight(), realX, realY)) {
      return true;
    }
    return false;
  }

  @Override
  public void startEdit(M ge) {
    factoryEditorElementUml.lazyGetEditorElementUml().startEdit(ge);
  }

  @Override
  public void validate(M eu, Set<String> result) {
    factoryEditorElementUml.lazyGetSrvEditElementUml().validate(eu, result);
  }

  //SGS:
  public IFactoryEditorElementUml<M, DLI> getFactoryEditorElementUml() {
    return factoryEditorElementUml;
  }
}
