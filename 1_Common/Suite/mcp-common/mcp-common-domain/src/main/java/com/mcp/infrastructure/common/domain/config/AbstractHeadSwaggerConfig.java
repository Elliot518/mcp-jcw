package com.mcp.infrastructure.common.domain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;


/**
 * @author: KG
 * @description:
 * @date: Created in 10:03 下午 2020/8/10
 * @modified by:
 */
@Configuration
public abstract class AbstractHeadSwaggerConfig {

    /**
     * Swagger Api Info
     * @return
     */
    protected abstract SwaggerApiInfo getApiInfo();

    @Bean
    public Docket createRestApi() {
        SwaggerApiInfo swaggerApiInfo = getApiInfo();

        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo(swaggerApiInfo))
                .select()
                .apis(RequestHandlerSelectors.basePackage(swaggerApiInfo.getBasePackage()))
                .paths(PathSelectors.any())
                .build()
                .globalRequestParameters(getGlobalRequestParameters(swaggerApiInfo.getTokenHead()))
                .globalResponses(HttpMethod.GET, getGlobalResonseMessage())
                .globalResponses(HttpMethod.POST, getGlobalResonseMessage());
    }

    private ApiInfo apiInfo(SwaggerApiInfo swaggerApiInfo) {
        return new ApiInfoBuilder()
                .title(swaggerApiInfo.getTitle())
                .description(swaggerApiInfo.getDescription())
                .contact(new Contact(swaggerApiInfo.getAuthor(), swaggerApiInfo.getServiceUrl(), swaggerApiInfo.getEmail()))
                .version(swaggerApiInfo.getVersion())
                .build();
    }

    // 生成全局通用参数
    private List<RequestParameter> getGlobalRequestParameters(String tokenHead) {
        List<RequestParameter> parameters = new ArrayList<>();
        parameters.add(new RequestParameterBuilder()
                .name(tokenHead)
                .description("令牌")
                .required(true)
                .in(ParameterType.HEADER)
                .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
                .required(false)
                .build());
        return parameters;
    }

    // 生成通用响应信息
    private List<Response> getGlobalResonseMessage() {
        List<Response> responseList = new ArrayList<>();
        responseList.add(new ResponseBuilder().code("404").description("找不到资源").build());
        return responseList;
    }
}


