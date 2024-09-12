package com.spring_bean.bean_demo.services.impl;

import com.spring_bean.bean_demo.services.inferfaces.BluePrinter;

public class SpanishBluePrinter implements BluePrinter {

    @Override
    public String print() {
        return "Azul";
    }

}
