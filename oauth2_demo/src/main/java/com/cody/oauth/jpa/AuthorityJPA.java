package com.cody.oauth.jpa;

import com.cody.oauth.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @File Name: com.cody.oauth.jpa
 * @Author: WQL //作者及
 * @Date: 2019/3/28 10:53//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public interface AuthorityJPA extends JpaRepository<Authority, String>
{
}
