/***********************************************************************************************************************
 * Copyright (c) 2008 empolis GmbH and brox IT Solutions GmbH. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: August Georg Schmidt (brox IT Solutions GmbH) - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.smila.search.templates;

public class TemplateException extends Exception {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * 
   */
  public TemplateException() {
    super();
  }

  /**
   * @param message -
   */
  public TemplateException(String message) {
    super(message);
  }

  /**
   * @param message -
   * @param cause -
   */
  public TemplateException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * @param cause -
   */
  public TemplateException(Throwable cause) {
    super(cause);
  }
}
