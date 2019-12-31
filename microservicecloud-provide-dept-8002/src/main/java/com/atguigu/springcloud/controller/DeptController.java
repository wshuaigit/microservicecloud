package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.Dept;
import com.atguigu.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wangzhao
 * @date 2019/12/30 21:49
 */
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;


    @RequestMapping(value = "/dept/add",method = RequestMethod.POST)
    public boolean add(@RequestBody Dept dept){
        return deptService.add(dept);
    }

    @RequestMapping(value = "/dept/get/{id}",method = RequestMethod.GET)
    public Dept get(@PathVariable Long id){
        return deptService.get(id);
    }

    @RequestMapping(value = "/dept/list",method = RequestMethod.GET)
    public List<Dept> list(){
        return deptService.list();
    }


    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/dept/discovery" , method = RequestMethod.GET)
    public Object discovery(){



        List<String> list = client.getServices();


        System.out.println("*******" + list);
        List<ServiceInstance> srvList = client.getInstances("MICROSERVICECLOUD-DEPT");
        for (ServiceInstance instance : srvList){
            System.out.println(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() +"\t" + instance.getUri());
        }

        return this.client;
    }
}
