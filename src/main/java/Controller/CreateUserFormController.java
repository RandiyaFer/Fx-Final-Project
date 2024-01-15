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

public class CreateUserFormController {
    public TextField emailTxt;
    public Button loginBtn;
    public TextField passwordTxt;
    public TextField confirmTxt;
    public BorderPane pane;

    private CreateUserBo createBo = new CreateUserBoImpl();

    private ObservableList<CreateUserTm> tmList = FXCollections.observableArrayList();

    public void EnterBtn(ActionEvent actionEvent) {
        List<CreateUserDto> list = new ArrayList<>();
        for (CreateUserTm tm:tmList) {
            list.add(new CreateUserDto(
                    tm.getEmail(),
                    tm.getPassword(),
                    tm.getConfirm()
            ));
        }

        CreateUserDto dto = new CreateUserDto(
                emailTxt.getText(),
                passwordTxt.getText(),
                confirmTxt.getText()
        );


        try {
            boolean isSaved = createBo.saveOrder(dto);
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
