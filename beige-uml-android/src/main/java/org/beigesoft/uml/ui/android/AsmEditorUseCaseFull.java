package org.beigesoft.uml.ui.android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;

import org.beigesoft.android.ui.widget.CheckBoxAndroid;
import org.beigesoft.uml.android.R;
import org.beigesoft.uml.assembly.ShapeFullVarious;
import org.beigesoft.uml.pojo.UseCase;
import org.beigesoft.uml.ui.EditorUseCaseFull;

public class AsmEditorUseCaseFull<UC extends UseCase, EDT extends EditorUseCaseFull<UC, Activity, View>> 
    extends AsmEditorShapeWithNameFull<ShapeFullVarious<UC>, EDT, UC> {

  protected CheckBox cbIsAdjustMinimumSize;
  
  protected CheckBox cbIsRectangle;
  
  public AsmEditorUseCaseFull(Activity instrumentWindow, EDT editor, String tag) {
    super(instrumentWindow, editor, tag);
  }

  @SuppressLint("InflateParams")
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    LayoutInflater inflater = getActivity().getLayoutInflater();
    // Pass null as the parent view because its going in the dialog layout
    View rootView = inflater.inflate(R.layout.dialog_editor_usecase, null);
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
    cbIsAdjustMinimumSize = (CheckBox) rootView.findViewById(R.id.cbIsAdjustMinimumSize);
    editor.setCbIsAdjustMinimumSize(new CheckBoxAndroid(cbIsAdjustMinimumSize));
    cbIsRectangle = (CheckBox) rootView.findViewById(R.id.cbIsRectangle);
    editor.setCbIsRectangle(new CheckBoxAndroid(cbIsRectangle));
  }
}
