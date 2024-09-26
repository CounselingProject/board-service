package xyz.sangdam.board.services;

import lombok.RequiredArgsConstructor;
import xyz.sangdam.board.entities.BoardData;
import xyz.sangdam.board.entities.BoardView;
import xyz.sangdam.board.entities.QBoardView;
import xyz.sangdam.board.repositories.BoardDataRepository;
import xyz.sangdam.board.repositories.BoardViewRepository;
import xyz.sangdam.global.Utils;
import xyz.sangdam.member.MemberUtil;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardViewCountService {
    private final BoardViewRepository viewRepository;
    private final BoardDataRepository dataRepository;
    private final MemberUtil memberUtil;
    private final Utils utils;

    public void update(Long seq) {
        BoardData data = dataRepository.findById(seq).orElse(null);
        if (data == null) {
            return;
        }

        try {
            int uid = memberUtil.isLogin() ? memberUtil.getMember().getSeq().intValue() : utils.guestUid();

            BoardView boardView = new BoardView(seq, uid);
            viewRepository.saveAndFlush(boardView);

            // 전체 조회수
            QBoardView bv = QBoardView.boardView;
            long total = viewRepository.count(bv.seq.eq(seq));

            data.setViewCount((int) total);
            dataRepository.saveAndFlush(data);
        } catch (Exception e) {}
    }
}