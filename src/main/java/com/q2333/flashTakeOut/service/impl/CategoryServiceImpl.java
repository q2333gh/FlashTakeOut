package com.q2333.flashTakeOut.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.q2333.flashTakeOut.common.CustomException;
import com.q2333.flashTakeOut.entity.Category;

import com.q2333.flashTakeOut.entity.Dish;
import com.q2333.flashTakeOut.entity.Setmeal;
import com.q2333.flashTakeOut.mapper.CategoryMapper;
import com.q2333.flashTakeOut.service.CategoryService;
import com.q2333.flashTakeOut.service.DishService;
import com.q2333.flashTakeOut.service.SetmealService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author q2333
 * @date 2022/06/16 11:47
 **/
@SuppressWarnings("all")
@Service
public class CategoryServiceImpl
        extends ServiceImpl<CategoryMapper, Category>
        implements CategoryService {
    @Resource
    private DishService dishService;
    @Resource
    private SetmealService setmealService;

    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishQueryWrapper = new LambdaQueryWrapper<>();
        dishQueryWrapper.eq(Dish::getCategoryId, id);
        if (dishService.count(dishQueryWrapper) > 0) {
            throw new CustomException("当前分类关联了菜品信息,不能删除");
        }
        LambdaQueryWrapper<Setmeal>
                setmealQueryWrapper = new LambdaQueryWrapper<>();
        setmealQueryWrapper.eq(Setmeal::getCategoryId, id);
        if (setmealService.count(setmealQueryWrapper) > 0) {
            throw new CustomException("当前分类关联了套餐信息,不能删除");
        }
        super.removeById(id);
    }
}
