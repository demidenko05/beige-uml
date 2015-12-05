package org.beigesoft.uml.service.persist.xmllight;

import java.util.List;
import java.util.UUID;

import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.graphic.service.persist.ASrvSaveXmlShape;
import org.beigesoft.graphic.service.persist.SaxPoint2DFiller;
import org.beigesoft.uml.assembly.ShapeFull;
import org.beigesoft.uml.container.IContainerShapesForTie;
import org.beigesoft.uml.pojo.TextUml;

public class SaxTextFiller<P extends TextUml> extends ASaxElementUml<P> {

  private IContainerShapesForTie containerShapesUmlForTie;
  
  private final SaxPoint2DFiller<Point2D> saxPoint2DFiller;
  
  public SaxTextFiller(String namePersistable, List<String> pathCurrent) {
    super(namePersistable, pathCurrent);
    saxPoint2DFiller = new SaxPoint2DFiller<Point2D>(ASrvSaveXmlShape.POINT_START, pathCurrent);
  }

  @Override
  public boolean fillModel(String elementName, String elementValue) {
    if(super.isConsumableForElement(elementName)) {
      if(SrvSaveXmlText.NAMEXML_ITSTEXT.equals(elementName)) {
        getModel().setItsText(makeString(getModel().getItsText(), elementValue));
        return true;
      }
      if(SrvSaveXmlText.NAMEXML_ISBOLD.equals(elementName)) {
        getModel().setIsBold(Boolean.valueOf(elementValue));
        return true;
      }
      if(SrvSaveXmlText.NAMEXML_ISTRANSPARENT.equals(elementName)) {
        getModel().setIsTransparent(Boolean.valueOf(elementValue));
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean fillModel(String elementName, String attrName, String attrValue) {
    if(isConsumableForAttribute(elementName, attrName)) {
      if(super.isConsumableForAttribute(elementName, attrName)) {
        if(super.fillModel(elementName, attrName, attrValue)) {//id
          return true;
        }
      }
      if(isConsumableForAttributePointStart(elementName, attrName)) {
        //pointStart - x, y
        if(saxPoint2DFiller.fillModel(elementName, attrName, attrValue)) {
          return true;
        }
      }
      else if(isConsumableForAttributeTiedShape(elementName, attrName)) {
        ShapeFull<?> tiedShape = containerShapesUmlForTie.getTiedShapeById(UUID.fromString(attrValue));
        getModel().setTiedShape(tiedShape);
        tiedShape.getTextsTied().add(getModel());
      }
    }
    return false;
  }

  @Override
  public boolean isConsumableForAttribute(String elementName, String attrName) {
    return super.isConsumableForAttribute(elementName, attrName) ||
        isConsumableForAttributePointStart(elementName, attrName) ||
        isConsumableForAttributeTiedShape(elementName, attrName);
  }

  public boolean isConsumableForAttributePointStart(String elementName, String attrName) {
    int idx = getPathCurrent().size() - 2;
    return idx >= 0 && getNamePersistable().equals(getPathCurrent().get(idx)) &&
        saxPoint2DFiller.isConsumableForAttribute(elementName, attrName);
  }

  public boolean isConsumableForAttributeTiedShape(String elementName, String attrName) {
    int idx = getPathCurrent().size() - 2;
    return idx >= 0 && getNamePersistable().equals(getPathCurrent().get(idx)) &&
        ASrvSaveXmlShape.TIED_SHAPE.equals(elementName) && 
        ASrvSaveXmlElementUml.NAMEXML_ID.equals(attrName);
  }

  @Override
  public void setModelAndPrepare(P model) {
    super.setModelAndPrepare(model);
    if(getModel() == null) {
      saxPoint2DFiller.setModelAndPrepare(null);
    }
    else {
      saxPoint2DFiller.setModelAndPrepare(getModel().getPointStart());
    }
  }

  //SGS:
  public IContainerShapesForTie getContainerShapesUmlForTie() {
    return containerShapesUmlForTie;
  }

  public void setContainerShapesUmlForTie(IContainerShapesForTie containerShapesUmlForTie) {
    this.containerShapesUmlForTie = containerShapesUmlForTie;
  }

  public SaxPoint2DFiller<Point2D> getSaxPoint2DFiller() {
    return saxPoint2DFiller;
  }
}
