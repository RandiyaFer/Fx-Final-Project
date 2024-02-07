package Controller;

import Bo.custom.PartsBo;
import Bo.custom.addPartsBo;
import Bo.custom.impl.PartsBoImpl;
import Bo.custom.impl.addPartsBoImpl;
import Dao.DaoFactory;
import Dao.custom.CustomerDao;
import Dao.custom.addPartsDao;
import Dao.util.DaoType;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.*;
import dto.tm.OrderTm;
import dto.tm.PlaceOrderTm;
import dto.tm.addPartsTm;
import dto.tm.partsTm;
import entity.Parts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class orderPartsFormController {
    public TextField searchBar;
    public JFXTextField customerIdTxt;
    public JFXTextField orderIdTxt;
    public JFXTextField subTxt;
    public JFXTextField dateTxt;
    public JFXTextField statusText;
    public JFXComboBox partsBox;
    public TableView tblOrder;
    public TableColumn orderIdCol;
    public TableColumn custCol;
    public TableColumn subCol;
    public TableColumn dateCol;
    public TableColumn statusCol;
    public TableColumn parts;
    public TableColumn total;
    public AnchorPane pane;
    public JFXTextField totalTxt;
    public JFXTextField qtyText;
    public JFXTextField amountText;
    public TableColumn qtyCol;
    private List<addPartsDto> parts2;

    private PartsBo partsBo = new PartsBoImpl();
    private addPartsBo AddPartsBo = new addPartsBoImpl();

    private double totals=0;

    private ObservableList<partsTm> tmList = FXCollections.observableArrayList();

    public void initialize(){
        orderIdCol.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        custCol.setCellValueFactory(new PropertyValueFactory<>("CustomerId"));
        subCol.setCellValueFactory(new PropertyValueFactory<>("subCategory"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        parts.setCellValueFactory(new PropertyValueFactory<>("part"));
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("qty"));
        total.setCellValueFactory(new PropertyValueFactory<>("total"));
        loadOrderTable();

        //partsBox.getItems().addAll("Antenna", "Board");

        tblOrder.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            setData((partsTm) newValue);
        });
        try {
            parts2 = AddPartsBo.allOrders();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        loadParts();

        partsBox.getSelectionModel().selectedItemProperty().addListener((observableValue, o, newValue) -> {
            for (addPartsDto dto:parts2) {
                if (dto.getName().equals(newValue.toString())){
                    qtyText.setText("1");
                    amountText.setText(String.valueOf(dto.getPrize()));
                }
            }
        });
    }

    private void loadOrderTable() {
        ObservableList<partsTm> tmList = FXCollections.observableArrayList();
        try {
            List<PartsDto> dtoList  = partsBo.allOrders();
            for (PartsDto dto:dtoList) {
                partsTm tm = new partsTm(
                        dto.getId(),
                        dto.getDate(),
                        dto.getCustomerId(),
                        dto.getSubCategory(),
                        dto.getStatus(),
                        dto.getPart(),
                        dto.getQty(),
                        dto.getTotal()
                );
                tmList.add(tm);
            }
            tblOrder.setItems(tmList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setData(partsTm newValue) {
        if (newValue != null) {
            orderIdTxt.setEditable(false);
            orderIdTxt.setText(String.valueOf(newValue.getOrderId()));
            partsBox.setValue(newValue.getPart().toString());
            subTxt.setText(newValue.getSubCategory());
            statusText.setText(newValue.getStatus());
            totalTxt.setText(String.valueOf(newValue.getTotal()));
            qtyText.setText(String.valueOf(newValue.getQty()));
            customerIdTxt.setText(newValue.getCustomerId());
        }
    }

    private void clearFields() {
        tblOrder.refresh();
        totalTxt.clear();
        qtyText.clear();
        partsBox.setValue(null);
        statusText.clear();
        subTxt.clear();
        customerIdTxt.clear();
        orderIdTxt.clear();
        orderIdTxt.setEditable(true);

    }

    private void loadParts() {
        ObservableList list = FXCollections.observableArrayList();

        for (addPartsDto dto:parts2) {
            list.add(dto.getName());
        }
        partsBox.setItems(list);
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
        PartsDto dto = new PartsDto(
                orderIdTxt.getText(),
                dateTxt.getText(),
                customerIdTxt.getText(),
                subTxt.getText(),
                statusText.getText(),
                partsBox.getValue().toString(),
                Integer.parseInt(qtyText.getText()),
                Double.parseDouble(totalTxt.getText())
        );

        try {
            boolean isUpdated = partsBo.updateOrder(dto);
            if (isUpdated){
                new Alert(Alert.AlertType.INFORMATION,"Order "+dto.getId()+" Updated!").show();
                loadOrderTable();
                clearFields();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteOrder(String code) {

        try {
            boolean isDeleted = partsBo.deleteOrder(code);
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

    public void qtyAction(KeyEvent keyEvent) {
        try {
            totals= Double.parseDouble(amountText.getText()) * Double.parseDouble(qtyText.getText());
            totalTxt.setText(String.valueOf(totals));
        } catch (NumberFormatException nfe){
            System.out.println(nfe);
        }

    }
}
