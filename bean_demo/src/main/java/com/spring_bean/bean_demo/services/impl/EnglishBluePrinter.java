package com.spring_bean.bean_demo.services.impl;

import org.springframework.stereotype.Component;

import com.spring_bean.bean_demo.services.inferfaces.BluePrinter;

@Component
public class EnglishBluePrinter implements BluePrinter {

    @Override
    public String print() {
        return "Blue";
    }
}
