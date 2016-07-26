package com.edi.manage.springfox;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableWebMvc
@EnableSwagger2
@Controller
public class MySwaggerConfig
{
	@Bean
	public Docket swaggerSpringMvcPlugin()
	{
		// return new Docket(DocumentationType.SWAGGER_2).select() //
		// 选择那些路径和api会生成document
		// .apis(RequestHandlerSelectors.basePackage("com.edi.manage.springfox"))
		// // 对所有api进行监控
		// .paths(PathSelectors.ant()) // 对所有路径进行监控
		// .build();

		/*
		 * Docket d = new Docket(DocumentationType.SWAGGER_2);
		 * d.apiInfo(apiInfo());// 首页显示信息 ApiSelectorBuilder api = d.select();
		 * api
		 * .apis(RequestHandlerSelectors.basePackage("com.edi.manage.springfox"
		 * ));// 选择那些路径和api会生成document // api.paths(PathSelectors.any()); //
		 * 对所有请求路径 进行监控 api.paths(PathSelectors.regex("正则表达式")); return
		 * api.build();
		 */
		// return new
		// Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
		// .apis(RequestHandlerSelectors.basePackage("com.edi.manage"))
		// .paths(PathSelectors.any()).build();
		//

		return new Docket(DocumentationType.SWAGGER_2).pathMapping("/rest").apiInfo(apiInfo())
				.select().apis(RequestHandlerSelectors.basePackage("com.edi.manage"))
				.paths(PathSelectors.any()).build();

	}

	/**
	 * 显示信息
	 * @return
	 */
	private ApiInfo apiInfo()
	{
		return new ApiInfoBuilder().title("标题 ").description("描述").termsOfServiceUrl("Url的服务条款")
				.contact("联系方式 ").version("版本").build();
	}

}
