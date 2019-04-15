package com.cody.controller;

import com.cody.dao.ResultJson;
import com.cody.dao.entity.DemoEntity;
import com.cody.service.DemoService;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @File Name: com.cody.controller
 * @Author: WQL //作者及
 * @Date: 2019/4/15 10:30//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
@RestController
@RequestMapping("demo")
public class DemoController
{

    @Autowired
    private DemoService demoService;

    @PostMapping("getUsers")
    public ResultJson getUsers()
    {
        List<DemoEntity> users = demoService.getUsers();
        return ResultJson.ok(users);
    }


    @PostMapping("getById")
    public ResultJson getById(@NotBlank(message = "不能为空") Integer id)
    {
        DemoEntity entity = demoService.getById(id);
        return ResultJson.ok(entity);
    }

    @PostMapping("addUser")
    public ResultJson addUser(DemoEntity demoEntity)
    {
        demoService.saveUser(demoEntity);
        return ResultJson.ok("添加成功");
    }

    @PostMapping("updateUser")
    public ResultJson updateUser(DemoEntity demoEntity)
    {
        demoService.updateUser(demoEntity);
        return ResultJson.ok("修改成功");
    }

    @PostMapping("deleteById")
    public ResultJson deleteById(Integer id)
    {
        demoService.deleteById(id);
        return ResultJson.ok("删除成功");
    }
}