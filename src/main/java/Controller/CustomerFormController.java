package Controller;

import Bo.custom.CustomerBo;
import Bo.custom.impl.CustomerBoImpl;
import com.jfoenix.controls.JFXTextField;
import dto.CustomerDto;
import dto.tm.CustomerTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerFormController {

    public AnchorPane pane;
    public Button searchBtn;
    public JFXTextField emailTxt;
    public JFXTextField nameTxt;
    public Label custLbl;
    public Label nameLbl;
    public Label emailLbl;
    public Label contLbl;
    public JFXTextField customerIdTxt;
    public JFXTextField contNoTxt;
    public TableView tblCust;
    public TableColumn customerIdCol;
    public TableColumn nameCol;
    public TableColumn ContactCol;
    public TableColumn emailCol;
    public TableColumn option;
    public Button saveBtn;
    public Button updateBtn;
    private CustomerBo customerBo = new CustomerBoImpl();
    private ObservableList<CustomerTm> tmList = FXCollections.observableArrayList();

    public void initialize(){
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        ContactCol.setCellValueFactory(new PropertyValueFactory<>("ContactNo"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("Email"));
        option.setCellValueFactory(new PropertyValueFactory<>("btn"));
        loadItemTable();

        tblCust.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            setData((CustomerTm) newValue);
        });
    }

    private void loadItemTable() {
        ObservableList<CustomerTm> tmList = FXCollections.observableArrayList();
        try {
            List<CustomerDto> dtoList  = customerBo.allCustomers();
            for (CustomerDto dto:dtoList) {
                Button btn = new Button("Delete");
                CustomerTm tm = new CustomerTm(
                        dto.getCustomerID(),
                        dto.getName(),
                        dto.getContactNo(),
                        dto.getEmail(),
                        btn
                );

                btn.setOnAction(actionEvent -> {
                    deleteItem(tm.getCustomerID());
                });
                tmList.add(tm);
            }
            tblCust.setItems(tmList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setData(CustomerTm newValue) {
        if (newValue != null) {
            customerIdTxt.setEditable(false);
            customerIdTxt.setText(newValue.getCustomerID());
            nameTxt.setText(newValue.getName());
            contNoTxt.setText(newValue.getContactNo());
            emailTxt.setText(newValue.getEmail());
        }
    }
    private void clearFields() {
        tblCust.refresh();
        emailTxt.clear();
        contNoTxt.clear();
        nameTxt.clear();
        customerIdTxt.clear();
        customerIdTxt.setEditable(true);
    }
    public void updateBtn(ActionEvent actionEvent) {
        CustomerDto dto = new CustomerDto(
                customerIdTxt.getText(),
                nameTxt.getText(),
                contNoTxt.getText(),
                emailTxt.getText()
        );

        try {
            boolean isUpdated = customerBo.updateCustomer(dto);
            if (isUpdated){
                new Alert(Alert.AlertType.INFORMATION,"Customer "+dto.getCustomerID()+" Updated!").show();
                loadItemTable();
                clearFields();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchBtn(ActionEvent actionEvent) {
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
    private void deleteItem(String code) {
        try {
            boolean isDeleted = customerBo.deleteCustomer(code);
            if (isDeleted){
                new Alert(Alert.AlertType.INFORMATION,"Customer Deleted!").show();
                loadItemTable();
            }else{
                new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    //public void saveBtn(ActionEvent actionEvent) {
//        List<CustomerDto> list = new ArrayList<>();
//        for (CustomerTm tm:tmList) {
//            list.add(new CustomerDto(
//                    tm.getCustomerID(),
//                    tm.getName(),
//                    tm.getContactNo(),
//                    tm.getEmail()
//            ));
//        }
//        CustomerDto dto = new CustomerDto(
//                customerIdTxt.getText(),
//                nameTxt.getText(),
//                contNoTxt.getText(),
//                emailTxt.getText()
//
//        );
//        try {
//            boolean isSaved = customerBo.saveCustomer(dto);
//            if (isSaved){
//                new Alert(Alert.AlertType.INFORMATION, "Item Saved!").show();
//                loadItemTable();
//                clearFields();
//            }else{
//                new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public void saveBtn(ActionEvent actionEvent) {
        List<CustomerDto> list = new ArrayList<>();
        for (CustomerTm tm:tmList) {
            list.add(new CustomerDto(
                    tm.getCustomerID(),
                    tm.getName(),
                    tm.getContactNo(),
                    tm.getEmail()
            ));
        }
        String customerId = customerIdTxt.getText();
        String customerName = nameTxt.getText();
        String customerContact = contNoTxt.getText();
        String customerEmail = emailTxt.getText();

        if (isValidCustomerId(customerId)) {
            if (isValidContactNumber(customerContact)) {
                if (isValidEmail(customerEmail)) {
                    CustomerDto dto = new
                            CustomerDto(customerId,
                            customerName,
                            customerContact,
                            customerEmail);
                    try {
                        boolean isSaved = customerBo.saveCustomer(dto);
                        if (isSaved) {
                            new Alert(Alert.AlertType.INFORMATION, "Customer Saved!").show();
                            loadItemTable();
                            clearFields();
                        }
                    } catch (SQLIntegrityConstraintViolationException ex) {
                        new Alert(Alert.AlertType.ERROR, "Duplicate Entry").show();
                    } catch (ClassNotFoundException | SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Invalid Email!").show();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid Contact Number").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid Customer ID!").show();
        }

    }

    private boolean isValidCustomerId(String customerId) {
        return customerId.matches("^C\\d{3}$");
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

