package Controller;

import Bo.custom.CreateUserBo;
import Bo.custom.adminBo;
import Bo.custom.impl.CreateUserBoImpl;
import Bo.custom.impl.adminBoImpl;
import dto.AdminDto;
import dto.CreateUserDto;
import entity.CreateUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class LoginForm2Controller {
    public TextField passwordTxt;

    public TextField emailTxt;
    public Label forgetId;
    public Button loginBtn;
    public BorderPane pane;
    public JOptionPane f;
    private CreateUserBo createUserBo = new CreateUserBoImpl();
    private List<CreateUserDto> users;
    public void forgetBtn(MouseEvent mouseEvent) {
        System.out.println("Clicked");
    }
    public void enterBtn(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String email = emailTxt.getText();
        String password = passwordTxt.getText();
        users = createUserBo.allUsers();

        if (isValidEmail(email)) {
            if (isValidPass(password)) {
                Object[] options = {"Order", "Item", "Customer"};
                int n = JOptionPane.showOptionDialog(f, "Please Select your Option Again?", "Confirm Box",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
                if (n == JOptionPane.YES_OPTION) {
                    Object[] options2 = {"Change Status", "Add Additional Parts", "Nothing"};
                    int m = JOptionPane.showOptionDialog(f, "What do you in Orders?", "Confirm Box",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE, null, options2, options2[2]);
                    if (m == JOptionPane.YES_OPTION) {
                        Stage stage = (Stage) pane.getScene().getWindow();
                        try {
                            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/OrderForm.fxml"))));
                            stage.centerOnScreen();
                            stage.show();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } else if (m == JOptionPane.NO_OPTION) {
                        Stage stage = (Stage) pane.getScene().getWindow();
                        try {
                            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/selectOrder.fxml"))));
                            stage.centerOnScreen();
                            stage.show();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        return;
                    }
                } else if (n == JOptionPane.NO_OPTION) {
                    Stage stage = (Stage) pane.getScene().getWindow();
                    try {
                        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/ItemForm.fxml"))));
                        stage.centerOnScreen();
                        stage.show();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    Stage stage = (Stage) pane.getScene().getWindow();
                    try {
                        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/CustomerForm.fxml"))));
                        stage.centerOnScreen();
                        stage.show();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }else {
                    new Alert(Alert.AlertType.ERROR, "Invalid Password!").show();
                }
            }else {
            new Alert(Alert.AlertType.ERROR, "Invalid Email!").show();
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
    private boolean isValidPass(String Password) {
        boolean isExist = false;
        for (CreateUserDto dto:users) {
            if (dto.getPassword().equals(Password)){
                isExist = true;
            }else {
                isExist = false;
            }
        }
        return isExist;
    }
    private boolean isValidEmail(String email) {
        boolean isExist = false;
        for (CreateUserDto dto:users) {
            if (dto.getEmail().equals(email)){
                isExist = true;
            }else {
                isExist = false;
            }
        }
        return isExist;
    }
}
