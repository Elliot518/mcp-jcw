package org.genesis.toolbox.beans.ui;

import org.genesis.toolbox.beans.core.CodeGenerator;
import org.genesis.toolbox.beans.core.impl.ACXmlCodeGenerator;
import org.genesis.toolbox.beans.domains.action.DomdHelper;
import org.genesis.toolbox.beans.domains.action.MemoryWatcher;
import org.genesis.toolbox.beans.domains.assembly.EnvAssembly;
import org.genesis.toolbox.beans.domains.excel.EntityMap;
import org.genesis.toolbox.beans.ui.base.HackConsole;
import org.genesis.toolbox.constants.code.GLOBAL;
import org.genesis.toolbox.constants.logo.Signature;

import javax.swing.*;

/**
 * @author KG(Kelvin Gu)
 * @Name: AutoCurationFrame
 * @Description: auto curation frame
 * @date 06-21-2018 03:54:32
 */
public class AutoCurationFrame extends HackConsole {
    private EnvAssembly envAssembly;

    private CodeGenerator acXmlCodeGenerator;

    public AutoCurationFrame(JFrame owner, EnvAssembly envAssembly) {
        super(owner, "ToolBox - Auto Curation");

        this.envAssembly = envAssembly;
        this.acXmlCodeGenerator = new ACXmlCodeGenerator();

        PROGRESS_MAX = 50;
        setProgressBar(PROGRESS_MAX);
    }

    @Override
    public void processCallback() {
        Signature.printVersionInfo(textPane, GLOBAL.VERSION_NO);
        showLogo("tool-box3.png", 220, 100);

        EntityMap map = envAssembly.getDomdGroup().getEntityMap();
        DomdHelper.printEntityMap(map, textPane);

        DomdHelper.printInMemoryTables(envAssembly.getDomdGroup().getInputTables(), textPane);

        acXmlCodeGenerator.generate(envAssembly);

        MemoryWatcher.viewGApp(textPane);
    }

    @Override
    public void doneCallback() {

    }
}




