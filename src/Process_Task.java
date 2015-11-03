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

                Double D_hour_minute = new Double(now.get(Calendar.HOUR_OF_DAY)) + (new Double(now.get(Calendar.MINUTE)) / 100);

                //System.out.println("Before D_hour_minute " + D_hour_minute);

                if (D_hour_minute >= 8.00 && D_hour_minute <= 17.00) {

                    Rotation_log();

                    Write_log("------------------------------------------------");

                    Write_log("Condition = True And D_hour_minute = " + D_hour_minute);

                    Process_Transaction();

                } else {

                    Write_log("***** Out of work time " + D_hour_minute);

                }

            }
        }, 0, 950000);

    }

    private static void Process_Transaction() {

        try {

            File source = new File("\\\\BW-RAWMAT\\c\\AppServ\\MySQL\\data\\genius_data");

            File dest = new File("D:\\AppServ\\MySQL\\data\\genius_data");

            File file_del = new File("D:\\AppServ\\MySQL\\data\\genius_data");

            try {

                //FileUtils.deleteDirectory(file_del);

                if (source.exists() && source.isDirectory()) {
                    Stop_mySQl();
                    Write_log("Start Copy File ... From : " + source + " : " + new Timestamp(new java.util.Date().getTime()).toString());
                    FileUtils.copyDirectory(source, dest);
                    Start_mySQl();
                    Write_log("End Copy File ... To : " + dest + " : " + new Timestamp(new java.util.Date().getTime()).toString());
                } else {
                    Write_log("Folder Not Found : " + new Timestamp(new java.util.Date().getTime()).toString());
                }

            } catch (IOException e) {
                e.printStackTrace();
                Write_log("Special Case : " + new Timestamp(new java.util.Date().getTime()).toString());
            }

        } catch (Exception ex) {
            Write_log("ERROR : " + new Timestamp(new java.util.Date().getTime()).toString());
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


    private static void Rotation_log() {

        Calendar calendar = Calendar.getInstance();

        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        int month = calendar.get(Calendar.MONTH) + 1; // Jan = 0, dec = 11
        int year = calendar.get(Calendar.YEAR);

        Double D_hour_minute = new Double(calendar.get(Calendar.HOUR_OF_DAY)) + (new Double(calendar.get(Calendar.MINUTE)) / 100);

        System.out.println(Integer.toString(dayOfMonth) + "-" + Integer.toString(month) + "-" + Integer.toString(year));

        File oldfile = new File("D:\\weight_imp_process\\Log\\process_log_weight.txt");
        File newfile = new File("D:\\weight_imp_process\\Log\\log-backup_"
                + Integer.toString(dayOfMonth) + "-" + Integer.toString(month) + "-" + Integer.toString(year) + "_" + Double.toString(D_hour_minute) + ".txt");

        File check_oldfile = new File("D:\\weight_imp_process\\Log\\LogProcessCheck.txt");
        File check_newfile = new File("D:\\weight_imp_process\\Log\\LogProcessCheck-backup_"
                + Integer.toString(dayOfMonth) + "-" + Integer.toString(month) + "-" + Integer.toString(year) + "_" + Double.toString(D_hour_minute) + ".txt");

        System.out.println("oldfile = " + oldfile);
        System.out.println("newfile = " + newfile);

        if (oldfile.length() >= 20000) {
            if (oldfile.renameTo(newfile)) {
                Write_log("");
                System.out.println("Rename succesful");
            } else {
                System.out.println("Rename failed");
            }
        }

        if (check_oldfile.length() >= 20000) {
            if (check_oldfile.renameTo(check_newfile)) {
                //Write_log("");
                System.out.println("LogProcessCheck Rename succesful");
            } else {
                System.out.println("LogProcessCheck Rename failed");
            }
        }


    }

    private static void Write_log(String msg) {

        try {

            if (!msg.equals("") || msg != null) {
                msg = msg + System.getProperty("line.separator");
                System.out.println("msg =" + msg);
            }

            File file = new File("D:\\weight_imp_process\\Log\\process_log_weight.txt");

            //if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            //true = append file
            FileWriter fileWritter = new FileWriter(file, true);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            bufferWritter.write(msg);
            bufferWritter.close();

            //System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
