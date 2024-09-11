package xyz.sangdam.board.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@IdClass(BoardViewId.class) // 복합 기본 키를 가지고 있으며, 이 키를 정의하기 위해 BoardViewId 클래스를 사용
public class BoardView {
    @Id
    private Long seq; // 게시글 번호

    @Id
    @Column(name="_uid")
    private Integer uid; // 비회원 - guestUid(), 회원 - 회원번호
} // 조회수 카운팅