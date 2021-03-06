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
package fstn.AppsWS.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace="http://www.w3.org/2005/Atom",name="feed")
public class MarketApps {
	
	  private List<MarketApp> marketApps = new LinkedList<MarketApp>();
	@XmlElement(namespace="http://www.w3.org/2005/Atom",name = "entry") 
	public final List<MarketApp> getMarketApp() { 
	    return marketApps; 
	} 
	

	  private List<Pagination> links = new LinkedList<Pagination>();
	@XmlElement(namespace="http://www.w3.org/2005/Atom",name = "link") 
	public final List<Pagination> getLinks() { 
	    return links; 
	} 

}
