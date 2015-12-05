package org.beigesoft.uml.service.persist.xmllight;

import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.uml.assembly.ContainerFull;
import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.assembly.ShapeFullVarious;
import org.beigesoft.uml.diagram.assembly.AsmDiagramObject;
import org.beigesoft.uml.diagram.pojo.DiagramUml;
import org.beigesoft.uml.factory.IFactoryAsmElementUml;
import org.beigesoft.uml.model.InstanceUml;
import org.beigesoft.uml.pojo.CommentUml;
import org.beigesoft.uml.pojo.FrameUml;
import org.beigesoft.uml.pojo.LineUml;
import org.beigesoft.uml.pojo.RectangleUml;
import org.beigesoft.uml.pojo.RelationshipBinaryVarious;
import org.beigesoft.uml.pojo.TextUml;

public class SaxDiagramObjectFiller<DRI, SD extends ISettingsDraw, IMG, DLI> extends ASaxDiagramUmlInteractiveFiller<AsmDiagramObject<DRI, SD, IMG, FileAndWriter, DLI>, DiagramUml, DRI, SD, IMG, DLI> {
  
  private final SaxInstanceFiller<InstanceUml> saxInstanceFiller;
  
  private final SaxRelationshipBinaryVariousFiller<RelationshipBinaryVarious> saxRelationshipBinaryVariousFiller;

  private final IFactoryAsmElementUml<IAsmElementUmlInteractive<ShapeFullVarious<InstanceUml>, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, ShapeFullVarious<InstanceUml>> factoryAsmInstanceFull;

  private final IFactoryAsmElementUml<IAsmElementUmlInteractive<RelationshipBinaryVarious, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, RelationshipBinaryVarious> factoryAsmRelationshipBinaryVarious;

  public SaxDiagramObjectFiller(String namePersistable,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<CommentUml, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, CommentUml> factoryAsmComment,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<TextUml, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, TextUml> factoryAsmText,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<ContainerFull<FrameUml>, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, ContainerFull<FrameUml>> factoryAsmFrame,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<RectangleUml, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, RectangleUml> factoryAsmRectangle,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<LineUml, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, LineUml> factoryAsmLine,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<ShapeFullVarious<InstanceUml>, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, ShapeFullVarious<InstanceUml>> factoryAsmInstanceFull,
      IFactoryAsmElementUml<IAsmElementUmlInteractive<RelationshipBinaryVarious, DRI, SD, FileAndWriter>, DRI, SD, FileAndWriter, RelationshipBinaryVarious> factoryAsmRelationshipBinaryVarious) {
    super(namePersistable, factoryAsmComment, factoryAsmText, factoryAsmFrame, factoryAsmRectangle, factoryAsmLine);
    this.factoryAsmInstanceFull = factoryAsmInstanceFull;
    this.factoryAsmRelationshipBinaryVarious = factoryAsmRelationshipBinaryVarious;
    saxInstanceFiller = new SaxInstanceFiller<InstanceUml>(SrvSaveXmlInstance.NAMEXML_INSTANCEUML, getPathCurrent());
    saxRelationshipBinaryVariousFiller = new SaxRelationshipBinaryVariousFiller<RelationshipBinaryVarious>(SrvSaveXmlRelationshipBinaryVarious.NAMEXML_RELATIONSHIPBINARYVARIOUS, getPathCurrent());
  }

