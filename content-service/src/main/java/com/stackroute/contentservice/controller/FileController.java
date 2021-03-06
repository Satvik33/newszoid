package com.stackroute.contentservice.controller;

import com.stackroute.contentservice.service.AmazonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
@RequestMapping("api/v1")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    private AmazonClient amazonClient;

    @Autowired
    public FileController(AmazonClient amazonClient) {
        this.amazonClient = amazonClient;
    }

    @PostMapping("/file")
    public ResponseEntity<?> uploadFile(@RequestPart(value = "file") MultipartFile file) {
        return new ResponseEntity<String>(this.amazonClient.uploadFile(file), HttpStatus.CREATED);
    }


    @DeleteMapping("/file")
    public String deleteFile(@RequestPart(value = "url") String fileUrl) {
        return this.amazonClient.deleteFileFromS3Bucket(fileUrl);
    }

}
