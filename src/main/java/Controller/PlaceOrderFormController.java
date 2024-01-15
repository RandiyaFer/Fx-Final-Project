package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import dto.tm.PlaceOrderTm;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class PlaceOrderFormController {
    public AnchorPane pane;
    public Button loginBtn;
    public Button placeOrderBtn;
    public Label TotalLb;
    public TableColumn orderId;
    public TableColumn custId;
    public TableColumn category;
    public TableColumn itemCode;
    public TableColumn date;
    public TableColumn status;
    public TableColumn details;
    public TableColumn option;
    public Button addCartBtn;
    public JFXTextField orderIdTxt1;
    public JFXTextField detailsTxt;
    public JFXTextField statusTxt;
    public JFXTextField dateTxt;
    public JFXTextField itemTxt;
    public JFXTextField categoryTxt;
    public JFXTextField orderIdTxt;


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

    public void addCartBtn(ActionEvent actionEvent) {
        JFXButton btn = new JFXButton("Delete");

//        PlaceOrderTm tm = new PlaceOrderTm(
//                orderId.getText(),
//                category.getText(),
//                itemCode.getText(),
//                category.getText(),
//                category.getText(),
//                btn
//        );
//        btn.setOnAction(actionEvent -> {
//            tmList.remove(tm);
//            total-=tm.getAmount();
//            lblTotal.setText(String.format("%.2f",total));
//            tblItem.refresh();
//        });
//        boolean isExist = false;
//        for (PlaceOrderTm order:tmList) {
//            if (order.getCode().equals(tm.getCode())){
//                order.setQty(order.getQty()+tm.getQty());
//                order.setAmount(order.getAmount()+tm.getAmount());
//                isExist = true;
//                total+= tm.getAmount();
//            }
//        }
//        if (!isExist){
//            tmList.add(tm);
//            total+=tm.getAmount();
//        }
//
//        lblTotal.setText(String.format("%.2f",total));
//
//        TreeItem treeItem = new RecursiveTreeItem<>(tmList, RecursiveTreeObject::getChildren);
//        tblItem.setRoot(treeItem);
//        tblItem.setShowRoot(false);
//
//    }
//
//    public void placeOrderBtn(ActionEvent actionEvent) {
//    }
    }

//    public void placeOrderBtn(ActionEvent actionEvent) {
//    }
}