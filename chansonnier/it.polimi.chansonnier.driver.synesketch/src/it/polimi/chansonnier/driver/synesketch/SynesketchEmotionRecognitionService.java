/****************************************************************************
 * Copyright (c) 2010 Giorgio Sironi. All rights reserved.
 * This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this 
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ****************************************************************************/
package it.polimi.chansonnier.driver.synesketch;

import it.polimi.chansonnier.spi.EmotionRecognitionService;

import java.io.IOException;

import synesketch.emotion.Emotion;
import synesketch.emotion.EmotionalState;
import synesketch.emotion.Empathyscope;

public class SynesketchEmotionRecognitionService implements
		EmotionRecognitionService {

	@Override
	public String getEmotion(String textSample) {
         EmotionalState state;
		try {
			state = Empathyscope.getInstance().feel(textSample);
			Emotion strongest = state.getFirstStrongestEmotions(1).get(0);
			return _toString(strongest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

       return null;
	}
	
	private String _toString(Emotion e) {
		if (e.getType() == Emotion.HAPPINESS) {
			return "happiness";
		}
		if (e.getType() == Emotion.SADNESS) {
			return "sadness";
		}
		if (e.getType() == Emotion.FEAR) {
			return "fear";
		}
		if (e.getType() == Emotion.ANGER) {
			return "anger";
		}
		if (e.getType() == Emotion.DISGUST) {
			return "disgust";
		}
		if (e.getType() == Emotion.SURPRISE) {
			return "surprise";
		}
		return "";
	}
}