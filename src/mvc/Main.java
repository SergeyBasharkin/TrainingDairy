package mvc;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mvc.models.Fit;
import mvc.models.NodesFileWrapper;
import mvc.models.Training;
import mvc.view.Controller;
import mvc.view.FitEditController;
import mvc.view.TrainingCreateController;
import mvc.view.TrainingDetailsController;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

public class Main extends Application {

    private Stage primaryStage;
    private Controller controller;

    private ObservableList<Training> trainingsData= FXCollections.observableArrayList();

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

        File file = getTrainsFilePath();
        if (file != null) {
            loadTrainsDataFromFile(file);
        }
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

    public void showFitEditDialog(Fit fit){
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/FitEditDialog.fxml"));
        AnchorPane pane=null;
        try {
            pane=loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage dialogStage=new Stage();
        dialogStage.setTitle("Edit Fit");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene=new Scene(pane);
        dialogStage.setScene(scene);

        FitEditController controller=loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setFit(fit);
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
        controller.setMainApp(this);
        dialogStage.showAndWait();
    }

    public File getTrainsFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    /**
     * Задаёт путь текущему загруженному файлу. Этот путь сохраняется
     * в реестре, специфичном для конкретной операционной системы.
     *
     * @param file - файл или null, чтобы удалить путь
     */
    public void setTrainsFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

            // Обновление заглавия сцены.
            primaryStage.setTitle("AddressApp - " + file.getName());
        } else {
            prefs.remove("filePath");

            // Обновление заглавия сцены.
            primaryStage.setTitle("AddressApp");
        }
    }
    public void loadTrainsDataFromFile(File file){
        try {
            JAXBContext context=JAXBContext.newInstance(NodesFileWrapper.class);
            Unmarshaller um=context.createUnmarshaller();

            NodesFileWrapper wrapper= (NodesFileWrapper) um.unmarshal(file);

            trainingsData.clear();
            trainingsData.addAll(wrapper.getTrains());

            setTrainsFilePath(file);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load data");
            alert.setContentText("Could not load data from file:\n" + file.getPath());

            alert.showAndWait();
        }
    }

    public void saveTrainsDataToFile(File file){
        try {
            JAXBContext context=JAXBContext.newInstance(NodesFileWrapper.class);
            Marshaller m=context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);

            NodesFileWrapper wrapper=new NodesFileWrapper();
            wrapper.setTrains(trainingsData);

            m.marshal(wrapper,file);

            setTrainsFilePath(file);
        } catch (JAXBException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not save data");
            alert.setContentText("Could not save data to file:\n" + file.getPath());

            alert.showAndWait();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public ObservableList<Training> getTrainsData() {
        return trainingsData;
    }
}
