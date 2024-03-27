package cn.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync(proxyTargetClass = true)
@SpringBootApplication
public class ExcelDemoApp {

  public static void main(String[] args) {
    SpringApplication.run(ExcelDemoApp.class, args);
  }

}
