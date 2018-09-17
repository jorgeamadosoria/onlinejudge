/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package cu.uci.coj.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.HikariDataSource;

import cu.uci.coj.model.dto.VerdictDTO;
import cu.uci.coj.utils.UEngineMessageListener;

@Configuration
@PropertySource({ "classpath:cu/uci/coj/config/config.properties", "classpath:cu/uci/coj/config/sql/postgres.properties" })
@EnableJpaRepositories
@EnableTransactionManagement

@EnableAsync
@EnableScheduling
@ComponentScan({ "cu.uci.coj.dao", "cu.uci.coj.mail", "cu.uci.coj.security", "cu.uci.coj.service", "cu.uci.coj.utils" })
public class RootConfiguration {
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
    @Resource
    protected Environment env;
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate bean = new RestTemplate();
        List<HttpMessageConverter<?>> lists = new ArrayList<>();
        lists.add(marshallingHttpMessageConverter());
        bean.setMessageConverters(lists);
        return bean;
    }

    @Bean
    public CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory bean = new CachingConnectionFactory(env.getProperty("rabbitmq.hostname"), Integer.parseInt(env.getProperty("rabbitmq.port")));
        bean.setUsername(env.getProperty("rabbitmq.username"));
        bean.setPassword(env.getProperty("rabbitmq.password"));
        return bean;
    }

    @Bean
    public RabbitAdmin rabbitAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    Queue queue() {
        return new Queue(env.getProperty("submit.response.queue"), true);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(env.getProperty("submit.exchange"));
    }

    @Bean
    public RabbitTemplate submitTemplate() {
        RabbitTemplate bean = new RabbitTemplate(connectionFactory());
        bean.setMessageConverter(jsonMessageConverter());
        // para enviar, el binding del exchange se configura en el rabbitmq
        // server a la cola correspondiente (que aqui no interesa)
        bean.setExchange(env.getProperty("submit.exchange"));
        return bean;
    }
    
    @Autowired
    private UEngineMessageListener messageListener;

    @Bean
    public DefaultClassMapper typeMapper() {
        DefaultClassMapper typeMapper = new DefaultClassMapper();
        Map<String, Class<?>> idClassMapping = new HashMap<>();
        idClassMapping.put("UEngineVerdict", VerdictDTO.class);
        typeMapper.setIdClassMapping(idClassMapping);
        return typeMapper;
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        Jackson2JsonMessageConverter bean = new Jackson2JsonMessageConverter();
        bean.setClassMapper(typeMapper());
        return bean;
    }

    @Bean
    public SimpleMessageListenerContainer listenerContainer() {
        SimpleMessageListenerContainer bean = new SimpleMessageListenerContainer(connectionFactory());
        bean.setMessageListener(messageListener);
        bean.setQueues(queue());

        bean.setAcknowledgeMode(AcknowledgeMode.AUTO);
        return bean;
    }

    @Bean
    public JavaMailSenderImpl mailSender() {
        JavaMailSenderImpl bean = new JavaMailSenderImpl();

        bean.setHost(env.getProperty("mail.host"));
        bean.setPort(Integer.valueOf(env.getProperty("mail.port")));
        bean.setUsername(env.getProperty("mail.username"));
        bean.setPassword(env.getProperty("mail.password"));
        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.auth", "true");
        javaMailProperties.put("mail.smtp.starttls.enable", "true");
        javaMailProperties.put("mail.smtp.auth.mechanisms", "LOGIN PLAIN");
        javaMailProperties.put("mail.smtp.starttls.required", "true");
        javaMailProperties.put("mail.smtp.ssl.trust", "*");
        bean.setJavaMailProperties(javaMailProperties);

        return bean;
    }

    @Bean
    public SimpleMailMessage mailMessage() {
        SimpleMailMessage bean = new SimpleMailMessage();
        bean.setFrom("coj@uci.cu");
        return bean;
    }

    @Bean
    public DataSource ds() {

        try {
            HikariDataSource bean = new HikariDataSource();
            bean.setDriverClassName("org.postgresql.Driver");
            bean.setJdbcUrl(env.getProperty("db.url"));
            bean.setUsername(env.getProperty("db.user"));
            bean.setPassword(env.getProperty("db.password"));
            bean.setMaximumPoolSize(10);
            bean.setIdleTimeout(300000);
            bean.setConnectionTimeout(600000);
            bean.setAutoCommit(true);
            return bean;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate bean = new JdbcTemplate();
        bean.setDataSource(ds());
        return bean;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("model.entities");
        factory.setDataSource(ds());
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory());
        return txManager;
    }
}
