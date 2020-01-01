package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.Dept;
import com.atguigu.springcloud.service.DeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
* @author wangzhao
* @date 2019/12/30 22:33
*/

@RestController
public class DeptController_Consumer {

        @Autowired
        private DeptClientService service;

        @RequestMapping(value = "/consumer/dept/add")
        public boolean add(Dept dept){

            return this.service.add(dept);

        }

        @RequestMapping(value = "/consumer/dept/get/{id}")
        public Dept get(@PathVariable("id") Long id){

            System.out.println(123456);
            System.out.println(123456);

             return this.service.get(id);

        }

        @RequestMapping(value = "/consumer/dept/list")
        public List<Dept> list(){

              return this.service.list();

        }

}
