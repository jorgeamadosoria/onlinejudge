package cu.uci.coj.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.tiles3.SimpleSpringPreparerFactory;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;

import cu.uci.coj.utils.HandlerInterceptorImpl;
import cu.uci.coj.utils.paging.PaginatedArgumentResolver;

@Configuration
@EnableWebMvc
@ComponentScan({ "cu.uci.coj.dao", "cu.uci.coj.controller", "cu.uci.coj.validator" })
public class WebConfiguration implements WebMvcConfigurer {
	@Resource
	private MarshallingHttpMessageConverter marshallingHttpMessageConverter;

	@Resource
	HandlerInterceptorImpl handlerInterceptor;
	
	
	
	
    @Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
    	registry.tiles();
    	registry.jsp("/WEB-INF/jsp/", ".jsp");
		WebMvcConfigurer.super.configureViewResolvers(registry);
	}
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(handlerInterceptor);
        LocaleChangeInterceptor bean = new LocaleChangeInterceptor();
        bean.setParamName("lang");
        registry.addInterceptor(bean);
    }
    @Bean
    public HttpMessageConverter<?> marshallingHttpMessageConverter() {
        
        return new MarshallingHttpMessageConverter();
    }

    @Bean
    public TilesConfigurer tilesConfigurer() {
        TilesConfigurer bean = new TilesConfigurer();
        bean.setDefinitions("/WEB-INF/tiles-defs.xml");
        bean.setPreparerFactoryClass(SimpleSpringPreparerFactory.class);
        return bean;
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver bean = new CommonsMultipartResolver();
        bean.setMaxUploadSize(104857600);
        bean.setMaxInMemorySize(524288000);
        return bean;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new PaginatedArgumentResolver());
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver bean = new SessionLocaleResolver();
        bean.setDefaultLocale(Locale.ENGLISH);
        return bean;
    }



    @Bean
    public RequestMappingHandlerAdapter adapter() {
        RequestMappingHandlerAdapter bean = new RequestMappingHandlerAdapter();
        List<HttpMessageConverter<?>> lists = new ArrayList<>();
        lists.add(marshallingHttpMessageConverter);
        bean.setMessageConverters(lists);
        return bean;
    }

    
}
