package org.beigesoft.uml.container;

import java.util.Collection;

import org.beigesoft.uml.assembly.IAsmElementUml;
import org.beigesoft.uml.pojo.CommentUml;
import org.beigesoft.uml.pojo.TextUml;

public interface IContainerTextComment {

  public Collection<? extends IAsmElementUml<? extends CommentUml, ?, ?, ?>> getCommentUmlList();
  
  public Collection<? extends IAsmElementUml<? extends TextUml, ?, ?, ?>> getTextUmlList();
}
