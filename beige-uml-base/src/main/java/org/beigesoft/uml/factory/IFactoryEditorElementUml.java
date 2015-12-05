package org.beigesoft.uml.factory;

import org.beigesoft.ui.service.edit.ISrvEdit;
import org.beigesoft.ui.widget.IEditor;
import org.beigesoft.uml.model.IElementUml;

public interface IFactoryEditorElementUml<EU extends IElementUml, DLI> {

  public IEditor<EU> lazyGetEditorElementUml();
  
  public ISrvEdit<EU, DLI> lazyGetSrvEditElementUml();
}
