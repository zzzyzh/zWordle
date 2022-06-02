package MainFrame;

import javax.swing.*;
import java.awt.*;

/**
 * The {@code TitleLabel} class extends {@code JLabel} stores the definition the {@code TitleLabel}.
 *
 * <p>
 *     The label display at the top of each {@code zWordle} to show the encouraging words or congratulations.
 * </p>
 *
 * @author Zhonghao Yan
 * @version 1.0
 */
public class TitleLabel extends JLabel {

    /**
     * A static constant holding the width of label.
     */
    private static final int WIDTH = 450;

    /**
     * A static constant holding the height of label.
     */
    private static final int HEIGHT = 30;

    /**
     * The constructor for class {@code TitleLabel}.
     *
     * This constructor will initiate label and complete the configuration.
     */
    TitleLabel() {
        // Initialize the attributes of the label.
        this.setSize(WIDTH, HEIGHT);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setFont(new Font("Times New Rome", Font.BOLD, 17));
        this.setText("New game!");
    }

    /**
     * This method allows itself and other classes sets the text of the label.
     */
    public void setLabel() {
        this.setText("New game!");
    }

    /**
     * This method sets encouraging words and congratulations based on the times the players have input.
     *
     * @param pos a type that set different encouraging words.
     */
    public void setLabel(int pos) {
        switch (pos) {
            case 0 -> {
                this.setText("Congratulations! Click the Next button to start a new game!");
                this.setFont(new Font("Times New Rome", Font.BOLD, 14));
                this.setForeground(Color.RED);
            }
            case 1 -> this.setText("Try again!");
            case 2 -> this.setText("Come on!");
            case 3 -> this.setText("Hurry up!");
            case 4 -> this.setText("Don't give up!");
            case 5 -> this.setText("The last chance!");
        }
    }

}
