package net.sppan.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import net.sppan.entity.Blog;


public interface BlogService {

	List<Blog> findHotN(int n);

	List<Blog> findFeaturedN(int i);

	Page<Blog> findByCategoryANDPrivacy(Long categoryId, int privacy, PageRequest pageRequest);
}
