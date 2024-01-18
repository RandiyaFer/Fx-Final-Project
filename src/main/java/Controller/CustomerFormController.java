package Controller;

import Bo.custom.CustomerBo;
import Bo.custom.ItemBo;
import Bo.custom.impl.CustomerBoImpl;
import Bo.custom.impl.ItemBoImpl;
import com.jfoenix.controls.JFXTextField;
import dto.CustomerDto;
import dto.ItemDto;
import dto.tm.CustomerTm;
import dto.tm.ItemTm;
import entity.Customer;
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
import java.util.ArrayList;
import java.util.List;

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

        CustomerDto dto = new CustomerDto(
                customerIdTxt.getText(),
                nameTxt.getText(),
                contNoTxt.getText(),
                emailTxt.getText()

        );

        try {
            boolean isSaved = customerBo.saveCustomer(dto);
            if (isSaved){
                new Alert(Alert.AlertType.INFORMATION, "Item Saved!").show();
                loadItemTable();
                clearFields();
            }else{
                new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
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
}

