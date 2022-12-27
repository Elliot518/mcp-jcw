//package org.genesis.toolbox.util;
//
//import com.jnj.adf.client.api.ADFService;
//import com.jnj.adf.client.api.PageResults;
//import com.jnj.adf.config.annotations.EnableADF;
//import com.jnj.adf.grid.client.api.impl.ADFServiceImpl;
//import com.jnj.adf.grid.client.api.impl.EntryImpl;
//import com.jnj.adf.grid.support.system.ADFConfigHelper;
//import com.jnj.adf.grid.utils.SpringBeanUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.Map;
//
//public class AdfUtil {
//
//
//    protected static ADFService adfService;
//
//    private static String USERNAME = "";
//    private static String PASSWORD = "";
//
//    public static String ENV = "";
//    public static String regionPath = "orig_btch";
//
//    static {
//        ApplicationContext applicationContext = SpringBeanUtils.initContext(AdfUtil.ADFConfig.class);
//        adfService = (ADFService) applicationContext.getBean(ADFService.class);
//        USERNAME = ADFConfigHelper.getProperty("login.name");
//        PASSWORD = ADFConfigHelper.getProperty("login.password");
//    }
//
//    public static String getRealRegionPath(String region) {
//        if (StringUtils.isNotEmpty(ENV)){
//            return "/" + ENV + region;
//        }
//        return region;
//    }
//
//    public static void connect() {
//        adfService.login(USERNAME, PASSWORD);
//    }
//
//    public static PageResults<String> query(String regionPath) {
//        PageResults<String> result = adfService.onPath(getRealRegionPath(regionPath)).queryPage("");
//        return result;
//    }
//
//    public static long count(String regionPath) {
//        long num = adfService.onPath(getRealRegionPath(regionPath)).count();
//        return num;
//    }
//
//    public static void disconnect() {
//        adfService.loginOut();
//    }
//
//    @EnableADF
//    protected static class ADFConfig {
//        protected ADFConfig() {
//        }
//    }
//}
//
