package net.sppan.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.sppan.blog.entity.Youlian;

public interface YoulianRepository extends JpaRepository<Youlian, Long> {

	/**
	 * 查询所有可见
	 * @param status
	 * @return
	 */
	List<Youlian> findAllByStatus(int status);


}
