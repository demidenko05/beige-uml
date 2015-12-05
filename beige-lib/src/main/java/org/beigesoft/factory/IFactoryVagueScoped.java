/*
 * Beigesoft ™
 * 
 * Licensed under the Apache License, Version 2.0
 * 
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

/**
 * <p>Client don't care about requested bean scope.</p>
 * @author Yury Demidenko
 */
package org.beigesoft.factory;

public interface IFactoryVagueScoped<M> {

  public M createOrGet();
}
