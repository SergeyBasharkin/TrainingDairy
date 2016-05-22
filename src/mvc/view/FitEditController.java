package mvc.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mvc.models.Fit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Admin on 20.05.2016.
 */
public class FitEditController {

    @FXML
    private TextField exerciseName;
    @FXML
    private TextField exerciseApproach;
    @FXML
    private TextField exerciseQuantity;
    @FXML
    private TextField exerciseWeight;

    private Stage dialogStage;
    private Fit fit;

    public static boolean isNumeric(String x)
    {
        Pattern p = Pattern.compile("^\\d+(?:\\.\\d+)?$");
        Matcher m = p.matcher(x);
        return m.matches();
    }

    public boolean isInputValid(){
        String errorMessage = "";

        if (exerciseName.getText() == null || exerciseName.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (exerciseApproach.getText() == null || exerciseApproach.getText().length() == 0||!isNumeric(exerciseApproach.getText())||!(Integer.parseInt(exerciseQuantity.getText())>0)) {
            errorMessage += "No valid approach!\n";
        }
        if (exerciseQuantity.getText() == null || exerciseQuantity.getText().length() == 0||!isNumeric(exerciseQuantity.getText())||!(Integer.parseInt(exerciseQuantity.getText())>0)) {
            errorMessage += "No valid Quantity!\n";
        }

        if (exerciseWeight.getText() == null || exerciseWeight.getText().length() == 0||!isNumeric(exerciseWeight.getText())||!(Integer.parseInt(exerciseQuantity.getText())>0)) {
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
    private void Ok(){
        if(isInputValid()) {
            fit.setName(exerciseName.getText());
            fit.setApproach(Integer.parseInt(exerciseApproach.getText()));
            fit.setQuantity(Integer.parseInt(exerciseQuantity.getText()));
            fit.setWeight(Integer.parseInt(exerciseWeight.getText()));
            dialogStage.close();
        }
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


    public void setFit(Fit fit) {
        this.fit = fit;

        exerciseName.setText(fit.getName());
        exerciseApproach.setText(String.valueOf(fit.getApproach()));
        exerciseQuantity.setText(String.valueOf(fit.getQuantity()));
        exerciseWeight.setText(String.valueOf(fit.getWeight()));
    }
}
