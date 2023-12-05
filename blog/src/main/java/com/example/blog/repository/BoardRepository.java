package com.example.blog.repository;

import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.blog.model.Board;


@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
    @Query("SELECT b.title FROM Board b WHERE b.seq = :seq")
    String findTitleBySeq(@Param("seq") int seq);
    @Query("SELECT b.content FROM Board b WHERE b.seq = :seq")
    String findContentBySeq(@Param("seq") int seq);
    @Query("SELECT b.writer FROM Board b WHERE b.seq = :seq")
    String findWriterBySeq(@Param("seq") int seq);
    @Query("SELECT b.writeDate FROM Board b WHERE b.seq = :seq")
    String findWriteDateBySeq(@Param("seq") int seq);

    Board findBySeqAndTitleAndWriterAndWriteDate(int seq, String title, String writer, String writeDate);
    Board findByTitleAndContentAndWriterAndWriteDate(String tilte, String content, String writer, String writeDate);
    // Board findBySeq(int seq);
    Optional<Board> findBySeq(int seq);

    Board findByUuid(String uid);
} 
