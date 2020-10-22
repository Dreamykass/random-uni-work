package sengine.drive;

import javax.swing.*;

public class InputDialog {
    /**
     * @param _question
     * @param frame
     * @return
     */
    static public String get(String _question, JFrame frame) {
        return (String) JOptionPane.showInputDialog(
                frame,
                _question,
                "Dialog",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "ham");
    }
}
