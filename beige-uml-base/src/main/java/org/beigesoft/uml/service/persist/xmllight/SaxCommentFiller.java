package org.beigesoft.uml.service.persist.xmllight;

import java.util.List;

import org.beigesoft.graphic.service.persist.ASrvSaveXmlShape;
import org.beigesoft.uml.pojo.CommentUml;
import org.beigesoft.uml.pojo.CommentRelationship;
import org.beigesoft.uml.service.UtilsRectangleRelationship;

public class SaxCommentFiller<P extends CommentUml> extends ASaxShapeUmlFiller<P> {

  private SaxCommentRelationshipFiller saxCommentRelationshipFiller;
    
  public SaxCommentFiller(String namePersistable, List<String> pathCurrent) {
    super(namePersistable, ASrvSaveXmlShape.POINT_START, pathCurrent);
    saxCommentRelationshipFiller = new SaxCommentRelationshipFiller(SrvSaveXmlCommentRelationship.NAMEXML_COMMENTRELATIONSHIP,
        SrvSaveXmlCommentRelationship.POINT_END, pathCurrent);
  }

  @Override
  public boolean fillModel(String elementName, String elementValue) {
    if(super.isConsumableForElement(elementName)) {
      if(SrvSaveXmlText.NAMEXML_ITSTEXT.equals(elementName)) {
        getModel().setItsText(makeString(getModel().getItsText(), elementValue));
        return true;
      }
      if(SrvSaveXmlComment.NAMEXML_ISDASHEDRELATIONS.equals(elementName)) {
        getModel().setIsDashedRelationships(Boolean.valueOf(elementValue));
        return true;
      }
      if(SrvSaveXmlComment.NAMEXML_HASBORDER.equals(elementName)) {
        getModel().setHasBorder(Boolean.valueOf(elementValue));
        return true;
      }
      if(super.fillModel(elementName, elementValue)) {//shape's width height, adjust min size
        return true;
      }
    }
    else if(saxCommentRelationshipFiller.isConsumableForElement(elementName)) {
      if(saxCommentRelationshipFiller.fillModel(elementName, elementValue)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean fillModel(String elementName, String attrName, String attrValue) {
    if(super.isConsumableForAttribute(elementName, attrName)) {
      if(super.fillModel(elementName, attrName, attrValue)) {//Point start, UUID
        return true;
      }
    }
    else if(saxCommentRelationshipFiller.isConsumableForAttribute(elementName, attrName)) {
       if(saxCommentRelationshipFiller.fillModel(elementName, attrName, attrValue)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean isConsumableForAttribute(String elementName, String attrName) {
    return super.isConsumableForAttribute(elementName, attrName) ||
        saxCommentRelationshipFiller.isConsumableForAttribute(elementName, attrName);
  }

  @Override
  public boolean isConsumableForElement(String elementName) {
    return super.isConsumableForElement(elementName)
        || saxCommentRelationshipFiller.isConsumableForElement(elementName);
  }

  @Override
  public boolean handleStartElement(String elementName) {
    if(saxCommentRelationshipFiller.getNamePersistable().equals(elementName)) {
      CommentRelationship commentRelationshipCurr = new CommentRelationship(getModel());
      commentRelationshipCurr.setShape(getModel());
      getModel().getRelationships().add(commentRelationshipCurr);
      saxCommentRelationshipFiller.setModelAndPrepare(commentRelationshipCurr);
      return true;
    }
    return false;
  }

  @Override
  public boolean handleEndElement(String elementName) throws Exception {
    if(saxCommentRelationshipFiller.getNamePersistable().equals(elementName)) {
      UtilsRectangleRelationship.evalPointJoint(saxCommentRelationshipFiller.getModel());
      saxCommentRelationshipFiller.setModelAndPrepare(null);
      return true;
    }
    return false;
  }

  public SaxCommentRelationshipFiller getSaxCommentRelationshipFiller() {
    return saxCommentRelationshipFiller;
  }

  public void setSaxCommentRelationshipFiller(
      SaxCommentRelationshipFiller saxCommentRelationshipFiller) {
    this.saxCommentRelationshipFiller = saxCommentRelationshipFiller;
  }
}
