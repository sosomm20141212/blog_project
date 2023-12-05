package com.example.blog.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class FileUpload {
    @Id
    private int seq;
    private String originalFileName;
    private String uuid;
    private String secretFlag;
    private String regDate;
    private String title;
    private String writer;
}
