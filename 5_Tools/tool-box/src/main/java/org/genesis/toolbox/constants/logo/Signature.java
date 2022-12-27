package org.genesis.toolbox.constants.logo;

import org.genesis.toolbox.beans.ui.component.MyTextPane;
import org.genesis.toolbox.util.Debugger;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: Signature
 * @Package org.genesis.toolbox.constants.logo
 * @Description: signature add
 * @date 2018/6/20 17:34
 */
public class Signature {
    public static void printHtmlName(MyTextPane textPane) {
        printHtmlName(textPane, "#000000");
    }

    public static void printVersionInfo(MyTextPane textPane, String versionNo) {
        Debugger.printLine(textPane);
        Debugger.printSubHead(textPane,"<center>____________________  TOOL BOX  ___________________</center>", "#008000");
        Debugger.printLine(textPane);
        Debugger.printHtml(textPane,"<center>Author: Kelvin Gu</center>", 0, "#3CB371");
        Debugger.printLine(textPane);
        Debugger.printHtml(textPane,"<center>Version: " + versionNo + "</center>", 0, "#CD853F");
        Debugger.printLine(textPane);
        Debugger.printSubHead(textPane,"<center>___________________________________________________</center>", "#008000");
        Debugger.printLine(textPane);
    }

    public static void printHtmlName(MyTextPane textPane, String color) {

        String line1 = "  ▄█     ▄█▄    ▄██████▄  ";
        String line2 = "  ███ ▄███▀   ███    ███ ";
        String line3 = "  ███▐██▀     ███    █▀  ";
        String line4 = "  ▄█████▀     ▄███        ";
        String line5 = " ▀▀█████▄   ▀▀███  ███▄  ";
        String line6 = "  ███▐██▄     ███    ███ ";
        String line7 = "  ███ ▀███▄   ███    ███ ";
        String line8 = "  ███   ▀█▀    ████████▀  ";

        Debugger.printLine(textPane);
        Debugger.printLine(textPane);
        Debugger.printHtml(textPane,"_____________________  Created By  ___________________", 0, color);
        Debugger.printHtml(textPane,line1.replace(" ", "&nbsp;"), 0, color);
        Debugger.printHtml(textPane,line2.replace(" ", "&nbsp;"), 0, color);
        Debugger.printHtml(textPane,line3.replace(" ", "&nbsp;"), 0, color);
        Debugger.printHtml(textPane,line4.replace(" ", "&nbsp;"), 0, color);
        Debugger.printHtml(textPane,line5.replace(" ", "&nbsp;"), 0, color);
        Debugger.printHtml(textPane,line6.replace(" ", "&nbsp;"), 0, color);
        Debugger.printHtml(textPane,line7.replace(" ", "&nbsp;"), 0, color);
        Debugger.printHtml(textPane,line8.replace(" ", "&nbsp;"), 0, color);
        Debugger.printHtml(textPane,"_____________________________________________________", 0, color);
        Debugger.printLine(textPane);
        Debugger.printLine(textPane);
    }
}
