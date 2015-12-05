package org.beigesoft.uml.ui.android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import org.beigesoft.android.ui.widget.ButtonAndroid;
import org.beigesoft.android.ui.widget.CheckBoxAndroid;
import org.beigesoft.android.ui.widget.ListAdapterTextView;
import org.beigesoft.android.ui.widget.ListAndroid;
import org.beigesoft.android.ui.widget.TextField;
import org.beigesoft.uml.android.R;
import org.beigesoft.uml.assembly.LifeLineFull;
import org.beigesoft.uml.pojo.Execution;
import org.beigesoft.uml.pojo.ShapeUmlWithName;
import org.beigesoft.uml.ui.EditorLifeLineFull;

public class AsmEditorLifeLineFull<M extends LifeLineFull<ShapeUmlWithName>, EDT extends EditorLifeLineFull<M, Activity, View>> 
    extends AAsmEditorElementUml<M, EDT> {

  protected EditText tfItsName;

  protected EditText tfItsFrame;
  
  protected CheckBox cbIsDestructionOccurence;
 
  protected ListAndroid<Execution> listExecutions;
  
  protected ListView lvExecutions;
  
  protected Button btAddExecution;

  protected Button btDelExecution;

  public AsmEditorLifeLineFull(Activity instrumentWindow, EDT editor, String tag) {
    super(instrumentWindow, editor, tag);
  }

  @SuppressLint("InflateParams")
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    LayoutInflater inflater = getActivity().getLayoutInflater();
    // Pass null as the parent view because its going in the dialog layout
    View rootView = inflater.inflate(R.layout.dialog_editor_lifeline, null);
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
    tfItsFrame = (EditText) rootView.findViewById(R.id.tfItsFrame);
    editor.setTfItsFrame(new TextField(tfItsFrame));
    cbIsDestructionOccurence = (CheckBox) rootView.findViewById(R.id.cbIsDestructionOccurence);
    editor.setCbIsDestructionOccurence(new CheckBoxAndroid(cbIsDestructionOccurence));
    ListAdapterTextView<Execution> listAdapterTextView = 
        new ListAdapterTextView<Execution>(getActivity(), android.R.layout.simple_list_item_activated_1);
    lvExecutions = (ListView) rootView.findViewById(R.id.lvExecutions);
    lvExecutions.setAdapter(listAdapterTextView);
    lvExecutions.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    listExecutions = new ListAndroid<Execution>(lvExecutions, listAdapterTextView);
    editor.setListExecutions(listExecutions);
    btAddExecution = (Button) rootView.findViewById(R.id.btAddExecution);
    btAddExecution.setOnClickListener(this);
    btDelExecution = (Button) rootView.findViewById(R.id.btDelExecution);
    btDelExecution.setOnClickListener(this);
    editor.setBtAddExecution(new ButtonAndroid(btAddExecution));
    editor.setBtDelExecution(new ButtonAndroid(btDelExecution));
  }
}
