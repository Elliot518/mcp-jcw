package org.genesis.toolbox.beans.ui;

import org.genesis.toolbox.beans.domains.assembly.EnvAssembly;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: MyMainFrame
 * @Package org.genesis.toolbox.beans.ui
 * @Description: main ui
 * @date 2018/6/20 15:15
 */
public class MyMainFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 600;
    private static final int MEDIUM_WIDTH = 1024;
    private static final int MEDIUM_HEIGHT = 768;
    private static final int LARGE_WIDTH = 1280;
    private static final int LARGE_HEIGHT = 960;
    private static final int DLG_WIDTH = 400;
    private static final int DLG_HEIGHT = 300;

    private JPanel panel;
    private MyAboutDialog dialog;

    private DomdCheckFrame domdCheckFrame;
    private RuleFrame ruleFrame;
    private AutoCurationFrame autoCurationFrame;
    private CodeGeneratorFrame codeGeneratorFrame;

    private EnvAssembly envAssembly;

    private void showLogo(JPanel panel) {
        URL url = getClass().getClassLoader().getResource("tool-box.png");
        ImageIcon img = new ImageIcon(url);
        JLabel labelImg = new JLabel(img);
        //指定插入的位置
        labelImg.setBounds(new Rectangle(panel.getX() + 750, panel.getY() + 400, 160, 160));
//        this.setSize(661, 572);
//        this.setLayout(null);
        panel.add(labelImg);
    }

    public MyMainFrame(EnvAssembly envAssembly, ApplicationContext serviceContext) {
        panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        add(panel, BorderLayout.CENTER);
        showLogo(panel);

        this.envAssembly = envAssembly;

        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(screensize.width / 2 - DEFAULT_WIDTH / 2, screensize.height / 2 - DEFAULT_HEIGHT / 2, DEFAULT_WIDTH, DEFAULT_HEIGHT);

        /**
         * Menu Items
         */
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        JMenu buitMenu = new JMenu("BUIT");
        menuBar.add(buitMenu);
        JMenu developerMenu = new JMenu("Developer");
        menuBar.add(developerMenu);

        /**
         * Add BUIT menu item
         */
        JMenuItem dcItem = new JMenuItem("DOMD Check");
        dcItem.addActionListener(event -> {
            if (domdCheckFrame == null) {
                // first time
                domdCheckFrame = new DomdCheckFrame(MyMainFrame.this, this.envAssembly, serviceContext);
                domdCheckFrame.setBounds(screensize.width / 2 - LARGE_WIDTH / 2, screensize.height / 2 - LARGE_HEIGHT / 2, LARGE_WIDTH, LARGE_HEIGHT);
            }

            // pop up dialog
            domdCheckFrame.setVisible(true);
        });
        buitMenu.add(dcItem);

//        JMenuItem rmItem = new JMenuItem("Rule Manager");
//        rmItem.addActionListener(event -> {
//            if (ruleFrame == null) {
//                // first time
//                ruleFrame = new RuleFrame(MyMainFrame.this, this.envAssembly, serviceContext);
//                ruleFrame.setBounds(screensize.width / 2 - LARGE_WIDTH / 2, screensize.height / 2 - LARGE_HEIGHT / 2, LARGE_WIDTH, LARGE_HEIGHT);
//            }
//
//            // pop up dialog
//            ruleFrame.setVisible(true);
//        });
//        buitMenu.add(rmItem);

        /**
         * Add Developer menu item
         */
        JMenuItem cgItem = new JMenuItem("Code Generator");
        cgItem.addActionListener(event -> {
            if (codeGeneratorFrame == null) {
                // first time
                codeGeneratorFrame = new CodeGeneratorFrame(MyMainFrame.this, this.envAssembly);
                codeGeneratorFrame.setBounds(screensize.width / 2 - LARGE_WIDTH / 2, screensize.height / 2 - LARGE_HEIGHT / 2, LARGE_WIDTH, LARGE_HEIGHT);
            }

            // pop up dialog
            codeGeneratorFrame.setVisible(true);
        });
        developerMenu.add(cgItem);

        JMenuItem acItem = new JMenuItem("Auto Curation");
        acItem.addActionListener(event -> {
            if (autoCurationFrame == null) {
                // first time
                autoCurationFrame = new AutoCurationFrame(MyMainFrame.this, this.envAssembly);
                autoCurationFrame.setBounds(screensize.width / 2 - LARGE_WIDTH / 2, screensize.height / 2 - LARGE_HEIGHT / 2, LARGE_WIDTH, LARGE_HEIGHT);
            }

            // pop up dialog
            autoCurationFrame.setVisible(true);
        });
        developerMenu.add(acItem);

        /**
         * Add About and Exit menu items.
         * The About item shows the About dialog
         */
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(event -> {
            if (dialog == null) {
                // first time
                dialog = new MyAboutDialog(MyMainFrame.this);
                dialog.setBounds(screensize.width / 2 - DLG_WIDTH / 2, screensize.height / 2 - DLG_HEIGHT / 2, DLG_WIDTH, DLG_HEIGHT);
            }

            // pop up dialog
            dialog.setVisible(true);
        });
        fileMenu.add(aboutItem);

        // The Exit item exits the program.
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(event -> System.exit(0));
        fileMenu.add(exitItem);
    }
}
