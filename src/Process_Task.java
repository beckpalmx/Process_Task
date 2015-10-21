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

<<<<<<< HEAD
=======
import java.lang.*;

>>>>>>> origin/master
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

<<<<<<< HEAD
                Double D_hour_minute = new Double(now.get(Calendar.HOUR_OF_DAY)) + (new Double(now.get(Calendar.MINUTE)) / 100);
=======
                Double D_hour_minute = new Double(now.get(Calendar.HOUR_OF_DAY)) + (new Double(now.get(Calendar.MINUTE))/100);
>>>>>>> origin/master

                //System.out.println("Before D_hour_minute " + D_hour_minute);

                if (D_hour_minute >= 8.00 && D_hour_minute <= 17.00) {

<<<<<<< HEAD
                    Rotation_log();

=======
>>>>>>> origin/master
                    Write_log("------------------------------------------------");

                    Write_log("Condition = True And D_hour_minute = " + D_hour_minute);

<<<<<<< HEAD
                    Process_Transaction();

                } else {

                    Write_log("***** Out of work time " + D_hour_minute);

                }

=======
                    //System.out.println("In Loop D_hour_minute " + D_hour_minute);

                    Stop_mySQl();

                    Process_Transaction();

                    Start_mySQl();
                }
>>>>>>> origin/master
            }
        }, 0, 950000);

    }

    private static void Process_Transaction() {

        try {

<<<<<<< HEAD
=======
            //File source = new File("S:\\AppServ\\MySQL\\data\\genius_data");

>>>>>>> origin/master
            File source = new File("\\\\BW-RAWMAT\\c\\AppServ\\MySQL\\data\\genius_data");

            File dest = new File("D:\\AppServ\\MySQL\\data\\genius_data");

            File file_del = new File("D:\\AppServ\\MySQL\\data\\genius_data");

<<<<<<< HEAD
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

        if (oldfile.length() >= 500000) {
            if (oldfile.renameTo(newfile)) {
                Write_log("");
                System.out.println("Rename succesful");
            } else {
                System.out.println("Rename failed");
            }
        }

        if (check_oldfile.length() >= 500000) {
            if (check_oldfile.renameTo(check_newfile)) {
                //Write_log("");
                System.out.println("LogProcessCheck Rename succesful");
            } else {
                System.out.println("LogProcessCheck Rename failed");
            }
        }


=======
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

>>>>>>> origin/master
    }

    private static void Write_log(String msg) {

        try {

<<<<<<< HEAD
            if (!msg.equals("") || msg != null) {
                msg = msg + System.getProperty("line.separator");
                System.out.println("msg =" + msg);
            }

            File file = new File("D:\\weight_imp_process\\Log\\process_log_weight.txt");
=======
            String data = msg + System.getProperty("line.separator");

            File file = new File("D:\\process_log_weight.txt");
>>>>>>> origin/master

            //if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            //true = append file
            FileWriter fileWritter = new FileWriter(file, true);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
<<<<<<< HEAD
            bufferWritter.write(msg);
=======
            bufferWritter.write(data);
>>>>>>> origin/master
            bufferWritter.close();

            //System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        }

<<<<<<< HEAD
    }

=======

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
>>>>>>> origin/master
}
