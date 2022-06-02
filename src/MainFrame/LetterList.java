package MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * The {@code LetterList} class extends {@code JPanel} stores the definition the component.
 *
 * <P>
 *     The panel display the letter boxes which can record the input of the players. The changes of the background color
 *     represent the result of input.
 * </P>
 *
 * <p>
 *     At the same time, the {@code LetterList} class includes some methods which display the messages of the result of input
 *     associating with the result of input.
 * </p>
 *
 * @author Zhonghao Yan
 * @version 1.0
 */
public class LetterList extends JPanel {

    /**
     * A {@code zWordle} storing the components of the game window.
     */
    private zWordle frame;

    /**
     * A {@code TitleLabel} storing the encouraging words
     */
    private TitleLabel titleLabel;

    /**
     * A {@code MessageButtons} storing the messages.
     */
    private MessageFrame messageFrame;

    /**
     * A two dimension arrays of {@code JLabel[][]} holding the input letters.
     */
    private JLabel[][] letters;

    /**
     * A two dimension arrays of {@code JLabel[][]} holding the virtual keyboard letters.
     */
    private JLabel[][] keyboardList;

    /**
     * A variable storing the index of input letters.
     */
    private int xLabel = 0;

    /**
     * A variable storing the times of input words.
     */
    private int yLabel = 0;

    /**
     * An {@code ArrayList} holding all 5-letters words in the dictionary.txt.
     */
    private ArrayList<String> dictionary;

    /**
     * A {@code String} holding the random word of a new game.
     */
    private String word;

    /**
     * A {@code String} holding the input word once.
     */
    private String result;

    /**
     * A variable recording the type of the message window.
     *
     * <p>
     *     The {@code MessageFrame} will display different messages for calling through {@link MessageFrame#setMessage()}.
     *     <pre class="code">
     *          switch (flag) {
     *             case 0 -> setHintMessage();
     *             case 1 -> setInputErrorMessage();
     *             case 2 -> setAdvancePressMessage();
     *             case 3 -> setIllegalMessage();
     *             case 4 -> setGetWordMessage();
     *             case 5 -> setAnswerMessage();
     *         }
     * </pre>
     */
    private int flag;

    /**
     * A boolean array holding state of repeat of input letters once.
     */
    private boolean[] repeat;

    /**
     * A static constant holding the value of row of letters.
     */
    private static final int LETTERS_ROWS = 6;

    /**
     * A static constant holding the value of col of letters.
     */
    private static final int LETTERS_COLS = 5;

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
    private static final int GAPE = 10;

    /**
     * A static constant holding the width of each letter box.
     */
    private static final int SIDE = 54;

    /**
     * A static {@code Color} storing the initial background color.
     */
    private static final Color INIT_BACKGROUND = Color.WHITE;

    /**
     * A static {@code Color} storing the background color if both index and letters are right.
     */
    private static final Color ANSWER_TRUE = new Color(121, 167, 107);

    /**
     * A static {@code Color} storing the background color if one of index and letters is right.
     */
    private static final Color ANSWER_HALF = new Color(198, 180, 102);

    /**
     * A static {@code Color} storing the background color if neither index nor letters is right.
     */
    private static final Color ANSWER_FALSE = new Color(121, 124, 126);

    /**
     * The constructor for class {@code LetterList}.
     *
     * <p>
     *     This constructor will initiate panel and complete the configuration.
     *     At the same time, the panel will get the input from keyboard for calling
     *     through {@link LetterList#addKeyListener(KeyListener)}.
     * </p>
     *
     * @param frame a frame that we have initialized in the {@code zWordle}.
     */
    LetterList(zWordle frame) {
        // Initialize the attributes of the panel.
        this.frame = frame;
        this.titleLabel = frame.getTitleLabel();
        this.setLayout(new GridLayout(LETTERS_ROWS, LETTERS_COLS, GAPE, GAPE));
        this.setSize(SIDE*LETTERS_COLS+GAPE*(LETTERS_COLS-1), SIDE*LETTERS_ROWS+GAPE*(LETTERS_ROWS-1));
        this.setOpaque(false);
        this.setLetters();
        this.addKeyListener(new UserInputListener());
        // Initialize the variable associated with word.
        this.setDictionary();
        this.setWord();
        this.result = "";
    }

