package org.genesis.toolbox.beans.ui;

import org.genesis.toolbox.beans.domains.assembly.EnvAssembly;
import org.genesis.toolbox.beans.ui.constraints.GBC;
import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * A frame that uses a grid bag layout to arrange font selection components.
 */
public class RuleFrame extends JFrame {
    public static final int TEXT_ROWS = 20;
    public static final int TEXT_COLUMNS = 40;

    private JTextField ruleText;
    private JCheckBox bold;
    private JComboBox<String> actionList;

    private JTable ruleTable;
    private JTable outputTable;

    private String[] ruleColumns = {"Rule", "Target Column", "Description"};
    private Object[][] ruleCells = {
            {"T1", "sourceSystem", "Get sourceSystem by sourceSystem-localSourceSystem = \"project_one\""},
            {"T2", "sourceSystem", "Get localSubsDocNo by sourceSystem-subsDocNo = \"xxx\""}
    };

    private String[] columnNames = {"Planet", "Radius", "Moons", "Gaseous", "Color"};
    private Object[][] cells = {{"Mercury", 2440.0, 0, false, Color.YELLOW},
            {"Venus", 6052.0, 0, false, Color.YELLOW}, {"Earth", 6378.0, 1, false, Color.BLUE},
            {"Mars", 3397.0, 2, false, Color.RED}, {"Jupiter", 71492.0, 16, true, Color.ORANGE},
            {"Saturn", 60268.0, 18, true, Color.ORANGE},
            {"Uranus", 25559.0, 17, true, Color.BLUE}, {"Neptune", 24766.0, 8, true, Color.BLUE},
            {"Pluto", 1137.0, 1, false, Color.BLACK}};

    public RuleFrame(JFrame owner, EnvAssembly envAssembly, ApplicationContext serviceContext) {
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);

        ActionListener listener = event -> updateSample();

        // construct components

        JLabel ruleLabel = new JLabel("Rule Name: ");

        ruleText = new JTextField();

        //face.addActionListener(listener);

        JLabel actionLabel = new JLabel("Action: ");

        bold = new JCheckBox("Bold");
        bold.addActionListener(listener);

        actionList = new JComboBox<>(new String[]{"Get", "Skip", "Concatenate", "Group"});

        ruleTable = new JTable(ruleCells, ruleColumns);
        ruleTable.setAutoCreateRowSorter(true);

        outputTable = new JTable(cells, columnNames);
        outputTable.setAutoCreateRowSorter(true);


        // add components to grid, using GBC convenience class

        add(ruleLabel, new GBC(0, 0).setAnchor(GBC.EAST));
        add(ruleText, new GBC(1, 0).setFill(GBC.HORIZONTAL).setWeight(100, 0)
                .setInsets(1));
        add(actionLabel, new GBC(0, 1).setAnchor(GBC.EAST));
        add(actionList, new GBC(1, 1).setFill(GBC.HORIZONTAL).setWeight(100, 0)
                .setInsets(1));
        //add(bold, new GBC(0, 2, 2, 1).setAnchor(GBC.CENTER).setWeight(100, 100));
        //add(actionList, new GBC(0, 3, 2, 1).setAnchor(GBC.CENTER).setWeight(100, 100));

        add(new JScrollPane(ruleTable), new GBC(2, 0, 1, 2).setFill(GBC.BOTH).setWeight(100, 50));
        add(new JScrollPane(outputTable), new GBC(2, 2, 1, 2).setFill(GBC.BOTH).setWeight(100, 50));
        pack();
        updateSample();
    }

    public void updateSample() {
        /*String fontFace = (String) face.getSelectedItem();
        int fontStyle = (bold.isSelected() ? Font.BOLD : 0)
                + (italic.isSelected() ? Font.ITALIC : 0);
        int fontSize = size.getItemAt(size.getSelectedIndex());
        Font font = new Font(fontFace, fontStyle, fontSize);
        sample.setFont(font);
        sample.repaint();*/
    }
}