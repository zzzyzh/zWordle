package MainFrame;

import javax.swing.*;
import java.awt.*;

/**
 * The {@code MessageFrame} class extends {@code JFrame} stores the definition of a new window to display messages.
 *
 * <p>
 *     The interface of the Wordle game mainly includes three main components: {@code JLabel} message, {@code JLabel} messageIcon
 *     and {@code JButton} messageConfirm. The class is defined at the {@code LetterList} when the game should notify the
 *     players some important messages.
 * </p>
 *
 * @author Zhonghao Yan
 * @version 1.0
 */
public class MessageFrame extends JFrame {

    /**
     * A {@code LetterList} storing the input letters.
     */
    private LetterList letterList;

    /**
     * A {@code MessageButtons} storing the messages.
     */
    private MessageButtons messageButtons;

    /**
     * A {@code JLabel} storing the message of the states.
     */
    private JLabel message;

    /**
     * A {@code JLabel} storing the icon of the types of state.
     */
    private JLabel messageIcon;

    /**
     * A {@code JButton} storing the buttons.
     */
    private JButton messageConfirm;

    /**
     * An int variable defining the type of messages.
     */
    private int flag;

    /**
     * A {@code String} storing the current word.
     */
    private String word;

    /**
     * A static constant holding the width of current window.
     */
    private static final int FRAME_WIDTH = 450;

    /**
     * A static constant holding the height of current window.
     */
    private static final int FRAME_HEIGHT = 200;

    /**
     * A static constant holding the width of message.
     */
    private static final int MESSAGE_WIDTH = 300;

    /**
     * A static constant holding the height of message.
     */
    private static final int MESSAGE_HEIGHT = 50;

    /**
     * A static constant holding the width of icon.
     */
    private static final int ICON_WIDTH = 50;

    /**
     * A static constant holding the height of icon.
     */
    private static final int ICON_HEIGHT = 50;

    /**
     * A static constant holding the width of button.
     */
    private static final int BUTTON_WIDTH = 100;

    /**
     * A static constant holding the height of button.
     */
    private static final int BUTTON_HEIGHT = 40;

