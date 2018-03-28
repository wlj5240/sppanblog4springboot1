package net.sppan.blog.service.impl;

import net.sppan.blog.entity.Tag;
import net.sppan.blog.exception.ServiceException;
import net.sppan.blog.repository.TagRepository;
import net.sppan.blog.service.TagService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TagServiceImpl implements TagService {

    @Resource
    private TagRepository tagRepository;

    @Override
    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    @Override
    public Page<Tag> findAll(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Override
    public Tag findById(Long id) {
        return tagRepository.findOne(id);
    }

    @Override
    public void saveOrUpdate(Tag tag) {
        if (tag != null) {
            if (tag.getId() != null) {
                Tag dbTag = findById(tag.getId());
                dbTag.setName(tag.getName());
                dbTag.setStatus(tag.getStatus());
                tagRepository.saveAndFlush(dbTag);
            } else {
                tag.setCount(0);
                tag.setStatus(0);
                tagRepository.save(tag);
            }
        } else {
            throw new ServiceException("保存对象不能为空");
        }

    }

    @Override
    public void delete(Long id) {
        tagRepository.delete(id);
    }

    @Override
    public void changeStatus(Long id) {
        if (id == null) {
            throw new ServiceException("ID不能为空");
        }
        Tag tag = findById(id);
        tag.setStatus(tag.getStatus() == 0 ? 1 : 0);
        tagRepository.saveAndFlush(tag);
    }

    @Override
    public List<String> findAllNameList() {
        List<String> result = new ArrayList<String>();
        List<Tag> list = tagRepository.findAllByStatus(0);
        for (Tag tag : list) {
            result.add(tag.getName());
        }
        return result;
    }

    @Override
    public Tag findByName(String tagName) {
        if (tagName == null) {
            return null;
        }
        return tagRepository.findByName(tagName);
    }

    @Override
    public void increaseCount(String tagName) {
        Tag tag = tagRepository.findByName(tagName);
        if (tag == null) {
            tag = new Tag();
            tag.setName(tagName);
            tag.setCount(1);
            tag.setStatus(0);
        } else {
            tag.setCount(tag.getCount() + 1);
        }
        tagRepository.saveAndFlush(tag);
    }

    @Override
    public void decreaseCount(String tagName) {
        Tag tag = tagRepository.findByName(tagName);
        tag.setCount(tag.getCount() - 1);
    }
}
