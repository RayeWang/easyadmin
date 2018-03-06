package wang.raye.admin.config;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * Druid的Servlet
 * @author Raye
 * @since 2016年10月7日14:13:39
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/druid/*",
initParams={
        @WebInitParam(name="allow",value="127.0.0.1,192.168.11.246"),// IP白名单 (没有配置或者为空，则允许所有访问)
        @WebInitParam(name="loginUsername",value="Raye"),// 用户名
        @WebInitParam(name="loginPassword",value="Wo123456."),// 密码
        @WebInitParam(name="resetEnable",value="false")// 禁用HTML页面上的“Reset All”功能
})
public class DruidStatViewServlet extends StatViewServlet {


}
