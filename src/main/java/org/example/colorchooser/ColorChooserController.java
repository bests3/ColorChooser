package org.example.colorchooser;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ColorChooserController {
    @FXML private Slider redSlider;
    @FXML private Slider greenSlider;
    @FXML private Slider blueSlider;
    @FXML private Slider alphaSlider;
    @FXML private TextField redTextField;
    @FXML private TextField greenTextField;
    @FXML private TextField blueTextField;
    @FXML private TextField alphaTextField;
    @FXML private Rectangle colorRectangle;

    private int red = 0;
    private int green = 0;
    private int blue = 0;
    private double alpha = 1.0;

    private boolean updating = false; // Prevent event loops

    public void initialize() {
        // --- Slider to TextField + Color Sync ---
        redSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (!updating) {
                updating = true;
                red = newVal.intValue();
                redTextField.setText(String.valueOf(red));
                updateColor();
                updating = false;
            }
        });
        greenSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (!updating) {
                updating = true;
                green = newVal.intValue();
                greenTextField.setText(String.valueOf(green));
                updateColor();
                updating = false;
            }
        });
        blueSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (!updating) {
                updating = true;
                blue = newVal.intValue();
                blueTextField.setText(String.valueOf(blue));
                updateColor();
                updating = false;
            }
        });
        alphaSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (!updating) {
                updating = true;
                alpha = newVal.doubleValue();
                alphaTextField.setText(String.format("%.2f", alpha));
                updateColor();
                updating = false;
            }
        });

        // --- TextField to Slider + Color Sync ---
        redTextField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!updating) {
                try {
                    updating = true;
                    int value = Integer.parseInt(newVal);
                    if (value < 0) value = 0;
                    if (value > 255) value = 255;
                    red = value;
                    redSlider.setValue(red);
                    updateColor();
                } catch (NumberFormatException e) {
                    // ignore
                } finally {
                    updating = false;
                }
            }
        });
        greenTextField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!updating) {
                try {
                    updating = true;
                    int value = Integer.parseInt(newVal);
                    if (value < 0) value = 0;
                    if (value > 255) value = 255;
                    green = value;
                    greenSlider.setValue(green);
                    updateColor();
                } catch (NumberFormatException e) {
                    // ignore
                } finally {
                    updating = false;
                }
            }
        });
        blueTextField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!updating) {
                try {
                    updating = true;
                    int value = Integer.parseInt(newVal);
                    if (value < 0) value = 0;
                    if (value > 255) value = 255;
                    blue = value;
                    blueSlider.setValue(blue);
                    updateColor();
                } catch (NumberFormatException e) {
                    // ignore
                } finally {
                    updating = false;
                }
            }
        });
        alphaTextField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!updating) {
                try {
                    updating = true;
                    double value = Double.parseDouble(newVal);
                    if (value < 0.0) value = 0.0;
                    if (value > 1.0) value = 1.0;
                    alpha = value;
                    alphaSlider.setValue(alpha);
                    updateColor();
                } catch (NumberFormatException e) {
                    // ignore
                } finally {
                    updating = false;
                }
            }
        });

        // Set initial values
        redSlider.setValue(red);
        greenSlider.setValue(green);
        blueSlider.setValue(blue);
        alphaSlider.setValue(alpha);

        // Initialize text fields
        redTextField.setText(String.valueOf(red));
        greenTextField.setText(String.valueOf(green));
        blueTextField.setText(String.valueOf(blue));
        alphaTextField.setText(String.format("%.2f", alpha));

        updateColor();
    }

    private void updateColor() {
        colorRectangle.setFill(Color.rgb(red, green, blue, alpha));
    }
}
