package com.ImprovedVendingMachine.improvedvendingmachine.controller;

import com.ImprovedVendingMachine.improvedvendingmachine.dao.BankDao;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/bank")
public class BankController {

    private BankDao balanceDao;

    public BankController(BankDao balanceDao){
        this.balanceDao = balanceDao;
    }

    @GetMapping(path = "")
    public BigDecimal viewBalance(){
        return balanceDao.viewBalance();
    }
}
