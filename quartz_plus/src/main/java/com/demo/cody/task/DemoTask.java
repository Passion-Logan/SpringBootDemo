package com.demo.cody.task;

import org.springframework.stereotype.Component;

/**
 * @File Name: com.demo.cody.task
 * @Author: WQL //作者及
 * @Date: 2019/8/5 22:00//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
@Component
public class DemoTask
{

    public void taskWithParams(String param1, Integer param2) {
        System.out.println("这是有参示例任务：" + param1 + param2);
    }

    public void taskNoParams() {
        System.out.println("执行无参示例任务");
    }

}
