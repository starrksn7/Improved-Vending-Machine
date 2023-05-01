package com.ImprovedVendingMachine.improvedvendingmachine.controller;

import com.ImprovedVendingMachine.improvedvendingmachine.dao.BankDao;
import com.ImprovedVendingMachine.improvedvendingmachine.model.Bank;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank")
@CrossOrigin

public class BankController {

    private BankDao balanceDao;

    public BankController(BankDao balanceDao){
        this.balanceDao = balanceDao;
    }

    @GetMapping(path = "")
    public Bank viewTotal(){
        return balanceDao.viewTotal();
    }
}
