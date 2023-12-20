package com.example.blog.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.example.blog.repository.BoardRepository;
import com.example.blog.repository.UserRepository;


import com.example.blog.model.Board;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.*;

@Controller
public class DBController {
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    UserRepository userRepository;

//메인
    @GetMapping("/main")
    public String main(){
        return "html/main";
    }

//게시판
    @GetMapping("/home")
    public String boardList(Model model) {
        int count = boardRepository.findAll().size();
        Map<String, Integer> cnt = new HashMap<>();
        cnt.put("count", count);
        model.addAttribute("count", cnt);
        model.addAttribute("board", boardRepository.findAll());
        return "html/home";
    }

// 게시판 입력 : insert, update 메소드
    @GetMapping("/board/insert")
    public String boardInsert(
        @RequestParam("title") String title,
        @RequestParam("content") String content,
        @RequestParam("writer") String writer
    ) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = now.format(formatter);
        Board data = new Board();
        data.setTitle(title);
        data.setContent(content);
        data.setWriter(writer);
        // data.setSearchCount(1);
        data.setWriteDate(formattedDate);
        boardRepository.save(data);

        return "html/saveend";
    }

//게시판 클릭 시 상세페이지 이동
    @GetMapping("/board/detail")
    public String boardDetail(@RequestParam("seq") int seq, Model model) {
        String title = boardRepository.findTitleBySeq(seq);
        String content = boardRepository.findContentBySeq(seq);
        String writer = boardRepository.findWriterBySeq(seq);
        String writeDate = boardRepository.findWriteDateBySeq(seq);

        int count = boardRepository.findAll().size();
        Map<String, Integer> cnt = new HashMap<>();
        cnt.put("count", count);
        model.addAttribute("count", cnt);
        model.addAttribute("board", boardRepository.findAll());

        model.addAttribute("seq", seq);
        model.addAttribute("sqlStmtTitle", title);
        model.addAttribute("sqlStmtContent", content);
        model.addAttribute("sqlStmtWriter", writer);
        model.addAttribute("sqlStmtWriteDate", writeDate);
    
        return "html/detail";
    }

//글 업데이트 방법
    @GetMapping("/board/update")
    public String boardUpdate(
        @RequestParam("seq") int seq,
        @RequestParam("title") String title,
        @RequestParam("content") String content,
        @RequestParam("writer") String writer
    ) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = now.format(formatter);
        Optional<Board> boardOptional = boardRepository.findBySeq(seq);
        
        if(boardOptional.isPresent()){
            Board data = new Board();
            data.setTitle(title);
            data.setContent(content);
            data.setWriter(writer);
            data.setSearchCount(2);
            data.setWriteDate(formattedDate);
            boardRepository.save(data);
        }
        
        return "html/saveend";
    }
}
