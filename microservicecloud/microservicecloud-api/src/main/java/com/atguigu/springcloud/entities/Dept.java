package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author wangzhao
 * @date 2019/12/30 20:25
 */

@AllArgsConstructor     // 全参构造方法
@NoArgsConstructor      // 无参构造方法
@Data                   // 生成 get、set 方法
@Accessors(chain = true)
public class Dept implements Serializable {

    private Long deptno; // 主键
    private String dname; // 部门名称
    private String db_source; // 来自哪个数据库，因为微服务架构可以一个服务对应一个数据库，同一个信息被存储到不同数据库。

}
