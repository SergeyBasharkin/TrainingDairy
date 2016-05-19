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
    private final IntegerProperty approach;
    private final IntegerProperty quantity;
    private final IntegerProperty weight;

    public Fit(String name) {
        this.name = new SimpleStringProperty(name);
        this.approach=new SimpleIntegerProperty(0);
        this.quantity=new SimpleIntegerProperty(0);
        this.weight= new SimpleIntegerProperty(0);
    }

    public Fit(String name, Integer approach, Integer quantity, Integer weight) {
        this.name = new SimpleStringProperty(name);
        this.approach = new SimpleIntegerProperty(approach);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.weight = new SimpleIntegerProperty(weight);
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

    public int getApproach() {
        return approach.get();
    }

    public IntegerProperty approachProperty() {
        return approach;
    }

    public void setApproach(int approach) {
        this.approach.set(approach);
    }

    public int getQuantity() {
        return quantity.get();
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public int getWeight() {
        return weight.get();
    }

    public IntegerProperty weightProperty() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight.set(weight);
    }

    @Override
    public String toString() {
        return name.getValue();
    }
}
