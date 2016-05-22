package mvc.models;


import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by Admin on 20.05.2016.
 */
@XmlRootElement(name = "trains")
@XmlSeeAlso({Fit.class})
@XmlAccessorType(XmlAccessType.FIELD)
public class NodesFileWrapper {
    @XmlElement(name = "training")
    private List<Training> trains;

    public List getTrains(){
        return trains;
    }

    public void setTrains(List<Training> trains) {
        this.trains = trains;
    }
}
