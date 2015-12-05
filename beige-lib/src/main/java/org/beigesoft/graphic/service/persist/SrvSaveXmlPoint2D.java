package org.beigesoft.graphic.service.persist;

import java.io.BufferedWriter;

import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.service.ISrvSaveModel;
import org.beigesoft.service.persist.xml.ASrvSaveXmlBase;

public class SrvSaveXmlPoint2D<P extends Point2D> extends ASrvSaveXmlBase implements ISrvSaveModel<P, BufferedWriter> {

  public static final String X = "x";

  public static final String Y = "y";

  public SrvSaveXmlPoint2D(String namePersistable) {
    super(namePersistable);
  }

  @Override
  public void persistModel(P p, BufferedWriter bf) throws Exception {
    bf.write(toStartElementOpened(getNamePersistable()) + 
        toAttribute(X, Double.valueOf(p.getX()).toString()) + 
        toAttribute(Y, Double.valueOf(p.getY()).toString()) +
        writeOtherAttrs(p) +
        endElementOpenedAndNewLine());
    writeOtherElements(p, bf);
    bf.write(toEndElementAndNewLine(getNamePersistable()));
  }
  
  //Utils:
  protected String writeOtherAttrs(P p) {
    return "";
  }

  protected void writeOtherElements(P p, BufferedWriter bf) {
    // nothing
  }
}
