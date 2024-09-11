package xyz.sangdam.board.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.sangdam.board.entities.Like;
import xyz.sangdam.board.entities.LikeId;
import xyz.sangdam.board.repositories.LikeRepository;
import xyz.sangdam.member.MemberUtil;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final MemberUtil memberUtil;
    private final LikeRepository likeRepository;

    // 게시글에 좋아요 추가
    public void add(Long seq) {
        if (!memberUtil.isLogin()) {
            return;
        }

        String email = memberUtil.getMember().getEmail();

        Like like = new Like();
        like.setSeq(seq);
        like.setEmail(email);
        likeRepository.saveAndFlush(like);
    }

    // 게시글에 좋아요 제거
    public void remove(Long seq) {
        if (!memberUtil.isLogin()) {
            return;
        }

        String email = memberUtil.getMember().getEmail();
        LikeId likeId = new LikeId(seq, email);
        likeRepository.deleteById(likeId);
        likeRepository.flush();
    }

    // 특정 게시글 번호에 대한 좋아요 수를 반환
    public long getLikeCount(Long seq) {
        return likeRepository.countBySeq(seq);
    }

    // 좋아요가 등록되어 있는지 확인
    public boolean check(Long seq) {
        if (memberUtil.isLogin()) {
            String email = memberUtil.getMember().getEmail();
            return likeRepository.existsById(new LikeId(seq, email));
        }
        return false;
    }
}