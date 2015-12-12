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

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import fstn.AppsWS.model.MarketApp;
@Stateless
public class MarketAppListProducer {
	
   @PersistenceContext
   private EntityManager em;

   private List<MarketApp> apps;

   // @Named provides access the return value via the EL variable name "apps" in the UI (e.g.,
   // Facelets or JSP view)
   @Produces
   @Named
   public List<MarketApp> getApps() {
      return apps;
   }


   @PostConstruct
   public void retrieveAllAppsOrderedByName() {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<MarketApp> criteria = cb.createQuery(MarketApp.class);
      Root<MarketApp> app = criteria.from(MarketApp.class);
      // feature in JPA 2.0
      criteria.select(app).orderBy(cb.asc(app.get("title")));
      apps = em.createQuery(criteria).getResultList();
   }
}
