package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {

    Stage window; //Initial window
    Scene scene1; //It contains all the items inside the stage (e.g. buttons, labels, menus...)
    Scene scene2;

    @Override
    public void start(Stage primaryStage) {

        Button moveToScene2, newWindow, file, edit, view;
        Label secondSceneInf, favVeg;
        window = primaryStage;
        window.setResizable(false);

        window.setOnCloseRequest(e-> {
            e.consume();
            closeProgram();
        });

        moveToScene2 = new Button("Go to scene 2!"); //Button that let you switch to a second scene
        StackPane sp1 = new StackPane(); //A basic layout that can be associated to a scene
        sp1.getChildren().add(moveToScene2); //It adds the button to the layout, in order to display it on the scene1
        moveToScene2.setOnAction(e -> window.setScene(scene2));

        secondSceneInf = new Label("You are in the 2nd scene!");

        newWindow = new Button("Open another window!");
        newWindow.setOnAction(e -> { /*When  is clicked (lambda expression) it opens a new window
        that has defined in Windows2.java. The method display returns a boolean variable in order to figure out
        if the user has chosen if close window2 and go back to the first one, or not.
        */
             Login.displayLogin("Login Window");
        });

        file = new Button("File");
        edit = new Button("Edit");
        view = new Button("View");

        HBox topMenu = new HBox();
        topMenu.getChildren().addAll(file, edit, view);


        ChoiceBox<String> vegChoice = new ChoiceBox<>();
        vegChoice.getItems().addAll("Tomatoes", "Carrots", "Pickles", "Eggplant");
        vegChoice.setValue("Tomatoes");
        vegChoice.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> System.out.println(newValue));


        VBox centralMenu = new VBox(20); //Other available layout that sticks items on top of another
        centralMenu.getChildren().addAll(secondSceneInf, newWindow);
        centralMenu.setPadding(new Insets(20, 20, 30, 30));

        favVeg = new Label("Select your favourite vegetable");

        VBox leftMenu = new VBox(20);
        leftMenu.getChildren().addAll(favVeg, vegChoice);
        leftMenu.setPadding(new Insets(20, 20, 0, 0));

        HBox menu = new HBox();
        menu.getChildren().addAll(leftMenu, centralMenu);
        BorderPane scene2layout = new BorderPane();
        scene2layout.setTop(topMenu);
        scene2layout.setLeft(menu);



        scene1 = new Scene(sp1, 500, 200);
        scene2 = new Scene(scene2layout, 600, 300);

        window.setScene(scene1);
        window.setTitle("Switching scenes");
        window.show();
    }

    private void closeProgram(){
        boolean reply = Login.close("Confirmation Box", "Are you sure you want to exit?");
        if (reply)
            window.close();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}