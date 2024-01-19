package com.dailycodebuffer.taskassigned.controller;

import com.dailycodebuffer.taskassigned.message.ResponseMessage;
import com.dailycodebuffer.taskassigned.service.FileStorageService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {


    @Autowired
    private FileStorageService storageService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@Valid @Size(max= 30*1024*1024 ,message = "File must be less than 30 MB") @RequestParam("file") MultipartFile file) {
        String message = "";
        if(!"application/pdf".equals(file.getContentType())){
            return ResponseEntity.badRequest().body(new ResponseMessage("File must be pdf"));
        }
        try {
            storageService.store(file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
        }
    }
}
