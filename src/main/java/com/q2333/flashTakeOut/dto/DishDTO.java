package com.q2333.flashTakeOut.dto;

import com.q2333.flashTakeOut.entity.Dish;
import com.q2333.flashTakeOut.entity.DishFlavor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 白桃乌龙
 */
@Data
public class DishDTO extends Dish {

    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;
}
