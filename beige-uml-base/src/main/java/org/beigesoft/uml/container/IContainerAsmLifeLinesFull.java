package org.beigesoft.uml.container;

import java.util.Collection;
import java.util.UUID;

import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.assembly.LifeLineFull;
import org.beigesoft.uml.pojo.ShapeUmlWithName;

public interface IContainerAsmLifeLinesFull {

  public IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, ?, ?, ?> findLifeLineFullById(UUID id);

  public IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, ?, ?, ?> findLifeLineFullAt(int screenX, int screenY);

  public Collection<IAsmElementUmlInteractive<LifeLineFull<ShapeUmlWithName>, ?, ?, ?>> getLifeLinesFullExcept(UUID id);

  public long getVersionAsmLifeLinesFull();
}
