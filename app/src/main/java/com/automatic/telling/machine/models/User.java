package com.automatic.telling.machine.models;
import org.apache.commons.io.*;
import org.apache.commons.io.FileUtils;
/**
 * Created by HITESH on 11/4/2017.
 */

public class User {
    public String userAccount;
    public String deposit;
    public String transfer;
    public String transferTo;
    public String withdraw;
    public String amount;
    public User(String userAccount, String deposit, String transfer, String transferTo, String withdraw, String amount) {
        this.userAccount = userAccount;
        this.deposit = deposit;
        this.transfer = transfer;
        this.transferTo = transferTo;
        this.withdraw = withdraw;
        this.amount = amount;
    }


    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getTransfer() {
        return transfer;
    }

    public void setTransfer(String transfer) {
        this.transfer = transfer;
    }

    public String getTransferTo() {
        return transferTo;
    }

    public void setTransferTo(String transferTo) {
        this.transferTo = transferTo;
    }

    public String getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(String withdraw) {
        this.withdraw = withdraw;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
