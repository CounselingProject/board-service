package xyz.sangdam.board.services.config;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import xyz.sangdam.board.controllers.RequestBoardConfig;
import xyz.sangdam.board.entities.Board;
import xyz.sangdam.board.repositories.BoardRepository;
import xyz.sangdam.file.services.FileUploadDoneService;
import xyz.sangdam.global.Utils;
import xyz.sangdam.member.constants.UserType;

@Service
@RequiredArgsConstructor
public class BoardConfigSaveService {

    private final BoardRepository boardRepository;
    private final FileUploadDoneService fileUploadDoneService;
    private final Utils utils;

    public void save(RequestBoardConfig form) {
        String bid = form.getBid();
        String mode = form.getMode();
        mode = StringUtils.hasText(mode) ? mode : "add";

        Board board = boardRepository.findById(bid).orElseGet(Board::new);

        if (mode.equals("add")) { // 게시판 등록시 gid, bid 등록 -> 수정시에는 변경 X
            board.setBid(bid);
            board.setGid(form.getGid());
        }

        board.setBName(form.getBname());
        board.setActive(form.isActive());
        board.setRowsPerPage(form.getRowsPerPage());
        board.setPageCountPc(form.getPageCountPc());
        board.setPageCountMobile(form.getPageCountMobile());
        board.setUseReply(form.isUseReply());
        board.setUseComment(form.isUseComment());
        board.setUseEditor(form.isUseEditor());
        board.setUseUploadImage(form.isUseUploadImage());
        board.setUseUploadFile(form.isUseUploadFile());
        board.setLocationAfterWriting(form.getLocationAfterWriting());
        board.setShowListBelowView(form.isShowListBelowView());
        board.setSkin(form.getSkin());
        board.setCategory(form.getCategory());

        board.setListAccessType(UserType.valueOf(form.getListAccessType()));
        board.setViewAccessType(UserType.valueOf(form.getViewAccessType()));
        board.setWriteAccessType(UserType.valueOf(form.getWriteAccessType()));
        board.setReplyAccessType(UserType.valueOf(form.getReplyAccessType()));
        board.setCommentAccessType(UserType.valueOf(form.getCommentAccessType()));

        // 로그인한 사용자만 접근 가능
        board.setPrivateAccess(form.isPrivateAccess());

        board.setListOrder(form.getListOrder());

        boardRepository.saveAndFlush(board);

        // 파일 업로드 완료 처리
        fileUploadDoneService.process(board.getGid());
    }
}