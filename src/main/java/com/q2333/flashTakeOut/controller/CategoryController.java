package com.q2333.flashTakeOut.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.q2333.flashTakeOut.common.Return;
import com.q2333.flashTakeOut.entity.Category;
import com.q2333.flashTakeOut.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author q2333
 * @date 2022/06/16 11:52
 **/
@SuppressWarnings("all")
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    /**
     * 新增分类
     * @param category
     * @return
     */
    @PostMapping
    public Return<String> save(@RequestBody Category category){
        categoryService.save(category);
        return  Return.success("新增分类成功");
    }

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public Return<Page> page(int page,int pageSize){
        //MP提供,分页构造器
        Page<Category> pageInfo=new Page<>(page,pageSize);
        //条件过滤器排序,sort
        LambdaQueryWrapper<Category> queryWrapper =
                new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Category::getSort);
        //查询
        categoryService.page(pageInfo,queryWrapper);
        return Return.success(pageInfo);
    }

    /**
     *根据id删除
     * @param id
     * @return
     */
    @DeleteMapping
    public Return<String> delete(Long id){
        categoryService.remove(id);
        return Return.success("分类信息删除成功");
    }
}




















