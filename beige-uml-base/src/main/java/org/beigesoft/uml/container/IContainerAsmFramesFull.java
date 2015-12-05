package org.beigesoft.uml.container;

import java.util.Collection;
import java.util.UUID;

import org.beigesoft.uml.assembly.ContainerFull;
import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.pojo.FrameUml;

public interface IContainerAsmFramesFull {

  public IAsmElementUmlInteractive<ContainerFull<FrameUml>, ?, ?, ?> findFrameFullById(UUID id);

  public IAsmElementUmlInteractive<ContainerFull<FrameUml>, ?, ?, ?> findFrameAt(int screenX, int screenY);

  public Collection<IAsmElementUmlInteractive<ContainerFull<FrameUml>, ?, ?, ?>> getFramesFullExcept(UUID id);

  public long getVersionAsmFramesFull();
}
