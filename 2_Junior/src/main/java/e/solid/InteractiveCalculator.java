package e.solid;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class InteractiveCalculator extends Application {

    private Stage rootStage = new Stage();
    private Pane root = new Pane();
    private Scene scene = new Scene(root, 210, 310, Color.WHITE);
    private TextField display = new TextField();

    private String firstNumber = null;
    private String operation = null;
    private String secondNumber = null;
    private String result = null;

    private String memory = null;

    @Override
    public void start(Stage primaryStage) throws Exception {
        rootStage.setScene(scene);
        rootStage.setResizable(false);
        rootStage.show();
        createScene();
        display.setText("0");
    }

    private void onButton(String number) {
        if (firstNumber == null) {
            if (number.equals(".")) {
                firstNumber = display.getText() + number;
                display.setText(firstNumber);
            } else if (!number.equals("0")) {
                firstNumber = number;
                display.setText(firstNumber);
            }
        } else if (firstNumber != null && operation == null) {
            if (number.equals(".")) {
                if (!firstNumber.contains(".")) {
                    firstNumber = firstNumber + number;
                    display.setText(firstNumber);
                }
            } else {
                firstNumber = firstNumber + number;
                display.setText(firstNumber);
            }
        } else if (operation != null && secondNumber == null) {
            if (number.equals(".")) {
                secondNumber = "0" + number;
                display.setText(secondNumber);
            } else {
                secondNumber = number;
                display.setText(secondNumber);
            }
        } else if (secondNumber != null) {
            if (number.equals(".")) {
                if (!secondNumber.contains(".")) {
                    secondNumber = secondNumber + number;
                    display.setText(secondNumber);
                }
            } else if (!secondNumber.equals("0")) {
                secondNumber = secondNumber + number;
                display.setText(secondNumber);
            }
        }
    }

    private void getResult() {
        if (operation.equals("/")) {
            result = String.valueOf(Double.valueOf(firstNumber) / Double.valueOf(secondNumber));
        } else if (operation.equals("*")) {
            result = String.valueOf(Double.valueOf(firstNumber) * Double.valueOf(secondNumber));
        } else if (operation.equals("+")) {
            result = String.valueOf(Double.valueOf(firstNumber) + Double.valueOf(secondNumber));
        } else if (operation.equals("-")) {
            result = String.valueOf(Double.valueOf(firstNumber) - Double.valueOf(secondNumber));
        } else if (operation.equals("%")) {
            result = String.valueOf(Double.valueOf(secondNumber) / 100 * Double.valueOf(firstNumber));
        }
        display.setText(result);
    }

    private void m() {
        if (memory != null) {
            if (operation == null) {
                firstNumber = memory;
                display.setText(firstNumber);
            } else if (operation != null) {
                secondNumber = memory;
                display.setText(secondNumber);
            }
        }
    }

    public void cancel() {
        result = null;
        firstNumber = null;
        secondNumber = null;
        operation = null;
        display.setText("0");
    }

    private void createScene() {

        VBox vBox = new VBox();

        HBox hBoxOne = new HBox();
        Pane paneOne = new Pane();
        display.setLayoutX(10);
        display.setLayoutY(10);
        display.setPrefSize(190, 40);
        display.setEditable(false);
        display.setAlignment(Pos.BASELINE_RIGHT);
        paneOne.getChildren().add(display);
        hBoxOne.getChildren().add(paneOne);

        HBox hBoxTwo = new HBox();
        Pane paneTwo = new Pane();

        Button percent = addButton("%", 10, 10, 40, 40, (x) -> operation = "%");
        Button m = addButton("M", 60, 10, 40, 40, (x) -> m());
        Button mr = addButton("MR", 110, 10, 40, 40, (x) -> memory = display.getText());
        Button cancel = addButton("C", 160, 10, 40, 40, (x) -> cancel());
        paneTwo.getChildren().addAll(percent, m, mr, cancel);
        hBoxTwo.getChildren().addAll(paneTwo);

        HBox hBoxThree = new HBox();
        Pane paneThree = new Pane();
        Button seven = addButton("7", 10, 10, 40, 40, (x) -> onButton("7"));
        Button eight = addButton("8", 60, 10, 40, 40, (x) -> onButton("8"));
        Button nine = addButton("9", 110, 10, 40, 40, (x) -> onButton("9"));
        Button div = addButton("/", 160, 10, 40, 40, (x) -> operation = "/");
        paneThree.getChildren().addAll(seven, eight, nine, div);
        hBoxThree.getChildren().addAll(paneThree);

        HBox hBoxFour = new HBox();
        Pane paneFour = new Pane();
        Button four = addButton("4", 10, 10, 40, 40, (x) -> onButton("4"));
        Button five = addButton("5", 60, 10, 40, 40, (x) -> onButton("5"));
        Button six = addButton("6", 110, 10, 40, 40, (x) -> onButton("6"));
        Button mult = addButton("*", 160, 10, 40, 40, (x) -> operation = "*");
        paneFour.getChildren().addAll(four, five, six, mult);
        hBoxFour.getChildren().addAll(paneFour);

        HBox hBoxFive = new HBox();
        Pane paneFive = new Pane();
        Button one = addButton("1", 10, 10, 40, 40, (x) -> onButton("1"));
        Button two = addButton("2", 60, 10, 40, 40, (x) -> onButton("2"));
        Button three = addButton("3", 110, 10, 40, 40, (x) -> onButton("3"));
        Button substract = addButton("-", 160, 10, 40, 40, (x) -> operation = "-");
        paneFive.getChildren().addAll(one, two, three, substract);
        hBoxFive.getChildren().addAll(paneFive);

        HBox hBoxSix = new HBox();
        Pane paneSix = new Pane();
        Button o = addButton("0", 10, 10, 40, 40, (x) -> onButton("0"));
        Button point = addButton(".", 60, 10, 40, 40, (x) -> onButton("."));
        Button ravno = addButton("=", 110, 10, 40, 40, (x) -> getResult());
        Button add = addButton("+", 160, 10, 40, 40, (x) -> operation = "+");
        paneSix.getChildren().addAll(o, point, ravno, add);
        hBoxSix.getChildren().addAll(paneSix);

        vBox.getChildren().addAll(hBoxOne, hBoxTwo, hBoxThree, hBoxFour, hBoxFive, hBoxSix);
        root.getChildren().addAll(vBox);
    }

    private Button addButton(String name, int layoutX, int layoutY, int width, int height, EventHandler<ActionEvent> event) {
        Button result = new Button(name);
        result.setLayoutX(layoutX);
        result.setLayoutY(layoutY);
        result.setMaxSize(width, height);
        result.setMinSize(width, height);
        result.setOnAction(event);
        return result;
    }
}
