package org.beigesoft.uml.ui.android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import org.beigesoft.android.ui.widget.ButtonAndroid;
import org.beigesoft.android.ui.widget.CheckBoxAndroid;
import org.beigesoft.android.ui.widget.ChooserList;
import org.beigesoft.android.ui.widget.ComboBox;
import org.beigesoft.android.ui.widget.TextField;
import org.beigesoft.uml.android.R;
import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.assembly.LifeLineFull;
import org.beigesoft.uml.assembly.MessageFull;
import org.beigesoft.uml.model.EFrameRoleForMessage;
import org.beigesoft.uml.model.EMessageKind;
import org.beigesoft.uml.pojo.InteractionUse;
import org.beigesoft.uml.pojo.ShapeUmlWithName;
import org.beigesoft.uml.ui.EditorMessageFull;

public class AsmEditorMessageFull<M extends MessageFull, EDT extends EditorMessageFull<M, Activity, View>> 
    extends AAsmEditorElementUml<M, EDT> {

  protected EditText tfItsName;

  protected Spinner cmbItsKind;

  protected CheckBox cbIsReversed;
  
  protected EditText tfItsFrame;
    
  protected Spinner cmbFrameRole;

  protected CheckBox cbIsRightSideOfFrame;

  protected EditText tfInteractionUseStart;
  
  protected Button btInteractionUseStart;
  
  protected Button btClearInteractionUseStart;

  protected CheckBox cbIsLeftSideOfInteractionUseStart;

  protected EditText tfInteractionUseEnd;
  
  protected Button btInteractionUseEnd;
  
  protected Button btClearInteractionUseEnd;

  protected CheckBox cbIsRightSideOfInteractionUseEnd;

  protected ChooserList<IAsmElementUmlInteractive<? extends InteractionUse, ?, ?, ?>> chooserAsmInteractionUses;
  
  protected EditText tfLifeLineStart;
  
  protected Button btLifeLineStart;
  
  protected Button btClearLifeLineStart;
  
  protected EditText tfLifeLineEnd;
  
  protected Button btLifeLineEnd;
  
  protected Button btClearLifeLineEnd;
  
  protected ChooserList<IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, ?, ?, ?>> chooserAsmLifeLinesFull;

  public AsmEditorMessageFull(Activity instrumentWindow, EDT editor, String tag) {
    super(instrumentWindow, editor, tag);
    chooserAsmInteractionUses = new ChooserList<IAsmElementUmlInteractive<? extends InteractionUse, ?, ?, ?>>(instrumentWindow, 
        editor.getSrvEdit().getSrvI18n().getMsg("chooser_interaction_use"));
    editor.setChooserAsmInteractionUses(chooserAsmInteractionUses);
    chooserAsmLifeLinesFull = new ChooserList<IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, ?, ?, ?>>(instrumentWindow, 
        editor.getSrvEdit().getSrvI18n().getMsg("chooser_lifeline"));
    editor.setChooserAsmLifeLinesFull(chooserAsmLifeLinesFull);
  }

  @SuppressLint("InflateParams")
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    LayoutInflater inflater = getActivity().getLayoutInflater();
    // Pass null as the parent view because its going in the dialog layout
    View rootView = inflater.inflate(R.layout.dialog_editor_message, null);
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
    cmbItsKind = (Spinner) rootView.findViewById(R.id.cmbItsKind);
    ArrayAdapter<EMessageKind> cmbItsKindAdapter = new ArrayAdapter<EMessageKind>(getInstrumentWindow(), android.R.layout.simple_spinner_item);
    cmbItsKindAdapter.add(EMessageKind.ASYNCHRONOUS);
    cmbItsKindAdapter.add(EMessageKind.CALL);
    cmbItsKindAdapter.add(EMessageKind.REPLY);
    cmbItsKind.setAdapter(cmbItsKindAdapter);
    editor.setCmbItsKind(new ComboBox<EMessageKind>(cmbItsKind));
    cbIsReversed = (CheckBox) rootView.findViewById(R.id.cbIsReversed);
    editor.setCbIsReversed(new CheckBoxAndroid(cbIsReversed));
    tfItsFrame = (EditText) rootView.findViewById(R.id.tfItsFrame);
    editor.setTfItsFrame(new TextField(tfItsFrame));
    cmbFrameRole = (Spinner) rootView.findViewById(R.id.cmbFrameRole);
    ArrayAdapter<EFrameRoleForMessage> cmbFrameRoleAdapter = new ArrayAdapter<EFrameRoleForMessage>(getInstrumentWindow(), android.R.layout.simple_spinner_item);
    cmbFrameRoleAdapter.add(null);
    cmbFrameRoleAdapter.add(EFrameRoleForMessage.IS_START);
    cmbFrameRoleAdapter.add(EFrameRoleForMessage.IS_END);
    cmbFrameRole.setAdapter(cmbFrameRoleAdapter);
    editor.setCmbFrameRole(new ComboBox<EFrameRoleForMessage>(cmbFrameRole));
    cbIsRightSideOfFrame = (CheckBox) rootView.findViewById(R.id.cbIsRightSideOfFrame);
    editor.setCbIsRightSideOfFrame(new CheckBoxAndroid(cbIsRightSideOfFrame));
    tfInteractionUseStart = (EditText) rootView.findViewById(R.id.tfInteractionUseStart);
    editor.setTfInteractionUseStart(new TextField(tfInteractionUseStart));
    btInteractionUseStart = (Button) rootView.findViewById(R.id.btInteractionUseStart);
    btInteractionUseStart.setOnClickListener(this);
    btClearInteractionUseStart = (Button) rootView.findViewById(R.id.btClearInteractionUseStart);
    btClearInteractionUseStart.setOnClickListener(this);
    editor.setBtInteractionUseStart(new ButtonAndroid(btInteractionUseStart));
    editor.setBtClearInteractionUseStart(new ButtonAndroid(btClearInteractionUseStart));
    cbIsLeftSideOfInteractionUseStart = (CheckBox) rootView.findViewById(R.id.cbIsLeftSideOfInteractionUseStart);
    editor.setCbIsLeftSideOfInteractionUseStart(new CheckBoxAndroid(cbIsLeftSideOfInteractionUseStart));
    tfInteractionUseEnd = (EditText) rootView.findViewById(R.id.tfInteractionUseEnd);
    editor.setTfInteractionUseEnd(new TextField(tfInteractionUseEnd));
    btInteractionUseEnd = (Button) rootView.findViewById(R.id.btInteractionUseEnd);
    btInteractionUseEnd.setOnClickListener(this);
    btClearInteractionUseEnd = (Button) rootView.findViewById(R.id.btClearInteractionUseEnd);
    btClearInteractionUseEnd.setOnClickListener(this);
    editor.setBtInteractionUseEnd(new ButtonAndroid(btInteractionUseEnd));
    editor.setBtClearInteractionUseEnd(new ButtonAndroid(btClearInteractionUseEnd));
    cbIsRightSideOfInteractionUseEnd = (CheckBox) rootView.findViewById(R.id.cbIsRightSideOfInteractionUseEnd);
    editor.setCbIsRightSideOfInteractionUseEnd(new CheckBoxAndroid(cbIsRightSideOfInteractionUseEnd));
    tfLifeLineStart = (EditText) rootView.findViewById(R.id.tfLifeLineStart);
    editor.setTfLifeLineStart(new TextField(tfLifeLineStart));
    btLifeLineStart = (Button) rootView.findViewById(R.id.btLifeLineStart);
    btLifeLineStart.setOnClickListener(this);
    btClearLifeLineStart = (Button) rootView.findViewById(R.id.btClearLifeLineStart);
    btClearLifeLineStart.setOnClickListener(this);
    editor.setBtLifeLineStart(new ButtonAndroid(btLifeLineStart));
    editor.setBtClearLifeLineStart(new ButtonAndroid(btClearLifeLineStart));
    tfLifeLineEnd = (EditText) rootView.findViewById(R.id.tfLifeLineEnd);
    editor.setTfLifeLineEnd(new TextField(tfLifeLineEnd));
    btLifeLineEnd = (Button) rootView.findViewById(R.id.btLifeLineEnd);
    btLifeLineEnd.setOnClickListener(this);
    btClearLifeLineEnd = (Button) rootView.findViewById(R.id.btClearLifeLineEnd);
    btClearLifeLineEnd.setOnClickListener(this);
    editor.setBtLifeLineEnd(new ButtonAndroid(btLifeLineEnd));
    editor.setBtClearLifeLineEnd(new ButtonAndroid(btClearLifeLineEnd));
  }
}
