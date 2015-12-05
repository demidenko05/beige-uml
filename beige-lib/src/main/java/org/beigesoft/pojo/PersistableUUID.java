/*
 * Beigesoft ™
 * 
 * Licensed under the Apache License, Version 2.0
 * 
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package org.beigesoft.pojo;

import java.util.UUID;

import org.beigesoft.model.IPersistable;

/**
 * <p>Base abstraction of a persistable</p>
 * 
 * 
 * @author Yury Demidenko
 */
public class PersistableUUID implements IPersistable<UUID> {
    
  private UUID id = UUID.randomUUID();

  private boolean isNew = true;
        
  @Override
  public UUID getId() {
    return id;
  }

  @Override
  public void setId(UUID id) {
    this.id = id;
  }

  @Override
  public boolean getIsNew() {
    return isNew;
  }

  @Override
  public void setIsNew(boolean isNew) {
    this.isNew = isNew;
  }
}
