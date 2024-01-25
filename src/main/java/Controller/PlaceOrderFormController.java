package Controller;

import Bo.custom.*;
import Bo.custom.impl.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import dto.*;
import dto.tm.PlaceOrderTm;
import entity.CreateUser;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderFormController {
    public AnchorPane pane;
    public Button loginBtn;
    public Button placeOrderBtn;
    public Label TotalLb;
    public TableColumn orderId;
    public TableColumn custId;
    public TableColumn itemCode;
    public TableColumn option;
    public Button addCartBtn;
    public Label customerIdLabel;
    public Label customerNameLbl;
    public Label itemCodeLabel;
    public Label desLbl;
    public Label catLbl;
    public Label subLbl;
    public Label userLbl;
    public JFXComboBox custBox;
    public JFXComboBox itemBox;
    public JFXComboBox user;
    public JFXTextField nameTxt;
    public JFXTextField descTxt;
    public JFXTextField catTxt;
    public JFXTextField subTxt;
    public TableColumn subCatCol;
    public Label orderLbl;
    public Label time;
    public TableColumn Advance;
    public JFXTextField advanceTxt;
    public Label advanceLbl;
    public TableView placeTbl;
    public TableColumn subCategory;
    public TableColumn description;
    public Label stsLbl;

    private CustomerBo customerBo = new CustomerBoImpl();
    private ItemBo itemBo = new ItemBoImpl();
    private OrderBo orderBo= new OrderBoImpl();
    private CreateUserBo usersBo= new CreateUserBoImpl();
    private placeOrdBo PlaceOrdBo= new placeOrdBoImpl();
    private List<CustomerDto> customers;
    private List<ItemDto> items;
    private List<CreateUserDto> users;
    private double total=0;

    private ObservableList<PlaceOrderTm> tmList = FXCollections.observableArrayList();
//    private OrderDao orderDao = new OrderDaoImpl();

    public void initialize(){
        itemCode.setCellValueFactory(new TreeItemPropertyValueFactory<>("itemCode"));
        subCategory.setCellValueFactory(new TreeItemPropertyValueFactory<>("subCategory"));
        description.setCellValueFactory(new TreeItemPropertyValueFactory<>("description"));
        Advance.setCellValueFactory(new TreeItemPropertyValueFactory<>("Advance"));
        option.setCellValueFactory(new TreeItemPropertyValueFactory<>("btn"));


        try {
            customers = customerBo.allCustomers();
            items = itemBo.allItems();
            users = usersBo.allUsers();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        loadCustomerIds();
        loadItemCodes();
        loadUsers();

        custBox.getSelectionModel().selectedItemProperty().addListener((observableValue, o, newValue) -> {
            for (CustomerDto dto:customers) {
                if (dto.getCustomerID().equals(newValue.toString())){
                    nameTxt.setText(dto.getName());
                }
            }
        });

        itemBox.getSelectionModel().selectedItemProperty().addListener((observableValue, o, newValue) -> {
            for (ItemDto dto:items) {
                if (dto.getItemCode().equals(newValue.toString())){
                    descTxt.setText(dto.getDescription());
                    catTxt.setText(dto.getCategory());
                    subTxt.setText(dto.getSubCategory());
                    descTxt.setText(dto.getDescription());
                }
            }
        });

        user.getSelectionModel().selectedItemProperty().addListener((observableValue, o, newValue) -> {
            for (CreateUserDto dto:users) {
                dto.getEmail();
            }
        });

        setOrderId();
        calculateTime();
    }


    private void setOrderId() {
        try {
            orderLbl.setText(orderBo.generateId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadItemCodes() {
        ObservableList list = FXCollections.observableArrayList();

        for (ItemDto dto:items) {
            list.add(dto.getItemCode());
        }
        itemBox.setItems(list);
    }

    private void loadCustomerIds() {
        ObservableList list = FXCollections.observableArrayList();

        for (CustomerDto dto:customers) {
            list.add(dto.getCustomerID());
        }
        custBox.setItems(list);
    }

    private void loadUsers() {
        ObservableList list = FXCollections.observableArrayList();

        for (CreateUserDto dto:users) {
            list.add(dto.getEmail());
        }
        user.setItems(list);
    }

    private void calculateTime() {
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.ZERO,
                actionEvent -> time.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")))
        ),
                new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    @FXML
    void addCartBtn(ActionEvent event) {
        JFXButton btn = new JFXButton("Delete");

        PlaceOrderTm tm = new PlaceOrderTm(
                itemBox.getValue().toString(),
                subTxt.getText(),
                descTxt.getText(),
                Double.parseDouble(advanceTxt.getText()),
                btn
        );

        btn.setOnAction(actionEvent -> {
            tmList.remove(tm);
            total-=tm.getAdvance();
            TotalLb.setText(String.format("%.2f",total));
            placeTbl.refresh();
        });
        boolean isExist = false;
        for (PlaceOrderTm order:tmList) {
            if (order.getItemCode().equals(tm.getItemCode())){
                order.setAdvance(order.getAdvance()+tm.getAdvance());
                isExist = true;
                total+= tm.getAdvance();
            }
        }
        if (!isExist){
            tmList.add(tm);
            total+=tm.getAdvance();
        }

        TotalLb.setText(String.format("%.2f",total));

        TreeItem treeItem = new RecursiveTreeItem<>(tmList, RecursiveTreeObject::getChildren);
//        placeTbl.setRoot(treeItem);
//        placeTbl.setShowRoot(false);
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


    public void placeOrderBtn(ActionEvent actionEvent) {
        List<placeOrdDto> list = new ArrayList<>();
        for (PlaceOrderTm tm:tmList) {
            list.add(new placeOrdDto(
                    orderLbl.getText(),
                    custBox.getValue().toString(),
                    itemBox.getValue().toString(),
                    user.getValue().toString(),
                    Double.parseDouble(advanceTxt.getText())
            ));
        }

        OrderDto dto = new OrderDto(
                    orderLbl.getText(),
                    time.getText(),
                    custBox.getValue().toString(),
                    subTxt.getText(),
                    stsLbl.getText()
                );
        try {
            boolean isSaved = orderBo.saveOrder(dto);
            if (isSaved){
                new Alert(Alert.AlertType.INFORMATION, "Order Saved!").show();
                setOrderId();
            }else{
                new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}