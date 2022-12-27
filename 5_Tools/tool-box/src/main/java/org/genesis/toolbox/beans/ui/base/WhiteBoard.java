package org.genesis.toolbox.beans.ui.base;

import javax.swing.*;
import java.awt.*;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: WhiteBoard
 * @Package org.genesis.toolbox.beans.ui
 * @Description: white board console with progress bar
 * @date 2018/7/13 15:44
 */
public abstract class WhiteBoard extends ProgressRichDialog {
    public WhiteBoard(JFrame owner, String title) {
        super(owner, title);

        panel.setPreferredSize(new Dimension(300, 150));

        textPane.setBackground(new Color(255, 255, 255));
        textPane.setForeground(new Color(0, 0, 0));
    }

    @Override
    public abstract void processCallback();

    @Override
    public abstract void doneCallback();
}

