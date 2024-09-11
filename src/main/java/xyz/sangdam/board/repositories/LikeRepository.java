package xyz.sangdam.board.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import xyz.sangdam.board.entities.Like;
import xyz.sangdam.board.entities.LikeId;

public interface LikeRepository extends JpaRepository<Like, LikeId> {

    // 특정 게시글 번호에 대한 좋아요 수 카운트
    long countBySeq(@Param("seq") Long seq);
}