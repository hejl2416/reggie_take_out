package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.dto.OrdersDto;
import com.itheima.reggie.entity.Orders;

public interface OrderService extends IService<Orders> {
    Page<OrdersDto> userPage(int page, int pageSize);

    void submit(Orders orders);

    Page<OrdersDto> pageInfo(int page, int pageSize, String number, String beginTime, String endTime);

    void again(Orders order1);
}
