package net.sppan.blog.service.impl;

import net.sppan.blog.common.vo.PostVo;
import net.sppan.blog.entity.Category;
import net.sppan.blog.entity.Post;
import net.sppan.blog.entity.Tag;
import net.sppan.blog.exception.ServiceException;
import net.sppan.blog.repository.PostRepository;
import net.sppan.blog.service.CategoryService;
import net.sppan.blog.service.PostService;
import net.sppan.blog.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    @Resource
    private PostRepository postRepository;

    @Resource
    private TagService tagService;

    @Resource
    private CategoryService categoryService;

    @Override
    public List<Post> findHotN(int n) {
        Pageable pageable = new PageRequest(0, n);
        return postRepository.findByPrivacyOrderByViewsDesc(0, pageable).getContent();
    }

    @Override
    public List<Post> findFeaturedN(int n) {
        Pageable pageable = new PageRequest(0, n);
        return postRepository.findByFeaturedAndPrivacyOrderByCreateAtDesc(1, 0, pageable).getContent();
    }

    @Override
    public Page<Post> findByCategoryANDPrivacy(Long categoryId, int privacy,
                                               Pageable pageable) {
        Category category = new Category();
        category.setId(categoryId);
        return postRepository.findByCategoryAndPrivacy(category, privacy, pageable);
    }

    @Override
    public Post findById(Long id) {
        Post post = postRepository.findOne(id);
        return post;
    }

    @Override
    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    public void saveOrUpdate(Post post, String tagArray) {
        if (post == null) {
            throw new ServiceException("操作对象不能为空");
        }

        List<Tag> tagList = new ArrayList<>();

        if (!StringUtils.isEmpty(tagArray)) {
            String[] tags = tagArray.split(",");
            for (String tagName : tags) {
                Tag tag = tagService.findByName(tagName);
                if (tag == null) {
                    tag = new Tag();
                    tag.setName(tagName);
                    tagService.saveOrUpdate(tag);
                }
                tagList.add(tag);
            }
        }

        post.setTags(tagList);
        if (post.getId() != null) {
            Post dbPost = findById(post.getId());
            dbPost.setTitle(post.getTitle());
            dbPost.setCategory(post.getCategory());
            dbPost.setPrivacy(post.getPrivacy());
            dbPost.setContent(post.getContent());
            dbPost.setSummary(post.getSummary());
            dbPost.setTags(post.getTags());
            postRepository.saveAndFlush(dbPost);
        } else {
            //设置博客基本属性
            post.setCreateAt(new Date());
            post.setFeatured(0);
            post.setStatus(0);
            post.setViews(0);
            post.setSummary(post.getSummary());
            postRepository.save(post);
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                //重新统计分类下面的文章数量
                categoryService.countCategoryHasBlog();
                //重新统计标签下面的文章数量
                tagService.countTagHasBlog();
            }
        }).start();
    }

    @Override
    public void change(Long id, String type) {
        Post post = findById(id);
        switch (type) {
            case "privacy":
                post.setPrivacy(post.getPrivacy() == 0 ? 1 : 0);
                break;
            case "featured":
                post.setFeatured(post.getFeatured() == 0 ? 1 : 0);
                break;
            case "status":
                post.setStatus(post.getStatus() == 0 ? 1 : 0);
                break;
            default:
                break;
        }
        postRepository.save(post);
    }

    @Override
    public void delete(Long id) {
        postRepository.delete(id);
    }

    @Override
    public Long getBlogCountByCategory(Category category) {
        return postRepository.countByCategory(category);
    }

    @Override
    public Page<Post> findByTagName(String tagName, Pageable pageable) {
        return postRepository.findByTagsContaining(tagName, pageable);
    }

    @Override
    public Long getBlogCountByTag(Tag tag) {
        return postRepository.countByTagsContaining(tag.getName());
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public void updateViewsCountById(Long blogId) {
        Post dbPost = postRepository.findOne(blogId);
        dbPost.setViews(dbPost.getViews() + 1);
    }

    @Override
    public PostVo findVoById(Long id) {
        PostVo vo = new PostVo();
        Post one = postRepository.findOne(id);
        BeanUtils.copyProperties(one, vo);

        Post pre = postRepository.findOne(Long.valueOf(id - 1));
        if (pre != null) {
            vo.setPrePostId(pre.getId());
            vo.setPrePostTitle(pre.getTitle());
        }

        Post next = postRepository.findOne(Long.valueOf(id + 1));
        if (next != null) {
            vo.setNextPostId(next.getId());
            vo.setNextPostTitle(next.getTitle());
        }
        return vo;
    }
}
