package net.sppan.blog.service;

import java.util.List;

import net.sppan.blog.common.vo.PostVo;
import net.sppan.blog.entity.Post;
import net.sppan.blog.entity.Category;
import net.sppan.blog.entity.Tag;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PostService {

    /**
     * 获取浏览量Top N
     *
     * @param n
     * @return
     */
    List<Post> findHotN(int n);

    /**
     * 获取推荐Top N
     *
     * @param i
     * @return
     */
    List<Post> findFeaturedN(int i);

    /**
     * 获取分页
     *
     * @param categoryId 类型ID
     * @param privacy    权限
     * @return
     */
    Page<Post> findByCategoryANDPrivacy(Long categoryId, int privacy, Pageable pageable);

    /**
     * 根据ID查找
     *
     * @param id
     * @return
     */
    Post findById(Long id);

    /**
     * 分页查询
     *
     * @param pageable
     * @return
     */
    Page<Post> findAll(Pageable pageable);

    void saveOrUpdate(Post post);

    /**
     * 改变博客状态
     *
     * @param id
     * @param type
     */
    void change(Long id, String type);

    /**
     * 删除
     *
     * @param id
     */
    void delete(Long id);

    /**
     * 根据分类统计文章数量
     *
     * @param category 分类
     * @return
     */
    Long getBlogCountByCategory(Category category);

    /**
     * 根据标签查找
     *
     * @param tagName
     * @return
     */
    Page<Post> findByTagName(String tagName, Pageable pageable);

    /**
     * 根据标签统计文章数量
     *
     * @param tag
     * @return
     */
    Long getBlogCountByTag(Tag tag);

    /**
     * 查询所有博客
     *
     * @return
     */
    List<Post> findAll();

    /**
     * 更新浏览量
     *
     * @param blogId
     */
    void updateViewsCountById(Long blogId);

    PostVo findVoById(Long id);
}
