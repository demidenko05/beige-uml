package org.beigesoft.android.ui.widget;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.beigesoft.android.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

public class ChooserListNew<O> extends AChooser<O> {

  protected ListView lvChooserList;
  
  protected ListAdapterTextView<O> listAdapter;
  
  protected List<O> dataSource;

  private String title;
  
  public ChooserListNew(Activity instrumentWindow, String title) {
    super(instrumentWindow);
    this.title = title;
    this.dataSource = new ArrayList<O>();
  }

  @SuppressLint("InflateParams")
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    LayoutInflater inflater = getActivity().getLayoutInflater();
    // Pass null as the parent view because its going in the dialog layout
    //android_docs/guide/topics/ui/dialogs.html
    View rootView = inflater.inflate(R.layout.dialog_chooser_list, null);
    builder.setView(rootView);
    setupWidgets(rootView);
    Dialog dialog = builder.create();
    dialog.setTitle(title);
    return dialog;
  }

  public ChooserListNew(Activity instrumentWindow, String title, List<O> dataSource) {
    super(instrumentWindow);
    this.title = title;
    this.dataSource = dataSource;
  }
  
  @Override
  protected void setupWidgets(View rootView) {
    super.setupWidgets(rootView);
    lvChooserList = (ListView) rootView.findViewById(R.id.lvChooserList);
    listAdapter = new ListAdapterTextView<O> (getActivity(), android.R.layout.simple_list_item_activated_1, dataSource);
    lvChooserList.setAdapter(listAdapter);
    lvChooserList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
  }

  @Override
  public void refillDataSource(Collection<O> ds) {
    if(lvChooserList != null && ds != null) {
      int pos = lvChooserList.getCheckedItemPosition();
      if(pos != android.widget.AdapterView.INVALID_POSITION) {
        lvChooserList.setItemChecked(pos, false);
      }
      if(dataSource.size() > 0) {
        dataSource.clear();
      }
      listAdapter.notifyDataSetChanged();
    }
  }

  @Override
  public void setSelectedValue(O value) {
    int pos = listAdapter.getDataSource().indexOf(value);
    if(pos != android.widget.AdapterView.INVALID_POSITION) {
      lvChooserList.setItemChecked(pos, true);
    }
  }

  @Override
  protected void onOkPress(View v) {
    int pos = lvChooserList.getCheckedItemPosition();
    if(pos == android.widget.AdapterView.INVALID_POSITION) {
      showAlertDialog(R.string.please_select_item, R.string.error);
    }
    else {
      O value = listAdapter.getDataSource().get(pos);
      consumer.consume(value);
      dismiss();
    }
  }
  
  protected void showAlertDialog(int msg, int title) {
    AlertDialog.Builder builder = new Builder(getActivity());
    builder.setMessage(msg).setTitle(title)
    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int id) {
        dialog.cancel();
      }});
    builder.show();
  }
}
