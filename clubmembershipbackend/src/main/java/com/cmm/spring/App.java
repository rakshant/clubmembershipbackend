package com.cmm.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class App 
{
    public static void main( String[] args )
    {
    	//ConfigurableApplicationContext context=new ClassPathXmlApplicationContext("beans.xml");
        SpringApplication.run(App.class, args);
    }
}
