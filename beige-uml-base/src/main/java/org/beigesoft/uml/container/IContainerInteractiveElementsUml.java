package org.beigesoft.uml.container;

import java.util.Collection;
import java.util.UUID;

import org.beigesoft.uml.assembly.ContainerFull;
import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;

public interface IContainerInteractiveElementsUml {

  public IAsmElementUmlInteractive<?, ?, ?, ?> getInteractiveAsmElementUml(UUID id);
  
  public Collection<IAsmElementUmlInteractive<?, ?, ?, ?>> getInteractiveAsmElementsUmlExcept(UUID id);
  
  public long getVersionInteractiveAsmElementsUml();
  
  public IAsmElementUmlInteractive<? extends ContainerFull<?>, ?, ?, ?> findContainerAtExcept(int screenX, int screenY, UUID id);
}
