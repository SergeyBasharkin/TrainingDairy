package mvc.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import mvc.Main;
import mvc.models.Fit;
import mvc.models.Training;
import mvc.util.Helper;

import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {
    @FXML
    private TableView<Training> trainingTable;
    @FXML
    private TableColumn<Training,LocalDate> dateColumn;
    @FXML
    private TableColumn<Training,ArrayList<Fit>> trainingColumn;

    private Main main;


    private ObservableList<Training> list= FXCollections.observableArrayList();

    public void setMainApp(Main main) {
        this.main = main;

        // Добавление в таблицу данных из наблюдаемого списка
    }

    @FXML
    private void initialize(){
        dateColumn.setCellValueFactory(c->c.getValue().dateProperty());
        trainingColumn.setCellValueFactory(c->c.getValue().fitsProperty());

        Helper.setData(list);
        trainingTable.setItems(list);

    }

    @FXML
    private void delete(){
        int selectedIndex=trainingTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex>=0) {
            trainingTable.getItems().remove(selectedIndex);
        }else {
            getAlert("No selection","No Traning Selected","Please select a person in the table");
        }
    }
    @FXML
    private void create(){
        Training training=new Training();
//        training.setDate(LocalDate.now());
//        training.setFits(trainingColumn.getCellData(0));
        main.showPersonEditDialog(training);
        trainingTable.getItems().add(training);

    }

    private void getAlert(String title,String header,String content){
        Alert alert=new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }
    @FXML
    private void details(){
        Training training=trainingTable.getSelectionModel().getSelectedItem();
        int id=trainingTable.getSelectionModel().getSelectedIndex();

        if (id>=0) {
            main.showDetailsDialog(training);
            trainingTable.getItems().set(id,training);
        }else {

            getAlert("No selection","No Traning Selected","Please select a person in the table");
        }
    }
}
