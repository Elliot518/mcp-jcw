package org.genesis.toolbox.util;

import org.genesis.toolbox.beans.ui.component.MyTextPane;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: ScreenMaster
 * @Package org.genesis.toolbox.util
 * @Description: screen master is to print all kinds of imaginary images
 * @date 2018/7/27 16:24
 */
public class ScreenMaster {
    public static void printInspectorStart(MyTextPane textPane) {
        String color = "#008000";

        String line1 = "*** Application Inspector Start... ***";
        String line2 = "            -^- -^- ";
        String line3 = "             @   @";
        String line4 = "              ###";
        String line5 = "**************************************";

        Debugger.printHtml(textPane,line1, 0, color);
        Debugger.printHtml(textPane,line2.replace(" ", "&nbsp;"), 0, color);
        Debugger.printHtml(textPane,line3.replace(" ", "&nbsp;"), 0, color);
        Debugger.printHtml(textPane,line4.replace(" ", "&nbsp;"), 0, color);
        Debugger.printHtml(textPane,line5.replace(" ", "&nbsp;"), 0, color);
    }
}
