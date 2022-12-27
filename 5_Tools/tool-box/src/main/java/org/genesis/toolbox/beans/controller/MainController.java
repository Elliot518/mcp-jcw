package org.genesis.toolbox.beans.controller;

import org.genesis.toolbox.beans.config.ServiceConfig;
import org.genesis.toolbox.beans.domains.assembly.EnvAssembly;
import org.genesis.toolbox.beans.domains.debug.DebugInfo;
import org.genesis.toolbox.beans.domains.global.GApplication;
import org.genesis.toolbox.beans.service.EnvService;
import org.genesis.toolbox.beans.ui.MyAboutDialog;
import org.genesis.toolbox.constants.config.FoldersConf;
import org.genesis.toolbox.util.Debugger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.genesis.toolbox.beans.ui.MyMainFrame;

import javax.swing.*;
import java.awt.*;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: MainController
 * @Package org.genesis.toolbox.beans.controller
 * @Description: main controller of the application
 * @date 2018/6/20 15:19
 */
@Controller("main")
public class MainController {
    @Autowired
    private DebugInfo debugInfo;

    @Autowired
    private EnvService envService;

    private ApplicationContext serviceContext;

    public void execute() {
        initContext();
        initCounters();
        initDebug();

        // environment bootstrap
        EnvAssembly envAssembly = envService.bootstrap(FoldersConf.JSON_MODE);

        MyMainFrame myMainFrame = new MyMainFrame(envAssembly, serviceContext);
        EventQueue.invokeLater(() -> {
            myMainFrame.setTitle("Tool Box");
            myMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            myMainFrame.setVisible(true);
        });
    }

    private void initContext() {
        serviceContext = new AnnotationConfigApplicationContext(ServiceConfig.class);
    }

    private void initDebug() {
        Debugger.Level = debugInfo.getLevel();
    }

    private void initCounters() {
        GApplication.CONSOLE_AC_COUNTER = 0;
    }
}
