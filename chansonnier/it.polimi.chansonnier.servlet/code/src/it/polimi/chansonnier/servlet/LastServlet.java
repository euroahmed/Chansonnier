/****************************************************************************
 * Copyright (c) 2010 Giorgio Sironi. All rights reserved.
 * This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this 
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ****************************************************************************/
package it.polimi.chansonnier.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.smila.datamodel.id.Id;

public class LastServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		writer.println("Last songs added to the index...");
		String lastSongTitle = Activator.getLastIndexedService().getLastTitle();
		response.getWriter().println("Last: " + lastSongTitle + "<br />");
        List<Id> lastSongs = Activator.getLastIndexedService().getLastSongs();
        for (Id id : lastSongs) {
        	String key = id.getKey().toString();
        	response.getWriter().println("<a href=\"" + key + "\">" + key + "</a><br />");
        }
    }
}
