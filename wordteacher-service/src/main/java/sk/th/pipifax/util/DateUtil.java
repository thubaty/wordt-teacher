package sk.th.pipifax.util;


import java.sql.Timestamp;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: tohy
 * Date: 17.11.13
 * Time: 16:51
 * To change this template use File | Settings | File Templates.
 */
public class DateUtil {

    public static Timestamp getCurrentDate() {
        return new Timestamp(new Date().getTime());
    }
}
