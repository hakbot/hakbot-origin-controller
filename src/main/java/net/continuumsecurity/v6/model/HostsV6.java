package net.continuumsecurity.v6.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by stephen on 08/02/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class HostsV6 {
    List<HostV6> hosts;

    public List<HostV6> getHosts() {
        return hosts;
    }

    public void setHosts(List<HostV6> hosts) {
        this.hosts = hosts;
    }
}
