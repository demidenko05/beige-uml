package org.beigesoft.uml.service.interactive;

import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.graphic.service.UtilsGraphMath;
import org.beigesoft.uml.assembly.ShapeFullVarious;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.pojo.PackageUml;

@Deprecated
public class SrvInteractivePackageVariousFull<SHF extends ShapeFullVarious<SH>, DRI, SD extends ISettingsDraw, DLI, SH extends PackageUml> 
    extends SrvInteractiveShapeVariousFull<SHF, DRI, SD, DLI, SH> {
  
  public SrvInteractivePackageVariousFull(IFactoryEditorElementUml<SHF, DLI> factoryEditorShapeUmlFull,
      SrvInteractiveShapeUml<SH, DRI, SD> srvInteractiveShapeUml) {
    super(factoryEditorShapeUmlFull, srvInteractiveShapeUml);
  }

  @Override
  public boolean isContainsScreenPointForManipulate(SHF ge, int x, int y) {
    double realX = UtilsGraphMath.toRealX(getSrvInteractiveShapeUml().getSrvGraphicShape().getSettingsGraphic(), x);
    double realY = UtilsGraphMath.toRealY(getSrvInteractiveShapeUml().getSrvGraphicShape().getSettingsGraphic(), y);
    if(realX >= ge.getShape().getPointStart().getX() && realX <= ge.getShape().getPointStart().getX() + ge.getShape().getWidthHead() && 
        realY >= ge.getShape().getPointStart().getY() && realY <= ge.getShape().getPointStart().getY() + ge.getShape().getHeightHead()) {
      return true;
    }
    if(UtilsGraphMath.isLineContainsPoint(getSrvInteractiveShapeUml().getSrvGraphicShape().getSettingsGraphic(), ge.getShape().getPointStart().getX(), 
        ge.getShape().getPointStart().getY(), ge.getShape().getPointStart().getX() + ge.getShape().getWidth(), 
        ge.getShape().getPointStart().getY(), realX, realY)) {
      return true;
    }
    if(UtilsGraphMath.isLineContainsPoint(getSrvInteractiveShapeUml().getSrvGraphicShape().getSettingsGraphic(), ge.getShape().getPointStart().getX(), 
        ge.getShape().getPointStart().getY() + ge.getShape().getHeight(), ge.getShape().getPointStart().getX() + ge.getShape().getWidth(), 
        ge.getShape().getPointStart().getY() + ge.getShape().getHeight(), realX, realY)) {
      return true;
    }
    if(UtilsGraphMath.isLineContainsPoint(getSrvInteractiveShapeUml().getSrvGraphicShape().getSettingsGraphic(), ge.getShape().getPointStart().getX(), 
        ge.getShape().getPointStart().getY(), ge.getShape().getPointStart().getX(), 
        ge.getShape().getPointStart().getY() + ge.getShape().getHeight(), realX, realY)) {
      return true;
    }
    if(UtilsGraphMath.isLineContainsPoint(getSrvInteractiveShapeUml().getSrvGraphicShape().getSettingsGraphic(), ge.getShape().getPointStart().getX() + ge.getShape().getWidth(), 
        ge.getShape().getPointStart().getY(), ge.getShape().getPointStart().getX() + ge.getShape().getWidth(), 
        ge.getShape().getPointStart().getY() + ge.getShape().getHeight(), realX, realY)) {
      return true;
    }
    return false;
  }
}
