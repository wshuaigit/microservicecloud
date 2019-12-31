package com.atguigu.myrule;


import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wangzhao
 * @date 2019/12/31 22:39
 */
public class RandomRule_ZY extends AbstractLoadBalancerRule {

    AtomicInteger total = new AtomicInteger(0);

    AtomicInteger index = new AtomicInteger(0);



    /**
     * Randomly choose from all living servers
     */
    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        }

        // 微服务的提供者
        Server server = null;

        while (server == null) {

            // 线程如果被中断，返回 null
            if (Thread.interrupted()) {
                return null;
            }


            List<Server> upList = lb.getReachableServers();
            List<Server> allList = lb.getAllServers();

            // 获取某一个微服务其提供者的个数
            int serverCount = allList.size();
            if (serverCount == 0) {
                return null;
            }

            // 随机产生一个数，范围 0 - serverCount
            // java.util.Random().next(serverCount)
            if (total.get() == 5) {
                index.set(chooseRandomInt(serverCount));
                total.set(0);
            }

            total.incrementAndGet();

            server = upList.get(index.get());

            if (server == null) {
                Thread.yield();
                continue;
            }

            if (server.isAlive()) {
                return (server);
            }

            // Shouldn't actually happen.. but must be transient or a bug.
            server = null;
            Thread.yield();
        }

        return server;

    }

    protected int chooseRandomInt(int serverCount) {
        return ThreadLocalRandom.current().nextInt(serverCount);
    }

    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }
}
