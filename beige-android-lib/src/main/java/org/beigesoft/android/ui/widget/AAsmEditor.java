package org.beigesoft.android.ui.widget;

import android.app.Activity;
import android.app.DialogFragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import org.beigesoft.android.R;
import org.beigesoft.android.ui.WindowsInstrumentDialog;
import org.beigesoft.delegate.IDelegate;
import org.beigesoft.ui.widget.AEditor;
import org.beigesoft.ui.widget.IEditor;

public abstract class AAsmEditor<M, EDT extends AEditor<M, Activity, View>> extends DialogFragment implements IEditor<M>, OnClickListener {

  protected final Activity instrumentWindow;
  
  protected final EDT editor;
  
  protected final String tag;

  protected Button btApply;
  
  protected Button btOk;
  
  protected Button btClose;
  
  protected String title;
    
  public AAsmEditor(Activity instrumentWindow, EDT editor, String tag) {
    this.instrumentWindow = instrumentWindow;
    this.editor = editor;
    this.tag = tag;
    editor.setWindowInstrument(new WindowsInstrumentDialog(instrumentWindow, this, tag, delegatorSetTitle));
    setCancelable(false);
  }
    
  public void doPostConstruct() {
    editor.doPostConstruct();
  }
  
  @Override
  public void startEdit(M model) {
    editor.startEdit(model);
    if(btOk != null) {
      editor.refreshGui();
    }
  }

  @Override
  public void onClick(View v) {
    editor.makeAction(v);
  }
  
  protected void setupWidgets(View rootView) {
    btOk = (Button) rootView.findViewById(R.id.btnOkForDialog);
    btOk.setOnClickListener(this);
    btApply = (Button) rootView.findViewById(R.id.btnApplyForDialog);
    btApply.setOnClickListener(this);
    btClose = (Button) rootView.findViewById(R.id.btnCloseForDialog);
    btClose.setOnClickListener(this);
    editor.setBtOk(new ButtonAndroid(btOk));
    editor.setBtApply(new ButtonAndroid(btApply));
    editor.setBtClose(new ButtonAndroid(btClose));
  }

  public EDT getEditor() {
    return editor;
  }

  public Activity getInstrumentWindow() {
    return instrumentWindow;
  }
  
  protected IDelegate<String> delegatorSetTitle = new IDelegate<String>() {
    
    @Override
    public void makeWith(String pTitle) {
      title = pTitle;
    }
  };
}
