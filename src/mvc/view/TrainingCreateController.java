package mvc.view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import mvc.models.Fit;
import mvc.models.Training;
import mvc.util.Helper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public static boolean isNumeric(String x)
    {
        Pattern p = Pattern.compile("^\\d+(?:\\.\\d+)?$");
        Matcher m = p.matcher(x);
        System.out.println(m.matches());
        return m.matches();
    }

    public boolean isInputValid(){
        String errorMessage = "";

        if (exerciseName.getText() == null || exerciseName.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (exerciseApproach.getText() == null || exerciseApproach.getText().length() == 0||!isNumeric(exerciseApproach.getText())||!(Integer.parseInt(exerciseApproach.getText())>0)) {
            errorMessage += "No valid approach!\n";
        }
        if (exerciseQuantity.getText() == null || exerciseQuantity.getText().length() == 0||!isNumeric(exerciseQuantity.getText())||!(Integer.parseInt(exerciseQuantity.getText())>0)) {
            errorMessage += "No valid Quantity!\n";
        }

        if (exerciseWeight.getText() == null || exerciseWeight.getText().length() == 0||!isNumeric(exerciseWeight.getText())||!(Integer.parseInt(exerciseWeight.getText())>0)) {
            errorMessage += "No valid Weight!\n";
        }



        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Показываем сообщение об ошибке.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

    @FXML
    private void create(){
        if (isInputValid()) {
            training.setDate(datePicker.getValue());

            fits.add(new Fit(exerciseName.getText(), Integer.valueOf(exerciseApproach.getText()), Integer.valueOf(exerciseQuantity.getText()), Integer.valueOf(exerciseWeight.getText())));
            training.setFits(fits);
            dialogStage.close();
        }
    }

    @FXML
    private void next(){
        if (isInputValid()) {
            training.setDate(datePicker.getValue());
            fits.add(new Fit(exerciseName.getText(), Integer.valueOf(exerciseApproach.getText()), Integer.valueOf(exerciseQuantity.getText()), Integer.valueOf(exerciseWeight.getText())));
            training.setFits(fits);

            exerciseName.setText("");
            exerciseApproach.setText("");
            exerciseQuantity.setText("");
            exerciseWeight.setText("");
        }
    }
}
