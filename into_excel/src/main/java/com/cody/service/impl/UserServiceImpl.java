package com.cody.service.impl;

import com.cody.entity.UserEntity;
import com.cody.jpa.UserJPA;
import com.cody.service.UserService;
import com.cody.util.PoiExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserJPA userJPA;

    /*@Override
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
                entity.setName(row.getCell(0).toString());
                String age = row.getCell(1).toString();
                entity.setAge(Long.parseLong(age.substring(0,age.indexOf("."))));
                entity.setTel(row.getCell(2).toString());

                list.add(entity);
            }
        }

        return list;
    }*/


    /**
     *  导入具有合并单元格的Excel
     *
     * @param fileName
     * @param file
     * @return
     * @throws Exception
     */
    @Override
    public List<UserEntity> importExcel(String fileName, MultipartFile file) throws Exception
    {
        List<UserEntity> list = new ArrayList<>();

        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            throw new Exception("上传文件格式不正确");
        }

        final InputStream inputStrea = file.getInputStream();
        HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inputStrea));

        final Sheet xssSheet0 = wb.getSheetAt(0);
        Row row;

        List<CellRangeAddress> cras = PoiExcelUtil.getCombineCell(xssSheet0);

        for (int i = 1; i < xssSheet0.getPhysicalNumberOfRows(); i++)
        {
            Row BigRow = xssSheet0.getRow(i);
            if (BigRow == null)
            {
                break;
            }
            if (PoiExcelUtil.isMergedRegion(xssSheet0, i, 0))
            {
                int lastRow = PoiExcelUtil.getRowNum(cras, xssSheet0.getRow(i).getCell(0), xssSheet0);

                for (; i <= lastRow; i++)
                {
                    row = xssSheet0.getRow(i);

                    // 判断该行第2列是合并单元格
                    if (PoiExcelUtil.isMergedRegion(xssSheet0, i, 1)) {
                        int lastRow2 = PoiExcelUtil.getRowNum(cras, xssSheet0.getRow(i).getCell(1), xssSheet0);
                        for (; i <= lastRow2; i++) {
                            Row nextRow = xssSheet0.getRow(i);

                            UserEntity userEntity= new UserEntity();
                            buildAdmission(row, nextRow, userEntity);
                            list.add(userEntity);
//                            userJPA.save(userEntity);
                        }
                    }
                    else
                    {
                        UserEntity userEntity = new UserEntity();
                        buildAdmission(row, row, userEntity);
                        list.add(userEntity);
//                        userJPA.save(userEntity);
                    }
                }
                i--;
            }
            else
            {
                row = xssSheet0.getRow(i);

                UserEntity userEntity = new UserEntity();
                buildAdmission(row, row, userEntity);
                list.add(userEntity);
//                userJPA.save(userEntity);
            }
        }
        return list;
    }

    private void buildAdmission(Row row, Row BigRow, UserEntity userEntity) {
        final String name = PoiExcelUtil.getCellValue(BigRow.getCell(0));
        userEntity.setName(name);

        Integer age = null;
        if (PoiExcelUtil.getCellValue(BigRow.getCell(1)) != null) {
            age = Integer.valueOf(PoiExcelUtil.getCellValue(BigRow.getCell(1)));
            userEntity.setAge(age);
        }

        String tel = PoiExcelUtil.getCellValue(row.getCell(2));
        userEntity.setTel(tel);
    }

    @Override
    public List<UserEntity> saveList(List<UserEntity> list)
    {
        return userJPA.saveAll(list);
    }

    @Override
    public void deleteById(Long id)
    {
        userJPA.deleteById(id);
    }

    @Override
    public List<UserEntity> findAll()
    {
        return userJPA.findAll();
    }
}