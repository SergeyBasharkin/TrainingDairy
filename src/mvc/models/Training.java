package mvc.models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.input.DataFormat;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by sergey on 18.05.16.
 */
public class Training {
    private final ObjectProperty<LocalDate> date;
    private final ObjectProperty<ArrayList<Fit>> fits;

    public LocalDate getDate() {
        return date.get();
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public ArrayList<Fit> getFits() {
        return fits.get();
    }

    public ObjectProperty<ArrayList<Fit>> fitsProperty() {
        return fits;
    }

    public void setFits(ArrayList<Fit> fits) {
        this.fits.set(fits);
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }

    public Training(ArrayList<Fit> fits) {
        this.date = new SimpleObjectProperty<LocalDate>(LocalDate.now());
        this.fits = new SimpleObjectProperty<ArrayList<Fit>>(fits);
    }

    public Training() {
        date=null;
        fits=new SimpleObjectProperty<>();

    }

}
