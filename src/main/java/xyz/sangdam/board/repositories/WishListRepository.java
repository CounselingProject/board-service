package xyz.sangdam.board.repositories;

import xyz.sangdam.board.entities.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface WishListRepository extends JpaRepository<WishList, Long>, QuerydslPredicateExecutor<WishList> {
}