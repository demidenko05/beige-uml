package org.beigesoft.uml.service.persist.xmllight;

import java.io.BufferedWriter;

import org.beigesoft.uml.pojo.CommentUml;
import org.beigesoft.uml.pojo.CommentRelationship;

public class SrvSaveXmlComment<P extends CommentUml> extends ASrvSaveXmlShapeUml<P> {

  public static final String NAMEXML_COMMENTUML = CommentUml.class.getSimpleName();

  public static final String NAMEXML_HASBORDER = "hasBorder";

  public static final String NAMEXML_ISDASHEDRELATIONS = "isDashedRelations";
  
  private SrvSaveXmlCommentRelationship srvSaveXmlCommentRelationship = new SrvSaveXmlCommentRelationship();

  public SrvSaveXmlComment() {
    super(NAMEXML_COMMENTUML);
  }

  @Override
  protected void writeOtherElements(P p, BufferedWriter bf) throws Exception {
    super.writeOtherElements(p, bf);
    bf.write(toStartElement(SrvSaveXmlText.NAMEXML_ITSTEXT) + toStringOrNull(p.getItsText()) +
        toEndElementAndNewLine(SrvSaveXmlText.NAMEXML_ITSTEXT));
    bf.write(toStartElement(NAMEXML_HASBORDER) + p.getHasBorder() +
        toEndElementAndNewLine(NAMEXML_HASBORDER));    
    bf.write(toStartElement(NAMEXML_ISDASHEDRELATIONS) + p.getIsDashedRelationships() +
        toEndElementAndNewLine(NAMEXML_ISDASHEDRELATIONS));
    for(CommentRelationship rel : p.getRelationships()) {
      srvSaveXmlCommentRelationship.persistModel(rel, bf);
    }
  }

  //SGS:
  public SrvSaveXmlCommentRelationship getSrvSaveXmlCommentRelationship() {
    return srvSaveXmlCommentRelationship;
  }

  public void setSrvSaveXmlCommentRelationship(
      SrvSaveXmlCommentRelationship srvSaveXmlCommentRelationship) {
    this.srvSaveXmlCommentRelationship = srvSaveXmlCommentRelationship;
  }
}
