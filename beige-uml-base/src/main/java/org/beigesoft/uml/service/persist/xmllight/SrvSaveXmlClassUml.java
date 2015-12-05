package org.beigesoft.uml.service.persist.xmllight;

import java.io.BufferedWriter;

import org.beigesoft.uml.pojo.AttributeClass;
import org.beigesoft.uml.pojo.ClassUml;
import org.beigesoft.uml.pojo.MethodClass;

public class SrvSaveXmlClassUml<P extends ClassUml> extends ASrvSaveXmlShapeUml<P> {

  public static final String NAMEXML_CLASSUML = ClassUml.class.getSimpleName();
  
  public static final String NAMEXML_ATTRIBUTECLASS = AttributeClass.class.getSimpleName();
  
  public static final String NAMEXML_METHODCLASS = MethodClass.class.getSimpleName();
  
  public static final String NAMEXML_NAMEPACKAGE = "namePackage";

  public static final String NAMEXML_CLASSKIND = "classKind";

  public static final String NAMEXML_ISMAIN = "isMain";

  public static final String NAMEXML_VISIBILITYKIND = "visibilityKind";

  public static final String NAMEXML_ITSNAME = "itsName";

  private final SrvSaveXmlAttributeClass<AttributeClass> srvSaveXmlAttributeClass;
  
  private final SrvSaveXmlMethodClass<MethodClass> srvSaveXmlMethodClass;
  
  public SrvSaveXmlClassUml() {
    super(NAMEXML_CLASSUML);
    srvSaveXmlAttributeClass = new SrvSaveXmlAttributeClass<AttributeClass>(NAMEXML_ATTRIBUTECLASS);
    srvSaveXmlMethodClass = new SrvSaveXmlMethodClass<MethodClass>(NAMEXML_METHODCLASS);
  }

  @Override
  protected String writeOtherAttrs(P p) {
    String packageName = p.getNamePackage() == null ? "" :
      toAttribute(NAMEXML_NAMEPACKAGE, p.getNamePackage().toString());
    return super.writeOtherAttrs(p) +
        toAttribute(NAMEXML_ITSNAME, p.getItsName()) +
        packageName;
  }

  @Override
  protected void writeOtherElements(P p, BufferedWriter bf) throws Exception {
    super.writeOtherElements(p, bf);
    bf.write(toStartElement(NAMEXML_CLASSKIND) + toEnumNameOrNull(p.getClassKind()) +
        toEndElementAndNewLine(NAMEXML_CLASSKIND));
    bf.write(toStartElement(NAMEXML_ISMAIN) + p.getIsMain() +
        toEndElementAndNewLine(NAMEXML_ISMAIN));
    for(AttributeClass attr : p.getAttributes()) {
      srvSaveXmlAttributeClass.persistModel(attr, bf);
    }
    for(MethodClass oper : p.getMethods()) {
      srvSaveXmlMethodClass.persistModel(oper, bf);
    }
  }

  public SrvSaveXmlAttributeClass<AttributeClass> getSrvSaveXmlAttributeClass() {
    return srvSaveXmlAttributeClass;
  }

  public SrvSaveXmlMethodClass<MethodClass> getSrvSaveXmlMethodClass() {
    return srvSaveXmlMethodClass;
  }
}
