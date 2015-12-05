package org.beigesoft.graphic.service.persist;

import java.io.BufferedWriter;

import org.beigesoft.delegate.IDelegate;
import org.beigesoft.graphic.model.IShape;
import org.beigesoft.graphic.pojo.Point2D;
import org.beigesoft.service.ISrvSaveModel;
import org.beigesoft.service.persist.xml.ASrvSaveXmlBase;

public abstract class ASrvSaveXmlShape<P extends IShape> extends ASrvSaveXmlBase implements ISrvSaveModel<P, BufferedWriter> {

  public static final String POINT_START = "pointStart";

  public static final String WIDTH = "width";

  public static final String HEIGHT = "height";

  public static final String TIED_SHAPE = "tiedShape";
  
  private IDelegate<BufferedWriter> delegateSaveOtherXmlElements;

  private SrvSaveXmlPoint2D<Point2D> srvSaveXmlPoint2D = new SrvSaveXmlPoint2D<Point2D>(POINT_START);
  
  public ASrvSaveXmlShape(String namePersistable) {
    super(namePersistable);
  }

  @Override
  public void persistModel(P p, BufferedWriter bf) throws Exception {
    bf.write(toStartElementOpened(getNamePersistable()) + 
        writeOtherAttrs(p) +
        endElementOpenedAndNewLine());
    srvSaveXmlPoint2D.persistModel(p.getPointStart(), bf);
    bf.write(toStartElement(WIDTH) +
        Double.valueOf(p.getWidth()).toString() + toEndElementAndNewLine(WIDTH));
    bf.write(toStartElement(HEIGHT) +
        Double.valueOf(p.getHeight()).toString() + toEndElementAndNewLine(HEIGHT));
    writeOtherElements(p, bf);
    bf.write(toEndElementAndTwoNewLine(getNamePersistable()));
  }
  
  //Utils:
  protected String writeOtherAttrs(P p) {
    return "";
  }

  protected void writeOtherElements(P p, BufferedWriter bf) throws Exception {
    if(delegateSaveOtherXmlElements != null) {
      delegateSaveOtherXmlElements.makeWith(bf);
    }
  }

  //SGS:
  public SrvSaveXmlPoint2D<Point2D> getSrvSaveXmlPoint2D() {
    return srvSaveXmlPoint2D;
  }

  public void setSrvSaveXmlPoint2D(SrvSaveXmlPoint2D<Point2D> srvSaveXmlPoint2D) {
    this.srvSaveXmlPoint2D = srvSaveXmlPoint2D;
  }

  public IDelegate<BufferedWriter> getDelegateSaveOtherXmlElements() {
    return delegateSaveOtherXmlElements;
  }

  public void setDelegateSaveOtherXmlElements(
      IDelegate<BufferedWriter> delegateSaveOtherXmlElements) {
    this.delegateSaveOtherXmlElements = delegateSaveOtherXmlElements;
  }
}
