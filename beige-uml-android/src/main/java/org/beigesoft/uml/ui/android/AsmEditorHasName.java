package org.beigesoft.uml.ui.android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import org.beigesoft.android.ui.widget.AAsmEditor;
import org.beigesoft.android.ui.widget.TextField;
import org.beigesoft.pojo.HasNameEditable;
import org.beigesoft.ui.EditorHasNameEditable;
import org.beigesoft.uml.android.R;

public class AsmEditorHasName<M extends HasNameEditable, EDT extends EditorHasNameEditable<M, Activity, View>> 
    extends AAsmEditor<M, EDT> {

  protected EditText taName;
    
  public AsmEditorHasName(Activity instrumentWindow, EDT editor, String tag) {
    super(instrumentWindow, editor, tag);
  }

  @SuppressLint("InflateParams")
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    LayoutInflater inflater = getActivity().getLayoutInflater();
    // Pass null as the parent view because its going in the dialog layout
    View rootView = inflater.inflate(R.layout.dialog_editor_has_name, null);
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
    taName = (EditText) rootView.findViewById(R.id.taName);
    editor.setTfValue(new TextField(taName));
  }
}
