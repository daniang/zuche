package com.example.zuche.config;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
//@ConditionalOnProperty(prefix = "mconfig", name = "swagger-ui-open", havingValue = "false")
public class SwaggerConfig implements WebMvcConfigurer {


    private boolean isSwaggerEnable = true;

    @Value(value = "${swagger.enabled}")
    Boolean swaggerEnabled;

    @Bean
    public Docket createRestApi() {

//        //添加请求参数，我们这里把token作为请求头部参数传入后端
//        ParameterBuilder  parameterBuilder = new ParameterBuilder();
//
//        List<Parameter> parameters = new ArrayList<Parameter>();
//
//        parameterBuilder.name("Authorization").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
//
//        parameters.add(parameterBuilder.build());


        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerEnabled)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class)).paths(PathSelectors.any()).build();
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("家用代码").description("Rest API接口")
                .termsOfServiceUrl("http://localhost:8080/hello").version("1.0.0").build();


    }


}
