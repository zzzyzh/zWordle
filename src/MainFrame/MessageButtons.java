package MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The {@code MessageButton} class stores the definition the {@code JButton} of the {@code MessageFrame}.
 *
 * <p>
 *     The {@code MessageButton} defines two types {@code JButton}: "Back" and "Next".
 *     The "Back" button let players back to guess the current word.
 *     The "Next" button let players play a new game.
 * </p>
 *
 * @author Zhonghao Yan
 * @version 1.0
 */

public class MessageButtons {

    /**
     * A {@code LetterList} storing the input letters.
     */
    private LetterList letterList;

    /**
     * A {@code JButton} letting players back to guess the current word.
     */
    private JButton backButton;

    /**
     * A {@code JButton} getting a new random word from dictionary.txt and refreshing the window.
     */
    private JButton nextButton;

    /**
     * A static constant holding the width of buttons.
     */
    private static final int WIDTH = 140;

    /**
     * A static constant holding the height of buttons.
     */
    private static final int HEIGHT = 60;

    /**
     * A static constant holding the dimension of buttons.
     */
    private static final Dimension preferredSize = new Dimension(WIDTH,HEIGHT);

    /**
     * The constructor for class {@code MessageButtons}.
     *
     * <p>
     *     This constructor will complete the configuration of {@code JButton}.
     * </p>
     *
     * @param frame a frame that we have initialized in the {@code zWordle}.
     */
    MessageButtons(zWordle frame) {
        // Initialize the attributes of the buttons.
        this.letterList = frame.getLetterList();
        backButton = new JButton("Back");
        nextButton = new JButton("Next");
        backButton.setPreferredSize(preferredSize);
        nextButton.setPreferredSize(preferredSize);
        backButton.addActionListener(new BackButtonListener());
        nextButton.addActionListener(new NextButtonListener());
    }

    /**
     * This method lets {@code MessageFrame} can add the type "Back" {@code JButton} to the window.
     *
     * @return a {@code JButton} that let players back to guess the current word.
     */
    public JButton getBackButton() { return backButton; }

    /**
     * This method lets {@code MessageFrame} can add the type "Next" {@code JButton} to the window.
     *
     * @return a {@code JButton} that let players play a new game.
     */
    public JButton getNextButton() { return nextButton; }

    /**
     * An inner class {@code BackButtonListener} implements {@code ActionListener} to back to current game.
     */
    class BackButtonListener implements ActionListener {
        /**
         * Override the abstract method in {@code ActionListener}.
         *
         * <p>
         *     The new window set invisible for calling through {@link MessageFrame#setVisible(boolean)}.
         *     Requests that letterList Component get the input focus after clicking the button for
         *     calling through {@link LetterList#requestFocusInWindow()}.
         * </p>
         *
         * @param e an ActionEvent that is a specific action generated by a component.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            letterList.getMessageFrame().setVisible(false);
            letterList.requestFocusInWindow();
        }
    }

    /**
     * An inner class {@code NextButtonListener} implements {@code ActionListener} to start a new game.
     */
    class NextButtonListener implements ActionListener {
        /**
         * Override the abstract method in {@code ActionListener}.
         *
         *     The main window is refreshed for calling through {@link LetterList#refreshLetters()}.
         *     Requests that letterList Component get the input focus after clicking the button for
         *     calling through {@link LetterList#requestFocusInWindow()}.
         *
         * @param e an ActionEvent that is a specific action generated by a component.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            letterList.getMessageFrame().setVisible(false);
            letterList.refreshLetters();
            letterList.requestFocusInWindow();
        }
    }

}
