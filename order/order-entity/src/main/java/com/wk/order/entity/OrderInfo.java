package com.wk.order.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author wk
 * @since 2021-11-05
 */
@Getter
@Setter
  @Accessors(chain = true)
  @TableName("order_info")
@ApiModel(value = "OrderInfo对象", description = "订单表")
public class OrderInfo extends Model<OrderInfo> {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.ASSIGN_ID)
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

      @TableField(value = "crt_time", fill = FieldFill.INSERT)
      private LocalDateTime crtTime;

    @TableField("crt_by")
    private String crtBy;

      @TableField(value = "upd_time", fill = FieldFill.INSERT_UPDATE)
      private LocalDateTime updTime;

    @TableField("upd_by")
    private String updBy;

    @TableField("user_id")
    private String userId;

    @TableField("del_flag")
    @TableLogic
    private String delFlag;

    @TableField("version")
    @Version
    private Integer version;


    @Override
    public Serializable pkVal() {
          return this.id;
      }

}
