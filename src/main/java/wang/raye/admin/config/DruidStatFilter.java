package wang.raye.admin.config;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

@WebFilter(filterName="druidWebStatFilter",urlPatterns="/*",
initParams={
    @WebInitParam(name="exclusions",value="*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")
})
/**
 * Druid拦截器，用于查看Druid监控
 * @author Raye
 * @since 2016年10月7日14:59:27
 */
public class DruidStatFilter extends WebStatFilter {

}
