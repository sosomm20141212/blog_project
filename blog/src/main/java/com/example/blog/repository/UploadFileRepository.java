package com.example.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.blog.model.FileUpload;


@Repository
public interface UploadFileRepository extends  JpaRepository<FileUpload, Integer> {
    FileUpload findByUuid(String uid);
    FileUpload findBySeq(int seq);

}