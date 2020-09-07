package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Login extends Application {

    private static boolean reply;
    private static boolean validUsername;
    private static Alert alert;

    public static void displayLogin(String title) {

        Scene scene;
        Stage login = new Stage();

        login.setTitle(title);
        login.initModality(Modality.APPLICATION_MODAL);
        login.setResizable(false);

        login.setOnCloseRequest(e -> {
                    e.consume();
                    boolean reply = close("Confirmation Box", "Are you sure?");
                    if (reply)
                        login.close();
                }
        );

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(8);
        grid.setHgap(10);

        Label name = new Label("Username");
        GridPane.setConstraints(name, 0, 0);
        TextField userInput = new TextField();
        userInput.textProperty().addListener((observable, oldValue, newValue) -> validUsername = isString(userInput, userInput.getText()));
        GridPane.setConstraints(userInput, 1, 0);

        Label password = new Label("Password");
        GridPane.setConstraints(password, 0, 1);
        PasswordField passInput = new PasswordField();
        GridPane.setConstraints(passInput, 1, 1);
        passInput.setPromptText("Min. 8 characters");


        Label specPw = new Label("Password must contains at least\n"+"one uppercase character, one special character\n" +
                "and min. 8 total characters.");
        GridPane.setConstraints(specPw, 2, 1);

        Button loginButt = new Button("Log in");
        GridPane.setConstraints(loginButt, 1, 2);

        loginButt.setOnAction(e -> {

            if (validUsername && validatePw(passInput.getText())) {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Validation successful");
                alert.setHeaderText("All right!");
                alert.setContentText("You typed your username correctly!");
            } else {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Validation went wrong");
                alert.setHeaderText("Something went wrong!");
                alert.setContentText("You made a mistake while typing your username!");
            }
            
            alert.show();



        });

        grid.getChildren().addAll(name, password, userInput, passInput, specPw, loginButt);

        scene = new Scene(grid, 600, 300);
        login.setScene(scene);
        login.showAndWait();

    }

    private static boolean isString(TextField nameInput, String s) { //It checks if the username does not contains digits.

        int digits = 0;
        boolean valid = false;

        if (s != null) {
            for (char c : s.toCharArray()) {
                if (Character.isDigit(c)) {
                    nameInput.setStyle("-fx-text-inner-color: red;");
                    digits++;
                }
            }

            if (digits == 0) {
                nameInput.setStyle("-fx-text-inner-color: black;");
                valid = true;
            }
        }

        return valid;

    }


    private static boolean validatePw(String s) { /*Password must contains at least one uppercase character, one special character
    and min. 8 total characters.*/

        boolean valid = false;
        int isUppercase = 0, isSpecial = 0;


        if (s != null && s.length() >= 8) {
            for (char c : s.toCharArray()) {
                if (Character.isUpperCase(c))
                    isUppercase++;
                if (!Character.isDigit(c) && !Character.isLetter(c)) {
                    ++isSpecial;
                }
            }

            if (isUppercase > 0 && isSpecial > 0)
                valid = true;
        }

        return valid;


    }

    public static boolean close(String title, String message) {

        Scene scene;
        Stage closeWindow = new Stage();
        Button yesButton, noButton;

        closeWindow.setTitle(title);
        closeWindow.setResizable(false);
        closeWindow.initModality(Modality.APPLICATION_MODAL);

        yesButton = new Button("Yes");
        noButton = new Button("No");


        yesButton.setOnAction(e -> {
            reply = true;
            closeWindow.close();
        });

        noButton.setOnAction(e -> {
            reply = false;
            closeWindow.close();
        });


        VBox vBox = new VBox(3);

        Label text = new Label(message);

        vBox.getChildren().addAll(text, yesButton, noButton);
        vBox.setAlignment(Pos.CENTER);

        scene = new Scene(vBox, 200, 100);

        closeWindow.setScene(scene);
        closeWindow.showAndWait();

        return reply;


    }


    @Override
    public void start(Stage primaryStage) {

    }
}
