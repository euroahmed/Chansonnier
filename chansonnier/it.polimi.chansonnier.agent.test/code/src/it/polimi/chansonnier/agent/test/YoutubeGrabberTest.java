/****************************************************************************
 * Copyright (c) 2010 Giorgio Sironi. All rights reserved.
 * This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this 
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ****************************************************************************/
package it.polimi.chansonnier.agent.test;

import java.io.InputStream;

import junit.framework.TestCase;

import it.polimi.chansonnier.agent.YoutubeGrabber;
import it.polimi.chansonnier.utils.URLUtils;
import it.polimi.chansonnier.fixtures.Fixtures;

public class YoutubeGrabberTest extends TestCase {
	private YoutubeGrabber _grabber;
	
	protected void setUp() throws Exception {
		_grabber = new YoutubeGrabber();
	}
	
	public void testLedZeppelinSStairwayToHeavenIsDownloaded() throws Exception {
		assertFlvStartIsTheSame("http://www.youtube.com/watch?v=BcL---4xQYA", "stairway_to_heaven_start.dat");
	}
	
	public void testGreenDaySBasketCaseIsDownloaded() throws Exception {
		assertFlvStartIsTheSame("http://www.youtube.com/watch?v=GTwJo0HeNmU", "basketcase_start.dat");
	}
	
	public void testGreenDaySTimeOfYourLifeIsDownloaded() throws Exception {
		assertFlvStartIsTheSame("http://www.youtube.com/watch?v=IR6uz_VTCUo", "time_of_your_life_start.dat");
	}
	
	public void assertFlvStartIsTheSame(String pageUrl, String datFile) throws Exception {
		InputStream is = _grabber.getVideo(pageUrl);
		assertEquals(URLUtils.readStart(Fixtures.getAsFile(datFile)), URLUtils.readStart(is));
	}
}
