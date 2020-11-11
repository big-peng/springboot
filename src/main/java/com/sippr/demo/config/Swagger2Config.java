package com.sippr.demo.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenXiangpeng
 * @ClassName Swagger2Config
 * @date：2019/9/23
 * @version: V1.0.0
 * @description：
 */
@EnableSwagger2
@Configuration
@Component
@Profile("swagger")
public class Swagger2Config {

    @Bean
    public Docket createRestApi(){
        //添加head参数
        List<Parameter> pars = new ArrayList<>();
        ParameterBuilder tokenPar = new ParameterBuilder();
        tokenPar.name("Authorization").description("AccessToken令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        ParameterBuilder tokenPar1 = new ParameterBuilder();
        tokenPar1.name("tenantId").description("租户").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        pars.add(tokenPar1.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //basePackage("com.example.shiro_demo1.controller"))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);
    }

    private ApiInfo apiInfo(){

        return new ApiInfoBuilder()
                .title("进度计划")
                .description("更多请关注http://www.xx.com")
                .termsOfServiceUrl("http://www.xx.com")
                .version("1.0")
                .build();
    }
}
