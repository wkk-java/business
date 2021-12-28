package com.wk.sys.entity.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;


@NoArgsConstructor
@SuperBuilder
@ApiModel(value="用户账户信息", description="用户账户信息说明")
public class UserAccount {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    private String id;
    @ApiModelProperty("userId")
    private String userId;
    @ApiModelProperty("余额")
    private BigDecimal balance;
    @ApiModelProperty("冻结金额")
    private BigDecimal freeze;
    @ApiModelProperty("账户总额")
    private BigDecimal amount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getFreeze() {
        return freeze;
    }

    public void setFreeze(BigDecimal freeze) {
        this.freeze = freeze;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}