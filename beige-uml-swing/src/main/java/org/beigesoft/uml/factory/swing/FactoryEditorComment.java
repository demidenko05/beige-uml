package org.beigesoft.uml.factory.swing;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import org.beigesoft.handler.IObserverModelChanged;
import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.pojo.CommentUml;
import org.beigesoft.uml.service.edit.SrvEditComment;
import org.beigesoft.uml.ui.EditorComment;
import org.beigesoft.uml.ui.swing.AsmEditorComment;

public class FactoryEditorComment implements IFactoryEditorElementUml<CommentUml, Frame> {

  private final ISrvI18n srvI18n;

  private final ISrvDialog<Frame> srvDialog;

  private final SettingsGraphicUml settingsGraphic;
  
  private final Frame frameMain;

  private SrvEditComment<CommentUml, Frame> srvEditComment;

  private AsmEditorComment<CommentUml, EditorComment<CommentUml, Frame, ActionEvent>> editorComment;

  private IObserverModelChanged observerCommentUmlChanged;
  
  public FactoryEditorComment(ISrvI18n srvI18n, ISrvDialog<Frame> srvDialog,
      SettingsGraphicUml settingsGraphic, Frame frameMain) {
    this.srvI18n = srvI18n;
    this.srvDialog = srvDialog;
    this.settingsGraphic = settingsGraphic;
    this.frameMain = frameMain;
  }

  @Override
  public AsmEditorComment<CommentUml, EditorComment<CommentUml, Frame, ActionEvent>> lazyGetEditorElementUml() {
    if(editorComment == null) {
      EditorComment<CommentUml, Frame, ActionEvent> editorComm = new EditorComment<CommentUml, Frame, ActionEvent>(frameMain, lazyGetSrvEditElementUml(), srvI18n.getMsg("comment"));
      editorComment = new AsmEditorComment<CommentUml, EditorComment<CommentUml, Frame, ActionEvent>>(frameMain, editorComm);
      editorComment.doPostConstruct();
      editorComm.addObserverModelChanged(observerCommentUmlChanged);
    }
    return editorComment;
  }

  @Override
  public SrvEditComment<CommentUml, Frame> lazyGetSrvEditElementUml() {
    if(srvEditComment == null) {
      srvEditComment = new SrvEditComment<CommentUml, Frame>(srvI18n, srvDialog, settingsGraphic);
    }
    return srvEditComment;
  }

  public IObserverModelChanged getObserverCommentUmlChanged() {
    return observerCommentUmlChanged;
  }

  public void setObserverCommentUmlChanged(
      IObserverModelChanged observerCommentUmlChanged) {
    this.observerCommentUmlChanged = observerCommentUmlChanged;
  }
}
