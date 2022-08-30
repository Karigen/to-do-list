package org.tai.todolist.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Karigen B
 * @create 2022-08-30 16:11
 */
public interface FileService {

    String upload(MultipartFile multipartFile) throws IOException;

}
