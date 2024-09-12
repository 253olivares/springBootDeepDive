package com.spring_bean.bean_demo.services.impl;

import com.spring_bean.bean_demo.services.inferfaces.GreenPrinter;

public class SpanishGreenPrinter implements GreenPrinter {

    @Override
    public String print() {
        return "Verde";
    }

}
