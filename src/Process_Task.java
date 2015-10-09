/**
 * Created by beckp on 3/10/2558.
 */


import com.cgc.Util.CheckTime;
import org.apache.commons.io.FileUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import java.lang.*;

public class Process_Task {

    public static void main(String args[]) {

        //System.out.println("Create Service ... ");

        Write_log("Create Service ... ");

        Timer myTimer;
        myTimer = new Timer();
        final CheckTime ObjTime = new CheckTime();
        myTimer.schedule(new TimerTask() {

            @Override
            public void run() {

                Calendar now = Calendar.getInstance();

                Double D_hour_minute = new Double(now.get(Calendar.HOUR_OF_DAY)) + (new Double(now.get(Calendar.MINUTE))/100);

                //System.out.println("Before D_hour_minute " + D_hour_minute);

                if (D_hour_minute >= 8.00 && D_hour_minute <= 17.00) {

                    Write_log("------------------------------------------------");

                    Write_log("Condition = True And D_hour_minute = " + D_hour_minute);

                    //System.out.println("In Loop D_hour_minute " + D_hour_minute);

                    Stop_mySQl();

                    Process_Transaction();

                    Start_mySQl();
                }
            }
        }, 0, 950000);

    }

    private static void Process_Transaction() {

        try {

            //File source = new File("S:\\AppServ\\MySQL\\data\\genius_data");

            File source = new File("\\\\BW-RAWMAT\\c\\AppServ\\MySQL\\data\\genius_data");

            File dest = new File("D:\\AppServ\\MySQL\\data\\genius_data");

            File file_del = new File("D:\\AppServ\\MySQL\\data\\genius_data");

            String content = "Process Time ";

            String result_process = "False";

            //File source = new File("S:\\OtherSource\\MySQL-DATA");
            //File dest = new File("D:\\Temp\\MySQL\\DATA");


            try {

                //String msg = "Start Copy File ... From : " + source + " : " + new Timestamp(new java.util.Date().getTime()).toString();

                //System.out.println(msg1);

                Write_log("Start Copy File ... From : " + source + " : " + new Timestamp(new java.util.Date().getTime()).toString());

                //FileUtils.deleteDirectory(file_del);

                FileUtils.copyDirectory(source, dest);

                //msg = "End Copy File ... To : " + dest + " : " + new Timestamp(new java.util.Date().getTime()).toString();

                //System.out.println(msg2);

                Write_log("End Copy File ... To : " + dest + " : " + new Timestamp(new java.util.Date().getTime()).toString());

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception ex) {
            Write_log("ERROR");
        }

    }

    private static void Write_log(String msg) {

        try {

            String data = msg + System.getProperty("line.separator");

            File file = new File("D:\\process_log_weight.txt");

            //if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            //true = append file
            FileWriter fileWritter = new FileWriter(file, true);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            bufferWritter.write(data);
            bufferWritter.close();

            //System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static void Stop_mySQl() {

        try {
            Process process1 = Runtime.getRuntime().exec("net STOP MySQL");
            //System.out.println("STOP MySQL..");
            Write_log("STOP MySQL.. " + new Timestamp(new java.util.Date().getTime()).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void Start_mySQl() {

        try {
            Process process1 = Runtime.getRuntime().exec("net START MySQL");
            //System.out.println("START MySQL..");
            Write_log("START MySQL.. " + new Timestamp(new java.util.Date().getTime()).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
