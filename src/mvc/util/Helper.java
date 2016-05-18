package mvc.util;

import javafx.collections.ObservableList;
import mvc.models.Fit;
import mvc.models.Training;

import java.util.ArrayList;

/**
 * Created by sergey on 18.05.16.
 */
public class Helper {
    public static void setData(ObservableList<Training> list){
        ArrayList<Fit> fits=new ArrayList<>();
        fits.add(new Fit("train1"));
        fits.add(new Fit("train2"));
        fits.add(new Fit("train3"));
        fits.add(new Fit("train4"));
        list.add(new Training(fits));
        list.add(new Training(fits));
    }
}
