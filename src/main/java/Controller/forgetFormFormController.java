package Controller;

import Bo.custom.adminBo;
import Bo.custom.impl.adminBoImpl;
import dto.AdminDto;
import email.Email;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

public class forgetFormFormController {
    public TextField otpTxt;
    public TextField emailTxt;
    public Button loginBtn;
    public TextField passwordTxt;
    public TextField confirmTxt;
    public Button OkBtn;
    public BorderPane pane;
    public Button OkBtn2;
    private adminBo AdminBo = new adminBoImpl();
    private List<AdminDto> admins2;
    private String email;
    private String otpS;
    private String password;
    private String confirm;
    int otp = -1;

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

    public void EnterBtn(ActionEvent actionEvent) {
        password = passwordTxt.getText();
        confirm = confirmTxt.getText();

        if (password.equals(confirm)){
            AdminDto dto = new AdminDto(
                    email,confirm
            );

            try {
                boolean isUpdated = AdminBo.updateAdmin(dto);
                if (isUpdated){
                    new Alert(Alert.AlertType.INFORMATION,"Admin "+dto.getEmail()+" Updated!").show();
                    clearFields();
                }

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void OkBtn(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        email = emailTxt.getText();
        if (isValidEmail(email)){
            new Alert(Alert.AlertType.INFORMATION, "valid email!").show();
                otp = sendEmail();
        }else {
            new Alert(Alert.AlertType.ERROR, "invalid email!").show();
        }
    }
    private boolean isValidEmail(String email) throws SQLException, ClassNotFoundException {
        admins2 = AdminBo.allAdmin();
        boolean isExist = false;
        for (AdminDto dto:admins2) {
            if (dto.getEmail().equals(email)){
                isExist = true;
            }else {
                isExist = false;
            }
        }
        return isExist;
    }
    public void OkBtn2(ActionEvent actionEvent) {
        otpS = otpTxt.getText();
        if (otp==Integer.valueOf(otpS)){
            new Alert(Alert.AlertType.INFORMATION, "Ok,Enter new Password!").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Wrong otp,please try again!").show();
            otpTxt.clear();
        }
    }
    private void clearFields() {
     confirmTxt.clear();
     passwordTxt.clear();
     otpTxt.clear();
     emailTxt.clear();
    }
    private int sendEmail(){
        //otp = -1;
        String senderEmail = "randiya4fernando@gmail.com";
        String recipientEmail = "pamindurandiya34@gmail.com";
        //userDao.getEmail(userName);
        Session session = Email.getInstance().getSession();

        try {
            // Create a message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipientEmail));
            message.setSubject("E&E Service (pvt).Ltd");
            otp = new Random().nextInt(10000 - 1000) + 1000;
            //num = String.format("%14d", otp);
            message.setText(String.valueOf(otp));

            // Send the message
            Transport.send(message);

            System.out.println("Email sent successfully.");
            OkBtn2.setDisable(false);
        } catch (MessagingException e) {
            System.out.println("Failed to send email. Error: " + e.getMessage());
        }
        return otp;
    }
}
