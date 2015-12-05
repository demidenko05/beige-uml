package org.beigesoft.uml.ui.android;

import android.app.Activity;
import android.view.View;

import org.beigesoft.android.ui.widget.ChooserList;
import org.beigesoft.uml.assembly.ShapeFullVarious;
import org.beigesoft.uml.pojo.RelationshipBinaryVarious;
import org.beigesoft.uml.ui.EditorRelationshipBinaryVarious;

public class AsmEditorRelationshipBinaryVarious<M extends RelationshipBinaryVarious, EDT extends EditorRelationshipBinaryVarious<M, Activity, View>> 
    extends AAsmEditorRelationshipBinary<M, EDT> {

  protected ChooserList<ShapeFullVarious<?>> chooserShapeFullVarious;
  
  public AsmEditorRelationshipBinaryVarious(Activity instrumentWindow, EDT editor, String tag) {
    super(instrumentWindow, editor, tag);
    chooserShapeFullVarious = new ChooserList<ShapeFullVarious<?>>(instrumentWindow, 
        editor.getSrvEdit().getSrvI18n().getMsg("chooser_shape"));
    editor.setChooserShapeFullVarious(chooserShapeFullVarious);
  }
}
