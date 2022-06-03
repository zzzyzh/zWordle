package MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The {@code KeyboardList} class extends {@code JPanel} stores the definition the component.
 *
 * <p>
 *     The panel display a virtual keyboard which can record the using of the letters. The letters of it will display
 *     the same color as the letters of the {@code LetterList}. At the same time, the last letter box is the hint letter
 *     which can notify the player one letter of the true word randomly.
 * </p>
 *
 * @author Zhonghao Yan
 * @version 1.0
 */
public class KeyboardList extends JPanel {

    /**
     * A {@code LetterList} storing the input letters.
     */
    private LetterList letterList;

    /**
     * A two dimension arrays of {@code JLabel[][]} holding the virtual keyboard letters.
     */
    private JLabel[][] keyboardList;

    /**
     * A static String holding the letters with the same order as physical the keyboard .
     */
    private static final String keyboard = "QWERTYUIOPASDFGHJKLZXCVBNM.";

    /**
     * A static constant holding the value of row of keyboardList.
     */
    private static final int KEYBOARD_ROWS = 3;

    /**
     * A static constant holding the value of col of keyboardList.
     */
    private static final int KEYBOARD_COLS = 9;

    /**
     * A static constant holding the width of the gape of each letter.
     */
    private static final int GAPE = 8;

    /**
     * A static constant holding the width of each letter box.
     */
    private static final int SIDE = 36;

    /**
     * A static color holding the initial background color of each letter.
     */
    private static final Color INIT_BACKGROUND = Color.WHITE;

    /**
     * The constructor for class {@code KeyboardList}.
     *
     * <p>
     *     This constructor will initiate panel and complete the configuration.
     * </p>
     *
     * @param frame a frame that we have initialized in the {@code zWordle}.
     */
    KeyboardList(zWordle frame) {
        // Initialize the attributes of the panel.
        this.letterList = frame.getLetterList();
        this.setLayout(new GridLayout(KEYBOARD_ROWS, KEYBOARD_COLS, GAPE, GAPE));
        this.setSize(KEYBOARD_COLS*SIDE + (KEYBOARD_COLS-1)*GAPE, KEYBOARD_ROWS*SIDE + (KEYBOARD_ROWS-1)*GAPE); // (388, 124)
        // Close the opaque to set background color.
        this.setOpaque(false);
        this.setKeyboardList();
    }

    /**
     * This method initializes virtual keyboard with the constant {@code String} keyboard.
     *
     * <p>
     *     The hint message will display for calling through {@link LetterList#displayHints()}.
     * </p>
     */
    public void setKeyboardList() {
        // Initialize the letters of the virtual keyboard.
        keyboardList = new JLabel[KEYBOARD_ROWS][KEYBOARD_COLS];
        for (int i=0; i<KEYBOARD_ROWS; i++) {
            for (int j=0; j<KEYBOARD_COLS; j++) {
                keyboardList[i][j] = new JLabel();
                keyboardList[i][j].setSize(SIDE, SIDE);
                keyboardList[i][j].setText(String.valueOf(keyboard.charAt(i*KEYBOARD_COLS+j)));
                keyboardList[i][j].setHorizontalAlignment(JLabel.CENTER);
                keyboardList[i][j].setFont(new Font("Times New Rome", Font.BOLD, 16));
                keyboardList[i][j].setOpaque(true);
                keyboardList[i][j].setBackground(INIT_BACKGROUND);
                keyboardList[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                this.add(keyboardList[i][j]);
            }
        }
        // Initialize the last letter box as the hint.
        keyboardList[KEYBOARD_ROWS-1][KEYBOARD_COLS-1].setText("");
        ImageIcon icon = new ImageIcon(new ImageIcon("data/tips.png").getImage().getScaledInstance(SIDE-5, SIDE-5, Image.SCALE_SMOOTH));
        keyboardList[KEYBOARD_ROWS-1][KEYBOARD_COLS-1].setIcon(icon);
        keyboardList[KEYBOARD_ROWS-1][KEYBOARD_COLS-1].addMouseListener(new HintLetterListener());
    }

    /**
     * A getter method to return the {@code JLabel[][]} that use or change the attributes of the {@code KeyboardList}
     * in the class invoking the methods.
     *
     * @return a {@code JLabel[][]} when the method is invoked by {@code KeyboardList}.
     */
    public JLabel[][] getKeyboardList() { return keyboardList; }

    /**
     * An inner class {@code HintLetterListener} extends {@code MouseAdapter} to display a letter of the word randomly.
     */
    class HintLetterListener extends MouseAdapter {
        /**
         * Override the method in {@code MouseAdapter}.
         *
         * <p>
         *     The new message window will be displayed for calling through {@link LetterList#displayHints()}.
         * </p>
         *
         * @param e indicates that a mouse action occurred in a component.
         */
        @Override
        public void mousePressed(MouseEvent e) {
            letterList.displayHints();
        }
    }

}
