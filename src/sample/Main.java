package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.*;
import java.io.*;

import javax.swing.*;
import java.io.IOException;

public class Main extends Application {

    TextField tf_login = new TextField();
    PasswordField tf_passvd = new PasswordField();

    static PrintWriter pw = new PrintWriter(System.out, true);

    Button btn = new Button("Click");

    public static void main(String[] args) {

        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {


//        Line line = new Line(0,0,200,200);
//        line.setStroke(Color.RED);
//        FirstWindow.getChildren().add(line);
//
//        Ellipse ellipse = new Ellipse(150,300,80,80);
//        FirstWindow.getChildren().add(ellipse);
//
//        Button button = new Button("Hello");
//        FirstWindow.getChildren().add(button);


        primaryStage.setTitle("Hello World");
        //  Group FirstWindow = new Group();


        Label lb_login = new Label("Логин");
        //  TextField tf_login = new TextField();
        Label lb_passvd = new Label("Пароль");
        // PasswordField tf_passvd = new PasswordField();


        //  textField.setPrefColumnCount(11);


        // btn.setOnAction(event -> lbl.setText("Input: " + textField.getText()));
        FlowPane FirstStr = new FlowPane(Orientation.HORIZONTAL, 10, 10, lb_login, tf_login);
        FlowPane SecondStr = new FlowPane(Orientation.HORIZONTAL, 10, 10, lb_passvd, tf_passvd);
        FlowPane FhirdStr = new FlowPane(Orientation.HORIZONTAL, 10, 10, btn);
        FhirdStr.setAlignment(Pos.CENTER);
        FlowPane FirstWindow = new FlowPane(Orientation.VERTICAL, 10, 10, FirstStr, SecondStr, FhirdStr);

        Scene scene = new Scene(FirstWindow, 300, 300);

        primaryStage.setScene(scene);
        primaryStage.show();

        btn.setOnAction(actionEvent -> {

            String nameAdmin = tf_login.getText();
            String passwdAdm = tf_passvd.getText();
            Connection connt;
            connt = conn(nameAdmin,passwdAdm);
            if (connt == null) pw.println("No adress");
            else pw.println("Adress = " + connt);
            openNewFrame("sample.fxml", btn);
        });



//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setScene(new Scene( FirstWindow, 300, 275));
//        Label lbl = new Label();
//        TextArea textArea = new TextArea();
//        textArea.setPrefColumnCount(15);
//        textArea.setPrefRowCount(5);
//        Button btn = new Button("Click");
//        btn.setOnAction(event -> lbl.setText("Input: " + textArea.getText()));
//        FlowPane root = new FlowPane(Orientation.VERTICAL, 10, 10, textArea, btn, lbl);
//        root.setAlignment(Pos.CENTER);
       // Scene scene = new Scene( FirstWindow, 100, 100);

    }
    public int count = 0;
    FXMLLoader loader = new FXMLLoader();
    Stage stage = new Stage();

    protected void openNewFrame (String window, Node button){

        button.getScene().getWindow().hide();

        loader.setLocation(getClass().getResource(window));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        stage.setScene(new Scene(root));
        count++;
        stage.show();
    }
    public static Connection conn(String nameAdmin,String passwdAdm){
        String url = "jdbc:mysql://localhost:3306/mysql";
//        String nameAdmin = "root";
//        String passwdAdm = "rootsqladm";
        Connection cnt = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            pw.println("Connect driver");
        }
        catch (Exception e){
            pw.println("Fail");
        }
        try {
            cnt = DriverManager.getConnection(url, nameAdmin, passwdAdm);
            pw.println("Connection server_2");
        }
        catch (SQLException e){
            pw.println("Fail_2");
        }
        return cnt;
    }
}