    /**
     * The constructor for class {@code zWordle}
     *
     * <p>
     *     This constructor will initiate the window and complete the configuration.
     * </p>
     */
    MessageFrame(zWordle frame, int flag) {
        // Initialize the attributes of the window.
        this.flag = flag;
        this.letterList = frame.getLetterList();
        this.messageButtons = frame.getMessageButtons();
        this.setLayout(null);
        this.getContentPane().setBackground(Color.white);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.word = letterList.getWord();
        this.setBackground(Color.WHITE);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // Get the height and width of the screen and set the window display at the middle of the screen.
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) screenSize.getWidth() / 2 - this.getWidth() / 2;
        int y = (int) screenSize.getHeight() / 2 - this.getHeight() / 2 - 15;
        this.setLocation(x, y);
        // Initialize the size of each component and set them at the specified bounds of the window.
        this.setMessageContent();
        this.setMessageIcon();
        this.setMessage();
        this.setVisible(true);
    }

    /**
     * This method sets the attributes of the label contains the message.
     */
    public void setMessageContent() {
        message = new JLabel();
        message.setOpaque(false);
        message.setSize(MESSAGE_WIDTH, MESSAGE_HEIGHT);
        message.setHorizontalAlignment(JLabel.CENTER);
        message.setFont(new Font("Times New Rome", Font.BOLD, 15));
        message.setBounds(140, 40, MESSAGE_WIDTH, MESSAGE_HEIGHT);
    }

    /**
     * This method sets the attributes of the label contains the icon of message.
     */
    public void setMessageIcon() {
        messageIcon = new JLabel();
        messageIcon.setBounds(70, 40, ICON_WIDTH, ICON_HEIGHT);
    }

    /**
     * This method sets special attributes based on the types of the message.
     *
     * <p>
     *     The window is used for displaying a random letter of the current word for calling through {@link MessageFrame#setHintMessage()}.
     *     The window is used for notifying the players they have input error letters for calling through {@link MessageFrame#setErrorLetterMessage()}.
     *     The window is used for notifying the players they press the key "ENTER" before they have input 5 legal letters for calling through {@link MessageFrame#setAdvancePressMessage()}.
     *     The window is used for notifying the players they have input illegal words for calling through {@link MessageFrame#setIllegalWordMessage()}.
     *     The window is used for congratulating players have found the current word for calling through {@link MessageFrame#setGetWordMessage()}.
     *     The window is used for notifying the players the current word for calling through {@link MessageFrame#setAnswerMessage()}.
     * </p>
     */
    public void setMessage() {
        switch (flag) {
            case 0 -> setHintMessage();
            case 1 -> setErrorLetterMessage();
            case 2 -> setAdvancePressMessage();
            case 3 -> setIllegalWordMessage();
            case 4 -> setGetWordMessage();
            case 5 -> setAnswerMessage();
        }
        this.setMessageConfirm();
        this.getContentPane().add(messageIcon);
        this.getContentPane().add(messageConfirm);
        this.getContentPane().add(message);
    }

    /**
     * This method displays a random letter of the current word and the corresponding icon.
     */
    public void setHintMessage() {
        int index = (int) (Math.random() * 5);
        switch (index) {
            case 0 -> message.setText("The first letter is " + word.charAt(index));
            case 1 -> message.setText("The second letter is " + word.charAt(index));
            case 2 -> message.setText("The third letter is " + word.charAt(index));
            case 3 -> message.setText("The fourth letter is " + word.charAt(index));
            case 4 -> message.setText("The fifth letter is " + word.charAt(index));
        }
        ImageIcon icon = new ImageIcon(new ImageIcon("data/tips.png").getImage().getScaledInstance(ICON_WIDTH,ICON_HEIGHT,Image.SCALE_SMOOTH));
        messageIcon.setIcon(icon);
    }

    /**
     * This method notifies the players they have input error letters and displays the corresponding icon.
     */
    public void setErrorLetterMessage() {
        message.setText("Error! You should input letter now!");
        ImageIcon icon = new ImageIcon(new ImageIcon("data/error.png").getImage().getScaledInstance(ICON_WIDTH,ICON_HEIGHT,Image.SCALE_SMOOTH));
        messageIcon.setIcon(icon);
    }

    /**
     * This method notifies the players they press the key "ENTER" before they have input 5 legal letters and displays the corresponding icon.
     */
    public void setAdvancePressMessage() {
        message.setText("Error! You should not press ENTER!");
        ImageIcon icon = new ImageIcon(new ImageIcon("data/error.png").getImage().getScaledInstance(ICON_WIDTH,ICON_HEIGHT,Image.SCALE_SMOOTH));
        messageIcon.setIcon(icon);
    }

    /**
     * This method notifies the players they have input illegal words and displays the corresponding icon.
     */
    public void setIllegalWordMessage() {
        message.setText("Error! You should input a word!");
        ImageIcon icon = new ImageIcon(new ImageIcon("data/error.png").getImage().getScaledInstance(ICON_WIDTH,ICON_HEIGHT,Image.SCALE_SMOOTH));
        messageIcon.setIcon(icon);
    }

    /**
     * This method congratulates the players have found the current word and displays the corresponding icon.
     */
    public void setGetWordMessage() {
        message.setText("You're right! The word is: " + word);
        ImageIcon icon = new ImageIcon(new ImageIcon("data/correct.png").getImage().getScaledInstance(ICON_WIDTH,ICON_HEIGHT,Image.SCALE_SMOOTH));
        messageIcon.setIcon(icon);
    }

    /**
     * This method notifies the players the current word and displays the corresponding icon.
     */
    public void setAnswerMessage() {
        message.setText("Sorry! The word is: " + word);
        ImageIcon icon = new ImageIcon(new ImageIcon("data/tips.png").getImage().getScaledInstance(ICON_WIDTH,ICON_HEIGHT,Image.SCALE_SMOOTH));
        messageIcon.setIcon(icon);
    }

    /**
     * This method sets the icon based on the type of the message.
     */
    public void setMessageConfirm() {
        switch (flag) {
            case 0, 1, 2, 3 -> messageConfirm = messageButtons.getBackButton();
            case 4, 5 -> messageConfirm = messageButtons.getNextButton();
        }
        messageConfirm.setBounds((FRAME_WIDTH-BUTTON_WIDTH)/2, 110, BUTTON_WIDTH, BUTTON_HEIGHT);
    }

}