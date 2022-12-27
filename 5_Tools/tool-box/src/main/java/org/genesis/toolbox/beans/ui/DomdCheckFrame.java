package org.genesis.toolbox.beans.ui;

import org.genesis.toolbox.beans.domains.action.DomdHelper;
import org.genesis.toolbox.beans.domains.assembly.EnvAssembly;
import org.genesis.toolbox.beans.service.DataCheckService;
import org.genesis.toolbox.beans.ui.base.WhiteBoard;
import org.genesis.toolbox.constants.code.GLOBAL;
import org.genesis.toolbox.constants.logo.Signature;
import org.springframework.context.ApplicationContext;

import javax.swing.*;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: DomdCheckFrame
 * @Package org.genesis.toolbox.beans.ui
 * @Description: check domd files data dialog
 * @date 2018/7/13 15:54
 */
public class DomdCheckFrame extends WhiteBoard {
    private EnvAssembly envAssembly;

    private DataCheckService dataCheckService;

    public DomdCheckFrame(JFrame owner, EnvAssembly envAssembly, ApplicationContext serviceContext) {
        super(owner, "ToolBox - DOMD Check");

        this.envAssembly = envAssembly;

        dataCheckService = serviceContext.getBean("dataCheckProvider", DataCheckService.class);

        PROGRESS_MAX = 80;
        setProgressBar(PROGRESS_MAX);
    }

    @Override
    public void processCallback() {
        Signature.printVersionInfo(textPane, GLOBAL.VERSION_NO);
        showLogo("tool-box2.png", 370, 110);

        //ScreenMaster.printInspectorStart(textPane);

        DomdHelper.printEntityMap(envAssembly.getDomdGroup().getEntityMap(), textPane);

        DomdHelper.printInMemoryTables(envAssembly.getDomdGroup().getInputTables(), textPane);

        // check input tables
        dataCheckService.checkInputData(envAssembly.getDomdGroup(), textPane);

        Signature.printHtmlName(textPane, "#80CAF4");
    }

    @Override
    public void doneCallback() {

    }
}
