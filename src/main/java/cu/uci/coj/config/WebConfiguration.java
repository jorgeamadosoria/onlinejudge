/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package cu.uci.coj.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.assertj.core.util.Arrays;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Sets;

@Configuration
public class WebConfiguration implements  WebMvcConfigurer{

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver bean = new SpringResourceTemplateResolver();
        bean.setPrefix("/WEB-INF/jsp/");
        bean.setSuffix(".html");
        bean.setTemplateMode("HTML5");
        bean.setCacheable(false);
        return bean;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine bean = new SpringTemplateEngine();
        bean.setTemplateResolver(templateResolver());
        bean.setAdditionalDialects(Sets.newHashSet(new SpringSecurityDialect()));
        return bean;
    }
    
    @Bean
    public ThymeleafViewResolver thymeleafViewResolver(){
        ThymeleafViewResolver bean = new ThymeleafViewResolver();
        bean.setTemplateEngine(templateEngine());
        bean.setOrder(1);
        bean.setViewNames(Arrays.array("thymeleaf/*"));
        return bean;
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }

    @Bean
    public LocaleResolver localResolver() {
        SessionLocaleResolver bean = new SessionLocaleResolver();
        bean.setDefaultLocale(Locale.ENGLISH);
        return bean;
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public HttpMessageConverter<?> marshallingHttpMessageConverter() {
        MappingJackson2HttpMessageConverter bean = new MappingJackson2HttpMessageConverter();
        bean.setObjectMapper(objectMapper());
        return bean;
    }

    @Bean
    public RequestMappingHandlerAdapter adapter() {
        RequestMappingHandlerAdapter bean = new RequestMappingHandlerAdapter();
        List<HttpMessageConverter<?>> lists = new ArrayList<>();
        lists.add(marshallingHttpMessageConverter());
        bean.setMessageConverters(lists);
        return bean;
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate bean = new RestTemplate();
        List<HttpMessageConverter<?>> lists = new ArrayList<>();
        lists.add(marshallingHttpMessageConverter());
        bean.setMessageConverters(lists);
        return bean;
    }

}
