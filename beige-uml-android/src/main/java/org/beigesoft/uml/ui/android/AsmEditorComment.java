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
import org.beigesoft.uml.pojo.CommentRelationship;
import org.beigesoft.uml.pojo.CommentUml;
import org.beigesoft.uml.ui.EditorComment;

public class AsmEditorComment<M extends CommentUml, EDT extends EditorComment<M, Activity, View>> 
    extends AAsmEditorElementUml<M, EDT> {

  protected EditText taItsText;
  
  protected CheckBox cbHasBorder;

  protected CheckBox cbIsDashedRelations;
  
  protected ListAndroid<CommentRelationship> listRelationships;
  
  protected ListView lvConnectors;
  
  protected Button btAddRelationship;

  protected Button btDelRelationship;

  public AsmEditorComment(Activity instrumentWindow, EDT editor, String tag) {
    super(instrumentWindow, editor, tag);
  }

  @SuppressLint("InflateParams")
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    LayoutInflater inflater = getActivity().getLayoutInflater();
    // Pass null as the parent view because its going in the dialog layout
    View rootView = inflater.inflate(R.layout.dialog_editor_comment, null);
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
    taItsText = (EditText) rootView.findViewById(R.id.taItsText);
    cbHasBorder = (CheckBox) rootView.findViewById(R.id.cbHasBorder);
    cbIsDashedRelations = (CheckBox) rootView.findViewById(R.id.cbIsDashedRelations);
    editor.setTaItsText(new TextField(taItsText));
    editor.setCbHasBorder(new CheckBoxAndroid(cbHasBorder));
    editor.setCbIsDashedRelations(new CheckBoxAndroid(cbIsDashedRelations));
    ListAdapterTextView<CommentRelationship> listAdapterTextView = 
        new ListAdapterTextView<CommentRelationship>(getActivity(), android.R.layout.simple_list_item_activated_1);
    lvConnectors = (ListView) rootView.findViewById(R.id.lvConnectors);
    lvConnectors.setAdapter(listAdapterTextView);
    lvConnectors.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    listRelationships = new ListAndroid<CommentRelationship>(lvConnectors, listAdapterTextView);
    editor.setListRelationships(listRelationships);
    btAddRelationship = (Button) rootView.findViewById(R.id.btAddRelationship);
    btAddRelationship.setOnClickListener(this);
    btDelRelationship = (Button) rootView.findViewById(R.id.btDelRelationship);
    btDelRelationship.setOnClickListener(this);
    editor.setBtAddRelationship(new ButtonAndroid(btAddRelationship));
    editor.setBtDelRelationship(new ButtonAndroid(btDelRelationship));
  }
}
