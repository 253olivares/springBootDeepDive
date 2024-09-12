package com.spring_bean.bean_demo.services.impl;

import org.springframework.stereotype.Component;

import com.spring_bean.bean_demo.services.inferfaces.GreenPrinter;

@Component
public class EnglishGreenPrinter implements GreenPrinter {

    @Override
    public String print() {
        return "Green";
    }

}
