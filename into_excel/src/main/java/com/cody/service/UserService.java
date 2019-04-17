package com.cody.service;

import com.cody.entity.UserEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @File Name: com.cody.service
 * @Author: WQL //作者及
 * @Date: 2019/4/17 17:26//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public interface UserService
{
    List<UserEntity> importExcel(String fileName, MultipartFile file) throws Exception;
}
