package org.beigesoft.uml.factory;

import org.beigesoft.factory.IFactorySimple;
import org.beigesoft.uml.assembly.ClassFull;
import org.beigesoft.uml.pojo.ClassUml;
import org.beigesoft.uml.pojo.RectangleRelationship;

public class FactoryClassRelationship implements IFactorySimple<RectangleRelationship<ClassFull<ClassUml>, ClassUml>>{

  @Override
  public RectangleRelationship<ClassFull<ClassUml>, ClassUml> create() {
    RectangleRelationship<ClassFull<ClassUml>, ClassUml> rectangleRelationship = new RectangleRelationship<ClassFull<ClassUml>, ClassUml>();
     return rectangleRelationship;
  }
}
