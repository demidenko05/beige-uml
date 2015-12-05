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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import org.beigesoft.android.ui.widget.ButtonAndroid;
import org.beigesoft.android.ui.widget.ComboBox;
import org.beigesoft.android.ui.widget.ListAdapterTextView;
import org.beigesoft.android.ui.widget.ListAndroid;
import org.beigesoft.android.ui.widget.TextField;
import org.beigesoft.uml.android.R;
import org.beigesoft.uml.model.EInteractionOperator;
import org.beigesoft.uml.pojo.CombinedFragment;
import org.beigesoft.uml.ui.EditorCombinedFragment;

public class AsmEditorCombinedFragment<M extends CombinedFragment, EDT extends EditorCombinedFragment<M, Activity, View>> 
    extends AAsmEditorElementUml<M, EDT> {

  protected EditText tfDescription;

  protected Spinner cmbInteractionOperator;

  protected ListAndroid<Double> listTracesY;
  
  protected ListView lvTracesY;
  
  protected Button btAddTraceY;

  protected Button btDelTraceY;

  public AsmEditorCombinedFragment(Activity instrumentWindow, EDT editor, String tag) {
    super(instrumentWindow, editor, tag);
  }

  @SuppressLint("InflateParams")
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    LayoutInflater inflater = getActivity().getLayoutInflater();
    // Pass null as the parent view because its going in the dialog layout
    View rootView = inflater.inflate(R.layout.dialog_editor_combinedfragment, null);
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
    tfDescription = (EditText) rootView.findViewById(R.id.tfDescription);
    editor.setTfDescription(new TextField(tfDescription));
    cmbInteractionOperator = (Spinner) rootView.findViewById(R.id.cmbInteractionOperator);
    ArrayAdapter<EInteractionOperator> cmbInteractionOperatorAdapter = new ArrayAdapter<EInteractionOperator>(getInstrumentWindow(), android.R.layout.simple_spinner_item);
    cmbInteractionOperatorAdapter.add(EInteractionOperator.ALTTERNATIVES);
    cmbInteractionOperatorAdapter.add(EInteractionOperator.ASSERTION);
    cmbInteractionOperatorAdapter.add(EInteractionOperator.BREAK);
    cmbInteractionOperatorAdapter.add(EInteractionOperator.CONSIDER);
    cmbInteractionOperatorAdapter.add(EInteractionOperator.CRITICAL_REGION);
    cmbInteractionOperatorAdapter.add(EInteractionOperator.IGNORE);
    cmbInteractionOperatorAdapter.add(EInteractionOperator.LOOP);
    cmbInteractionOperatorAdapter.add(EInteractionOperator.NEGATIVE);
    cmbInteractionOperatorAdapter.add(EInteractionOperator.OPTION);
    cmbInteractionOperatorAdapter.add(EInteractionOperator.PARALLEL);
    cmbInteractionOperatorAdapter.add(EInteractionOperator.STRICT_SEQUENCING);
    cmbInteractionOperatorAdapter.add(EInteractionOperator.WEEK_SEQUENCING);
    cmbInteractionOperator.setAdapter(cmbInteractionOperatorAdapter);
    editor.setCmbInteractionOperator(new ComboBox<EInteractionOperator>(cmbInteractionOperator));
    ListAdapterTextView<Double> listAdapterTextView = 
        new ListAdapterTextView<Double>(getActivity(), android.R.layout.simple_list_item_activated_1);
    lvTracesY = (ListView) rootView.findViewById(R.id.lvTracesY);
    lvTracesY.setAdapter(listAdapterTextView);
    lvTracesY.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    listTracesY = new ListAndroid<Double>(lvTracesY, listAdapterTextView);
    editor.setListTracesY(listTracesY);
    btAddTraceY = (Button) rootView.findViewById(R.id.btAddTraceY);
    btAddTraceY.setOnClickListener(this);
    btDelTraceY = (Button) rootView.findViewById(R.id.btDelTraceY);
    btDelTraceY.setOnClickListener(this);
    editor.setBtAddTraceY(new ButtonAndroid(btAddTraceY));
    editor.setBtDelTraceY(new ButtonAndroid(btDelTraceY));
  }
}
