package com.cody.jpa;

import com.cody.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @File Name: com.cody.jpa
 * @Author: WQL //作者及
 * @Date: 2019/4/17 14:40//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public interface UserJPA extends JpaRepository<UserEntity, Long>
{
}
