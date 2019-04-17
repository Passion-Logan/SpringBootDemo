package com.cody.service.impl;

import com.cody.entity.UserEntity;
import com.cody.service.UserService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @File Name: com.cody.service.impl
 * @Author: WQL //作者及
 * @Date: 2019/4/17 17:27//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
@Service
public class UserServiceImpl implements UserService
{

    @Override
    public List<UserEntity> importExcel(String fileName, MultipartFile file) throws Exception
    {
        List<UserEntity> list = new ArrayList<>();

        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            throw new Exception("上传文件格式不正确");
        }
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        InputStream is = file.getInputStream();
        HSSFWorkbook wb = null;
        if (isExcel2003) {
            wb = new HSSFWorkbook(new POIFSFileSystem(is));
        }
        // 有多少个sheet
        int sheets = wb.getNumberOfSheets();
        for (int i = 0; i < sheets; i++) {
            HSSFSheet sheet = wb.getSheetAt(i);
            // 获取多少行
            int rows = sheet.getPhysicalNumberOfRows();
            UserEntity entity = null;
            // 遍历每一行，注意：第 0 行为标题
            for (int j = 1; j < rows; j++) {
                entity = new UserEntity();
                // 获得第 j 行
                HSSFRow row = sheet.getRow(j);
                entity.setId(Integer.parseInt(row.getCell(0).toString()));
                entity.setName(row.getCell(1).toString());
                entity.setAge(Long.parseLong(row.getCell(2).toString()));
                entity.setTel(row.getCell(3).toString());

                list.add(entity);
            }
        }

        return list;
    }
}