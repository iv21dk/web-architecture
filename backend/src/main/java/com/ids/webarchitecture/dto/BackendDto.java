package com.ids.webarchitecture.dto;

import com.ids.webarchitecture.model.NodeStatus;

public class BackendDto {
    private String host;
    private Integer port;
    private NodeStatus status;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public NodeStatus getStatus() {
        return status;
    }

    public void setStatus(NodeStatus status) {
        this.status = status;
    }
}
