package org.genesis.toolbox.beans.ui.component;

import java.io.IOException;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLDocument;

/**
 * @author KG(Kelvin Gu)
 * @ClassName: MyTextPane
 * @Package org.genesis.toolbox.beans.ui
 * @Description: my custom textpane
 * @date 2018/7/13 22:41
 */
public class MyTextPane extends JTextPane {

    /**
     * Creates a new instance of JTextPanel_N
     */
    StringBuffer temp = new StringBuffer();
    javax.swing.text.html.HTMLDocument hd;
    int tempnum = 0;

    public MyTextPane() {
        super.setContentType("text/html");
        super.setEditable(false);
        hd = new HTMLDocument();
        hd = (HTMLDocument) super.getDocument();


    }

    /**
     * add html text
     */
    public void addText(String str) {

        if (tempnum == 0) {
            try {
                hd.setInnerHTML(hd.getDefaultRootElement(), str);
            } catch (BadLocationException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            tempnum++;
        } else {
            try {
                //System.out.println("super.getDocument().getEndPosition().getOffset() is: " + super.getDocument().getEndPosition().getOffset());
                hd.insertBeforeEnd(hd.getDefaultRootElement(), str);
                super.setCaretPosition(super.getDocument().getLength());
            } catch (BadLocationException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
        //temp.append(str);
        // super.setText(temp.toString());
    }
}
