package com.q2333.flashTakeOut.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.q2333.flashTakeOut.dto.DishDTO;
import com.q2333.flashTakeOut.entity.Dish;

/**
* @author 白桃乌龙
* @description 针对表【dish(菜品管理)】的数据库操作Service
* @createDate 2022-06-17 09:07:10
*/
public interface DishService extends IService<Dish> {
    //新增菜品已经对应的口味数据.操作两张表
    public void saveWithFlavor(DishDTO dishDTO);
    //根据id查询菜品信息和口味信息
    public DishDTO getByIdWithFlavor(Long id);

    void updateWithFlavor(DishDTO dishDTO);
}
