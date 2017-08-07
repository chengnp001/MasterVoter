package com.asiainfo.common.mv;

import org.I0Itec.zkclient.ZkClient;

/**
 * @author Jay Wu
 */
public class MasterVoterConfig {

    /**
     * 应用标识
     */
    private String appId;

    /**
     * 应用节点标识
     */
    private String appNodeId;

    /**
     * ZK服务器列表
     */
    private String zkServers;

    // 连接ZK服务器的超时时间，默认为最大值Integer.MAX_VALUE.
    private int timeout = Integer.MAX_VALUE;

    // 默认sessionTimeout 为 10s;
    private int sessionTimeout = 10000;

    // ZkClient对象，没有就新建连接
    private ZkClient zkClient;

    public MasterVoterConfig(String appId, String appNodeId, String zkServers, ZkClient zkClient) {
        this.appId = appId;
        this.appNodeId = appNodeId;
        this.zkServers = zkServers;
        this.zkClient = zkClient;
    }

    public MasterVoterConfig(String appId, String appNodeId, String zkServers, ZkClient zkClient, int timeout, int sessionTimeout) {
        this.appId = appId;
        this.appNodeId = appNodeId;
        this.zkServers = zkServers;
        this.zkClient = zkClient;
        this.timeout = timeout;
        this.sessionTimeout = sessionTimeout;
    }

    public String getAppId() {
        return appId;
    }

    public MasterVoterConfig setAppId(String appId) {
        this.appId = appId;
        return this;
    }

    public String getAppNodeId() {
        return appNodeId;
    }

    public MasterVoterConfig setAppNodeId(String appNodeId) {
        this.appNodeId = appNodeId;
        return this;
    }

    public String getZkServers() {
        return zkServers;
    }

    public MasterVoterConfig setZkServers(String zkServers) {
        this.zkServers = zkServers;
        return this;
    }

    public int getTimeout() {
        return timeout;
    }

    public MasterVoterConfig setTimeout(int timeout) {
        this.timeout = timeout;
        return this;
    }

    public ZkClient getZkClient() {
        return zkClient;
    }

    public MasterVoterConfig setZkClient(ZkClient zkClient) {
        this.zkClient = zkClient;
        return this;
    }

    public int getSessionTimeout() {
        return sessionTimeout;
    }

    public MasterVoterConfig setSessionTimeout(int sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
        return this;
    }
}