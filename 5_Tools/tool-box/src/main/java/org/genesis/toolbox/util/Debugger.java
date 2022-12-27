package org.genesis.toolbox.util;

import org.genesis.toolbox.beans.domains.global.GApplication;
import org.genesis.toolbox.beans.ui.component.MyTextPane;
import org.genesis.toolbox.constants.code.GLOBAL;
import org.genesis.toolbox.constants.debug.DebugLevel;

import javax.swing.*;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: Debugger
 * @Package org.genesis.fastcode.util
 * @Description: debug utility for console print
 * @date 2018/6/4 21:28
 */
public class Debugger {
    private static final int sleepTime = 50;

    public static Integer Level;

    public static void print(String info, Integer level) {
        String dbgLevel = getDebugLevel(level);

        if (Debugger.Level >= level) {
            System.out.println("# Level: " + dbgLevel + "     " + info);
        }
    }

    public static void print(JTextArea console, String info, Integer level) {
        String dbgLevel = getDebugLevel(level);

        if (Debugger.Level >= level) {
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            console.append("# Level: " + dbgLevel + "     " + info + "\n");
            GApplication.CONSOLE_AC_COUNTER++;
        }
    }

    public static void printLine(MyTextPane console) {
        printHtml(console, "<br />", 0);
    }

    public static void printSeparator(MyTextPane console) {
        String line = "<hr style=\"border:1px solid #008000\" />";
        Debugger.printHtml(console, line , 0, "#008000");
    }

    public static void printTableSplitor(MyTextPane console) {
        Debugger.printHtml(console, "______________________________________________________________________________________________________________________________" , DebugLevel.DBG, "#008000");
    }

    public static void printHtml(MyTextPane console, String info, Integer level) {
        printHtmlWithSize(console, info, level, 18);
    }

    public static void printHtmlWithSize(MyTextPane console, String info, Integer level, int size) {
        String dbgLevel = getDebugLevel(level);
        String htmlLine = "";
        String font = "";
        String prefix = "";
        if (level.equals(DebugLevel.ERR)) {
            font = "red";
            prefix = dbgLevel + ":     ";
        } else if (level.equals(DebugLevel.INFO)) {
            font = "black";
            prefix = dbgLevel + ":     ";
        } else if (level.equals(DebugLevel.DBG)) {
            font = "green";
            prefix = dbgLevel + ":     ";
        } else {
            font = "black";
        }

        if (Debugger.Level >= level) {
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            htmlLine = "<div align=\"left\"><font color=\"" + font + "\" style= 'font-size: " + size + "px;'>" + prefix + info + "</font></div>";
            console.addText(htmlLine);
            GApplication.CONSOLE_AC_COUNTER++;
        }
    }

    public static void printHtml(MyTextPane console, String info, Integer level, String color) {
        printHtmlLineWithSize(console, info, level, color, GLOBAL.FONT_SMALL);
    }

    public static void printTwoColumns(MyTextPane console, String info1, String info2, Integer level, String color1, String color2) {
        printHtmlTwoColumnsWithSize(console, info1, info2, level, color1, color2, GLOBAL.FONT_SMALL);
    }

    public static void printBanner(MyTextPane console, String info, Integer level, String color) {
        String border = "******************************************************";
        printHtml(console, border, 0, color);
        printHtml(console, "**&nbsp;" + info, 0, color);
        printHtml(console, border, 0, color);
    }

    public static void printHead(MyTextPane console, String info, String color) {
        printHtmlLineWithSize(console, info, 0, color, GLOBAL.FONT_MEDIUM);
    }

    public static void printSubHead(MyTextPane console, String info, String color) {
        printHtmlLineWithSize(console, info, 0, color, GLOBAL.FONT_NORMAL);
    }

    public static void printHtmlLineWithSize(MyTextPane console, String info, Integer level, String color, int size) {
        String dbgLevel = getDebugLevel(level);
        String htmlLine = "";

        if (Debugger.Level >= level) {
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            htmlLine = "<div align=\"left\" style=\"padding-left: 10px;font-size: " + size + "px;\"><font color=\"" + color + "\">" + info + "</font></div>";
            console.addText(htmlLine);
            GApplication.CONSOLE_AC_COUNTER++;
        }
    }

    public static void printHtmlTwoColumnsWithSize(MyTextPane console, String info1, String info2, Integer level, String color1, String color2, int size) {
        String dbgLevel = getDebugLevel(level);
        String htmlLine = "<table border=\"0\" width=\"98%\">";

        if (Debugger.Level >= level) {
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            htmlLine += "<tr><td width=\"60%\"><span style=\"float: left;\"><font color=\"" + color1 + "\">" + info1 + "</font></span></td><td><span style=\"float: left;\"><font color=\"" + color2 + "\">" + info2 + "</font></span></td></tr></table>";
            console.addText(htmlLine);
            GApplication.CONSOLE_AC_COUNTER++;
        }
    }

    private static String getDebugLevel(Integer level) {
        String dbgLevel = "";
        switch (level) {
            case 3:
                dbgLevel = "DBG";
                break;
            case 2:
                dbgLevel = "INFO";
                break;
            case 1:
                dbgLevel = "ERR";
                break;
            default:
                dbgLevel = "X";
                break;
        }
        return dbgLevel;
    }

    public static void printImage(MyTextPane console) {
        String line = "<img src=\"file://image/tool-box.png\" />";

        console.addText(line);
    }
}
