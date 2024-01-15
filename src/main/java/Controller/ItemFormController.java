package Controller;

import Bo.custom.ItemBo;
import Bo.custom.impl.ItemBoImpl;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.ItemDto;
import dto.tm.ItemTm;
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

public class ItemFormController {
    public Button updateBtn;
    public Button saveBtn;
    public TableColumn option;
    public TableColumn descriptionCol;
    public TableColumn subCol;
    public TableColumn categoryCol;
    public TableColumn itemcodecol;
    public Label subCateLbl;
    public Label descLbl;
    public JFXComboBox categoryTxt;
    public Label cateLbl;
    public Label itemLbl;
    public JFXTextField subTxt;
    public JFXTextField descTxt;
    public Button searchBtn;
    public AnchorPane pane;
    public JFXTextField itemCodeTxt;
    public TableView tblItem;

    private ItemBo itemBo = new ItemBoImpl();

    private ObservableList<ItemTm> tmList = FXCollections.observableArrayList();

    public void initialize(){
        itemcodecol.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        subCol.setCellValueFactory(new PropertyValueFactory<>("subCategory"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        option.setCellValueFactory(new PropertyValueFactory<>("btn"));
        loadItemTable();

        categoryTxt.getItems().addAll("Electrical", "Electronic");

        tblItem.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            setData((ItemTm) newValue);
        });
    }

    private void loadItemTable() {
        ObservableList<ItemTm> tmList = FXCollections.observableArrayList();
        try {
            List<ItemDto> dtoList  = itemBo.allItems();
            for (ItemDto dto:dtoList) {
                Button btn = new Button("Delete");
                ItemTm tm = new ItemTm(
                        dto.getItemCode(),
                        dto.getCategory(),
                        dto.getSubCategory(),
                        dto.getDescription(),
                        btn
                );

                btn.setOnAction(actionEvent -> {
                    deleteItem(tm.getItemCode());
                });
                tmList.add(tm);
            }
            tblItem.setItems(tmList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setData(ItemTm newValue) {
        if (newValue != null) {
            itemCodeTxt.setEditable(false);
            itemCodeTxt.setText(newValue.getItemCode());
//            categoryTxt.setItems(newValue.getCategory());
            subTxt.setText(newValue.getSubCategory());
            descTxt.setText(newValue.getDescription());
        }
    }

    private void clearFields() {
        tblItem.refresh();
        descTxt.clear();
        subTxt.clear();
        categoryTxt.setValue(null);
        itemCodeTxt.clear();
        itemCodeTxt.setEditable(true);
    }

    public void updateBtn(ActionEvent actionEvent) {
        ItemDto dto = new ItemDto(
                itemCodeTxt.getText(),
                categoryTxt.getValue().toString(),
                subTxt.getText(),
                descTxt.getText()
        );

        try {
            boolean isUpdated = itemBo.updateItem(dto);
            if (isUpdated){
                new Alert(Alert.AlertType.INFORMATION,"Item "+dto.getItemCode()+" Updated!").show();
                loadItemTable();
                clearFields();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveBtn(ActionEvent actionEvent) {
        List<ItemDto> list = new ArrayList<>();
        for (ItemTm tm:tmList) {
            list.add(new ItemDto(
                    tm.getItemCode(),
                    tm.getCategory(),
                    tm.getSubCategory(),
                    tm.getDescription()
            ));
        }

        ItemDto dto = new ItemDto(
                itemCodeTxt.getText(),
                categoryTxt.getValue().toString(),
                subTxt.getText(),
                descTxt.getText()

        );


        try {
            boolean isSaved = itemBo.saveItem(dto);
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
            boolean isDeleted = itemBo.deleteItem(code);
            if (isDeleted){
                new Alert(Alert.AlertType.INFORMATION,"Item Deleted!").show();
                loadItemTable();
            }else{
                new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
