/*
 * Beigesoft ™
 * 
 * Licensed under the Apache License, Version 2.0
 * 
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package org.beigesoft.uml.controller;

import org.beigesoft.uml.diagram.assembly.IAsmDiagramClassInteractive;

/**
 * Abstraction of a interactive class diagram maker
 * 
 * @author Yury Demidenko
 */
public interface IControllerDiagramClass<ADUML extends IAsmDiagramClassInteractive<?, ?, ?, ?, PRI, ?>, PRI> extends IControllerDiagramUml<ADUML, PRI> {

  public void newDiagramFromJavaSource();

  public void addClassFromJavaSource();

  public void rearrange();

  public void adjustRelationsFor90Degree();
  
  public int evalCountClasses();
  
  public int evalCountRelationshipsBinaryClass();
}