    /**
     * This method initializes the keyboardList to change the background color of the letter boxes and gets rid of the NullPointerException.
     *
     * @param keyboardList a two dimension array of {@code JLabel} that initialize the keyboardList.
     */
    public void setKeyboardList(JLabel[][] keyboardList) { this.keyboardList = keyboardList; }

    /**
     * This method initializes the {@code ArrayList} by reading words from dictionary.txt and regularize all the letters to upper letters.
     */
    public void setDictionary() {
        dictionary = new ArrayList<>();
        try {
            String line;
            // Speed up character reading.
            BufferedReader reader = new BufferedReader(new FileReader("data/dictionary.txt"));
            while ((line=reader.readLine())!=null) {
                // Regularize all the letters to upper letters.
                dictionary.add(line.toUpperCase());
            }
            reader.close();
        } catch (Exception exception){
            exception.printStackTrace();
        }
    }

    /**
     * This method sets a random word before a new game.
     */
    public void setWord() {
        int flag = (int) (Math.random() * 3088);
        word = dictionary.get(flag);
        System.out.println(word);
    }

    /**
     * This method makes others classes can get the current word.
     *
     * @return a {@code String} representing the current word。
     */
    public String getWord() { return word; }

    /**
     * This method initializes input letter boxes.
     */
    public void setLetters() {
        letters = new JLabel[LETTERS_ROWS][LETTERS_COLS];
        for (int i=0; i<LETTERS_ROWS; i++) {
            for (int j=0; j<LETTERS_COLS; j++) {
                letters[i][j] = new JLabel();
                // Close the opaque to make the boxes changeable.
                letters[i][j].setOpaque(true);
                letters[i][j].setBackground(INIT_BACKGROUND);
                letters[i][j].setSize(SIDE, SIDE);
                letters[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                this.add(letters[i][j]);
            }
        }
    }

    /**
     * This method refreshes the titleLabel, letters and keyboardList before a new game.
     *
     * <p>
     *     Reset the titleLabel as the text "New Game".
     *     Empty the text of each letter box and reset all letter boxes background as INIT_BACKGROUND.
     *     Reset all letter boxes background as INIT_BACKGROUND.
     *     Reset variable xLabel and yLabel as 0.
     *     Reset word randomly.
     * </p>
     */
    public void refreshLetters() {
        titleLabel.setLabel();
        for (int i=0; i<LETTERS_ROWS; i++) {
            for (int j=0; j<LETTERS_COLS; j++) {
                letters[i][j].setText("");
                letters[i][j].setBackground(INIT_BACKGROUND);
            }
        }
        for (int i=0; i<KEYBOARD_ROWS; i++) {
            for (int j=0; j<KEYBOARD_COLS; j++) {
                keyboardList[i][j].setBackground(INIT_BACKGROUND);
            }
        }
        xLabel = 0;
        yLabel = 0;
        this.setWord();
    }

    /**
     * This method allows methods in other classes change the state of {@code MessageFrame}.
     *
     * @return a {@code MessageFrame} of special message.
     */
    public MessageFrame getMessageFrame() { return messageFrame; }

    /**
     * An inner class {@code UserInputListener} extends {@code KeyAdapter} to get user input.
     */
    class UserInputListener extends KeyAdapter {
        /**
         * Override the method in {@code KeyAdapter}.
         *
         * <p>
         *     The key players types will be assigned to key for calling through {@link KeyEvent#getKeyChar()}.
         *     Different inputs will cause different results for calling through {@link LetterList#validateLetter(char)}.
         * </p>
         *
         * @param e indicates that a keystroke occurred in a component.
         */
        @Override
        public void keyTyped(KeyEvent e) {
            char key = Character.toUpperCase(e.getKeyChar());
            int flag = validateLetter(key);
            switch (flag) {
                case 1 -> typeLetter(key);
                case 2 -> backSpace();
                case 3 -> nextLine();
                default -> illegalLetter();
            }
        }
    }

    /**
     * This method validates what kinds of keys the players input by using the ASCII of each key.
     *
     * <p>
     *     If the ASCII of the letter is larger than 96 and smaller than 123, the letter represents the letter form 'a' to 'z'.
     *     If the ASCII of the letter is larger than 64 and smaller than 91, the letter represents the letter form 'A' to 'Z'.
     *     And it will let the method return 1.
     *     If the ASCII of the letter is 8, the letter represents the key "BACKSPACE".
     *     And it will let the method return 2.
     *     If the ASCII of the letter is 10, the letter represents the key "ENTER".
     *     And it will let the method return 3.
     *     If the ASCII of the letter is others value, the letter represent illegal input.
     *     And it will let the method return 4.
     * </p>
     *
     * @param letter a char representing the players' input.
     * @return an integer that represents the types of the players' input.
     */
    private int validateLetter(char letter) {
        if ((letter >= 97 && letter <= 122) || (letter >= 65 && letter <=90)) {
            return 1;
        } else if (letter == 8) {
            return 2;
        } else if (letter == 10) {
            return 3;
        } else {
            return 4;
        }
    }

    /**
     * This method sets the text of specified letter box as the letter.
     *
     * @param letter is the letter players' input.
     */
    private void typeLetter(char letter) {
        letters[yLabel][xLabel].setText(String.valueOf(letter));
        // Set text in the center of the letter box.
        letters[yLabel][xLabel].setHorizontalAlignment(JLabel.CENTER);
        // Set the font of the text of the letter box.
        letters[yLabel][xLabel++].setFont(new Font("Times New Rome", Font.BOLD, 30));
    }

    /**
     * This method resets the text of the letter box as empty;
     */
    private void backSpace() {
        if (xLabel > 0) {
            letters[yLabel][--xLabel].setText("");
        }
    }

    /**
     * This method checks that the current result is legal word in the dictionary.
     *
     * <p>
     *     The current result is connected by all the text in order of a row of letter boxes for calling through {@link JLabel#getText()}.
     *     At the same time, this method initializes the boolean array repeat.
     * </p>
     *
     * @return the judgement of the current result.
     */
    public boolean checkResult() {
        result = "";
        repeat = new boolean[LETTERS_COLS];
        for (int i=0; i<LETTERS_COLS; i++) {
            result += letters[yLabel][i].getText();
            repeat[i] = true;
        }
        System.out.println(result);
        return dictionary.contains(result);
    }

    /**
     * This method validates the matching between current result and the word.
     *
     * <p>
     *     Iterate through the current result and the word to validate the correctness of each letter of the current result.
     *     If both index and letters are right, we set the background color as ANSWER_TRUE and set the corresponding index of
     *     current result of repeat as false.
     *     If one of index and letters is right, we set the background color as ANSWER_HALF.
     *     If neither index nor letters is right, we set the background color as ANSWER_FALSE.
     *     And iterate through the keyboardList to set the corresponding letters the same background color.
     * </p>
     */
    public void validateInput() {
        for (int i=0; i<LETTERS_COLS; i++) {
            boolean flag = true;
            for (int j=0; j<LETTERS_COLS; j++) {
                if (result.charAt(i) == word.charAt(j)) {
                    flag = false;
                    if (i == j && repeat[i]) {
                        letters[yLabel][i].setBackground(ANSWER_TRUE);
                        repeat[i] = false;
                    } else if (repeat[i]) {
                        letters[yLabel][i].setBackground(ANSWER_HALF);
                    }
                }
            }
            if (flag) {
                letters[yLabel][i].setBackground(ANSWER_FALSE);
            }
            for(int m=0; m<KEYBOARD_ROWS; m++) {
                for (int n=0; n<KEYBOARD_COLS; n++) {
                    if (m == KEYBOARD_ROWS-1 && n == KEYBOARD_COLS-2) { break; }
                    if (letters[yLabel][i].getText().equals(keyboardList[m][n].getText())) {
                        keyboardList[m][n].setBackground(letters[yLabel][i].getBackground());
                    }
                }
            }
        }
    }

    /**
     * This method judges the state of using the key "ENTER".
     *
     * <p>
     *     If the value of xLabel equals to the value of the cols of letters and the current result exists in the dictionary,
     *     it will change the background color of letter boxes for calling through {@link LetterList#validateInput()}.
     *     At the same time, change the encouraging words for calling through {@link TitleLabel#setLabel(int)}.
     *     Else, the word is a illegal word, and it will display the message to notify players for calling through {@link LetterList#illegalWord()}.
     *     Else, players press the key "ENTER" before they have input 5 letters, and it will display the message to
     *     notify player for calling through {@link LetterList#advanceInput()}.
     *     And if the current result equals to the word, it will display the message to congratulate players for calling through {@link LetterList#findWord()}.
     *     And if players have input 6 times words, but none of them equals to the word, it will display the message to
     *     notify player for calling through {@link LetterList#outOfChances()}.
     * </p>
     */
    private void nextLine() {
        if (xLabel == LETTERS_COLS) {
            if (checkResult()) {
                System.out.println(result);
                validateInput();
                yLabel++;
                xLabel = 0;
                titleLabel.setLabel(yLabel);
            } else {
                illegalWord();
            }
        } else {
            advanceInput();
        }
        if (result.equals(word)) {
            findWord();
        }
        if (yLabel == LETTERS_ROWS) {
            outOfChances();
        }
    }

    /**
     * This method displays a new window with hints of the word.
     *
     * <p>
     *     It will display a letter of the word randomly to notify the players.
     * </p>
     */
    public void displayHints() {
        flag = 0;
        messageFrame = new MessageFrame(frame, flag);
    }

    /**
     * This method displays a new window with illegal letters input error message.
     *
     * <p>
     *      Illegal letters means that the key except the letter from 'a' to 'z', the letter from 'A' to 'Z',
     *      the key "ENTER" and the key "BACKSPACE".
     * </p>
     */
    public void illegalLetter() {
        flag = 1;
        messageFrame = new MessageFrame(frame, flag);
    }

    /**
     * This method displays a new window with advanced input error message.
     *
     * <p>
     *     It means that players press the key "ENTER" before they have input 5 legal letters.
     * </p>
     */
    public void advanceInput() {
        flag = 2;
        messageFrame = new MessageFrame(frame, flag);
    }

    /**
     * This method displays a new window with illegal word input error message.
     *
     * <p>
     *     Illegal words means that the words except the words in the dictionary.
     * </p>
     */
    public void illegalWord() {
        flag = 3;
        messageFrame = new MessageFrame(frame, flag);
    }

    /**
     * This method displays a new window with congratulations message.
     *
     * <p>
     *     The titleLabel will change the text to "Congratulations" to congratulate the players.
     *     And the message will display the word.
     * </p>
     */
    public void findWord() {
        flag = 4;
        titleLabel.setLabel(0);
        messageFrame = new MessageFrame(frame, flag);
    }

    /**
     * This method displays a new window with the answer of the word.
     */
    public void displayWord() {
        flag = 5;
        messageFrame = new MessageFrame(frame, flag);
    }

    /**
     * This method displays a new window with the message which notifies the players they have been out of chances.
     */
    public void outOfChances() {
        flag = 5;
        titleLabel.setText("You're out of chance!");
        titleLabel.setForeground(Color.RED);
        messageFrame = new MessageFrame(frame, flag);
    }

}
