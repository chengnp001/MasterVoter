package com.asiainfo.common.mv;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.List;

/**
 * @author Chengnp
 */
class ZKManager {
    /**
     * 订阅children变化
     */
    static void childChangesListener(final ZkClient zkClient, final String path) {
        zkClient.subscribeChildChanges(path, new IZkChildListener() {

            public void handleChildChange(String parentPath, List<String> currentChildren) throws Exception {
                if(currentChildren == null || currentChildren.isEmpty()) {
                    return ;
                }

                // 设置当前最小的节点 为master
                MasterVoter.masterAppNodeId = getMinAppNodeId(currentChildren);
            }
        });
    }

    /**
     * 获取最小的数字的节点.
     * @param appNodeIds 节点名称
     * @return 最小的节点名称
     */
    private static String getMinAppNodeId(List<String> appNodeIds) {
        String masterNode = "";
        long masterNodeValue = Long.MAX_VALUE;
        for (String appNodeId : appNodeIds) {
            int divideIndex = appNodeId.indexOf(MasterVoterConstants.SEQUENTIAL_DIVIDE);
            if (divideIndex == -1) {
                continue;
            }
            int length = appNodeId.length();
            int nodeIdLength = length - divideIndex - MasterVoterConstants.SEQUENTIAL_DIVIDE.length();
            long currentNodeValue = Long.parseLong(appNodeId.substring(nodeIdLength, length));
            if(currentNodeValue < masterNodeValue) {
                masterNodeValue = currentNodeValue;
                masterNode = appNodeId.substring(0, divideIndex);
            }
        }
        return masterNode;
    }
}


