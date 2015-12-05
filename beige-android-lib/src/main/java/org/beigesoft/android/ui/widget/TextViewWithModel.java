package org.beigesoft.android.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class TextViewWithModel<M> extends TextView {
  
  private M model;

  public TextViewWithModel(Context context, AttributeSet attrs) {
    super(context, attrs);
  }
  
  public TextViewWithModel(Context context, M model) {
    super(context);
    this.model = model;
    refreshView();
  }
  
  public void refreshView() {
    if(model != null) {
      setText(model.toString());
    }
  }
 
  //SGS:
  public M getModel() {
    return model;
  }

  public void setModelAndRefresh(M model) {
    this.model = model;
    refreshView();
  }
}
