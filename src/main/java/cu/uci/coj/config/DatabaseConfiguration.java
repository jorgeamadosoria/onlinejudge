/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package cu.uci.coj.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource({ "classpath:cu/uci/coj/config/config.properties",
		"classpath:cu/uci/coj/config/sql/contest_awards.properties",
		"classpath:cu/uci/coj/config/sql/notifications.properties",
		"classpath:cu/uci/coj/config/sql/postgres.properties", "classpath:cu/uci/coj/config/sql/mail.properties",
		"classpath:cu/uci/coj/config/sql/entries.properties", "classpath:cu/uci/coj/config/sql/json.api.properties",
		"classpath:cu/uci/coj/config/sql/achievements.properties", "classpath:cu/uci/coj/config/sql/events.properties",
		"classpath:cu/uci/coj/config/sql/corrections.properties",
		"classpath:cu/uci/coj/config/sql/teamanalyzer.properties",
		"classpath:cu/uci/coj/config/sql/recommender.properties", "classpath:cu/uci/coj/config/sql/wboard.properties",
		"classpath:cu/uci/coj/config/sql/ws.properties" })
public class DatabaseConfiguration {

	@Resource
	protected Environment env;

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
		} catch (Exception e) {
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
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(ds());
	}
}
