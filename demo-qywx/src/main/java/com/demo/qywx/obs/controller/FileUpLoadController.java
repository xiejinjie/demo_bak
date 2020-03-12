package com.demo.qywx.obs.controller;

import com.example.demo.service.IObsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/file")
public class FileUpLoadController {
    public static final String filePath =  FileUpLoadController.class.getClassLoader().getResource("").getPath() + File.separator + "material";
    @Autowired
    public IObsService obsService;

    @RequestMapping(value = "/formUpload", method = RequestMethod.POST)
    public void formUpload(@RequestParam("file") MultipartFile file){
        System.out.println("========上传文件========");
        saveLocal(file);

    }
    private void saveLocal(MultipartFile file){
        String fileName = file.getOriginalFilename();
        try {
            File newfile = new File(filePath +"/" + fileName);
            System.out.println(newfile.getAbsolutePath());
            createFile(newfile);
            InputStream is = file.getInputStream();
            FileOutputStream fos = new FileOutputStream(newfile);
            byte[] temp = new byte[1024];
            while (is.read(temp) != -1){
                fos.write(temp);
            }
            fos.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/testObs")
    public void testObs(){
        obsService.listObject();
    }
    private void createFile(File file){
        File parentFile = file.getParentFile();
        if (!parentFile.exists()){
            parentFile.mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
