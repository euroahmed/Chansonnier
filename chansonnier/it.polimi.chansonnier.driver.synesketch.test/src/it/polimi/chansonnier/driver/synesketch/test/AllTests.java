/****************************************************************************
 * Copyright (c) 2010 Giorgio Sironi. All rights reserved.
 * This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this 
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ****************************************************************************/
package it.polimi.chansonnier.driver.synesketch.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for it.polimi.chansonnier.driver.synesketch.test");
		//$JUnit-BEGIN$
		suite.addTestSuite(SynesketchEmotionRecognitionServiceTest.class);
		//$JUnit-END$
		return suite;
	}

}
