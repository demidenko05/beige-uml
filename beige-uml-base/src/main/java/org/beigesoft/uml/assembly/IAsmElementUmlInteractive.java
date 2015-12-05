package org.beigesoft.uml.assembly;

import java.util.Set;

import org.beigesoft.graphic.IDrawableInteractive;
import org.beigesoft.graphic.model.ISettingsDraw;
import org.beigesoft.uml.model.IElementUml;

public interface IAsmElementUmlInteractive<EU extends IElementUml, DRI, DS extends ISettingsDraw, PRI> 
    extends IAsmElementUml<EU, DRI, DS, PRI>, IDrawableInteractive<DRI> {

  public void validate(Set<String> result);
}
