package mvc.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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


    private ObservableList<Training> list= FXCollections.observableArrayList();


    @FXML
    private void initialize(){
        dateColumn.setCellValueFactory(c->c.getValue().dateProperty());
        trainingColumn.setCellValueFactory(c->c.getValue().fitsProperty());

        Helper.setData(list);
        trainingTable.setItems(list);

    }
}
