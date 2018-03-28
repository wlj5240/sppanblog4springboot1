package net.sppan.blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import net.sppan.blog.entity.Post;
import net.sppan.blog.entity.Category;

public interface PostRepository extends JpaRepository<Post, Long> {

    /**
     * 获取推荐博客列表
     *
     * @param featured 推荐状态
     * @param privacy  权限
     * @param pageable
     * @return
     */
    Page<Post> findByFeaturedAndPrivacyOrderByCreateAtDesc(int featured, int privacy, Pageable pageable);

    /**
     * 获取博客分页
     *
     * @param category
     * @param privacy
     * @param pageable
     * @return
     */
    Page<Post> findByCategoryAndPrivacy(Category category, int privacy, Pageable pageable);

    /**
     * 根据浏览数量获取博客
     *
     * @param privacy  权限
     * @param pageable
     * @return
     */
    Page<Post> findByPrivacyOrderByViewsDesc(int privacy, Pageable pageable);

    /**
     * 根据分类获取文章数量
     *
     * @param category
     * @return
     */
    Long countByCategory(Category category);

    /**
     * 根据标签查找
     *
     * @param tagName
     * @return
     */
    Page<Post> findByTagsContaining(String tagName, Pageable pageable);
}
