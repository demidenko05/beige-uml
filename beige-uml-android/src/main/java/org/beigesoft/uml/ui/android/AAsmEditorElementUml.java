package org.beigesoft.uml.ui.android;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;

import org.beigesoft.android.ui.widget.AAsmEditor;
import org.beigesoft.android.ui.widget.TextField;
import org.beigesoft.uml.android.R;
import org.beigesoft.uml.model.IElementUml;
import org.beigesoft.uml.ui.AEditorElementUml;

public class AAsmEditorElementUml<M extends IElementUml, EDT extends AEditorElementUml<M, Activity, View>> extends AAsmEditor<M, EDT> {

  protected EditText tfIndexZ;
  
  public AAsmEditorElementUml(Activity instrumentWindow, EDT editor, String tag) {
    super(instrumentWindow, editor, tag);
  }

  @Override
  protected void setupWidgets(View rootView) {
    super.setupWidgets(rootView);
    tfIndexZ = (EditText) rootView.findViewById(R.id.tfIndexZ);
    editor.setTfIndexZ(new TextField(tfIndexZ));
  }
}
