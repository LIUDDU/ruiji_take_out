package com.liu.boot.controller;

import com.liu.boot.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

/**
 * @Author
 * @Date 2023/9/5 17:20
 * @Description 文件上传下载的控制类
 */
@RestController
@Slf4j
@RequestMapping("/common")
public class FileController {

    @Value("${ruiji.basePath}")
    private String basePath;

    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file) throws IOException {
        //获取文件原始文件名
        String originalFilename = file.getOriginalFilename();
        log.info("文件原始文件名：{}" +  originalFilename);
        int index = originalFilename.lastIndexOf(".");
        //获取文件后缀
        String fileSuffix = originalFilename.substring(index);
        //新文件名，防止上传文件重名，覆盖原文件的问题
        String newFileName = UUID.randomUUID().toString() + fileSuffix;
        log.info("新文件名：{}",newFileName);

        //文件存放路径
        File dir = new File(basePath);
        //判断路径是否存在，若不存在自动创建
        if (!dir.exists()){
            //创建文件保存目录
            dir.mkdir();
        }
        
        log.info("文件保存路径：{}",basePath);

        try {
            //将文件存放在本项目resources文件夹下的fileupload目录下
            file.transferTo(new File(basePath + newFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //给前端返回文件名，用于保存到数据库
        return R.success(newFileName);
    }

    /**
     * 用于文件下载
     * @param name
     * @param request
     * @param response
     */
    @GetMapping("/download")
    public void download(String name, HttpServletRequest request, HttpServletResponse response) throws IOException {

        //扩大作用域
        FileInputStream fileInputStream = null;

        ServletOutputStream outputStream = null;

        try {
            //输入流，通过输入流读取文件内容
            fileInputStream = new FileInputStream(new File(basePath + name));
            //输出流，通过输出流将文件写回浏览器，在浏览器展示图片
            outputStream = response.getOutputStream();

            int len = 0;
            //用于表示每次读写的字节长度
            byte[] bytes = new byte[1024];
            //判断文件是否已经读完
            while ( (len = fileInputStream.read(bytes)) != -1) {
                //写文件将字节数组中的数据写出
                outputStream.write(bytes,0,len);
                //刷新
                outputStream.flush();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            try {
                fileInputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
