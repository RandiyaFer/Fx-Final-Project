package Controller;

import Bo.custom.CreateUserBo;
import Bo.custom.impl.CreateUserBoImpl;
import dto.CreateUserDto;
import dto.tm.CreateUserTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateUserFormController {
    public TextField emailTxt;
    public Button loginBtn;
    public TextField passwordTxt;
    public TextField confirmTxt;
    public BorderPane pane;

    private CreateUserBo createBo = new CreateUserBoImpl();

    private ObservableList<CreateUserTm> tmList = FXCollections.observableArrayList();

    public void EnterBtn(ActionEvent actionEvent) {

        String email = emailTxt.getText();
        String password = passwordTxt.getText();
        String confirm = confirmTxt.getText();

        if (isValidEmail(email)) {
            if (password.equals(confirm)) {
                List<CreateUserDto> list = new ArrayList<>();
                for (CreateUserTm tm : tmList) {
                    list.add(new CreateUserDto(
                            tm.getEmail(),
                            tm.getPassword()
                    ));
                }

                CreateUserDto dto = new CreateUserDto(
                        emailTxt.getText(),
                        passwordTxt.getText()
                );


                try {
                    boolean isSaved = createBo.saveOrder(dto);
                    if (isSaved) {
                        new Alert(Alert.AlertType.INFORMATION, "User Created!").show();
                        confirmTxt.clear();
                        passwordTxt.clear();
                        emailTxt.clear();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
                        confirmTxt.clear();
                        passwordTxt.clear();
                        emailTxt.clear();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Unmatched Password!").show();
                confirmTxt.clear();
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Invalid Email!").show();
            confirmTxt.clear();
            passwordTxt.clear();
            emailTxt.clear();
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
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&-]+(?:\\.[a-zA-Z0-9_+&-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
