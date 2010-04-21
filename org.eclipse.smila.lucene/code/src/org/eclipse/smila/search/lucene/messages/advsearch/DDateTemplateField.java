/***********************************************************************************************************************
 * Copyright (c) 2008 empolis GmbH and brox IT Solutions GmbH. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: August Georg Schmidt (brox IT Solutions GmbH) - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.smila.search.lucene.messages.advsearch;

import org.eclipse.smila.search.lucene.tools.search.lucene.DDateFieldParameter;

/**
 * @author GSchmidt
 * 
 * To change this generated comment go to Window>Preferences>Java>Code Generation>Code and Comments
 */
public class DDateTemplateField extends DTemplateField implements Cloneable {

  private DDateFieldParameter _parameter;

  /**
   * 
   */
  DDateTemplateField() {
  }

  /**
   * @param fieldNo -
   * @param sourceFieldNo -
   */
  public DDateTemplateField(int fieldNo, int sourceFieldNo) {
    super(fieldNo, sourceFieldNo);
  }

  public DDateFieldParameter getParameter() {
    return _parameter;
  }

  public void setParameter(DDateFieldParameter parameter) {
    this._parameter = parameter;
  }

  @Override
  public Object clone() {
    final DDateTemplateField obj = (DDateTemplateField) super.clone();

    if (_parameter != null) {
      obj.setParameter((DDateFieldParameter) _parameter.clone());
    }
    return obj;
  }

}
