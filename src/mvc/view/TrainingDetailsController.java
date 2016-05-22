package mvc.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import mvc.Main;
import mvc.models.Fit;
import mvc.models.Training;

/**
 * Created by Admin on 19.05.2016.
 */
public class TrainingDetailsController {
    @FXML
    private TableView<Fit> fitTable;
    @FXML
    private TableColumn<Fit,String> nameColumn;
    @FXML
    private TableColumn<Fit,Integer> approachColumn;
    @FXML
    private TableColumn<Fit,Integer> quantityColumn;
    @FXML
    private TableColumn<Fit,Integer> weightColumn;

    private Main main;
    private Training training;
    private Stage dialogStage;
    private Fit curFit;
    private ObservableList<Fit> fits= FXCollections.observableArrayList();

    public void setMainApp(Main main) {
        this.main = main;

        // Добавление в таблицу данных из наблюдаемого списка
    }

    public void setTraining(Training training){
        this.training=training;
        for (Fit fit: training.getFits()) {
            fits.add(fit);
        }
    }


    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    @FXML
    private void initialize(){
        nameColumn.setCellValueFactory(c->c.getValue().nameProperty());
        approachColumn.setCellValueFactory(c->c.getValue().approachProperty().asObject());
        quantityColumn.setCellValueFactory(c->c.getValue().quantityProperty().asObject());
        weightColumn.setCellValueFactory(c->c.getValue().weightProperty().asObject());

        fitTable.setItems(fits);
    }

    @FXML
    private void create(){
        Fit fit=new Fit();
//        training.setDate(LocalDate.now());
//        training.setFits(trainingColumn.getCellData(0));
        main.showFitEditDialog(fit);
        training.getFits().add(fit);
        fitTable.getItems().add(fit);

    }
    @FXML
    private void update(){
        Fit fit=fitTable.getSelectionModel().getSelectedItem();
        int id=fitTable.getSelectionModel().getSelectedIndex();

        main.showFitEditDialog(fit);
        training.getFits().set(id,fit);
        fitTable.getItems().set(id,fit);
    }
    @FXML
    private void delete(){
        int selectedIndex=fitTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex>=0) {
            fitTable.getItems().remove(selectedIndex);
            training.getFits().remove(selectedIndex);
        }else {
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No selection");
            alert.setHeaderText("No Exercise Selected");
            alert.setContentText("Please select a person in the table");

            alert.showAndWait();
        }
    }

    public void setFit(Fit fit) {
        this.curFit = fit;
    }
}
