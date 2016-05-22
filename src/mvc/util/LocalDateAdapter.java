package mvc.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

/**
 * Created by Admin on 20.05.2016.
 */
public class LocalDateAdapter extends XmlAdapter {
    @Override
    public LocalDate unmarshal(Object v) throws Exception {
        return LocalDate.parse((CharSequence) v);
    }

    @Override
    public String marshal(Object v) throws Exception {
        return v.toString();
    }
}
