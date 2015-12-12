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
@Path("/apps")
@RequestScoped
public class AppsResourceRESTService {

	private static final Logger logger = Logger
			.getLogger(AppsResourceRESTService.class.getCanonicalName());

	@PersistenceContext
	private EntityManager em;

	@GET
	@Produces("text/xml")
	public List<MarketApp> listAllApps() {
		@SuppressWarnings("unchecked")
		final List<MarketApp> results = em
				.createQuery("select m from MarketApp m order by m.title")
				.setMaxResults(20).getResultList();
		return results;
	}

	@GET
	@Path("/random/")
	@Produces("text/xml")
	public MarketApp lookupAppsRandom() {
		Query query = em.createQuery("from MarketApp ORDER BY RAND()");
		query.setMaxResults(1);
		return (MarketApp) query.getSingleResult();
	}

	@GET
	@Path("/randomBest/")
	@Produces("text/xml")
	public MarketApp lookupAppsRandomBest() {
		Query query = em
				.createQuery("select m from MarketApp m where m.averageUserRating>9 AND m.userRatingCount>50  ORDER BY RAND()");
		query.setMaxResults(1);
		return (MarketApp) query.getSingleResult();
	}

	@GET
	@Path("/randomWithCat/{cat:.*}/{minRate:.*}/{minCount:.*}")
	@Produces("text/xml")
	public MarketApp lookupAppsRandomWithCat(@PathParam("cat") String catId,@PathParam("minRate") String minRate,@PathParam("minCount") int minCount) {
		MarketApp retour = new MarketApp();
		if (!catId.equals("windowsphone.Best")) {
			try {
				Query query = em
						.createQuery("select a from MarketApp a join  a.categories  c where c.id=:cat AND a.averageUserRating>=:minRate AND a.userRatingCount>=:minCount  ORDER BY RAND()");
				query.setParameter("cat", catId);
				query.setParameter("minCount", minCount);
				query.setParameter("minRate", Double.parseDouble(minRate));
				query.setMaxResults(1);
				retour = (MarketApp) query.getSingleResult();
			} catch (NoResultException e) {
				logger.log(Level.SEVERE, "can't find categorie with id= "
						+ catId);
			}
		} else {
			retour = lookupAppsRandomBest();
		}
		return retour;
	}

	@GET
	@Path("/search/{title:.*}")
	@Produces("text/xml")
	public List<MarketApp> lookupAppsById(@PathParam("title") String title) {
		Query query = em.createQuery("from MarketApp where title like :title");
		query.setParameter("title", "%" + title + "%");
		return query.getResultList();
	}
}
