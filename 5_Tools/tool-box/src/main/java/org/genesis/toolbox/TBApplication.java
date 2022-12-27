package org.genesis.toolbox;

import org.genesis.toolbox.beans.controller.MainController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: TBApplication
 * @Package org.genesis.toolbox
 * @Description: toolbox application main
 * @date 2018/6/19 16:26
 */
public class TBApplication {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
        MainController controller = ctx.getBean("main", MainController.class);
        controller.execute();
        ((ClassPathXmlApplicationContext) ctx).close();
    }
}
