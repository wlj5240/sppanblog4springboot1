package net.sppan.blog.service.impl;

import net.sppan.blog.entity.Category;
import net.sppan.blog.exception.ServiceException;
import net.sppan.blog.repository.CategoryRepository;
import net.sppan.blog.service.CategoryService;
import net.sppan.blog.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryRepository categoryRepository;
    @Resource
    private PostService postService;

    @Override
    public List<Category> findVisible() {
        return categoryRepository.findByStatus(0);
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findOne(id);
    }

    @Override
    public void saveOrUpdate(Category category) {
        if (category.getId() != null) {
            Category dbcCategory = findById(category.getId());
            dbcCategory.setName(category.getName());
            categoryRepository.save(dbcCategory);
        } else {
            category.setCount(0);
            category.setStatus(0);
            categoryRepository.saveAndFlush(category);
        }
    }

    @Override
    public void delete(Long id) {
        Long count = postService.getBlogCountByCategory(findById(id));
        if (count != null && count > 0) {
            throw new ServiceException("分类下面包含博客，不能删除");
        } else {
            categoryRepository.delete(id);
        }
    }

    @Override
    public void changeStatus(Long id) {
        if (id == null) {
            throw new ServiceException("ID不能为空");
        }
        Category category = findById(id);
        category.setStatus(category.getStatus() == 0 ? 1 : 0);
        categoryRepository.saveAndFlush(category);
    }

    @Override
    public void increaseCount(Long id) {
        Category category = categoryRepository.findOne(id);
        category.setCount(category.getCount() + 1);
    }

    @Override
    public void decreaseCount(Long id) {
        Category category = categoryRepository.findOne(id);
        category.setCount(category.getCount() - 1);
    }
}
