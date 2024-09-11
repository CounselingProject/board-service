package xyz.sangdam.board.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class BoardViewId { // BoardView 엔티티에서 사용하는 복합 키를 정의하는 키 클래스다. 엔티티가 아니고, Board 엔티티의 기본 키를 정의하는 복합 키 클래스이다.
    private Long seq;
    private Integer uid;
}