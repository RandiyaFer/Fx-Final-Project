package Controller;

import Bo.custom.CustomerBo;
import Bo.custom.adminBo;
import Bo.custom.impl.CustomerBoImpl;
import Bo.custom.impl.adminBoImpl;
import dto.AdminDto;
import dto.CustomerDto;
import entity.Admin;
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
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class LoginFormController {
    public static Object enterBtn;
    public TextField passwordTxt;
    public TextField emailTxt;
    public Button loginBtn;
    public Label forgetId;
    public BorderPane pane;
    public JFrame f;
    private adminBo AdminBo = new adminBoImpl();
    private List<AdminDto> admins;
    public void forgetBtn(MouseEvent mouseEvent) {
        Stage stage = (Stage) pane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/forgetPwForm.fxml"))));
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void enterBtn(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String email = emailTxt.getText();
        String password = passwordTxt.getText();
        admins = AdminBo.allAdmin();

        if (isValidEmail(email)) {
            if (isValidPass(password)) {

                Object[] options = {"Register User",
                        "Create User",
                        "Report"};
                int n = JOptionPane.showOptionDialog(f, "Please Select your Option Again?", "Confirm Box",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
                if (n == JOptionPane.YES_OPTION) {
                    Stage stage = (Stage) pane.getScene().getWindow();
                    try {
                        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/RegisterForm.fxml"))));
                        stage.centerOnScreen();
                        stage.show();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                } else if (n == JOptionPane.NO_OPTION) {
                    Stage stage = (Stage) pane.getScene().getWindow();
                    try {
                        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/CreateUserForm.fxml"))));
                        stage.centerOnScreen();
                        stage.show();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    Stage stage = (Stage) pane.getScene().getWindow();
                    try {
                        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/PlaceOrderForm.fxml"))));
                        stage.centerOnScreen();
                        stage.show();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }else {
                new Alert(Alert.AlertType.ERROR, "Invalid Password!").show();
                emailTxt.clear();
                passwordTxt.clear();
            }
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid Email!").show();
                emailTxt.clear();
                passwordTxt.clear();
            }
        }
    private boolean isValidEmail(String email) {
        boolean isExist = false;
        for (AdminDto dto:admins) {
            if (dto.getEmail().equals(email)){
                isExist = true;
            }else {
                isExist = false;
            }
        }
        return isExist;
    }

    private boolean isValidPass(String Password) {
        boolean isExist = false;
        for (AdminDto dto:admins) {
            if (dto.getPassword().equals(Password)){
                isExist = true;
            }else {
                isExist = false;
            }
        }
        return isExist;
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
