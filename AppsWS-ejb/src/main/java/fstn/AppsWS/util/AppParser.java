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
package fstn.AppsWS.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import javax.xml.bind.JAXB;

import fstn.AppsWS.model.MarketApp;
import fstn.AppsWS.model.MarketApps;
import fstn.AppsWS.model.MarketCat;
import fstn.AppsWS.model.Pagination;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class AppParser {

	@PersistenceContext
	private EntityManager em;

	@Inject
	private UrlManager url;

	@Resource
	private UserTransaction transaction;

	private int nbIterations = 0;
	private int maxIterations = 500000;
	private Logger log = Logger.getLogger(this.getClass().getCanonicalName());

	private void parseApp(MarketCat cat, String link) throws IOException,
			NotSupportedException, SystemException, SecurityException,
			IllegalStateException, RollbackException, HeuristicMixedException,
			HeuristicRollbackException {
		log.log(Level.INFO, link);
		nbIterations++;
		URL linkURL = new URL(link);
		InputStream stream = linkURL.openStream();
		MarketApps apps = JAXB.unmarshal(stream, MarketApps.class);
		for (MarketApp app : apps.getMarketApp()) {
			if(! app.getSortTitle().contains("???")){
				em.merge(app);			
			}
		}

		for (Pagination pag : apps.getLinks()) {
			if (pag.getRel().equals("next")) {
				log.log(Level.INFO, pag.toString());
				pag.setCategorie(cat);
				em.merge(pag);
				if (nbIterations < maxIterations) {
					parseApp(cat, url.getRootUrl() + pag.getHref());
				}
			}
		}
	}

	public void fillDatabase() throws Exception {
		try{
			transaction.begin();
			Query catQuery = em.createQuery("from MarketCat ");
			List<MarketCat> cats = catQuery.getResultList();
			transaction.commit();

			for (MarketCat cat : cats) {
				transaction.begin();
				Query query = em
						.createQuery("select p from Pagination p where categorie=:categorie order by p.createdAt DESC");
				query.setParameter("categorie", cat);
				query.setMaxResults(1);
				String link = url.getRootUrl() + url.getVersion() + url.getFr()
						+ "appCategories/" + cat.getId() + "/" + "apps/?chunksize=20";

				try {
					Pagination pag = (Pagination) query.getSingleResult();
					transaction.commit();
					if (pag != null) {
						link = url.getRootUrl() + pag.getHref();
					}
				} catch (NoResultException e) {
					transaction.rollback();
				}
				transaction.begin();
				parseApp(cat, link);
				transaction.commit();
			}
		}catch(Exception e){
			try{
			transaction.rollback();
			}catch(Exception e1){
				throw e1;				
			}
		}		
	}
}
