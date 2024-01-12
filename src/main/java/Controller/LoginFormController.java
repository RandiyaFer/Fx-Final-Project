package Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginFormController {


    public TextField passwordTxt;
    public TextField emailTxt;
    public Button loginBtn;
    public Label forgetId;


    public void forgetBtn(MouseEvent mouseEvent) {
        System.out.println("Clicked");
    }
    public void enterBtn(ActionEvent actionEvent) {
        System.out.println("clicked");
    }


}
