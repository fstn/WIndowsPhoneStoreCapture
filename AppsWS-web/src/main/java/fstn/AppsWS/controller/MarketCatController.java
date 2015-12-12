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
package fstn.AppsWS.controller;

import java.io.InputStream;
import java.net.URL;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.bind.JAXB;

import fstn.AppsWS.model.MarketCat;
import fstn.AppsWS.model.MarketCats;
import fstn.AppsWS.util.UrlManager;
@Stateless
@Named
public class MarketCatController {

	private Logger logger;
   @Inject
   private FacesContext facesContext;

	@PersistenceContext
   private EntityManager em;
   
   @Inject
   private UrlManager url;

   public String getCats() throws Exception {
       try {
    		   logger=Logger.getLogger(MarketCatController.class.getCanonicalName());
   			String link="http://marketplaceedgeservice.windowsphone.com/v3.2/fr-FR/appCategories/";
   			URL linkURL = new URL(link);
   			InputStream stream=linkURL.openStream();
   			MarketCats cats = JAXB.unmarshal(stream, MarketCats.class);
   			for (MarketCat cat : cats.getMarketCats()){
   				em.merge(cat);
   				/* link="http://marketplaceedgeservice.windowsphone.com/v3.2/fr-FR/appCategories/"+cat.getId()+"/";
   	   			 linkURL = new URL(link);
   	   			 stream=linkURL.openStream();
   	   			MarketCats cats2 = JAXB.unmarshal(stream, MarketCats.class);
   	   			for (MarketCat cat2 : cats2.getMarketCats()){
   	   				em.merge(cat2);
   	   			}*/
   			}
           FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful");
           facesContext.addMessage(null, m);
       } catch (Exception e) {
    	   e.printStackTrace();
           String errorMessage = getRootErrorMessage(e);
           FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
           facesContext.addMessage(null, m);
       }
       return "";
   }

   
   private String getRootErrorMessage(Exception e) {
       // Default to general error message that registration failed.
       String errorMessage = "Registration failed. See server log for more information";
       if (e == null) {
           // This shouldn't happen, but return the default messages
           return errorMessage;
       }

       // Start with the exception and recurse to find the root cause
       Throwable t = e;
       while (t != null) {
           // Get the message from the Throwable class instance
           errorMessage = t.getLocalizedMessage();
           t = t.getCause();
       }
       // This is the root cause message
       return errorMessage;
   }
}
