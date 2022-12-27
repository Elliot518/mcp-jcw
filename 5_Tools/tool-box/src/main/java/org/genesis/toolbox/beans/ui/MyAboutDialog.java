package org.genesis.toolbox.beans.ui;

import java.awt.*;

import javax.swing.*;

/**
 * A sample modal dialog that displays a message and waits for the user to click the OK button.
 */
public class MyAboutDialog extends JDialog {
    public MyAboutDialog(JFrame owner) {
        super(owner, "ToolBox", true);

        Font f1 = new Font("Serif", Font.BOLD, 80);
        Font f2 = new Font("Serif", Font.BOLD, 22);

        // add HTML label to center
        JLabel label1 = new JLabel("<html><br /><br />Tool Box</html>", JLabel.CENTER);
        label1.setFont(f1);
        add(label1, BorderLayout.NORTH);

        JLabel label2 = new JLabel("<html><i>Created By KG<i></html>", JLabel.CENTER);
        label1.setFont(f2);
        add(label2, BorderLayout.CENTER);

        JLabel label3 = new JLabel("<html><i>Version: v0.6 beta<i></html>", JLabel.CENTER);
        label1.setFont(f2);
        add(label3, BorderLayout.CENTER);

        // OK button closes the dialog
        JButton ok = new JButton("OK");
        ok.addActionListener(event -> setVisible(false));

        // add OK button to southern border
        JPanel panel = new JPanel();
        panel.add(ok);
//        JTextArea text = new JTextArea();
//        text.setRows(5);
//        text.setColumns(30);

//        panel.add(text);
        add(panel, BorderLayout.SOUTH);

        pack();
    }
}
