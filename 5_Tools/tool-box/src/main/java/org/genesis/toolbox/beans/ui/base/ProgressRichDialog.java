package org.genesis.toolbox.beans.ui.base;

import org.genesis.toolbox.beans.ui.component.MyTextPane;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.List;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: ProgressRichDialog
 * @Package org.genesis.toolbox.beans.ui
 * @Description: base richtext dialog with a progress bar and two buttons
 * @date 2018/7/13 15:36
 */
public abstract class ProgressRichDialog extends JDialog {
//    public static final int TEXT_ROWS = 10;
//    public static final int TEXT_COLUMNS = 40;

    private static final int sleepTime = 250;

    protected JButton startButton;
    protected JButton clearButton;
    protected int PROGRESS_MAX;
    protected JProgressBar progressBar;
    protected MyTextPane textPane;
    protected SimulatedActivity activity;
    protected JPanel panel;

    protected JLabel labelImg = null;

    public ProgressRichDialog(JFrame owner, String title) {
        super(owner, title, true);

        panel = new JPanel();

        // this text area holds the activity output
        textPane = new MyTextPane();

        // set up panel with button and progress bar
        startButton = new JButton("Start");
        clearButton = new JButton("Clear");
        panel.add(startButton);
        panel.add(clearButton);

        add(new JScrollPane(textPane), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        clearButton.addActionListener(event ->
        {
            textPane.setText("");
        });

        pack();
    }

    protected void showLogo(String fileName, int logoWidth, int logoHeight) {
        URL url = getClass().getClassLoader().getResource(fileName);
        ImageIcon img = new ImageIcon(url);
        labelImg = new JLabel(img);
        //指定插入的位置
        labelImg.setBounds(new Rectangle(textPane.getX(), textPane.getY(), logoWidth, logoHeight));
//        this.setSize(661, 572);
//        this.setLayout(null);
        this.textPane.add(labelImg);
    }

    protected void setProgressBar(int progressMax) {
        progressBar = new JProgressBar(0, progressMax);
        progressBar.setStringPainted(true);
        panel.add(progressBar);

        // set up the button action
        startButton.addActionListener(event ->
        {
            startButton.setEnabled(false);
            new Thread(() -> processCallback()).start();
            activity = new SimulatedActivity(progressMax);
            activity.execute();
        });
    }

    class SimulatedActivity extends SwingWorker<Void, Integer> {
        private int current;
        private int target;

        /**
         * Constructs the simulated activity that increments a counter from 0 to a
         * given target.
         *
         * @param t the target value of the counter.
         */
        public SimulatedActivity(int t) {
            current = 0;
            target = t;
        }

        @Override
        protected Void doInBackground() throws Exception {
            try {
                while (current < target) {
                    Thread.sleep(sleepTime);
                    current++;
                    publish(current);
                }
            } catch (InterruptedException e) {
            }
            return null;
        }

        @Override
        protected void process(List<Integer> chunks) {
            for (Integer chunk : chunks) {
                progressBar.setValue(chunk);
            }
        }

        @Override
        protected void done() {
            startButton.setEnabled(true);
            try {
                Thread.sleep(100);
                doneCallback();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public abstract void processCallback();

    public abstract void doneCallback();
}

