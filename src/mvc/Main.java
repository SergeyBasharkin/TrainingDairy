package mvc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mvc.models.Training;
import mvc.view.Controller;
import mvc.view.TrainingCreateController;
import mvc.view.TrainingDetailsController;

import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;
    private Controller controller;
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

        showPersonOverview();
    }
    public void showPersonOverview() throws IOException {
            // Загружаем сведения об адресатах.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/sample.fxml"));
        AnchorPane personOverview = (AnchorPane) loader.load();
        Scene scene = new Scene(personOverview);
        primaryStage.setScene(scene);
        primaryStage.show();
        controller = loader.getController();
        controller.setMainApp(this);

    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void showPersonEditDialog(Training training) {
        // Загружаем fxml-файл и создаём новую сцену
        // для всплывающего диалогового окна.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/TrainingCreateDialog.fxml"));
        AnchorPane page = null;
        try {
            page = (AnchorPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }



        // Создаём диалоговое окно Stage.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Edit Person");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Передаём адресата в контроллер.
        TrainingCreateController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setTraining(training);

        // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
        dialogStage.showAndWait();

    }

    public void showDetailsDialog(Training training){
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/TrainingDetailDialog.fxml"));
        AnchorPane pane=null;
        try {
            pane=loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage dialogStage=new Stage();
        dialogStage.setTitle("Training Details");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene=new Scene(pane);
        dialogStage.setScene(scene);

        TrainingDetailsController controller=loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setTraining(training);

        dialogStage.showAndWait();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
