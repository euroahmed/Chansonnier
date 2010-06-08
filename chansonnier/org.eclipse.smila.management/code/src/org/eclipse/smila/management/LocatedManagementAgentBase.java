/***********************************************************************************************************************
 * Copyright (c) 2008 empolis GmbH and brox IT Solutions GmbH. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Ivan Churkin (brox IT Solutions GmbH) - initial creator
 **********************************************************************************************************************/
package org.eclipse.smila.management;

/**
 * The Class LocatedManagementAgentBase.
 */
public abstract class LocatedManagementAgentBase implements LocatedManagementAgent {

  /**
   * {@inheritDoc}
   * 
   * @see org.eclipse.smila.management.LocatedManagementAgent#getLocation()
   */
  public ManagementAgentLocation getLocation() {
    return ManagementRegistration.INSTANCE.getCategory(getCategory()).getLocation(getName());
  }

  /**
   * Gets the category.
   * 
   * @return the category
   */
  protected abstract String getCategory();

  /**
   * Gets the name.
   * 
   * @return the name
   */
  protected abstract String getName();

}