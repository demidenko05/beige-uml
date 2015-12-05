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

import org.beigesoft.android.ui.widget.ButtonAndroid;
import org.beigesoft.android.ui.widget.CheckBoxAndroid;
import org.beigesoft.android.ui.widget.ChooserList;
import org.beigesoft.android.ui.widget.TextField;
import org.beigesoft.uml.android.R;
import org.beigesoft.uml.assembly.ShapeFull;
import org.beigesoft.uml.pojo.TextUml;
import org.beigesoft.uml.ui.EditorText;

public class AsmEditorText<M extends TextUml, EDT extends EditorText<M, Activity, View>> 
    extends AAsmEditorElementUml<M, EDT> {

  protected EditText taItsText;
  
  protected CheckBox cbIsBold;
  
  protected CheckBox cbIsTransparent;
  
  protected EditText tfTiedShape;
  
  protected Button btChooseTiedShape;
  
  protected Button btClearTiedShape;
  
  protected ChooserList<ShapeFull<?>> chooserTiedShape;

  public AsmEditorText(Activity instrumentWindow, EDT editor, String tag) {
    super(instrumentWindow, editor, tag);
    chooserTiedShape = new ChooserList<ShapeFull<?>>(instrumentWindow, 
        editor.getSrvEdit().getSrvI18n().getMsg("chooser_shape"));
    editor.setChooserTiedShape(chooserTiedShape);
  }

  @SuppressLint("InflateParams")
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    LayoutInflater inflater = getActivity().getLayoutInflater();
    // Pass null as the parent view because its going in the dialog layout
    View rootView = inflater.inflate(R.layout.dialog_editor_text, null);
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
    cbIsBold = (CheckBox) rootView.findViewById(R.id.cbIsBold);
    editor.setCbIsBold(new CheckBoxAndroid(cbIsBold));
    cbIsTransparent = (CheckBox) rootView.findViewById(R.id.cbIsTransparent);
    editor.setCbIsTransparent(new CheckBoxAndroid(cbIsTransparent));
    tfTiedShape = (EditText) rootView.findViewById(R.id.tfTiedShape);
    tfTiedShape.setEnabled(false);
    btChooseTiedShape = (Button) rootView.findViewById(R.id.btChooseTiedShape);
    btChooseTiedShape.setOnClickListener(this);
    btClearTiedShape = (Button) rootView.findViewById(R.id.btClearTiedShape);
    btClearTiedShape.setOnClickListener(this);
    editor.setTaItsText(new TextField(taItsText));
    editor.setTfTiedShape(new TextField(tfTiedShape));
    editor.setBtChooseTiedShape(new ButtonAndroid(btChooseTiedShape));
    editor.setBtClearTiedShape(new ButtonAndroid(btClearTiedShape));
  }
}
