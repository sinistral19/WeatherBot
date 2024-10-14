package com.project.WeatherBot;
import com.project.WeatherBot.*;
import com.project.WeatherBot.config.Initializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args){
        Initializer initializer = new Initializer();
        initializer.init();

        SpringApplication.run(Main.class,args);
    }
}
