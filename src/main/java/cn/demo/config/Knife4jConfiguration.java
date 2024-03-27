package cn.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Knife4jConfiguration {


    @Bean(value = "dockerBean")
    public Docket dockerBean() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(new ApiInfoBuilder()
                .title("Excel-Demo后端API文档")
                .description("Excel-Demo后端API文档")
                .termsOfServiceUrl("")
                .contact(new Contact("xxx", "", "xxx@qq.com"))
                .version("1.0")
                .build())
            .select()
            .apis(RequestHandlerSelectors.basePackage("cn.demo.controller"))
            .paths(PathSelectors.any())
            .build();
        return docket;
    }
}
