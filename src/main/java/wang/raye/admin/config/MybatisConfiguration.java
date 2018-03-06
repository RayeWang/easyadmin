package wang.raye.admin.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
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
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Config;
import tk.mybatis.mapper.mapperhelper.MapperHelper;

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

	private Logger logger = Logger.getLogger(MybatisConfiguration.class);
	@Resource
	private DataSource dataSource;

	@Bean(name = "sqlSessionFactory")
	@ConditionalOnMissingBean
	public SqlSessionFactory sqlSessionFactory() {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		try {
			
			SqlSessionFactory session = bean.getObject();
			MapperHelper mapperHelper = new MapperHelper();
			//特殊配置

			Config config = new Config();
			//具体支持的参数看后面的文档

			config.setNotEmpty(true);
			//设置配置

			mapperHelper.setConfig(config);
			// 注册自己项目中使用的通用Mapper接口，这里没有默认值，必须手动注册

			mapperHelper.registerMapper(Mapper.class);
			//配置完成后，执行下面的操作

			mapperHelper.processConfiguration(session.getConfiguration());
			return session;
		} catch (Exception e) {
			logger.error("配置SqlSessionFactory失败",e);
		}
		return null;
	}


	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	@Bean
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		logger.info("事物配置");
		return new DataSourceTransactionManager(dataSource);
	}
}
