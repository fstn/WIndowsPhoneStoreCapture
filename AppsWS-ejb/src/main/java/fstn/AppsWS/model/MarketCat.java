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
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlRootElement(namespace="http://schemas.zune.net/catalog/apps/2008/02",name="category")
@Table(name = "MARKETCAT")
@XmlAccessorType(XmlAccessType.FIELD)
public class MarketCat implements Serializable {
   /** Default value included to remove warning. Remove or modify at will. **/
   private static final long serialVersionUID = 1L;
private static final String defaut = "http://schemas.zune.net/catalog/apps/2008/02";
private static final String a="http://www.w3.org/2005/Atom";
   @Id
   @XmlElement(namespace=defaut,name="id")
   private String id;
   
   @XmlElement(namespace=a,name="id")
   public void setIdA(String value){
	   id=value;
   }
   public String getIdA(){
	   return null;
   }

   @Column
   @NotNull
   @XmlElement(namespace=defaut,name="title")
   private String title;

   @XmlElement(namespace=a,name="title")
   public void setTitleA(String value){
	   title=value;
   }
   public String getTitleA(){
	   return null;
   }
   
   @Column
   @XmlElement(namespace=defaut,name="parentId")
   private String parentId;
   
   @XmlElement(namespace=a,name="parentId")
   public void setParentIdA(String value){
	   parentId=value;
   }
   public String getParentIdA(){
	   return null;
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

public String getParentId() {
	return parentId;
}

public void setParentId(String parentId) {
	this.parentId = parentId;
}

   
}
