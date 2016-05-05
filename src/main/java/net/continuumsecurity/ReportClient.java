package net.continuumsecurity;

import java.util.Map;

import net.continuumsecurity.v5.model.Issue;

/**
 * Created by stephen on 08/02/15.
 */
public interface ReportClient extends SessionClient {
	public Map<Integer, Issue> getAllIssuesSortedByPluginId(String uuid);
}