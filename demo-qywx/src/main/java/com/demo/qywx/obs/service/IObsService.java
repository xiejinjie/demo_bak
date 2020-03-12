package com.demo.qywx.obs.service;

import org.springframework.web.multipart.MultipartFile;

public interface IObsService {
    void upload(MultipartFile file);
    void listObject();
}
