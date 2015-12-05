package org.beigesoft.graphic;

import java.util.Set;
import java.util.UUID;

import org.beigesoft.model.IPersistable;

public interface ISrvInteractiveGraphicElement<EU extends IPersistable<UUID>> {

  public void move(EU eu, double deltaX, double deltaY);
  
  public boolean move(EU eu, int screenWasX, int screenWasY, int screenX, int screenY);
  
  public boolean resize(EU eu, int screenWasX, int screenWasY, int screenX, int screenY);
  
  public void startEdit(EU eu);
  
  public void validate(EU eu, Set<String> result);

  public boolean handleStopDraggingAt(EU eu, int mouseWasAtX, int mouseWasAtY);

  public boolean isContainsScreenPointForManipulate(EU eu, int screenX, int screenY);
}
