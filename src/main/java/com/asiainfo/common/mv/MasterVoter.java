package com.asiainfo.common.mv;

import com.asiainfo.common.util.DateUtil;
import org.I0Itec.zkclient.ZkClient;

/**
 * @author Jay Wu
 */
public class MasterVoter {

    // 配置参数
    private MasterVoterConfig config;

    // zkClient连接
    private ZkClient zkClient = null;

    static String masterAppNodeId = null;

    public MasterVoter(MasterVoterConfig config) {
        if (config.getZkClient() != null) {
            zkClient = config.getZkClient();
        } else {
            zkClient = new ZkClient(config.getZkServers(), config.getSessionTimeout(), config.getTimeout());
        }
        // 建立应用节点和目录 (目录为 /app/vote)
        String appIdDir = "/" + config.getAppId();
        if (!zkClient.exists(appIdDir)) {
            zkClient.createPersistent(appIdDir, appIdDir);
        }
        String zNodeDir = appIdDir + MasterVoterConstants.VOTE_NODE;
        if (!zkClient.exists(zNodeDir)) {
            zkClient.createPersistent(zNodeDir, zNodeDir);
        }
        ZKManager.childChangesListener(zkClient, zNodeDir);
        // 创建临时有序节点 目录为 /app/vote/服务名称+分隔符+序列号 （/app/vote/server1#*$0000000001）
        String zNodeName = zNodeDir + "/" + config.getAppNodeId() + MasterVoterConstants.SEQUENTIAL_DIVIDE;
        if (!zkClient.exists(zNodeName)) {
            zkClient.createEphemeralSequential(zNodeName, DateUtil.getNow().getTime());
        }
        this.config = config;
    }

    /**
     * 判断是不是为Master(根据有序节点，来找到最先连接上的服务，作为master)
     */
    public boolean isMasterNode() {
        return config.getAppNodeId().equals(MasterVoter.masterAppNodeId);
    }

    public ZkClient getZkClient() {
        return zkClient;
    }
}