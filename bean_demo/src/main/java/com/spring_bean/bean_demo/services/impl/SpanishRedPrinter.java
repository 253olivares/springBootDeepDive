package com.spring_bean.bean_demo.services.impl;

import com.spring_bean.bean_demo.services.inferfaces.RedPrinter;

public class SpanishRedPrinter implements RedPrinter {

    @Override
    public String print() {
        return "Rojo";
    }

}
