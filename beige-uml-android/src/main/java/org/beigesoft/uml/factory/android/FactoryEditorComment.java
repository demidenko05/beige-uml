package org.beigesoft.uml.factory.android;

import org.beigesoft.handler.IObserverModelChanged;
import org.beigesoft.service.ISrvI18n;
import org.beigesoft.ui.container.IContainerSrvsGui;
import org.beigesoft.ui.service.ISrvDialog;
import org.beigesoft.ui.service.edit.ISrvEdit;
import org.beigesoft.ui.widget.IEditor;
import org.beigesoft.uml.ui.android.AsmEditorComment;
import org.beigesoft.uml.app.model.SettingsGraphicUml;
import org.beigesoft.uml.factory.IFactoryEditorElementUml;
import org.beigesoft.uml.pojo.CommentUml;
import org.beigesoft.uml.service.edit.SrvEditComment;
import org.beigesoft.uml.ui.EditorComment;

import android.app.Activity;
import android.view.View;

public class FactoryEditorComment implements IFactoryEditorElementUml<CommentUml, Activity> {

  private final ISrvI18n srvI18n;
  
  private final ISrvDialog<Activity> srvDialog;
  
  private final SettingsGraphicUml settingsGraphic;
  
  private final Activity activity;
  
  private SrvEditComment<CommentUml, Activity> srvEditComment;
  
  private AsmEditorComment<CommentUml, EditorComment<CommentUml, Activity, View>> asmEditorComment; 
  
  private IObserverModelChanged observerCommentUmlChanged;
  
  public FactoryEditorComment(Activity activity, IContainerSrvsGui<Activity> containerGui) {
    this.activity = activity;
    this.srvI18n = containerGui.getSrvI18n();
    this.srvDialog = containerGui.getSrvDialog();
    this.settingsGraphic = (SettingsGraphicUml) containerGui.getSettingsGraphic();
  }

  @Override
  public IEditor<CommentUml> lazyGetEditorElementUml() {
    if(asmEditorComment == null) {
      lazyGetSrvEditElementUml();
      EditorComment<CommentUml, Activity, View> editor = new EditorComment<CommentUml, Activity, View>(activity,
          srvEditComment, srvI18n.getMsg("comment"));
      asmEditorComment = new AsmEditorComment<CommentUml, EditorComment<CommentUml,Activity,View>>(activity,
          editor, AsmEditorComment.class.getSimpleName());
      editor.addObserverModelChanged(observerCommentUmlChanged);
    }
    return asmEditorComment;
  }

  @Override
  public ISrvEdit<CommentUml, Activity> lazyGetSrvEditElementUml() {
    if(srvEditComment == null) {
      srvEditComment = new SrvEditComment<CommentUml, Activity>(srvI18n, srvDialog, settingsGraphic);
    }
    return srvEditComment;
  }

  //SGS:
  public void setSrvEditComment(
      SrvEditComment<CommentUml, Activity> srvEditComment) {
    this.srvEditComment = srvEditComment;
  }

  public AsmEditorComment<CommentUml, EditorComment<CommentUml, Activity, View>> getEditorComment() {
    return asmEditorComment;
  }

  public void setEditorComment(
      AsmEditorComment<CommentUml, EditorComment<CommentUml, Activity, View>> editorComment) {
    this.asmEditorComment = editorComment;
  }

  public IObserverModelChanged getObserverCommentUmlChanged() {
    return observerCommentUmlChanged;
  }

  public void setObserverCommentUmlChanged(
      IObserverModelChanged observerCommentUmlChanged) {
    this.observerCommentUmlChanged = observerCommentUmlChanged;
  }

  public ISrvI18n getSrvI18n() {
    return srvI18n;
  }

  public ISrvDialog<Activity> getSrvDialog() {
    return srvDialog;
  }

  public SettingsGraphicUml getSettingsGraphic() {
    return settingsGraphic;
  }

  public Activity getActivity() {
    return activity;
  }
}
