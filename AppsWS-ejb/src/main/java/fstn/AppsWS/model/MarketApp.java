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

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import fstn.AppsWS.util.DateTimeAdapter;

@Entity
@XmlRootElement(namespace="http://www.w3.org/2005/Atom",name="entry")
@Table(name = "MARKETAPP")
@XmlAccessorType(XmlAccessType.FIELD)
public class MarketApp implements Serializable {
   /** Default value included to remove warning. Remove or modify at will. **/
   private static final long serialVersionUID = 1L;

   @Id
   @XmlElement(namespace="http://www.w3.org/2005/Atom",name="id")
   private String id;

   @Column
   @NotNull
   @XmlElement(namespace="http://www.w3.org/2005/Atom",name="title")
   private String title;

   @Column
   @NotNull
   @XmlElement(namespace="http://schemas.zune.net/catalog/apps/2008/02",name="sortTitle")
   private String sortTitle;

   @Column
   @XmlElement(namespace="http://schemas.zune.net/catalog/apps/2008/02",name="version")
   private String version;
   
   @Column(name="updateDate")
   @XmlElement(namespace="http://schemas.zune.net/catalog/apps/2008/02",name="update")
   @XmlJavaTypeAdapter(value=DateTimeAdapter.class)
   private Date  update;
   
   @Column 
   @XmlElement(namespace="http://schemas.zune.net/catalog/apps/2008/02",name="releaseDate")
   @XmlJavaTypeAdapter( value=DateTimeAdapter.class)
   private Date  releaseDate;
   
   @Column 
   @XmlElement(namespace="http://schemas.zune.net/catalog/apps/2008/02",name="averageUserRating")
   private Double averageUserRating;
   
   @Column
   @XmlElement(namespace="http://schemas.zune.net/catalog/apps/2008/02",name="userRatingCount")
   private Integer userRatingCount;

   @XmlElement(namespace="http://schemas.zune.net/catalog/apps/2008/02",name="image")
   @OneToOne(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
   private MarketImage image;
   
   @XmlElementWrapper(namespace="http://schemas.zune.net/catalog/apps/2008/02",name="categories")
   @XmlElement(namespace="http://schemas.zune.net/catalog/apps/2008/02",name="category")
   @ManyToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
   @JoinTable(name="LK_APPS_CATS", joinColumns = { @JoinColumn(name = "APP_ID") }, inverseJoinColumns = { @JoinColumn(name = "CAT_ID") })
   private Set<MarketCat> categories = new HashSet<MarketCat>(0);

  public Set<MarketCat> getCategories(){
	   return categories;
   }

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public String getSortTitle() {
	return sortTitle;
}

public void setSortTitle(String sortTitle) {
	this.sortTitle = sortTitle;
}

public String getVersion() {
	return version;
}

public void setVersion(String version) {
	this.version = version;
}

public Date getUpdate() {
	return update;
}

public void setUpdate(Date update) {
	this.update = update;
}

public Date getReleaseDate() {
	return releaseDate;
}

public void setReleaseDate(Date releaseDate) {
	this.releaseDate = releaseDate;
}

public Double getAverageUserRating() {
	return averageUserRating;
}

public void setAverageUserRating(Double averageUserRating) {
	this.averageUserRating = averageUserRating;
}

public Integer getUserRatingCount() {
	return userRatingCount;
}

public void setUserRatingCount(Integer userRatingCount) {
	this.userRatingCount = userRatingCount;
}

public MarketImage getImage() {
	return image;
}

public void setImage(MarketImage image) {
	this.image = image;
}

public void setCategories(Set<MarketCat> categories) {
	this.categories = categories;
}

public void addCategorie(MarketCat cat) {
	if( categories==null)
		categories=new HashSet<MarketCat>();
	categories.add(cat);
	
}
   
   
   
   
   
   /*
   @NotNull
   @Size(min = 1, max = 25)
   @Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
   private String name;

   @NotNull
   @NotEmpty
   @Email
   private String email;

   @NotNull
   @Size(min = 10, max = 12)
   @Digits(fraction = 0, integer = 12)
   @Column(name = "phone_number")
   private String phoneNumber;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getPhoneNumber() {
      return phoneNumber;
   }

   public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
   }*/
}
