package com.wk.order.entity.ext;

import com.wk.entity.exception.BusinessRuntimeException;
import com.wk.entity.exception.ExceptionType;
import com.wk.order.entity.base.OrderInfo;
import com.wk.product.entity.base.ProductInfo;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: vince
 * create at: 2021/2/2 下午3:50
 * @description:
 */
@Data
@NoArgsConstructor
@SuperBuilder
public class OrderInfoExt extends OrderInfo implements Serializable {

    private ProductInfo productInfo;

    public void validateNewOrder() {
        if (StringUtils.isEmpty(getProductId())) {
            throw new BusinessRuntimeException(ExceptionType.REMARK, "产品id为空!");
        }
        if (getProductNum() == null) {
            throw new BusinessRuntimeException(ExceptionType.REMARK, "产品数量为空!");
        }
        if (StringUtils.isEmpty(getUserId())) {
            throw new BusinessRuntimeException(ExceptionType.REMARK, "用户id为空!");
        }
    }

    public static OrderInfo getTestBean(String orderId) {
        OrderInfo orderInfo = OrderInfoExt.builder().id(orderId).crtBy("wk").crtTime(new Date()).price("56.555").build();
        return orderInfo;
    }

}
