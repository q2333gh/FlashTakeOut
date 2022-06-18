package com.q2333.flashTakeOut.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.q2333.flashTakeOut.dto.DishDTO;
import com.q2333.flashTakeOut.entity.Dish;
import com.q2333.flashTakeOut.entity.DishFlavor;
import com.q2333.flashTakeOut.mapper.DishMapper;
import com.q2333.flashTakeOut.service.DishFlavorService;
import com.q2333.flashTakeOut.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 白桃乌龙
 * @description 针对表【dish(菜品管理)】的数据库操作Service实现
 * @createDate 2022-06-17 09:07:10
 */
@SuppressWarnings("all")
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish>
        implements DishService {
    @Autowired
    private DishFlavorService dishFlavorService;

    //新增菜品已经对应的口味数据.操作两张表
    @Override
    @Transactional//多表操作
    public void saveWithFlavor(DishDTO dishDTO) {
        //存取dish信息
        this.save(dishDTO);
        Long dishId = dishDTO.getId();
        List<DishFlavor> flavors = dishDTO.getFlavors();
        flavors = flavors.stream().map((item) -> {
            item.setDishId(dishId);
            return item;
        }).collect(Collectors.toList());
        //查询口味表
        dishFlavorService.saveBatch(flavors);
    }

    //根据id查询菜品信息和口味信息
    @Override
    public DishDTO getByIdWithFlavor(Long id) {
        Dish dish = this.getById(id);
        DishDTO dishDTO = new DishDTO();
        BeanUtils.copyProperties(dish, dishDTO);
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId, dish.getId());
        List<DishFlavor> flavors = dishFlavorService.list(queryWrapper);
        dishDTO.setFlavors(flavors);
        return dishDTO;
    }

    //更新菜品信息,同时更新口味信息
    @Override
//    @Transactional
    public void updateWithFlavor(DishDTO dishDTO) {
        this.updateById(dishDTO);
        final Long id = dishDTO.getId();
        final LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId, dishDTO.getId());
        List<DishFlavor> flavors = dishDTO.getFlavors();
        flavors = flavors.stream().map((item) -> {
            item.setDishId(dishDTO.getId());
            return item;
        }).collect(Collectors.toList());
        dishFlavorService.remove(queryWrapper);
        dishFlavorService.saveBatch(flavors);
    }

}




