package Controller;

import Bo.custom.RegisterBo;
import Bo.custom.impl.RegisterBoImpl;
import com.jfoenix.controls.JFXComboBox;
import dto.RegisterDto;
import dto.tm.RegisterTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterFormController {

    public TextField emailTxt;
    public TextField nameTxt;
    public Button loginBtn;
    public Label jobTxt;
    public TextField contactTxt;
    public BorderPane pane;
    public JFXComboBox jobBox;
    private RegisterBo registerBo = new RegisterBoImpl();
    private ObservableList<RegisterTm> tmList = FXCollections.observableArrayList();
    public void initialize() {
        jobBox.getItems().addAll("Electrical", "Electronic");
    }
    public void EnterBtn(ActionEvent actionEvent) {
        String name=nameTxt.getText();
        String email=emailTxt.getText();
        String job=jobBox.getValue().toString();
        String contNo=contactTxt.getText();

        if (isValidEmail(email)) {
            if (isValidContactNumber(contNo)) {
                List<RegisterDto> list = new ArrayList<>();
                for (RegisterTm tm : tmList) {
                    list.add(new RegisterDto(
                            tm.getName(),
                            tm.getEmail(),
                            tm.getJobRole(),
                            tm.getConNo()
                    ));
                }
                RegisterDto dto = new RegisterDto(
                        nameTxt.getText(),
                        emailTxt.getText(),
                        jobTxt.getText(),
                        contactTxt.getText()
                );
                try {
                    boolean isSaved = registerBo.saveOrder(dto);
                    if (isSaved) {
                        new Alert(Alert.AlertType.INFORMATION, "User Saved!").show();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid ContactNumber!").show();
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
    private boolean isValidContactNumber(String contactNumber) {
        String contactRegex = "^0\\d{9}$";
        Pattern pattern = Pattern.compile(contactRegex);
        Matcher matcher = pattern.matcher(contactNumber);
        return matcher.matches();
    }
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&-]+(?:\\.[a-zA-Z0-9_+&-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
