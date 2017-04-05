package wang.raye.admin.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.annotation.Resource;
import javax.sql.DataSource;


/**
 * MyBatis的配置类
 * 
 * @author Raye
 * @since 2016年10月7日14:13:39
 */
@Configuration
@AutoConfigureAfter({ DruidDataSourceConfig.class })
@EnableTransactionManagement
public class MybatisConfiguration implements TransactionManagementConfigurer {
	@Resource
	private DataSource dataSource;

	@Bean(name = "sqlSessionFactory")
	@ConditionalOnMissingBean
	public SqlSessionFactory sqlSessionFactory() {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		try {
			
			SqlSessionFactory session = bean.getObject();
			
			return session;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
//	@Bean
//	public MapperHelper mapperHelper(){
//		
//		return mapperHelper;
//	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	@Bean
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		System.out.println("事物配置");
		return new DataSourceTransactionManager(dataSource);
	}
}
