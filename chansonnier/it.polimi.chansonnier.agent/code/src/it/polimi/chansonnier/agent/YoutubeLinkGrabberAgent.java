/****************************************************************************
 * Copyright (c) 2010 Giorgio Sironi. All rights reserved.
 * This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this 
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ****************************************************************************/
package it.polimi.chansonnier.agent;


import it.polimi.chansonnier.utils.URLUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.smila.connectivity.framework.AbstractAgent;
import org.eclipse.smila.connectivity.framework.AgentException;
import org.eclipse.smila.connectivity.framework.util.AgentThreadState;
import org.eclipse.smila.connectivity.framework.util.ConnectivityIdFactory;
import org.eclipse.smila.datamodel.id.Id;
import org.eclipse.smila.datamodel.record.Attribute;
import org.eclipse.smila.datamodel.record.Literal;
import org.eclipse.smila.datamodel.record.MObject;
import org.eclipse.smila.datamodel.record.Record;
import org.eclipse.smila.datamodel.record.RecordFactory;
import org.eclipse.smila.datamodel.record.impl.AttributeImpl;
import org.eclipse.smila.datamodel.record.impl.LiteralImpl;

public class YoutubeLinkGrabberAgent extends AbstractAgent implements LinkGrabberAgent {
	private RecordFactory _factory = RecordFactory.DEFAULT_INSTANCE;
	private Queue<String> _linksToProcess = new LinkedList<String>();
	private YoutubeGrabber _grabber;
	private final Log _log = LogFactory.getLog(YoutubeLinkGrabberAgent.class);
	private Map<URL, String> _htmlPages = new HashMap<URL, String>();
	
	@Override
	protected void initialize() throws AgentException {
		_grabber = new YoutubeGrabber();
	}
	
	@Override
	public synchronized void addLink(String link) {
		_linksToProcess.add(link);		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
		      while (!isStopThread()) {
		    	  if (!_linksToProcess.isEmpty()) {
		    		String newLink = _linksToProcess.poll();
		    		Record newRecord = _createRecord();
		        	newRecord.setId(_createId(newLink));
		        	_setLink(newRecord, newLink);
		        	_setPageTitle(newRecord, _getPageTitle(newLink));
		        	_setDescription(newRecord, _getDescription(newLink));
		        	_setKeywords(newRecord, _getKeywords(newLink));
		        	_log.debug("it.polimi.chansonnier.agent.YoutubeLinkGrabberAgent: downloading video");
		        	_setOriginal(newRecord, _grabber.getVideo(newLink));
		        	_log.debug("it.polimi.chansonnier.agent.YoutubeLinkGrabberAgent: completed video");
		        	getControllerCallback().add(getSessionId(), getConfig().getDeltaIndexing(), newRecord, "424242");
		    	  }
		      } // while
		    } catch (Throwable t) {
		      getAgentState().setLastError(t);
		      getAgentState().setState(AgentThreadState.Aborted);
		      throw new RuntimeException(t);
		    } finally {
		      try {
		        stop();
		      } catch (Exception e) {
		        throw new RuntimeException(e);
		      }
		    }

	}

	private Record _createRecord() {
		Record newRecord = _factory.createRecord();
        final MObject metadata = _factory.createMetadataObject();
        newRecord.setMetadata(metadata);
        return newRecord;
	}
	
	private Id _createId(String link) {
    	Attribute[] idAttributes = new Attribute[1];
    	idAttributes[0] = new AttributeImpl();
    	Literal idL = new LiteralImpl(); 
    	idL.setStringValue(link);
    	idAttributes[0].addLiteral(idL);
    	return ConnectivityIdFactory.getInstance().createId(getConfig().getDataSourceID(), idAttributes);
	}
	
	private void _setLink(Record record, String link) {
		Attribute attribute = new AttributeImpl();
        attribute.setName("link");
        Literal l = new LiteralImpl();
        l.setStringValue(link);
        attribute.addLiteral(l);
        record.getMetadata().setAttribute("link", attribute);
	}
	
	private void _setPageTitle(Record record, String value) {
		Attribute attribute = new AttributeImpl();
        attribute.setName("PageTitle");
        Literal l = new LiteralImpl();
        l.setStringValue(value);
        attribute.addLiteral(l);
        record.getMetadata().setAttribute("pageTitle", attribute);
	}
	
	private void _setDescription(Record record, String value) {
		Attribute attribute = new AttributeImpl();
        attribute.setName("Description");
        Literal l = new LiteralImpl();
        l.setStringValue(value);
        attribute.addLiteral(l);
        record.getMetadata().setAttribute("description", attribute);
	}
	
	private void _setKeywords(Record record, String value) {
		Attribute attribute = new AttributeImpl();
        attribute.setName("Keywords");
        Literal l = new LiteralImpl();
        l.setStringValue(value);
        attribute.addLiteral(l);
        record.getMetadata().setAttribute("keywords", attribute);
	}
	
	private void _setOriginal(Record record, InputStream video) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] b = new byte[4096];
		for (int n; (n = video.read(b)) != -1; ) {
			out.write(b, 0, n);
		}
		record.setAttachment("original", out.toByteArray());
		
	}

	/**
	 * I know, HTML is not a regular language, but regular expressions are quick
	 * @param newLink
	 * @return the content of <title>
	 * @throws IOException
	 */
	private String _getPageTitle(String newLink) throws IOException {
		String html = _getHtml(new URL(newLink));
		html = html.replaceAll("\\s+", " ");
		Pattern p = Pattern.compile("<title>(.*?)</title>");
		Matcher m = p.matcher(html);
		m.find();
		String fullTitle = m.group(1);
		String title = fullTitle.replace("YouTube -", "");
		title = title.trim();
		return title;
	}
	
	private String _getDescription(String newLink) throws MalformedURLException, IOException {
		String html = _getHtml(new URL(newLink));
		html = html.replaceAll("\\s+", " ");
		Pattern p = Pattern.compile("<meta name=\"description\" content=\"(.*?)\"");
		Matcher m = p.matcher(html);
		m.find();
		String description = m.group(1);
		description = description.trim();
		return description;
	}

	private String _getKeywords(String newLink) throws MalformedURLException, IOException {
		String html = _getHtml(new URL(newLink));
		html = html.replaceAll("\\s+", " ");
		Pattern p = Pattern.compile("<meta name=\"keywords\" content=\"(.*?)\"");
		Matcher m = p.matcher(html);
		m.find();
		String keywords = m.group(1);
		keywords = keywords.trim();
		return keywords;
	}	
	
	private String _getHtml(URL link) throws IOException {
		if (!_htmlPages.containsKey(link)) {
			_htmlPages.put(link, URLUtils.retrieve(link));
		}
		return _htmlPages.get(link);
	}
	
	
	
}
 