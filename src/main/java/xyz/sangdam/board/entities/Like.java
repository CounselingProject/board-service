package xyz.sangdam.board.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="_LIKE")
@IdClass(LikeId.class)
public class Like {
    @Id
    private Long seq; // 게시글 번호

    @Id
    @Column(length=80)
    private String email; // 회원 이메일 주소
}