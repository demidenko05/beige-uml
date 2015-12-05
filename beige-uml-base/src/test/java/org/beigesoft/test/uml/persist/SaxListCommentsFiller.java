package org.beigesoft.test.uml.persist;

import java.util.List;

import org.beigesoft.service.persist.xml.ASaxModelFiller;
import org.beigesoft.uml.pojo.CommentUml;
import org.beigesoft.uml.service.persist.xmllight.SaxCommentFiller;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlComment;

public class SaxListCommentsFiller extends ASaxModelFiller<List<CommentUml>>{

  private SaxCommentFiller<CommentUml> saxCommentFiller;
  
  protected CommentUml commentCurrent;
  
  public SaxListCommentsFiller(String namePersistable, List<String> pathCurrent) {
    super(namePersistable, pathCurrent);
    saxCommentFiller = new SaxCommentFiller<CommentUml>(SrvSaveXmlComment.NAMEXML_COMMENTUML, pathCurrent);
  }

  @Override
  public boolean fillModel(String elementName, String elementValue) {
    if(saxCommentFiller.isConsumableForElement(elementName)) {
      if(saxCommentFiller.fillModel(elementName, elementValue)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean fillModel(String elementName, String attrName, String attrValue) {
    if(saxCommentFiller.isConsumableForAttribute(elementName, attrName)) {
      if(saxCommentFiller.fillModel(elementName, attrName, attrValue)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean handleStartElement(String localName) {
    if(SrvSaveXmlComment.NAMEXML_COMMENTUML.equals(localName)) {
      commentCurrent = new CommentUml(1.2, 1.2);
      getModel().add(commentCurrent);
      saxCommentFiller.setModelAndPrepare(commentCurrent);
      return true;
    }
    if(saxCommentFiller.handleStartElement(localName)) {
      return true;
    }
    return false;
  }

  @Override
  public boolean handleEndElement(String localName) throws Exception {
    if(SrvSaveXmlComment.NAMEXML_COMMENTUML.equals(localName)) {
      commentCurrent = null;
      saxCommentFiller.setModelAndPrepare(commentCurrent);
      return true;
    }
    if(saxCommentFiller.handleEndElement(localName)) {
      return true;
    }
    return false;
  }

  //SGS:
  public SaxCommentFiller<CommentUml> getSaxCommentFiller() {
    return saxCommentFiller;
  }

  public void setSaxCommentFiller(SaxCommentFiller<CommentUml> saxCommentFiller) {
    this.saxCommentFiller = saxCommentFiller;
  }
}
