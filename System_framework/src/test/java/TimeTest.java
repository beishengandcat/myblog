import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 功能描述：
 *
 * @Author: 李振
 * @Date: 2022/12/15 9:43
 **/

public class TimeTest {

    public static String dateStringYYYYMMDDHHMMSS(Date datetime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return simpleDateFormat.format(datetime);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Test
    public void test1(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(System.currentTimeMillis());
        System.out.println(format);
    }
}
