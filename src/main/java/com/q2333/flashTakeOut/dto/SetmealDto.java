package com.q2333.flashTakeOut.dto;


import com.q2333.flashTakeOut.entity.Setmeal;
import com.q2333.flashTakeOut.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
