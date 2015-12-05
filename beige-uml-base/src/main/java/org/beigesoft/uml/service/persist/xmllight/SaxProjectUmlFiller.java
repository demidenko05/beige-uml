package org.beigesoft.uml.service.persist.xmllight;

import static org.beigesoft.uml.service.persist.xmllight.SrvPersistLightXmlProjectUml.*;

import java.util.List;

import org.beigesoft.graphic.model.EMeasurementUnit;
import org.beigesoft.service.persist.xml.ASaxModelFiller;
import org.beigesoft.uml.app.model.ProjectUml;
import org.beigesoft.uml.app.model.SettingsGraphicUml;

public class SaxProjectUmlFiller<M extends ProjectUml> extends ASaxModelFiller<M> {
  
  public SaxProjectUmlFiller(String namePersistable, List<String> pathCurrent) {
    super(namePersistable, pathCurrent);
  }

  public boolean fillModel(String elementName, String elementValue) {
    if(JAVA_SOURCE_PATH.equals(elementName)) {
      getModel().setJavaSourceFilePath(toStringOrNull(elementValue));
      return true;
    }
    if(IS_USE_JAVA_LANG_OBJECT.equals(elementName)) {
      getModel().setIsUseJavaLangObject(Boolean.valueOf(elementValue));
      return true;
    }
    if(IS_USE_GENERICS_FOR_GENERATE_FROM_CLASS.equals(elementName)) {
      getModel().setIsUseGenericForGenerateFromJavaClass(Boolean.valueOf(elementValue));
      return true;
    }
    if(IS_RETRIEVE_NONPUBLIC.equals(elementName)) {
      getModel().setIsRetrieveNonpublic(Boolean.valueOf(elementValue));
      return true;
    }
    if(SettingsGraphicUml.MARGIN_DIAGRAM.equals(elementName)) {
      getModel().getSettingsGraphicUml().setMarginDiagram(Double.valueOf(elementValue));
      return true;
    }
    if(SettingsGraphicUml.GAP_DIAGRAM.equals(elementName)) {
      getModel().getSettingsGraphicUml().setGapDiagram(Double.valueOf(elementValue));
      return true;
    }
    if(SettingsGraphicUml.WIDTH_END_RELATION.equals(elementName)) {
      getModel().getSettingsGraphicUml().setWidthEndRelation(Double.valueOf(elementValue));
      return true;
    }
    if(SettingsGraphicUml.SELECT_APPROXIMATION.equals(elementName)) {
      getModel().getSettingsGraphicUml().setSelectApproximation(Double.valueOf(elementValue));
      return true;
    }
    if(SettingsGraphicUml.WIDTH_DRAG_RECTANGLE.equals(elementName)) {
      getModel().getSettingsGraphicUml().setWidthDragRectangle(Double.valueOf(elementValue));
      return true;
    }
    if(SettingsGraphicUml.HEIGHT_CLASS_HEAD.equals(elementName)) {
      getModel().getSettingsGraphicUml().setHeightHeadClass(Double.valueOf(elementValue));
      return true;
    }
    if(SettingsGraphicUml.HEIGHT_ATTRIBUTE_CLASS.equals(elementName)) {
      getModel().getSettingsGraphicUml().setHeightAttributeClass(Double.valueOf(elementValue));
      return true;
    }
    if(SettingsGraphicUml.MARGIN_ELEMENT.equals(elementName)) {
      getModel().getSettingsGraphicUml().setMarginElement(Double.valueOf(elementValue));
      return true;
    }
    if(SettingsGraphicUml.HEIGHT_EMPTY_ATTRIBUTES.equals(elementName)) {
      getModel().getSettingsGraphicUml().setHeightEmptyAttributes(Double.valueOf(elementValue));
      return true;
    }
    if(SettingsGraphicUml.LENGTH_SELF_RELATION.equals(elementName)) {
      getModel().getSettingsGraphicUml().setLengthSelfRelation(Double.valueOf(elementValue));
      return true;
    }
    if(SettingsGraphicUml.WIDTH_COMMENT.equals(elementName)) {
      getModel().getSettingsGraphicUml().setWidthComment(Double.valueOf(elementValue));
      return true;
    }
     if(SettingsGraphicUml.HEIGHT_MIN_COMMENT.equals(elementName)) {
      getModel().getSettingsGraphicUml().setHeightMinComment(Double.valueOf(elementValue));
      return true;
    }
    if(SettingsGraphicUml.MEASUREMENT_UNIT.equals(elementName)) {
      getModel().getSettingsGraphicUml().setMeasurementUnitAndRecalculateIfNeed(EMeasurementUnit.valueOf(elementValue));
      return true;
    }
    return false;
  }

  @Override
  public boolean fillModel(String elementName, String attrName, String attrValue) {
    return false;
  }
}