  @Override
  public boolean fillModel(String elementName, String elementValue) {
    if(elementName == null || elementValue.equals("\\n")) {
      return false;
    }
    if(super.fillModel(elementName, elementValue)) {
      return true;
    }
    else if(saxInstanceFiller.getModel() != null) {
      if(saxInstanceFiller.fillModel(elementName, elementValue)) {
        return true;
      }
    }
    else if(saxRelationshipBinaryVariousFiller.getModel() != null) {
      if(saxRelationshipBinaryVariousFiller.fillModel(elementName, elementValue)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean fillModel(String elementName, String attrName, String attrValue) {
    if(elementName == null || attrName == null || attrValue.equals("\\n")) {
      return false;
    }
    if(super.fillModel(elementName, attrName, attrValue)) {
      return true;
    }
    else if(saxInstanceFiller.getModel() != null) {
      if(saxInstanceFiller.fillModel(elementName, attrName, attrValue)) {
        return true;
      }
    }
    else if(saxRelationshipBinaryVariousFiller.getModel() != null) {
      if(saxRelationshipBinaryVariousFiller.fillModel(elementName, attrName, attrValue)) {
        return true;
      }
    }
    return false;
  }
 
  @Override
  public boolean handleStartElement(String localName) {
    if(super.handleStartElement(localName)) {
      return true;
    }
    if(saxInstanceFiller.getNamePersistable().equals(localName)) {
      IAsmElementUmlInteractive<ShapeFullVarious<InstanceUml>, DRI, SD, FileAndWriter> asmInstanceFull = factoryAsmInstanceFull.createAsmElementUml();
      getModel().getAsmListAsmInstancesFull().addElementUml(asmInstanceFull);
      asmInstanceFull.getElementUml().setIsNew(false);
      saxInstanceFiller.setModelAndPrepare(asmInstanceFull.getElementUml().getShape());
      return true;
    }
    if(saxRelationshipBinaryVariousFiller.getNamePersistable().equals(localName)) {
      IAsmElementUmlInteractive<RelationshipBinaryVarious, DRI, SD, FileAndWriter> asmRelVar = factoryAsmRelationshipBinaryVarious.createAsmElementUml();
      getModel().getAsmListAsmRelationshipsBinVar().addElementUml(asmRelVar);
      asmRelVar.getElementUml().setIsNew(false);
      saxRelationshipBinaryVariousFiller.setModelAndPrepare(asmRelVar.getElementUml());
      return true;
    }
    if(saxInstanceFiller.getModel() != null && saxInstanceFiller.handleStartElement(localName)) {
      return true;
    }
    if(saxRelationshipBinaryVariousFiller.getModel() != null && saxRelationshipBinaryVariousFiller.handleStartElement(localName)) {
      return true;
    }
    return false;
  }

  @Override
  public boolean handleEndElement(String localName) throws Exception {
    if(super.handleEndElement(localName)) {
      return true;
    }
    if(saxInstanceFiller.getNamePersistable().equals(localName)) {
      saxInstanceFiller.setModelAndPrepare(null);
      return true;
    }
    if(saxRelationshipBinaryVariousFiller.getNamePersistable().equals(localName)) {
      ShapeFullVarious<?> shapeFullStart = saxRelationshipBinaryVariousFiller.getModel().getShapeRelationshipStart().getShapeFull();
      shapeFullStart.getRelationshipsVariousStart().add(saxRelationshipBinaryVariousFiller.getModel().getShapeRelationshipStart());
      ShapeFullVarious<?> shapeFullEnd = saxRelationshipBinaryVariousFiller.getModel().getShapeRelationshipEnd().getShapeFull();
      shapeFullEnd.getRelationshipsVariousEnd().add(saxRelationshipBinaryVariousFiller.getModel().getShapeRelationshipEnd());
      saxRelationshipBinaryVariousFiller.setModelAndPrepare(null);
      return true;
    }
    if(saxInstanceFiller.getModel() != null && saxInstanceFiller.handleEndElement(localName)) {
      return true;
    }
    if(saxRelationshipBinaryVariousFiller.getModel() != null && saxRelationshipBinaryVariousFiller.handleEndElement(localName)) {
      return true;
    }
    return false;
  }

  //SGS:
  public SaxInstanceFiller<InstanceUml> getSaxInstanceFiller() {
    return saxInstanceFiller;
  }

  public SaxRelationshipBinaryVariousFiller<RelationshipBinaryVarious> getSaxRelationshipBinaryVariousFiller() {
    return saxRelationshipBinaryVariousFiller;
  }
}
