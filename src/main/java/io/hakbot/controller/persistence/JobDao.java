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
package io.hakbot.controller.persistence;

import io.hakbot.controller.logging.Logger;
import io.hakbot.controller.model.Job;
import io.hakbot.controller.workers.State;
import org.apache.commons.lang3.StringUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class JobDao extends BaseDao {
/*
    // Setup logging
    private static final Logger logger = Logger.getLogger(JobDao.class);

    private static final String FIND_ALL = "SELECT * FROM job ORDER BY created ASC";
    private static final String FIND_BY_STATE = "SELECT * FROM job WHERE state = ? ORDER BY created ASC";
    private static final String FIND_BY_UUID = "SELECT * FROM job WHERE uuid = ? ORDER BY created ASC";
    private static final String DELETE_ALL = "DELETE FROM job";
    private static final String DELETE_BY_UUID = "DELETE FROM job WHERE uuid = ?";
    private static final String DELETE_BY_STATE = "DELETE FROM job WHERE state = ?";
    private static final String INSERT = "INSERT INTO job (uuid, provider, publisher, providerpayload, publisherpayload, created, state) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE job SET provider=?, publisher=?, message=?, providerpayload=?, publisherpayload=?, created=?, started=?, completed=?, state=?, success=? WHERE uuid = ?";

    public List<Job> getJobs() {
        return getJobs(FIND_ALL, null);
    }

    public List<Job> getJobs(State state) {
        return getJobs(FIND_BY_STATE, state.getValue());
    }

    public Job getJob(String uuid) {
        List<Job> jobs = getJobs(FIND_BY_UUID, uuid);
        if (jobs.size() > 0) {
            return jobs.get(0);
        } else {
            return null;
        }
    }

    private List<Job> getJobs(String statement, String by) {
        Connection conn = null;
        PreparedStatement stmt = null;
        List<Job> list = new ArrayList<>();
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(statement);
            if (by != null) {
                stmt.setString(1, by);
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(map(rs));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            close(stmt);
            close(conn);
        }
        return list;
    }

    public Job create(Job transientJob) {
        Connection conn = null;
        PreparedStatement stmt = null;
        String uuid = UUID.randomUUID().toString();
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(INSERT);
            stmt.setString(1, uuid);
            stmt.setString(2, StringUtils.trimToNull(transientJob.getProvider()));
            stmt.setString(3, StringUtils.trimToNull(transientJob.getPublisher()));
            stmt.setString(4, StringUtils.trimToNull(transientJob.getProviderPayload()));
            stmt.setString(5, StringUtils.trimToNull(transientJob.getPublisherPayload()));
            stmt.setTimestamp(6, new java.sql.Timestamp(new Date().getTime()));
            stmt.setString(7, State.CREATED.getValue());
            stmt.executeUpdate();
            return getJob(uuid);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            close(stmt);
            close(conn);
        }
        return null;
    }

    public int delete() {
        return delete(DELETE_ALL, null);
    }

    public int delete(String uuid) {
        return delete(DELETE_BY_UUID, uuid);
    }

    public int delete(State state) {
        return delete(DELETE_BY_STATE, state.getValue());
    }

    private int delete(String statement, String by) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(statement);
            if (by != null) {
                stmt.setString(1, by);
            }
            return stmt.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            close(stmt);
            close(conn);
        }
    }

    public Job update(Job job) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(UPDATE);
            map(stmt, job);
            stmt.executeUpdate();
            return getJob(job.getUuid());
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            close(stmt);
            close(conn);
        }
        return null;
    }

    private Job map(ResultSet rs) throws SQLException {
        Job job = new Job();
        job.setUuid(rs.getString("uuid"));
        job.setProvider(StringUtils.trimToNull(rs.getString("provider")));
        job.setPublisher(StringUtils.trimToNull(rs.getString("publisher")));
        job.setMessage(StringUtils.trimToNull(rs.getString("message")));
        job.setProviderPayload(StringUtils.trimToNull(rs.getString("providerpayload")));
        job.setPublisherPayload(StringUtils.trimToNull(rs.getString("publisherpayload")));
        if (rs.getTimestamp("created") != null)
            job.setCreated(rs.getTimestamp("created"));
        if (rs.getTimestamp("started") != null)
            job.setStarted(rs.getTimestamp("started"));
        if (rs.getTimestamp("completed") != null)
            job.setCompleted(rs.getTimestamp("completed"));
        job.setState(State.parse(rs.getString("state")));
        job.setSuccess(rs.getBoolean("success"));
        return job;
    }

    private PreparedStatement map(PreparedStatement stmt, Job job) throws SQLException {
        stmt.setString(1, StringUtils.trimToNull(job.getProvider()));
        stmt.setString(2, StringUtils.trimToNull(job.getPublisher()));
        stmt.setString(3, StringUtils.trimToNull(job.getMessage()));
        stmt.setString(4, StringUtils.trimToNull(job.getProviderPayload()));
        stmt.setString(5, StringUtils.trimToNull(job.getPublisherPayload()));
        stmt.setTimestamp(6, setDate(job.getCreated()));
        stmt.setTimestamp(7, setDate(job.getStarted()));
        stmt.setTimestamp(8, setDate(job.getCompleted()));
        stmt.setString(9, job.getState().getValue());
        stmt.setBoolean(10, job.getSuccess());
        stmt.setString(11, job.getUuid()); // where condition
        return stmt;
    }

    private java.sql.Timestamp setDate(Date date) {
        if (date == null) {
            return null;
        } else {
            return new java.sql.Timestamp(date.getTime());
        }
    }
*/
}
