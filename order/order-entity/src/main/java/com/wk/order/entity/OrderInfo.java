package com.wk.order.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author wk
 * @since 2021-11-04
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("order_info")
@ApiModel(value = "OrderInfo对象", description = "订单表")
public class OrderInfo extends Model<OrderInfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @TableField("name")
    private String name;

    @TableField("product_id")
    private String productId;

    @TableField("price")
    private String price;

    @TableField("product_num")
    private Integer productNum;

    @TableField("discount")
    private BigDecimal discount;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("crt_time")
    private LocalDateTime crtTime;

    @TableField("crt_by")
    private String crtBy;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("upd_time")
    private LocalDateTime updTime;

    @TableField("upd_by")
    private String updBy;

    @TableField("user_id")
    private String userId;

    @TableField("del_flag")
    @TableLogic
    private String delFlag;

}
