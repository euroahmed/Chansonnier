/***********************************************************************************************************************
 * Copyright (c) 2008 empolis GmbH and brox IT Solutions GmbH. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Ivan Churkin (brox IT Solutions GmbH) - initial creator Sebastian Voigt (Brox IT Solutions GmbH) -
 * initial creator
 **********************************************************************************************************************/

package org.eclipse.smila.connectivity.deltaindexing.impl.test;

import org.eclipse.smila.connectivity.deltaindexing.DeltaIndexingException;
import org.eclipse.smila.connectivity.deltaindexing.DeltaIndexingManager;
import org.eclipse.smila.connectivity.deltaindexing.test.AbstractDeltaIndexerWorkflowTest;

/**
 * Test DeltaIndexingManager workflow with in memory implementation..
 */
public class TestDeltaIndexerWorkflowMemory extends AbstractDeltaIndexerWorkflowTest {
   /**
   * {@inheritDoc}
   * 
   * @see junit.framework.TestCase#setUp()
   */
  @Override
  protected void setUp() throws Exception {
    _dim = getService(DeltaIndexingManager.class, "(smila.connectivity.deltaindexing.impl=memory)");
    if (_dim == null) {
      throw new DeltaIndexingException("Unable to find Delta Indexing Manager reference!");
    }
  }
}
