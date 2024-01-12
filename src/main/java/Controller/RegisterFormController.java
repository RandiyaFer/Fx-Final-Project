package Controller;

import Bo.custom.RegisterBo;
import Bo.custom.impl.RegisterBoImpl;
import dto.RegisterDto;
import dto.tm.RegisterTm;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RegisterFormController {

    public TextField emailTxt;
    public TextField nameTxt;
    public Button loginBtn;
    public TextField jobTxt;
    public TextField contactTxt;
    public BorderPane pane;

    private RegisterBo registerBo = new RegisterBoImpl();

    private ObservableList<RegisterTm> tmList = FXCollections.observableArrayList();

    public void EnterBtn(ActionEvent actionEvent) {
        List<RegisterDto> list = new ArrayList<>();
        for (RegisterTm tm:tmList) {
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
            if (isSaved){
                new Alert(Alert.AlertType.INFORMATION, "Order Saved!").show();
            }else{
                new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
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
