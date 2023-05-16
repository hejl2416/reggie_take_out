package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.dto.DishDto;
import com.itheima.reggie.entity.Dish;

public interface DishService extends IService<Dish> {
    // 新增菜品信息
    void saveWithFlavor(DishDto dishDto);

    // 获取菜品信息
    DishDto getByIdWithFlavor(Long id);

    // 更新菜品信息
    void updateWithFlavor(DishDto dishDto);
}
