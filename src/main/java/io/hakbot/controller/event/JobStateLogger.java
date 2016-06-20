/*
 * This file is part of Hakbot Origin Controller.
 *
 * Hakbot Origin Controller is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * Hakbot Origin Controller is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Hakbot Origin Controller. If not, see http://www.gnu.org/licenses/.
 */
package io.hakbot.controller.event;

import io.hakbot.controller.event.framework.Event;
import io.hakbot.controller.event.framework.Subscriber;
import io.hakbot.controller.model.Job;
import io.hakbot.controller.persistence.LocalPersistenceManagerFactory;
import javax.jdo.PersistenceManager;

public class JobStateLogger implements Subscriber {

    public void inform(Event event) {
        if(event instanceof JobStateEvent) {
            JobStateEvent jse = (JobStateEvent)event;
            Job job = jse.getJob();
            PersistenceManager pm = LocalPersistenceManagerFactory.createPersistenceManager();
            pm.currentTransaction().begin();
            job.addMessage("Job state changed to " + jse.getState().getValue());
            job.setState(jse.getState());
            pm.currentTransaction().commit();
            pm.getObjectById(Job.class, job.getId());
        }
    }
}