package ru.malishev.notes.objects;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * JavaFXMavenSQLite. "Notes"
 * @author Aleksey Malyshev
 * @version 1.0 dated April 08, 2018
 */
public class DateNote {

    public String getDate(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E. dd:MM:yyyy, kk:mm");
        return simpleDateFormat.format(date);
    }

    public String getDateMilliseconds(long dateMilliseconds){
        Date date = new Date(dateMilliseconds);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E. dd:MM:yyyy, kk:mm");
        return simpleDateFormat.format(date);
    }
}
