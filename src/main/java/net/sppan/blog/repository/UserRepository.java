package net.sppan.blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import net.sppan.blog.entity.User;

public interface UserRepository extends JpaRepository<User, Long>, QueryDslPredicateExecutor<User> {

	User findByUserName(String userName);

	Page<User> findAllByOrderByCreateAtDesc(Pageable pageable);

}
