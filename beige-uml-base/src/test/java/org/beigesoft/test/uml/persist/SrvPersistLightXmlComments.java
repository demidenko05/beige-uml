package org.beigesoft.test.uml.persist;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.beigesoft.service.ISrvPersistSimple;
import org.beigesoft.service.persist.xml.ASrvSaveXmlBase;
import org.beigesoft.service.persist.xml.SaxReader;
import org.beigesoft.uml.pojo.CommentUml;
import org.beigesoft.uml.service.persist.xmllight.SrvSaveXmlComment;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class SrvPersistLightXmlComments extends ASrvSaveXmlBase implements ISrvPersistSimple<List<CommentUml>> {

  private final SaxReader saxReader;
  
  private final SaxListCommentsFiller saxListCommentsFiller;
  
  private final SrvSaveXmlComment<CommentUml> srvSaveXmlComment = new SrvSaveXmlComment<CommentUml>();
  
  private File fileXml;
  
  protected static final String nameComments = "comments";

  public SrvPersistLightXmlComments(File fileXml) {
    super(nameComments);
    this.fileXml = fileXml;
    saxListCommentsFiller = new SaxListCommentsFiller(nameComments, new ArrayList<String>());
    saxReader = new SaxReader(saxListCommentsFiller);
  }

  @Override
  public void persist(List<CommentUml> p) throws Exception {
    BufferedWriter bf = null;
    try {
      bf = new BufferedWriter(new FileWriter(fileXml));
      bf.write(getStartXmlAndNewLine());
      bf.write(toStartElementAndTwoNewLine(getNamePersistable()));
      for(CommentUml cm : p) {
        srvSaveXmlComment.persistModel(cm, bf);
      }
      bf.write(toEndElement(getNamePersistable()));
      bf.flush();
      bf.close();
    } 
    catch (Exception e) {
      if(bf != null) {
        bf.close();
      }
      throw new Exception(e);
    }
  }

  @Override
  public void restore(List<CommentUml> p) throws Exception {
    XMLReader xr = XMLReaderFactory.createXMLReader();
    getSaxListCommentsFiller().setModelAndPrepare(p);
    xr.setContentHandler(getSaxReader());
    xr.setErrorHandler(getSaxReader());
    FileReader fr = new FileReader(fileXml);
    xr.parse(new InputSource(fr));
  }

  //SGS:
  public SaxReader getSaxReader() {
    return saxReader;
  }

  public SaxListCommentsFiller getSaxListCommentsFiller() {
    return saxListCommentsFiller;
  }

  public File getFileXml() {
    return fileXml;
  }

  public void setFileXml(File fileXml) {
    this.fileXml = fileXml;
  }

  public SrvSaveXmlComment<CommentUml> getSrvSaveXmlComment() {
    return srvSaveXmlComment;
  }
}
