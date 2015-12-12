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

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;


@Singleton
public class PeriodicTask {

	@Inject
	private AppParser parser;
	
    private static final Logger logger = Logger.getLogger(PeriodicTask.class.getCanonicalName());

    @Schedule(second = "0", minute = "0", hour = "1",dayOfWeek="1")
	    public void execute() throws Exception {
		parser.fillDatabase();
	            logger.log(Level.INFO,"periodic task invoque: {0}", System.currentTimeMillis());	    
	    }


	}
