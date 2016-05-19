package mvc.view;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mvc.models.Fit;
import mvc.models.Training;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by Admin on 18.05.2016.
 */
public class TrainingCreateController {
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField exerciseName;
    @FXML
    private TextField exerciseApproach;
    @FXML
    private TextField exerciseQuantity;
    @FXML
    private TextField exerciseWeight;
    private Stage dialogStage;
    private Training training;
    ArrayList<Fit> fits;
    @FXML
    private void initialize(){


        fits=new ArrayList<>();
    }


    public void setTraining(Training training){
        this.training=training;

    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


    @FXML
    private void create(){
        training.setDate(datePicker.getValue());

        fits.add(new Fit(exerciseName.getText(),Integer.valueOf(exerciseApproach.getText()),Integer.valueOf(exerciseQuantity.getText()),Integer.valueOf(exerciseWeight.getText())));
        training.setFits(fits);
        dialogStage.close();

    }

    @FXML
    private void next(){
        training.setDate(datePicker.getValue());
        fits.add(new Fit(exerciseName.getText(),Integer.valueOf(exerciseApproach.getText()),Integer.valueOf(exerciseQuantity.getText()),Integer.valueOf(exerciseWeight.getText())));
        training.setFits(fits);

        exerciseName.setText("");
        exerciseApproach.setText("");
        exerciseQuantity.setText("");
        exerciseWeight.setText("");
    }
}
