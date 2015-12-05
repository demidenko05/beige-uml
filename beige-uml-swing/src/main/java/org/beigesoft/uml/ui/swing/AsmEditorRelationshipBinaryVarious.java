package org.beigesoft.uml.ui.swing;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import org.beigesoft.ui.widget.swing.ChooserList;
import org.beigesoft.uml.assembly.ShapeFullVarious;
import org.beigesoft.uml.pojo.RelationshipBinaryVarious;
import org.beigesoft.uml.ui.EditorRelationshipBinaryVarious;

public class AsmEditorRelationshipBinaryVarious<M extends RelationshipBinaryVarious, EDT extends EditorRelationshipBinaryVarious<M, Frame, ActionEvent>> 
    extends AAsmEditorRelationshipBinary<M, EDT>{

  private static final long serialVersionUID = 4033820598479809790L;

  protected final ChooserList<ShapeFullVarious<?>> chooserShapeFullVarious;

  public AsmEditorRelationshipBinaryVarious(Frame frame, EDT editor) {
    super(frame, editor);
    chooserShapeFullVarious = new ChooserList<ShapeFullVarious<?>>(frame, editor.getSrvEdit(), 
        editor.getSrvEdit().getSrvI18n().getMsg("chooser_shape"));
    editor.setChooserShapeFullVarious(chooserShapeFullVarious);
  }
}
