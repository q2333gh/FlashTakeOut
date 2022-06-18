package com.q2333.flashTakeOut.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.q2333.flashTakeOut.common.Return;
import com.q2333.flashTakeOut.dto.DishDTO;
import com.q2333.flashTakeOut.entity.Category;
import com.q2333.flashTakeOut.entity.Dish;
import com.q2333.flashTakeOut.service.CategoryService;
import com.q2333.flashTakeOut.service.DishFlavorService;
import com.q2333.flashTakeOut.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author q2333
 * @date 2022/06/17 13:58
 **/
@Slf4j
@RestController
@RequestMapping("/dish")
public class DishController {
    @Resource
    private DishFlavorService dishFlavorService;
    @Resource
    private DishService dishService;
    @Resource
    private CategoryService categoryService;

    /**
     * 新增菜品
     * @param dishDTO
     * @return
     */
    @PostMapping
    public Return<String> save(@RequestBody DishDTO dishDTO){
        log.info(dishDTO.toString());
        dishService.saveWithFlavor(dishDTO);
        return Return.success("新增菜品成功");
    }

    /**
     * 菜品信息分页查询
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public Return<Page> page(int page,int pageSize,String name){
        //分页构造器,
        Page<Dish> pageInfo=new Page<>(page,pageSize);
        Page<DishDTO> dishDTOPage=new Page<>();
        //条件构造器
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name!=null,Dish::getName,name);
        queryWrapper.orderByDesc(Dish::getUpdateTime);
        //执行查询
        dishService.page(pageInfo,queryWrapper);
        //转义菜品分类id为菜品分类中文
        BeanUtils.copyProperties(pageInfo,dishDTOPage,"records");
        List<Dish> records=pageInfo.getRecords();
        List<DishDTO> list = records.stream().map((item)->{
            DishDTO dishDTO = new DishDTO();
            BeanUtils.copyProperties(item,dishDTO);
            Long categoryId = item.getCategoryId();//分类id
            Category category = categoryService.getById(categoryId);
            if (category!=null){
                String categoryName = category.getName();
                dishDTO.setCategoryName(categoryName);
            }
            return dishDTO;
        }).collect(Collectors.toList());
        dishDTOPage.setRecords(list);
        return Return.success(dishDTOPage);
    }

    /**
     * 根据id查询菜品信息和对应的口味信息使用DishDTO
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Return<DishDTO> get(@PathVariable Long id){
        DishDTO dishDTO = dishService.getByIdWithFlavor(id);
        return Return.success(dishDTO);
    }

//    @DeleteMapping

    /**
     * 修改菜品
     * @param dishDTO
     * @return
     */
    @PutMapping
    public Return<String> update(@RequestBody DishDTO dishDTO){
        log.info(dishDTO.toString());
        dishService.updateWithFlavor(dishDTO);
        return Return.success("修改菜品成功");
    }












}
