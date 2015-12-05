package org.beigesoft.uml.container;

import java.util.Collection;
import java.util.UUID;

import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.pojo.InteractionUse;

public interface IContainerInteractionUses {

  public IAsmElementUmlInteractive<? extends InteractionUse, ?, ?, ?> findAsmInteractionUseById(UUID id);

  public Collection<IAsmElementUmlInteractive<? extends InteractionUse, ?, ?, ?>> getAsmInteractionUsesExcept(UUID id);

  public long getVersionAsmInteractionUses();
}
