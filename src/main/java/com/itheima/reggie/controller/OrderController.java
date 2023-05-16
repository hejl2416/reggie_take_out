package com.itheima.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.R;
import com.itheima.reggie.dto.OrdersDto;
import com.itheima.reggie.entity.Orders;
import com.itheima.reggie.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 用户下单
     *
     * @param orders
     * @return
     */
    @PostMapping("/submit")
    public R<String> submit(@RequestBody Orders orders) {
        log.info("orders:{}", orders);
        orderService.submit(orders);
        return R.success("下单成功");
    }

    /**
     * 订单查询
     *
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/userPage")
    public R<Page> userPage(int page, int pageSize) {
        Page<OrdersDto> ordersDtoPage = orderService.userPage(page, pageSize);
        return R.success(ordersDtoPage);
    }

    /**
     * 订单派送
     *
     * @param orders
     * @return
     */
    @PutMapping
    public R<String> send(@RequestBody Orders orders) {
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Orders::getId, orders.getId());
        Orders one = orderService.getOne(queryWrapper);

        one.setStatus(orders.getStatus());
        orderService.updateById(one);
        return R.success("派送成功");
    }

    /**
     * 后台订单明细
     *
     * @param page
     * @param pageSize
     * @param number
     * @param beginTime
     * @param endTime
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String number, String beginTime, String endTime) {

        Page<OrdersDto> ordersDtoPage = orderService.pageInfo(page, pageSize, number, beginTime, endTime);

        return R.success(ordersDtoPage);
    }

    /**
     * 根据历史订单再来一单
     *
     * @param order1
     * @return
     */
    @PostMapping("/again")
    public R<String> again(@RequestBody Orders order1) {
        orderService.again(order1);
        return R.success("再来一单");
    }
}
