package org.beigesoft.uml.ui.android;

import android.app.Activity;
import android.view.View;

import org.beigesoft.android.ui.widget.ChooserList;
import org.beigesoft.uml.assembly.ClassFull;
import org.beigesoft.uml.pojo.ClassUml;
import org.beigesoft.uml.pojo.RectangleRelationship;
import org.beigesoft.uml.pojo.RelationshipBinary;
import org.beigesoft.uml.ui.EditorRelationshipBinaryClass;

public class AsmEditorRelationshipBinaryClass<EDT extends EditorRelationshipBinaryClass<Activity, View, CL>, CL extends ClassUml> 
    extends AAsmEditorRelationshipBinary<RelationshipBinary<RectangleRelationship<ClassFull<CL>, CL>, ClassFull<CL>, CL>, EDT> {

  protected ChooserList<ClassFull<CL>> chooserClassFull;
  
  public AsmEditorRelationshipBinaryClass(Activity instrumentWindow, EDT editor, String tag) {
    super(instrumentWindow, editor, tag);
    chooserClassFull = new ChooserList<ClassFull<CL>>(instrumentWindow, 
        editor.getSrvEdit().getSrvI18n().getMsg("chooser_class"));
    editor.setChooserClassFull(chooserClassFull);
  }
}
