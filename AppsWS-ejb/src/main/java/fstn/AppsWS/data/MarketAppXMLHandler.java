/*******************************************************************************
 * Copyright 2013 Stephen ZAMBAUX
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package fstn.AppsWS.data;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import fstn.AppsWS.model.MarketApp;

public class MarketAppXMLHandler extends DefaultHandler {

	// this holds the data
	private List<MarketApp> listOfApps;
	private MarketApp app;
	private StringBuffer buffer;

	/**
	 * This gets called when the xml document is first opened
	 * 
	 * @throws SAXException
	 */
	@Override
	public void startDocument() throws SAXException {
		listOfApps = new ArrayList<MarketApp>();
	}

	/**
	 * Called when it's finished handling the document
	 * 
	 * @throws SAXException
	 */
	@Override
	public void endDocument() throws SAXException {

	}


	@Override
	public void startElement(String namespaceURI, String localName,
			String qName, Attributes atts) throws SAXException {
		if(qName.equals("a:entry")){
			app = new MarketApp();
			
		}else {
			buffer = new StringBuffer();
		}
	}

	@Override
	public void endElement(String namespaceURI, String localName, String qName)
			throws SAXException {
		if(qName.equals("a:entry")){
			listOfApps.add(app);
			app=null;
		}else if(qName.equals("a:update")){
			try {
				app.setUpdate((Date) DateFormat.getInstance().parse(buffer.toString()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			buffer=null;
		}else if(qName.equals("a:title")){
			app.setTitle(buffer.toString());
			buffer = null;
		}else if(qName.equals("a:id")){			
			app.setId(buffer.toString());
			buffer = null;
		}else if(qName.equals("sortTitle")){	
			app.setSortTitle(buffer.toString());
			buffer = null;
		}else if(qName.equals("releaseDate")){	
			try {
				app.setReleaseDate((Date) DateFormat.getInstance().parse(buffer.toString()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			buffer = null;
		}else if(qName.equals("version")){	
			app.setVersion(buffer.toString());
			buffer = null;
		}else if(qName.equals("averageUserRating")){	
			app.setAverageUserRating(Double.valueOf(buffer.toString()));
			buffer = null;
		}else if(qName.equals("userRatingCount")){	
			app.setUserRatingCount(Integer.valueOf(buffer.toString()));
			buffer = null;
		}else{
			//erreur, on peut lever une exception
			//throw new SAXException("Balise "+qName+" inconnue.");
		}          
	}

	/**
	 * Calling when we're within an element. Here we're checking to see if there
	 * is any content in the tags that we're interested in and populating it in
	 * the Config object.
	 * 
	 * @param ch
	 * @param start
	 * @param length
	 */
	@Override
	public void characters(char ch[], int start, int length) {
		String lecture = new String(ch,start,length);
		if(buffer != null) buffer.append(lecture);     

	}

}
