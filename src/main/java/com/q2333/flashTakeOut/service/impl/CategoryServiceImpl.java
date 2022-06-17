package com.q2333.flashTakeOut.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.q2333.flashTakeOut.entity.Category;
import com.q2333.flashTakeOut.mapper.CategoryMapper;
import com.q2333.flashTakeOut.service.CategoryService;
import org.springframework.stereotype.Service;

/**
 * @author q2333
 * @date 2022/06/16 11:47
 **/
@Service
public class CategoryServiceImpl
        extends ServiceImpl <CategoryMapper, Category>
        implements CategoryService {
}
