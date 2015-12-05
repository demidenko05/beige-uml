package org.beigesoft.uml.ui.android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import org.beigesoft.android.ui.widget.CheckBoxAndroid;
import org.beigesoft.android.ui.widget.TextField;
import org.beigesoft.uml.android.R;
import org.beigesoft.uml.assembly.ShapeFullVarious;
import org.beigesoft.uml.pojo.PackageUml;
import org.beigesoft.uml.ui.EditorPackageFull;

public class AsmEditorPackageFull<M extends PackageUml, EDT extends EditorPackageFull<M, Activity, View>> 
    extends AAsmEditorElementUml<ShapeFullVarious<M>, EDT> {

  protected EditText tfItsName;

  protected EditText tfComment;

  protected CheckBox cbIsNameInHead;
    
  public AsmEditorPackageFull(Activity instrumentWindow, EDT editor, String tag) {
    super(instrumentWindow, editor, tag);
  }

  @SuppressLint("InflateParams")
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    LayoutInflater inflater = getActivity().getLayoutInflater();
    // Pass null as the parent view because its going in the dialog layout
    View rootView = inflater.inflate(R.layout.dialog_editor_package, null);
    builder.setView(rootView);
    setupWidgets(rootView);
    Dialog dialog = builder.create();
    dialog.setTitle(title);
    editor.refreshGui();
    return dialog;
  }

  @Override
  protected void setupWidgets(View rootView) {
    super.setupWidgets(rootView);
    tfItsName = (EditText) rootView.findViewById(R.id.tfItsName);
    editor.setTfItsName(new TextField(tfItsName));
    tfComment = (EditText) rootView.findViewById(R.id.tfComment);
    editor.setTfComment(new TextField(tfComment));
    cbIsNameInHead = (CheckBox) rootView.findViewById(R.id.cbIsNameInHead);
    editor.setCbIsNameInHead(new CheckBoxAndroid(cbIsNameInHead));
  }
}
