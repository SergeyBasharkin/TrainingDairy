package mvc.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;

/**
 * Created by sergey on 18.05.16.
 */
public class Fit {

    private final StringProperty name;
    private final IntegerProperty podhod;
    private final IntegerProperty povtor;
    private final IntegerProperty ves;

    public Fit(String name) {
        this.name = new SimpleStringProperty(name);
        this.podhod=new SimpleIntegerProperty(0);
        this.povtor=new SimpleIntegerProperty(0);
        this.ves=new SimpleIntegerProperty(0);
    }

    public Fit() {
        this(null);

    }

    public String getName() {

        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getPodhod() {
        return podhod.get();
    }

    public IntegerProperty podhodProperty() {
        return podhod;
    }

    public void setPodhod(int podhod) {
        this.podhod.set(podhod);
    }

    public int getPovtor() {
        return povtor.get();
    }

    public IntegerProperty povtorProperty() {
        return povtor;
    }

    public void setPovtor(int povtor) {
        this.povtor.set(povtor);
    }

    public int getVes() {
        return ves.get();
    }

    public IntegerProperty vesProperty() {
        return ves;
    }

    public void setVes(int ves) {
        this.ves.set(ves);
    }

    @Override
    public String toString() {
        return name.getValue();
    }
}
