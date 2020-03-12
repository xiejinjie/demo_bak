package com.demo.qywx.obs.service;

import com.example.demo.controller.FileUpLoadController;
import com.obs.services.ObsClient;
import com.obs.services.model.ObjectListing;
import com.obs.services.model.ObsObject;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class ObsService implements IObsService {
    public static final String endPoint = "https://obs.cn-south-1.myhuaweicloud.com";
    public static final String ak = "Q9ZCTYGLGKMJDPJPJDAA";
    public static final String sk = "SNAydHETw7sVfbOy9EzfCBR7ZdpWlX2Fjf2XhpBD";
    public static final String bucketName = "hscrm";
    public static final String materialPath = "hscrm/material/";

    @Override
    public void upload(){
        ObsClient obsClient = new ObsClient(ak, sk, endPoint);
        obsClient.putObject(bucketName, materialPath + "c7519d034fc209688249fce55a0149d3.jpg", new File(FileUpLoadController.filePath + File.separator + "c7519d034fc209688249fce55a0149d3.jpg"));
        try {
            obsClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void listObject() {
        ObsClient obsClient = new ObsClient(ak, sk, endPoint);
        ObjectListing objectListing = obsClient.listObjects(bucketName);
        for(ObsObject obsObject : objectListing.getObjects()){
            System.out.println(" - " + obsObject.getObjectKey() + "  " +  "(size = " + obsObject.getMetadata().getContentLength() + ")");
        }
        try {
            obsClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
