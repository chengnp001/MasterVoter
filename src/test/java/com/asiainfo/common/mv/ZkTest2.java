package com.asiainfo.common.mv;

import org.I0Itec.zkclient.ZkClient;

/**
 * @author Chengnp
 */
public class ZkTest2 {

    public static void main(String[] args) {
        System.out.println("连接zookeepr，服务器名称为" + TestConstants.appNodeIds[1]);
        System.out.println("采用有序的算法计算master，最先连接上的为Master");

        // 1.初始化数据 每个客户端连接zookeeper创建不同的节点值
        ZkClient zkClient = new ZkClient(TestConstants.servers,100000);
        MasterVoterConfig config = new MasterVoterConfig(TestConstants.appId, TestConstants.appNodeIds[1], TestConstants.servers, zkClient);
        MasterVoter voter = new MasterVoter(config);

        // 打印结果
        int i = 0;
        while(i< 100) {
            System.out.print("这是第" + (i+1) +"次检测 ，结果为 ");
            if(voter.isMasterNode()) {
                System.out.println(TestConstants.appNodeIds[1]+" 是 Master");
            }else{
                System.out.println(TestConstants.appNodeIds[1]+" 不是 Master");
            }
            try{
                Thread.sleep(3000);
            }catch (Exception e) {e.printStackTrace();}
            i++;
            System.out.println("");
        }
    }

}
