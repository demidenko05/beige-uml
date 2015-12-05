package org.beigesoft.test.uml.persist;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.beigesoft.uml.pojo.CommentUml;
import org.beigesoft.uml.pojo.CommentRelationship;
import org.junit.Test;

public class TestPersistComment {

  @Test
  public void tst1() {
    try {
      List<CommentUml> commentsOrigin = new ArrayList<CommentUml>();
      CommentUml cm1 = new CommentUml(1.66, 1.66);
      cm1.setItsText("cm1");
      commentsOrigin.add(cm1);
      CommentRelationship cr1cm1 = new CommentRelationship(cm1);
      cr1cm1.getPointEnd().setX(2.12);
      cm1.getRelationships().add(cr1cm1);
      CommentRelationship cr2cm1 = new CommentRelationship(cm1);
      cr2cm1.getPointEnd().setX(2.13);
      cm1.getRelationships().add(cr2cm1);
      CommentUml cm2 = new CommentUml(1.66, 1.66);
      cm2.setItsText("cm2");
      commentsOrigin.add(cm2);
      File fileXml = new File("TestXmlComments.xml");
      if(!fileXml.exists()) {
        fileXml.createNewFile();
      }
      System.out.println("!!!Test Xml File writtten into " + fileXml.getAbsolutePath());
      SrvPersistLightXmlComments srvPersistLightXmlComments = new SrvPersistLightXmlComments(fileXml);
      srvPersistLightXmlComments.persist(commentsOrigin);
      List<CommentUml> commentsRestored = new ArrayList<CommentUml>();
      srvPersistLightXmlComments.restore(commentsRestored);
      assertTrue(commentsRestored.size() == commentsOrigin.size());
      assertTrue(commentsRestored.get(0).getId().equals(cm1.getId()));
      assertTrue(commentsRestored.get(0).getRelationships().size() == 
          cm1.getRelationships().size());
      assertTrue(commentsRestored.get(0).getRelationships().get(0).getPointEnd().getX() == 
          cm1.getRelationships().get(0).getPointEnd().getX() &&
          commentsRestored.get(0).getRelationships().get(0).getPointEnd().getX() == 2.12);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
