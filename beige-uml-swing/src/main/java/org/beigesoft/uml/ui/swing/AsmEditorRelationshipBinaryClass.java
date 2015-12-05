package org.beigesoft.uml.ui.swing;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import org.beigesoft.ui.widget.swing.ChooserList;
import org.beigesoft.uml.assembly.ClassFull;
import org.beigesoft.uml.pojo.ClassUml;
import org.beigesoft.uml.pojo.RectangleRelationship;
import org.beigesoft.uml.pojo.RelationshipBinary;
import org.beigesoft.uml.ui.EditorRelationshipBinaryClass;

public class AsmEditorRelationshipBinaryClass<EDT extends EditorRelationshipBinaryClass<Frame, ActionEvent, CL>, CL extends ClassUml> 
    extends AAsmEditorRelationshipBinary<RelationshipBinary<RectangleRelationship<ClassFull<CL>, CL>, ClassFull<CL>, CL>, EDT>{

  private static final long serialVersionUID = 4033820598478789790L;

  protected final ChooserList<ClassFull<CL>> chooserClassFull;

  public AsmEditorRelationshipBinaryClass(Frame frame, EDT editor) {
    super(frame, editor);
    chooserClassFull = new ChooserList<ClassFull<CL>>(frame, editor.getSrvEdit(), 
        editor.getSrvEdit().getSrvI18n().getMsg("chooser_class"));
    editor.setChooserClassFull(chooserClassFull);
  }
}
