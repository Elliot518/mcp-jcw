package org.genesis.toolbox.beans.ui.base;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.List;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: ProgressConsoleDialog
 * @Package org.genesis.toolbox.beans.ui
 * @Description: base dialog with a progress bar and two buttons
 * @date 2018/7/13 15:36
 */
public abstract class ProgressConsoleDialog extends JDialog {
    public static final int TEXT_ROWS = 10;
    public static final int TEXT_COLUMNS = 40;

    protected JButton startButton;
    protected JButton clearButton;
    protected int PROGRESS_MAX;
    protected JProgressBar progressBar;
    protected JTextArea textArea;
    protected SimulatedActivity activity;
    protected JPanel panel;

    public ProgressConsoleDialog(JFrame owner, String title) {
        super(owner, title, true);

        panel = new JPanel();

        // this text area holds the activity output
        textArea = new JTextArea(TEXT_ROWS, TEXT_COLUMNS);

        // set up panel with button and progress bar
        startButton = new JButton("Start");
        clearButton = new JButton("Clear");
        panel.add(startButton);
        panel.add(clearButton);

        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        clearButton.addActionListener(event ->
        {
            textArea.setText("");
        });

        pack();
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
                    Thread.sleep(100);
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
                Thread.sleep(1000);
                doneCallback();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public abstract void processCallback();

    public abstract void doneCallback();
}

