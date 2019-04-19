package com.cody.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @File Name: com.cody.controller
 * @Author: WQL //作者及
 * @Date: 2019/4/19 11:36//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
@Controller
public class demoController
{

    private static String UPLOADED_FOLDER = "C://Users//user//Desktop//upload//";

    @RequestMapping("/")
    public String demo()
    {
        return "demo";
    }

    @PostMapping("upload")
    public String upload(@RequestParam(name = "file")MultipartFile file, RedirectAttributes redirectAttributes)
    {
        if (file.isEmpty())
        {
            redirectAttributes.addFlashAttribute("message", "请选择文件");
            return "redirect:/";
        }

        try
        {
            byte[] bytes = file.getBytes();

            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "成功上传 '" + file.getOriginalFilename() + "'");
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return "redirect:/";
    }

}