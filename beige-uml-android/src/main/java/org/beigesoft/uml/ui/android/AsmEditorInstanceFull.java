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
import org.beigesoft.android.ui.widget.ListAndroidWithEditor;
import org.beigesoft.android.ui.widget.TextField;
import org.beigesoft.pojo.HasNameEditable;
import org.beigesoft.uml.android.R;
import org.beigesoft.uml.assembly.ShapeFullVarious;
import org.beigesoft.uml.model.InstanceUml;
import org.beigesoft.uml.ui.EditorInstanceFull;

public class AsmEditorInstanceFull<M extends InstanceUml, EDT extends EditorInstanceFull<M, Activity, View>> 
    extends AAsmEditorElementUml<ShapeFullVarious<M>, EDT> {

  protected EditText tfItsName;

  protected EditText tfNameClass;

  protected EditText tfValue;

  protected CheckBox cbIsAdjustMinimumSize;

  protected ListAndroidWithEditor<HasNameEditable> listStructure;
  
  protected ListView lvStructure;
  
  protected Button btAddMember;
  
  protected Button btEditMember;
  
  protected Button btDelMember;

  private final AsmEditorHasName<HasNameEditable, ?> asmEditorMember;
  
  public AsmEditorInstanceFull(Activity instrumentWindow, EDT editor, String tag,
      AsmEditorHasName<HasNameEditable, ?> asmEditorMember) {
    super(instrumentWindow, editor, tag);
    this.asmEditorMember = asmEditorMember;
  }

  @SuppressLint("InflateParams")
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    LayoutInflater inflater = getActivity().getLayoutInflater();
    // Pass null as the parent view because its going in the dialog layout
    View rootView = inflater.inflate(R.layout.dialog_editor_instance, null);
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
    tfNameClass = (EditText) rootView.findViewById(R.id.tfNameClass);
    editor.setTfNameClass(new TextField(tfNameClass));
    tfValue = (EditText) rootView.findViewById(R.id.tfValue);
    editor.setTfValue(new TextField(tfValue));
    cbIsAdjustMinimumSize = (CheckBox) rootView.findViewById(R.id.cbIsAdjustMinimumSize);
    editor.setCbIsAdjustMinimumSize(new CheckBoxAndroid(cbIsAdjustMinimumSize));
    lvStructure = (ListView) rootView.findViewById(R.id.lvStructure);
    ListAdapterTextView<HasNameEditable> listAdapterTextViewm = 
        new ListAdapterTextView<HasNameEditable>(getActivity(), android.R.layout.simple_list_item_activated_1);
    lvStructure.setAdapter(listAdapterTextViewm);
    lvStructure.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    listStructure = new ListAndroidWithEditor<HasNameEditable>(lvStructure, listAdapterTextViewm, asmEditorMember);
    editor.setListStructure(listStructure);
    btAddMember = (Button) rootView.findViewById(R.id.btAddMember);
    btAddMember.setOnClickListener(this);
    editor.setBtAddMember(new ButtonAndroid(btAddMember));
    btEditMember = (Button) rootView.findViewById(R.id.btEditMember);
    btEditMember.setOnClickListener(this);
    editor.setBtEditMember(new ButtonAndroid(btEditMember));
    btDelMember = (Button) rootView.findViewById(R.id.btDelMember);
    btDelMember.setOnClickListener(this);
    editor.setBtDelMember(new ButtonAndroid(btDelMember));
  }
}
