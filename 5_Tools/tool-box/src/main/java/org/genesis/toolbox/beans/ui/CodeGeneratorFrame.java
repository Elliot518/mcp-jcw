package org.genesis.toolbox.beans.ui;

import org.genesis.toolbox.beans.core.CodeGenerator;
import org.genesis.toolbox.beans.core.ICodeGenerator;
import org.genesis.toolbox.beans.core.impl.ACXmlCodeGenerator;
import org.genesis.toolbox.beans.core.impl.MybatisPlusCodeGenerator;
import org.genesis.toolbox.beans.domains.action.DomdHelper;
import org.genesis.toolbox.beans.domains.action.MemoryWatcher;
import org.genesis.toolbox.beans.domains.assembly.EnvAssembly;
import org.genesis.toolbox.beans.domains.excel.EntityMap;
import org.genesis.toolbox.beans.ui.base.HackConsole;
import org.genesis.toolbox.constants.code.GLOBAL;
import org.genesis.toolbox.constants.debug.DebugLevel;
import org.genesis.toolbox.constants.logo.Signature;
import org.genesis.toolbox.util.Debugger;

import javax.swing.*;

/**
 * @author KG(Kelvin Gu)
 * @Name: CodeGeneratorFrame
 * @Description: code generator frame
 * @date 10-07-2022 12:02:08
 */
public class CodeGeneratorFrame extends HackConsole {
    private EnvAssembly envAssembly;

//    private CodeGenerator acXmlCodeGenerator;

    private ICodeGenerator generator;

    public CodeGeneratorFrame(JFrame owner, EnvAssembly envAssembly) {
        super(owner, "ToolBox - Code Generator");

        this.envAssembly = envAssembly;
        //this.acXmlCodeGenerator = new ACXmlCodeGenerator();
        this.generator = new MybatisPlusCodeGenerator();

        PROGRESS_MAX = 15;
        setProgressBar(PROGRESS_MAX);
    }

    @Override
    public void processCallback() {
        Signature.printVersionInfo(textPane, GLOBAL.VERSION_NO);
        showLogo("tool-box3.png", 220, 100);

//        EntityMap map = envAssembly.getDomdGroup().getEntityMap();
//        DomdHelper.printEntityMap(map, textPane);
//
//        DomdHelper.printInMemoryTables(envAssembly.getDomdGroup().getInputTables(), textPane);

        String outputPath = envAssembly.getCodeConfigGroup().getCodeGenConfig().getCodeOutputPath();

        Debugger.printLine(textPane);
        Debugger.printHead(textPane, "代码生成中, 请等待...", "#885FA0");
        Debugger.printSeparator(textPane);

        //acXmlCodeGenerator.generate(envAssembly);

        generator.generate(envAssembly);
        Debugger.printHtml(textPane, String.format("代码生成完毕! 请去[%s]位置查看代码!", outputPath), DebugLevel.DBG, "#008000");

        MemoryWatcher.viewGApp(textPane);
    }

    @Override
    public void doneCallback() {

    }
}




