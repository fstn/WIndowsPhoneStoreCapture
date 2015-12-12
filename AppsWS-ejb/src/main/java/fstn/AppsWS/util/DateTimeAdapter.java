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

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateTimeAdapter extends XmlAdapter<java.util.Date, java.sql.Date> {

	public java.util.Date marshal(java.sql.Date v) {
		return new java.util.Date(v.getTime());
	}

	public java.sql.Date unmarshal(java.util.Date v) {
	          return new java.sql.Date(v.getTime());
	      }
}
