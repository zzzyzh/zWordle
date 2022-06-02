package MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

/**
 * The {@code zWordle} class extends {@code JFrame} stores the definition of all the components of the Wordle game and starts the game.
 *
 * <p>
 *     The interface of the Wordle game mainly includes four main components from top to bottom: {@code TitleLabel} titleLabel,
 *     {@code LetterList} letterList, {@code KeyboardList} keyboardList and {@code ButtonList} buttonList. Each component must be initialized in
 *     the zWordle class. At the same time, the {@code zWordle} class initialize the {@code MessageButtons} messageButtons.
 *     class which provide buttons with different ActionListener.
 * </p>
 *
 * <p>
 *     It is important that if components want to invoke the parameters of others components, they must use the
 *     getter methods of the {@code zWordle} class.
 * </p>
 *
 * @author Zhonghao Yan
 * @version 1.0
 */
public class zWordle extends JFrame {

    /**
     * A {@code TitleLabel} storing the encouraging words.
     */
    private TitleLabel titleLabel;

    /**
     * A {@code LetterList} storing the input letters.
     */
    private LetterList letterList;

    /**
     *  A {@code KeyboardList} storing the keyboard.
     */
    private KeyboardList keyboardList;

    /**
     * A {@code JPanel} storing two {@code ButtonList}.
     */
    private ButtonList buttonList;

    /**
     * A {@code MessageButtons} storing the messages.
     */
    private MessageButtons messageButtons;

    /**
     * A static constant holding the width of current window.
     */
    private static final int WIDTH = 500;

    /**
     * A static constant holding the height of current window.
     */
    private static final int HEIGHT = 780;

    /**
     * The constructor for class {@code zWordle}
     *
     * <p>
     *     This constructor will initiate the window and complete the configuration.
     * </p>
     */
    zWordle() {
        // Initialize the attributes of the window.
        this.setTitle("Wordle by Z.Yan");
        this.setSize(WIDTH, HEIGHT);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        // Get the height and width of the screen and set the window display at the middle of the screen.
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) screenSize.getWidth() / 2 - this.getWidth() / 2;
        int y = (int) screenSize.getHeight() / 2 - this.getHeight() / 2 - 15;
        this.setLocation(x, y);
        this._initFrame();
    }

    /**
     * This method complete the rest of the constructor to simplify constructor.
     */
    public void _initFrame() {
        // Initialize the size of each component and set them at the specified bounds of the window.
        Container container = this.getContentPane();
        titleLabel = new TitleLabel();
        titleLabel.setBounds(25, 40, 450, 30);
        container.add(titleLabel);
        letterList = new LetterList(this);
        letterList.setBounds(95, 90, 310, 374);
        container.add(letterList);
        keyboardList = new KeyboardList(this);
        keyboardList.setBounds(56, 490, 388, 124);
        container.add(keyboardList);
        letterList.setKeyboardList(keyboardList.getKeyboardList());
        buttonList = new ButtonList(this);
        buttonList.setBounds(70, 630, 360, 70);
        container.add(buttonList);
        messageButtons = new MessageButtons(this);
    }

    /**
     * This method defines a new {@code Wordle} to start the game.
     *
     * @param args a default String array which is not used by this program.
     */
    public static void main(String[] args) {
        zWordle zWordle = new zWordle();
        zWordle.goWordle();
    }

    /**
     * This method adds {@link #addWindowFocusListener(WindowFocusListener)} to the window in order to receive window
     * events from this window.
     */
    public void goWordle() {
        this.addWindowFocusListener(new WindowAdapter() {
            public void windowGainedFocus(WindowEvent e) {
                // Requests that letterList Component get the input focus.
                letterList.requestFocusInWindow();
            }
        });
        // Set the window visible after all operations.
        this.setVisible(true);
    }

    /**
     * A getter method to return the {@code TitleLabel} that use or change the attributes of the {@code TitleLabel}
     * in the class invoking the methods.
     *
     * @return {@code TitleLabel} when the method is invoked by {@code zWordle}.
     */
    public TitleLabel getTitleLabel() { return titleLabel; }

    /**
     * A getter method to return the {@code LetterList} that use or change the attributes of the {@code LetterList}
     * in the class invoking the methods.
     *
     * @return {@code LetterList} when the method is invoked by {@code zWordle}.
     */
    public LetterList getLetterList() { return letterList; }

    /**
     * A getter method to return the {@code MessageButtons} that use or change the attributes of the {@code MessageButtons}
     * in the class invoking the methods.
     *
     * @return {@code MessageButtons} when the method is invoked by {@code zWordle}.
     */
    public MessageButtons getMessageButtons() { return messageButtons; }

}
