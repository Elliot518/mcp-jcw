package org.genesis.toolbox.beans.ui.base;

import javax.swing.*;
import java.awt.*;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: HackConsole
 * @Package org.genesis.toolbox.beans.ui
 * @Description: console for the hacker
 * @date 2018/6/20 20:52
 */
public abstract class HackConsole extends ProgressRichDialog {
    public HackConsole(JFrame owner, String title) {
        super(owner, title);

        textPane.setBackground(new Color(0, 0, 0));
        textPane.setForeground(new Color(0, 128, 0));
    }

    @Override
    public abstract void processCallback();

    @Override
    public abstract void doneCallback();
}
