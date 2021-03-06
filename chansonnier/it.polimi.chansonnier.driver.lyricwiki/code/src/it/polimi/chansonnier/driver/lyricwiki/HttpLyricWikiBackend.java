/****************************************************************************
 * Copyright (c) 2010 Giorgio Sironi. All rights reserved.
 * This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this 
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ****************************************************************************/
package it.polimi.chansonnier.driver.lyricwiki;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class HttpLyricWikiBackend implements LyricWikiBackend {
	private static final String LYRICWIKI_REST_API = "http://lyrics.wikia.com/api.php";

	@Override
	public String getSong(String title, String artist) {
		try {
			URL apiEndPoint = new URL(LYRICWIKI_REST_API 
					                             + "?func=getSong&artist="
					                             + _escape(artist)
					                             + "&song=" 
					                             + _escape(title)
					                             + "&fmt=xml");
	        URLConnection connection = apiEndPoint.openConnection();
	        BufferedReader in = new BufferedReader(
	                                new InputStreamReader(
	                                connection.getInputStream()));
	
	        StringBuilder builder = new StringBuilder();
	        String inputLine;
	        while ((inputLine = in.readLine()) != null) {
	        	builder.append(inputLine);
	        	builder.append("\n");
	        }
	        in.close();
	
			return builder.toString();
		} catch (MalformedURLException e) {
			throw new RuntimeException("Url of the API is not correct.");
		} catch (IOException e) {
			throw new RuntimeException("Cannot communicate with the server.");
		}
	}

	private String _escape(String phrase) {
		return phrase.replaceAll(" ", "%20");
	}
}
