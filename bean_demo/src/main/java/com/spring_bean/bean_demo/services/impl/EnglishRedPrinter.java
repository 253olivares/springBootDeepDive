package com.spring_bean.bean_demo.services.impl;

import org.springframework.stereotype.Component;

import com.spring_bean.bean_demo.services.inferfaces.RedPrinter;

@Component
public class EnglishRedPrinter implements RedPrinter {

    @Override
    public String print() {
        // TODO Auto-generated method stub
        return "Red";
    }

}
