/***********************************************************************************************************************
 * Copyright (c) 2008 empolis GmbH and brox IT Solutions GmbH. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: brox IT-Solutions GmbH - initial creator
 **********************************************************************************************************************/

package org.eclipse.smila.search.datadictionary.messages.ddconfig;

import org.eclipse.smila.search.utils.search.IDFParameter;

/**
 * @author brox IT-Solutions GmbH
 * 
 * To change this generated comment go to Window>Preferences>Java>Code Generation>Code and Comments
 */
public class DDateField extends DFieldConfig {

  private IDFParameter parameter = null;

  public IDFParameter getParameter() {
    return parameter;
  }

  public void setParameter(IDFParameter parameter) {
    this.parameter = parameter;
  }

}