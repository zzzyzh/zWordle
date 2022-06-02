package MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The {@code ButtonList} class extends {@code JPanel} stores the definition the component including two {@code JButton}.
 *
 * <p>
 *     The {@code ButtonList} defines {@code JButton} Answer and {@code JButton} Next.
 *     It will display a {@code MessageFrame} to show the true word when we click the {@code JButton} Answer.
 *     It will get a new random word from dictionary.txt and refresh the window when we click the {@code JButton} Next.
 * </p>
 *
 * @author Zhonghao Yan
 * @version 1.0
 */
public class ButtonList extends JPanel{

    /**
     * A {@code LetterList} storing the input letters.
     */
    private LetterList letterList;

    /**
     * A {@code JButton} displaying the information of the true word.
     */
    private JButton answer;

    /**
     * A {@code JButton} getting a new random word from dictionary.txt and refreshing the window.
     */
    private JButton next;

    /**
     * A static constant holding the width of panel.
     */
    private static final int PANEL_WIDTH = 360;

    /**
     * A static constant holding the height of panel.
     */
    private static final int PANEL_HEIGHT = 70;

    /**
     * A static constant holding the width of buttons.
     */
    private static final int BUTTON_WIDTH = 140;

    /**
     * A static constant holding the height of buttons.
     */
    private static final int BUTTON_HEIGHT = 60;

    /**
     * A static constant holding the dimension of buttons.
     */
    private static final Dimension preferredSize = new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT);

    /**
     * The constructor for class {@code ButtonList}.
     *
     * <p>
     *     This constructor will initiate panel and complete the configuration.
     * </p>
     *
     * @param frame a frame that we have initialized in the {@code zWordle}.
     */
    ButtonList(zWordle frame) {
        // Initialize the attributes of the panel.
        this.letterList = frame.getLetterList();
        this.setSize(PANEL_WIDTH, PANEL_HEIGHT);
        // Initialize the attributes of the buttons and add them to the panel.
        this.answer = new JButton("Answer");
        this.next = new JButton("Next");
        this.setButtons();
        this.add(answer);
        this.add(next);
    }

    /**
     * This method completes the configuration of each button.
     */
    public void setButtons() {
        answer.setPreferredSize(preferredSize);
        next.setPreferredSize(preferredSize);
        answer.addActionListener(new DisplayAnswerListener());
        next.addActionListener(new NextPageListener());
    }

    /**
     * An {@code DisplayAnswerListener} implements {@code ActionListener} to display the word through the Answer button.
     */
    class DisplayAnswerListener implements ActionListener {
        /**
         * Override the abstract methods in {@code ActionListener}.
         *
         * <p>
         *     The message window is displayed for calling through {@link LetterList#displayWord()}.
         *     Requests that letterList Component get the input focus after clicking the button for
         *     calling through {@link LetterList#requestFocusInWindow()}.
         * </p>
         *
         * @param e an ActionEvent that is a specific action generated by a component.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            letterList.displayWord();
            letterList.requestFocusInWindow();
        }
    }

    /**
     * An inner class {@code NextPageListener} implements {@code ActionListener} to start a new game.
     */
    class NextPageListener implements ActionListener {
        /**
         * Override the abstract method in {@code ActionListener}.
         *
         * <p>
         *     The main window is refreshed for calling through {@link LetterList#refreshLetters()}.
         *     Requests that letterList Component get the input focus after clicking the button for
         *     calling through {@link LetterList#requestFocusInWindow()}.
         * </p>
         *
         * @param e an ActionEvent that is a specific action generated by a component.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            letterList.refreshLetters();
            letterList.requestFocusInWindow();
        }
    }

}
