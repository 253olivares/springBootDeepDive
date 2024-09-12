// package com.spring_bean.bean_demo.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import com.spring_bean.bean_demo.services.ColorPrinter;
// import com.spring_bean.bean_demo.services.impl.ColorPrinterImpl;
// import com.spring_bean.bean_demo.services.impl.EnglishBluePrinter;
// import com.spring_bean.bean_demo.services.impl.EnglishGreenPrinter;
// import com.spring_bean.bean_demo.services.impl.EnglishRedPrinter;
// import com.spring_bean.bean_demo.services.impl.SpanishBluePrinter;
// import com.spring_bean.bean_demo.services.impl.SpanishGreenPrinter;
// import com.spring_bean.bean_demo.services.impl.SpanishRedPrinter;
// import com.spring_bean.bean_demo.services.inferfaces.BluePrinter;
// import com.spring_bean.bean_demo.services.inferfaces.GreenPrinter;
// import com.spring_bean.bean_demo.services.inferfaces.RedPrinter;

// // Look into this class for bean declarations
// @Configuration
// public class PrinterConfig {

// @Bean
// public BluePrinter bluePrinter() {
// return new SpanishBluePrinter();
// }

// @Bean
// public RedPrinter redPrinter() {
// return new SpanishRedPrinter();
// }

// @Bean
// public GreenPrinter greenPrinter() {
// return new SpanishGreenPrinter();
// }

// @Bean
// public ColorPrinter colorPrinter(BluePrinter bluePrinter, RedPrinter
// redPrinter, GreenPrinter greenPrinter) {
// return new ColorPrinterImpl(redPrinter, bluePrinter, greenPrinter);
// }

// }
