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
package fstn.AppsWS.rest;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import fstn.AppsWS.Bootstrap;
import fstn.AppsWS.model.MarketApp;
import fstn.AppsWS.model.MarketCat;

/**
 * JAX-RS Example
 * 
 * This class produces a RESTful service to read the contents of the apps table.
 */
@Path("/cats")
@RequestScoped
public class CatsResourceRESTService {

	private static final Logger logger = Logger.getLogger(CatsResourceRESTService.class
			.getCanonicalName());

	@PersistenceContext
   private EntityManager em;

   @GET
   @Produces("text/xml")
   public List<MarketCat> listAllCats() {
      @SuppressWarnings("unchecked")
      final List<MarketCat> results = em.createQuery("select c from MarketCat c WHERE c.parentId IS NULL order by c.title").getResultList();
      return results;
   }
}
