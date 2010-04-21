/*******************************************************************************
 * Copyright (c) 2009 empolis GmbH and brox IT Solutions GmbH. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: Juergen Schumacher (empolis GmbH) - initial API and implementation
 *******************************************************************************/

package org.eclipse.smila.ontology.test;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Suite combining all ontology tests.
 *
 * @author jschumacher
 *
 */
public final class AllTests {
  /**
   * utility class, do not create instances.
   */
  private AllTests() {
  }

  /**
   * @return suite for all ontology tests
   */
  public static Test suite() {
    final TestSuite suite = new TestSuite("Test for org.eclipse.smila.ontology.test");
    // $JUnit-BEGIN$
    suite.addTestSuite(TestSesameConfig.class);
    suite.addTestSuite(TestRepositoryAccess.class);
    suite.addTestSuite(TestManagementAgent.class);
    suite.addTestSuite(TestSesameRecordReaderPipelet.class);
    suite.addTestSuite(TestSesameMemoryRecordReaderPipelet.class);
    suite.addTestSuite(TestSesameRecordWriterPipelet.class);
    suite.addTestSuite(TestReadDatatypes.class);
    suite.addTestSuite(TestWriteDatatypes.class);
    suite.addTestSuite(TestCreateFileUriPipelet.class);
    suite.addTestSuite(TestCreateResourcePipelet.class);
    suite.addTestSuite(TestCreateRelationPipelet.class);
    // $JUnit-END$
    return suite;
  }
}
