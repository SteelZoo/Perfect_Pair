package com.olduo.last_dance;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import com.olduo.last_dance.model.dao.UserDao;

import springfox.documentation.service.Contact;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableCaching
@SpringBootApplication
@EnableSwagger2
@MapperScan(basePackageClasses = UserDao.class)
public class PerfectPairApplication {

	public static void main(String[] args) {
		SpringApplication.run(PerfectPairApplication.class, args);
	}
	
	@Bean
    public Docket postsApi() {
       final ApiInfo apiInfo = new ApiInfoBuilder()
               .title("Perfect Pair Rest API")
               .description("<h3>Perfect Pair에서 제공되는 Rest api의 문서 제공</h3>")
               .contact(new Contact("SSAFY", "https://edu.ssafy.com", "ssafy@ssafy.com"))
               .license("MIT License")
               .version("1.0")
               .build();
       
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName("olduo")
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.olduo.last_dance.controller.rest"))
//              .paths(PathSelectors.ant("/book/**"))
                .build();
        System.out.println(docket);
        return docket;
    }
}
