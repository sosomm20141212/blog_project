package com.example.blog.Controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.UUID;
import org.springframework.ui.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.util.*;

import java.io.File;

import com.example.blog.model.FileUpload;
import com.example.blog.repository.UploadFileRepository;


@Controller
public class UploadController {
    @Autowired
    UploadFileRepository uploadFileRepository;


//여러개의 파일 업로드
    @GetMapping("/upload")
    public String upload4(){
        return "/html/upload";
    }
    @PostMapping("/upload")
    public String uploadPost(@RequestParam("file") MultipartFile[] mFiles,
                             @RequestParam("writer") String writer,
                             @RequestParam("title") String title
    ){
        FileUpload fileUpload = new FileUpload();
        String saveFolder = "C:/data/";
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = now.format(formatter);

        for (MultipartFile mFile : mFiles){
            String uid = UUID.randomUUID().toString();
            String oName = mFile.getOriginalFilename();
            int seq = uploadFileRepository.findAll().size()+1;
            File saveFile = new File(saveFolder+oName);
            
            fileUpload.setWriter(writer);
            fileUpload.setOriginalFileName(oName);
            fileUpload.setRegDate(formattedDate);
            fileUpload.setSecretFlag("1");
            fileUpload.setUuid(uid);
            fileUpload.setSeq(seq);
            fileUpload.setTitle(title);
            try {                                                
                mFile.transferTo(saveFile);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            } 
        }
        return "html/saveend";
    }

    @GetMapping("/storage")
    public String storage(Model model){
        int count = uploadFileRepository.findAll().size();
        Map<String, Integer> cnt = new HashMap<>();
        cnt.put("count", count);
        model.addAttribute("count", cnt);
        model.addAttribute("board", uploadFileRepository.findAll());
        return "html/Storage";
    }
}
