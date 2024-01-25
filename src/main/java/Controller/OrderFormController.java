package Controller;

import Bo.custom.CustomerBo;
import Bo.custom.OrderBo;
import Bo.custom.impl.CustomerBoImpl;
import Bo.custom.impl.OrderBoImpl;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import dto.CustomerDto;
import dto.ItemDto;
import dto.OrderDto;
import dto.tm.CustomerTm;
import dto.tm.OrderTm;
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
import java.util.List;

public class OrderFormController {
    public TextField searchBar;
    public JFXTextField orderIdTxt;
    public JFXTextField subTxt;
    public TableColumn orderIdCol;
    public TableColumn custCol;
    public TableColumn subCol;
    public TableColumn statusCol;
    public JFXTextField customerIdTxt;
    public TableColumn option;

    public AnchorPane pane;
    public JFXRadioButton processingBtn;
    public JFXRadioButton preparedBtn;
    public ToggleGroup radioStatus;
    public JFXRadioButton completeBtn;
    public JFXTextField statusText;
    public TableView tblOrder;
    public JFXTextField dateTxt;

    private OrderBo orderBo = new OrderBoImpl();

    private ObservableList<OrderTm> tmList = FXCollections.observableArrayList();

    public void initialize(){
        orderIdCol.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        custCol.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        subCol.setCellValueFactory(new PropertyValueFactory<>("subCategoryCol"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        option.setCellValueFactory(new PropertyValueFactory<>("btn"));
        loadOrderTable();

        tblOrder.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            setData((OrderTm) newValue);
        });
    }

    private void loadOrderTable() {
        ObservableList<OrderTm> tmList = FXCollections.observableArrayList();
        try {
            List<OrderDto> dtoList  = orderBo.allOrders();
            for (OrderDto dto:dtoList) {
                Button btn = new Button("Delete");
                OrderTm tm = new OrderTm(
                        dto.getOrderId(),
                        dto.getDate(),
                        dto.getCustomerID(),
                        dto.getSubCategory(),
                        dto.getStatus(),
                        btn
                );

                btn.setOnAction(actionEvent -> {
                    deleteOrder(tm.getOrderId());
                });
                tmList.add(tm);
            }
            tblOrder.setItems(tmList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setData(OrderTm newValue) {
        if (newValue != null) {
            orderIdTxt.setEditable(false);
            orderIdTxt.setText(newValue.getOrderId());
            customerIdTxt.setText(newValue.getCustomerId());
            subTxt.setText(newValue.getSubCategory());
            statusText.setText(newValue.getStatus());
        }
    }

    private void clearFields() {
        tblOrder.refresh();
        statusText.clear();
        subTxt.clear();
        customerIdTxt.clear();
        orderIdTxt.clear();
        orderIdTxt.setEditable(true);
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

    public void searchBtn(ActionEvent actionEvent) {
    }

    public void updateBtn(ActionEvent actionEvent) {
        OrderDto dto = new OrderDto(
                orderIdTxt.getText(),
                dateTxt.getText(),
                customerIdTxt.getText(),
                subTxt.getText(),
                statusText.getText()
        );

        try {
            boolean isUpdated = orderBo.updateOrder(dto);
            if (isUpdated){
                new Alert(Alert.AlertType.INFORMATION,"Order "+dto.getOrderId()+" Updated!").show();
                loadOrderTable();
                clearFields();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteOrder(String code) {

        try {
            boolean isDeleted = orderBo.deleteOrder(code);
            if (isDeleted){
                new Alert(Alert.AlertType.INFORMATION,"Order Deleted!").show();
                loadOrderTable();
            }else{
                new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void radioBtn(ActionEvent actionEvent) {
        if(preparedBtn.isSelected()) {
            statusText.setText(preparedBtn.getText());
        }
        else if(processingBtn.isSelected()) {
            statusText.setText(processingBtn.getText());
        }
        else if(completeBtn.isSelected()) {
            statusText.setText(completeBtn.getText());
        }
    }
}
