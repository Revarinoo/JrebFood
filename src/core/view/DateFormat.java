package core.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
 
import javax.swing.JFormattedTextField.AbstractFormatter;
 
public class DateFormat extends AbstractFormatter {
 
    private String Pattern = "yyyy-MM-dd";
    private SimpleDateFormat dateFormat = new SimpleDateFormat(Pattern);
     
    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormat.parseObject(text);
    }
 
    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormat.format(cal.getTime());
        }
        else return null;
    }
 
}
