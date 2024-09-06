package xyz.sangdam.board.services.config;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import xyz.sangdam.board.entities.Board;
import xyz.sangdam.board.repositories.BoardRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardConfigDeleteService {

    private final BoardRepository boardRepository;
    private final BoardConfigInfoService configInfoService;
    //private final Utils utils;

    /**
     * 게시판 삭제
     * 
     * @param bid : 게시판 아이디
     */
    public void delete(String bid) {
        Board board = configInfoService.get(bid);

        String gid = board.getGid();

        boardRepository.delete(board);

        boardRepository.flush();

        //fileDeleteService.delete(gid);
    }


}
