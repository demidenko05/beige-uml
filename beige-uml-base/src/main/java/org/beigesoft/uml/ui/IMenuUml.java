/*
 * Beigesoft ™
 * 
 * Licensed under the Apache License, Version 2.0
 * 
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package org.beigesoft.uml.ui;

/**
 * <p>Abstraction of a UML Menu</p>
 * 
 * 
 * @author Yury Demidenko
 */
public interface IMenuUml {

  public void setVisibleProjectMenu(boolean isVisible);

  public void setSaveDiagramEnabled(boolean isEnabled);

  public void setDeleteSelectedElementEnabled(boolean isEnabled);

  public void setAddClassFromJavaSourceEnabled(boolean isEnabled);

  public void setRearrangeEnabled(boolean isEnabled);

  public void setEditDiagramEnabled(boolean isEnabled);

  public void setAddClassDiagramFromJavaSourceEnabled(boolean isEnabled);

  public void setAdjustRelationsFor90DegreeEnabled(boolean isEnabled);
}
