package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class LoginFormController {


    public static Object enterBtn;
    public TextField passwordTxt;
    public TextField emailTxt;
    public Button loginBtn;
    public Label forgetId;
    public BorderPane pane;

    public JFrame f;


    public void forgetBtn(MouseEvent mouseEvent) {
        System.out.println("Clicked");
    }
    public void enterBtn(ActionEvent actionEvent) {

        Object[] options = {"Register User",
                "Create User",
                "Report"};
        int n = JOptionPane.showOptionDialog(f, "Please Select your Option Again?", "Confirm Box",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
        if (n==JOptionPane.YES_OPTION){
            Stage stage = (Stage) pane.getScene().getWindow();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/RegisterForm.fxml"))));
                stage.centerOnScreen();
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }else if (n==JOptionPane.NO_OPTION){
            Stage stage = (Stage) pane.getScene().getWindow();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/CreateUserForm.fxml"))));
                stage.centerOnScreen();
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            Stage stage = (Stage) pane.getScene().getWindow();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/PlaceOrderForm.fxml"))));
                stage.centerOnScreen();
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void backButtonOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) pane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashBoardForm.fxml"))));
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
