package com.wk.sys.entity.ext;

import com.wk.entity.exception.BusinessSeataException;
import com.wk.entity.exception.ExceptionType;
import com.wk.sys.entity.base.UserAccount;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author: vince
 * create at: 2021/3/24 下午4:31
 * @description:
 */
@Slf4j
@Data
@NoArgsConstructor
@SuperBuilder
public class UserAccountExt extends UserAccount implements Serializable {

    /**
     * 增减的money.
     */
    private BigDecimal money;

    public UserAccount addMoney(BigDecimal money) {
        BigDecimal amount = this.getAmount();
        BigDecimal balance = this.getBalance();
        BigDecimal freeze = this.getFreeze();
        this.setAmount(amount.add(money));
        this.setBalance(balance.add(money));
//        this.setFreeze(freeze.add(money));
        return this;
    }

    public UserAccount freezeMoney(BigDecimal money) throws BusinessSeataException {
        BigDecimal amount = this.getAmount();
        BigDecimal balance = this.getBalance();
        BigDecimal freeze = this.getFreeze();
//        this.setAmount(amount.subtract(money));
        this.setBalance(balance.subtract(money));
        if (this.getBalance().compareTo(BigDecimal.ZERO) < 0) {
            log.error("当前金额不足以提交冻结, balance:{}, freeze:{}", balance, freeze);
            throw new BusinessSeataException(ExceptionType.REMARK, "当前金额不足以提交冻结");
        }
        this.setFreeze(freeze.add(money));
        return this;
    }

    public UserAccount payMoney(BigDecimal money) throws BusinessSeataException {
        BigDecimal amount = this.getAmount();
        BigDecimal balance = this.getBalance();
        BigDecimal freeze = this.getFreeze();
        this.setAmount(amount.subtract(money));
//        this.setBalance(balance.subtract(money));
        this.setFreeze(freeze.subtract(money));
        if (this.getFreeze().compareTo(BigDecimal.ZERO) < 0) {
            log.error("当前冻结金额不足以支付, freeze:{}, payMoney:{}", freeze, money);
            throw new BusinessSeataException(ExceptionType.REMARK, "当前冻结金额不足以提交支付");
        }
        return this;
    }


}
