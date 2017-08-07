#MasterVoter

[TOC]

##MAVEN

```xml
<dependency>
    <groupId>com.asiainfo.zk</groupId>
    <artifactId>master-voter</artifactId>
    <version>1.0.0</version>
</dependency>
```

##SYSTEM REQUIREMENTS

* JDK 1.6+
* ZooKeeper 3.4.9+

###HOW TO USE

1、判断是不是主节点:
根据有序节点，来找到最先连接上zookeeper的服务，作为master。
节点目录为 /app/vote/服务名称+分隔符+序列号 （/app/vote/server1#*$0000000001）
MasterVoterConfig config = new MasterVoterConfig("app", "server1", "192.168.10.141:2181,192.168.10.141:2182,192.168.10.141:2183", null);
MasterVoter voter = new MasterVoter(config);
voter.isMasterNode()

####QUICK START

**End**# MasterVoter
# MasterVoter
