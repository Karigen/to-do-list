package org.tai.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.tai.todolist.entity.JSONResponseEntity;
import org.tai.todolist.service.FileService;

import java.io.IOException;

/**
 * @author Karigen B
 * @create 2022-08-30 16:42
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public JSONResponseEntity upload(@RequestPart("image") MultipartFile multipartFile) throws IOException {
        String url = fileService.upload(multipartFile);
        return JSONResponseEntity.ok()
                .newData("url", url);
    }

}
