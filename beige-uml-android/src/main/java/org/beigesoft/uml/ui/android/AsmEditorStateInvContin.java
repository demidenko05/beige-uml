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
import org.beigesoft.uml.pojo.StateInvContin;
import org.beigesoft.uml.ui.EditorStateInvContin;

public class AsmEditorStateInvContin<M extends StateInvContin, EDT extends EditorStateInvContin<M, Activity, View>> 
    extends AAsmEditorElementUml<M, EDT> {

  protected EditText tfItsName;

  protected CheckBox cbIsBold;
  
  public AsmEditorStateInvContin(Activity instrumentWindow, EDT editor, String tag) {
    super(instrumentWindow, editor, tag);
  }

  @SuppressLint("InflateParams")
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    LayoutInflater inflater = getActivity().getLayoutInflater();
    // Pass null as the parent view because its going in the dialog layout
    View rootView = inflater.inflate(R.layout.dialog_editor_stateinvcontin, null);
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
    cbIsBold = (CheckBox) rootView.findViewById(R.id.cbIsBold);
    editor.setCbIsBold(new CheckBoxAndroid(cbIsBold));
  }
}
