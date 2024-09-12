package com.spring_bean.bean_demo.services.impl;

import org.springframework.stereotype.Component;

import com.spring_bean.bean_demo.services.ColorPrinter;
import com.spring_bean.bean_demo.services.inferfaces.BluePrinter;
import com.spring_bean.bean_demo.services.inferfaces.GreenPrinter;
import com.spring_bean.bean_demo.services.inferfaces.RedPrinter;

@Component
// Component scanning
// At the start of a project start up it looks for beans and places where beans
// are needed

// It also scans for what beans are needed for example for this class we need 3
// beans to start the application
// so then when it sees that it searches its context for beans that match the
// interface

// if were ever missing beans the application fails to start up and tell us
// which bean we are looking for!
public class ColorPrinterImpl implements ColorPrinter {

    private RedPrinter redPrinter;

    private BluePrinter bluePrinter;

    private GreenPrinter greenPrinter;

    public ColorPrinterImpl(RedPrinter redPrinter, BluePrinter bluePrinter, GreenPrinter greenPrinter) {
        this.redPrinter = redPrinter;
        this.bluePrinter = bluePrinter;
        this.greenPrinter = greenPrinter;
    }

    @Override
    public String print() {
        // TODO Auto-generated method stub
        return String.join(",", redPrinter.print(), bluePrinter.print(), greenPrinter.print());
    }

}
