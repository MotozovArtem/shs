package ru.rienel.shsheadcontroller.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:persistence.properties")
@ConfigurationProperties(prefix = "db")
public class DatabaseProperties {

	private String url;

	private String username;

	private String password;

	private String driverClassName;

	private Hibernate hibernate;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public Hibernate getHibernate() {
		return hibernate;
	}

	public void setHibernate(Hibernate hibernate) {
		this.hibernate = hibernate;
	}

	public static class Hibernate {

		private String dialect;

		private Boolean showSql;

		public String getDialect() {
			return dialect;
		}

		public void setDialect(String dialect) {
			this.dialect = dialect;
		}

		public Boolean getShowSql() {
			return showSql;
		}

		public void setShowSql(Boolean showSql) {
			this.showSql = showSql;
		}
	}
}
