package xyz.sangdam.board.controllers;

import lombok.Data;
import xyz.sangdam.global.CommonSearch;

import java.util.List;

@Data
public class BoardDataSearch extends CommonSearch {

    private String bid; // 게시판 ID
    private List<String> bids; // 게시한 ID 여러개

    private String sort; // 정렬 조건

    private List<String> email; // 회원 이메일
}
