package com.cody.controller;

import com.cody.entity.UserEntity;
import com.cody.jpa.UserJPA;
import com.cody.service.UserService;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * @File Name: com.cody.controller
 * @Author: WQL //作者及
 * @Date: 2019/4/17 15:19//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
@Controller
public class DemoController
{

    @Autowired
    private UserJPA userJPA;

    @Autowired
    private UserService userService;

    @RequestMapping("")
    public String demo()
    {
        return "demo";
    }

    /**
     * 导出Excel
     *
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "export")
    public void export(HttpServletResponse response) throws IOException
    {
        List<UserEntity> list = userJPA.findAll();
        //声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建一个Excel表单,参数为sheet的名字
        HSSFSheet sheet = workbook.createSheet("用户名单");
        //创建表头
        setTitle(workbook, sheet);
        //新增数据行，并且设置单元格数据
        HSSFRow hssfRow = sheet.createRow(1);
        for (UserEntity entity : list)
        {
            hssfRow.createCell(0).setCellValue(entity.getId());
            hssfRow.createCell(1).setCellValue(entity.getName());
            hssfRow.createCell(2).setCellValue(entity.getAge());
            hssfRow.createCell(3).setCellValue(entity.getTel());
        }
        String fileName = "用户名单.xls";
        //清空response
        response.reset();
        //设置response的Header
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
        OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
        //将excel写入到输出流中
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

    @RequestMapping(value = "/import")
    public String importExcel(@RequestParam(value = "filename") MultipartFile multipartFile)
    {
        String fileName = multipartFile.getOriginalFilename();
        List<UserEntity> save = null;

        try
        {
            save = userService.importExcel(fileName, multipartFile);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        if (save != null)
        {
            userJPA.saveAll(save);
        }

        return "redirect:/";
    }

    /**
     * 创建表头
     * @param workbook
     * @param sheet
     */
    private void setTitle(HSSFWorkbook workbook, HSSFSheet sheet)
    {
        HSSFRow row = sheet.createRow(0);
        // 设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
        sheet.setColumnWidth(8, 60 * 256);
        // 设置为居中加粗
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 导出的Excel头部
        String[] headers = { "编号", "姓名", "年龄", "联系方式"};
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 16);
        for (short i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
            cell.setCellStyle(style);
        }
    }

}