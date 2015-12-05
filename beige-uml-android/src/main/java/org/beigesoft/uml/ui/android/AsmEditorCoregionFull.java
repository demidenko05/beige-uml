package org.beigesoft.uml.ui.android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.beigesoft.android.ui.widget.ButtonAndroid;
import org.beigesoft.android.ui.widget.ChooserList;
import org.beigesoft.android.ui.widget.ListAdapterTextView;
import org.beigesoft.android.ui.widget.ListAndroid;
import org.beigesoft.android.ui.widget.TextField;
import org.beigesoft.uml.android.R;
import org.beigesoft.uml.assembly.CoregionFull;
import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.assembly.MessageFull;
import org.beigesoft.uml.ui.EditorCoregionFull;

public class AsmEditorCoregionFull<M extends CoregionFull, EDT extends EditorCoregionFull<M, Activity, View>> 
    extends AAsmEditorElementUml<M, EDT> {

  protected EditText tfLifeLine;
    
  protected ListAndroid<IAsmElementUmlInteractive<MessageFull,?,?,?>> listAsmMessageFulls;
  
  protected ListView lvAsmMessagesFull;
  
  protected Button btAddAsmMessageFull;

  protected Button btDelAsmMessageFull;

  protected ChooserList<IAsmElementUmlInteractive<MessageFull,?,?,?>> chooserAsmMessagesFull;

  public AsmEditorCoregionFull(Activity instrumentWindow, EDT editor, String tag) {
    super(instrumentWindow, editor, tag);
    chooserAsmMessagesFull = new ChooserList<IAsmElementUmlInteractive<MessageFull,?,?,?>>(instrumentWindow, 
        editor.getSrvEdit().getSrvI18n().getMsg("chooser_message"));
    editor.setChooserAsmMessagesFull(chooserAsmMessagesFull);
  }

  @SuppressLint("InflateParams")
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    LayoutInflater inflater = getActivity().getLayoutInflater();
    // Pass null as the parent view because its going in the dialog layout
    View rootView = inflater.inflate(R.layout.dialog_editor_coregion, null);
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
    tfLifeLine = (EditText) rootView.findViewById(R.id.tfLifeLine);
    editor.setTfLifeLine(new TextField(tfLifeLine));
    ListAdapterTextView<IAsmElementUmlInteractive<MessageFull,?,?,?>> listAdapterTextView = 
        new ListAdapterTextView<IAsmElementUmlInteractive<MessageFull,?,?,?>>(getActivity(), android.R.layout.simple_list_item_activated_1);
    lvAsmMessagesFull = (ListView) rootView.findViewById(R.id.lvAsmMessagesFull);
    lvAsmMessagesFull.setAdapter(listAdapterTextView);
    lvAsmMessagesFull.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    listAsmMessageFulls = new ListAndroid<IAsmElementUmlInteractive<MessageFull,?,?,?>>(lvAsmMessagesFull, listAdapterTextView);
    editor.setListAsmMessagesFull(listAsmMessageFulls);
    btAddAsmMessageFull = (Button) rootView.findViewById(R.id.btAddAsmMessageFull);
    btAddAsmMessageFull.setOnClickListener(this);
    btDelAsmMessageFull = (Button) rootView.findViewById(R.id.btDelAsmMessageFull);
    btDelAsmMessageFull.setOnClickListener(this);
    editor.setBtAddAsmMessageFull(new ButtonAndroid(btAddAsmMessageFull));
    editor.setBtDelAsmMessageFull(new ButtonAndroid(btDelAsmMessageFull));
  }
}
