package com.example.blog.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Board {
    @Id
    private int seq;
    private String title;
    private String content;
    private String writer;
    private int searchCount;
    private String writeDate;

    private String originalFileName;
    private String uuid;
    private String secretFlag;


    public void boardInsert(String title, String content, String writer) {
       String.format("INSERT INTO BOARD(title, content, wirter) VALUES ('%S','%S','%S')", title,content,writer);
    }

}
