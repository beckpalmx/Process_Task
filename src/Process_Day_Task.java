/**
 * Created by beckp on 3/10/2558.
 */
import com.cgc.Util.DateUtil;
import com.cgc.Util.OS_Type;
import com.cgc.Util.PeriodDate;
import com.cgc.engine.Process_transaction_rawmat_friction_summary;
import com.cgc.engine.Process_transaction_wh_summary;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Process_Day_Task {

    public static void main(String args[]) {
        System.out.println("Test 1");
        Process_Transaction();
        System.out.println("Test End");

    }

    private static void Process_Transaction() {
        try {

            System.out.println("Test 2");

            Process_transaction_rawmat_friction_summary objTrans_raw = new Process_transaction_rawmat_friction_summary();
            System.out.println("Test 3");
            Process_transaction_wh_summary objTrans_wh = new Process_transaction_wh_summary();
            System.out.println("Test 4");

            String username = "System", process_for;
            System.out.println("Test 5");
            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

            OS_Type os_type = new OS_Type();

            DateUtil ObjDate = new DateUtil();
            PeriodDate period = new PeriodDate();
            String current_date = ObjDate.Return_Date_Now_full();
            String current_month = ObjDate.Return_Month_Now();
            String current_year = ObjDate.Return_Year_Now();

            System.out.println("Test 6" + current_month);

            String date_from = period.Start_Current_Month(current_month);

            //String date_from = "01-10-2558" ;

            System.out.println("Test 7");

            String date_to = ObjDate.Return_Date_Now_full();

            //String date_to = period.End_Current_Month(current_month);

            System.out.println("Test 8");

            if (os_type.GetOS_Type("Y").equals("WIN")) {
                System.out.println("Y date_to = " + date_to);
            } else {
                date_to = ObjDate.EngDate_To_ThaiDate(ObjDate.Return_Date_Now_full());
                System.out.println("N date_to = " + date_to);
            }

            System.out.println("current_month : " + current_month);
            System.out.println("current_year : " + current_year);

            System.out.println("Start Process Date : " + new Timestamp(new java.util.Date().getTime()));

            process_for = "RAWMAT_FRICTION";

            objTrans_raw.main_check(date_from, date_to, username, process_for);

            process_for = "WAREHOUSE";

            objTrans_wh.main_check(date_from, date_to, username, process_for);

            System.out.println("End Process Date : " + new Timestamp(new java.util.Date().getTime()));


        } catch (Exception ex) {
            System.out.println("ERROR");
        }

    }
}
