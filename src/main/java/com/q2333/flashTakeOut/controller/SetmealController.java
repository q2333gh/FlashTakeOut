package com.q2333.flashTakeOut.controller;

import com.q2333.flashTakeOut.service.SetmealDishService;
import com.q2333.flashTakeOut.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author q2333
 * @date 2022/06/19 08:20
 **/
@RestController
@RequestMapping("/setmeal")
@Slf4j
@SuppressWarnings("all")
public class SetmealController {
    @Autowired
    private SetmealService setmealService;
    @Autowired
    private SetmealDishService setmealDishService;

}
