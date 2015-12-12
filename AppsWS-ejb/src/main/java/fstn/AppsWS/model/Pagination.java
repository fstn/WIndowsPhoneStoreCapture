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

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace="http://www.w3.org/2005/Atom",name="entry")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "LINKS")
public class Pagination {
	 
	public Pagination() {
	}
	
	@XmlAttribute(name="rel")
	private String relF;
	
	public String getRel() {
		return relF;
	}

	public void setRel(String rel) {
		this.relF = rel;
	}
	
	 @Column(name = "created_at")
	  private Date createdAt;

	  @PrePersist
	  void createdAt() {
	    this.createdAt  = new Date();
	  }
	  
	  @PreUpdate
	  void updatedAt() {
		    this.createdAt  = new Date();
		  }

	@Id
	@XmlAttribute(name="href")
	private String hrefF;

	public String getHref() {
		return hrefF;
	}

	public void setHref(String href) {
		this.hrefF = href;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	 @OneToOne
	   private MarketCat categorie;
	 
	 
	public MarketCat getCategorie() {
		return categorie;
	}

	public void setCategorie(MarketCat categorie) {
		this.categorie = categorie;
	}

	@Override
	public String toString() {
		return "Pagination [relF=" + relF + ", hrefF=" + hrefF + "]";
	}
	
	

}
