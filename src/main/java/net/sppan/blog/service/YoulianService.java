package net.sppan.blog.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.sppan.blog.entity.Youlian;

public interface YoulianService {

    /**
     * 查询所有
     *
     * @return
     */
    List<Youlian> findAll();

    /**
     * 查询所有可见
     *
     * @return
     */
    List<Youlian> findAllVisiable();

    /**
     * 分页查询
     *
     * @param pageable
     * @return
     */
    Page<Youlian> findAll(Pageable pageable);

    /**
     * 根据ID查找
     *
     * @param id
     * @return
     */
    Youlian findById(Long id);

    /**
     * 修改或者新增
     *
     * @param youlian
     */
    void saveOrUpdate(Youlian youlian);

    /**
     * 删除
     *
     * @param id
     */
    void delete(Long id);

    /**
     * 改变状态
     *
     * @param id
     */
    void changeStatus(Long id);
}
