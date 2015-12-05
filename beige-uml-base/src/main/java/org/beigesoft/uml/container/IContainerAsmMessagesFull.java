package org.beigesoft.uml.container;

import java.util.Collection;
import java.util.UUID;

import org.beigesoft.uml.assembly.IAsmElementUmlInteractive;
import org.beigesoft.uml.assembly.MessageFull;

public interface IContainerAsmMessagesFull {

  public IAsmElementUmlInteractive<MessageFull, ?, ?, ?> findMessageFullById(UUID id);

  public Collection<IAsmElementUmlInteractive<MessageFull, ?, ?, ?>> getMessagesFullForLifeLine(UUID id);

  public long getVersionAsmMessagesFull();
}
