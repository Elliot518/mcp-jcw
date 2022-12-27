package org.genesis.toolbox.beans.domains.action;

import org.genesis.toolbox.beans.domains.global.GApplication;
import org.genesis.toolbox.beans.ui.component.MyTextPane;
import org.genesis.toolbox.constants.debug.DebugLevel;
import org.genesis.toolbox.constants.logo.Signature;
import org.genesis.toolbox.util.Debugger;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: MemoryWatcher
 * @Package org.genesis.toolbox.beans.domains.action
 * @Description: memory watcher
 * @date 2018/7/27 16:08
 */
public class MemoryWatcher {
    public static void viewGApp(MyTextPane textPane) {
        Debugger.printLine(textPane);
        Debugger.printHtml(textPane, "GApp AC Count: " + GApplication.CONSOLE_AC_COUNTER, DebugLevel.INFO, "#FFFF3C");
        Debugger.printLine(textPane);
        Signature.printHtmlName(textPane, "#008000");
        Debugger.printLine(textPane);
    }
}
