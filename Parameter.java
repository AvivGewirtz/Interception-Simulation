import javax.swing.*;

/**
 * The Parameter class extends JPanel and provides a reusable UI component
 * consisting of a label and a spinner. This component is used to allow users
 * to input or adjust numerical values within a defined range.
 */
public class Parameter extends JPanel {
    JLabel label; // Label to describe the parameter
    JSpinner spinner; // Spinner for adjusting the parameter value

    /**
     * Constructor to initialize the parameter with integer values.
     *
     * @param def  The default value for the spinner.
     * @param min  The minimum allowable value for the spinner.
     * @param max  The maximum allowable value for the spinner.
     * @param step The step size for incrementing or decrementing the value.
     * @param s    The text for the label.
     */
    public Parameter(int def, int min, int max, int step, String s) {
        label = new JLabel(s); // Create the label with the given text
        // Define the spinner's behavior with a SpinnerNumberModel
        SpinnerModel model = new SpinnerNumberModel(def, min, max, step);
        spinner = new JSpinner(model); // Create the spinner with the model
    }

    /**
     * Constructor to initialize the parameter with double values.
     *
     * @param def  The default value for the spinner.
     * @param min  The minimum allowable value for the spinner.
     * @param max  The maximum allowable value for the spinner.
     * @param step The step size for incrementing or decrementing the value.
     * @param s    The text for the label.
     */
    public Parameter(double def, double min, double max, double step, String s) {
        label = new JLabel(s); // Create the label with the given text
        // Define the spinner's behavior with a SpinnerNumberModel
        SpinnerModel model = new SpinnerNumberModel(def, min, max, step);
        spinner = new JSpinner(model); // Create the spinner with the model
    }
}
