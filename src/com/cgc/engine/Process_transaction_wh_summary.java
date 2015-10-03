/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cgc.engine;

import com.cgc.DB.Process_transaction_wh_summaryDB;
import java.util.Random;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;


public class Process_transaction_wh_summary {

    /**
     * @param date_from
     * @param date_to
     * @param username
     * @param process_for
     * @return 
     * @throws java.lang.Exception
     */
    public String main_check(String date_from, String date_to, String username, String process_for) throws Exception {
        StringBuilder String_return = new StringBuilder();

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss");

        Timestamp cur_time = new Timestamp(new java.util.Date().getTime());
        String start_time = sdf.format(cur_time);

        try {
            Random r = new Random();
            Process_transaction_wh_summaryDB obj = new Process_transaction_wh_summaryDB();
            String r_create = Long.toString(Math.abs(r.nextLong()), 36);
            //เรียกใช้งานให้ส่ง Parameter ตามนี้ obj.generater_transaction_process(date_from, date_to,process_id,table,doc_type); 

            if (process_for.equals("WAREHOUSE")) {

                System.out.println("Begin WAREHOUSE ");

                obj.generater_transaction_process(date_from, date_to, "PR_WH_STOCK_VALUE", "vt_transaction_stock_process_report_wh", "*", r_create, username);

                System.out.println("End WAREHOUSE ");
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        //String_return.append(" ");

        //Timestamp stop_time = new Timestamp(new java.util.Date().getTime());
        Timestamp cur_time2 = new Timestamp(new java.util.Date().getTime());
        String stop_time = sdf.format(cur_time2);

        System.out.println("END Transaction Process ... " + '\n');
        String_return.append("ประมวลผลเสร็จสิ้น " + '\n');
        String_return.append("เริ่มประมวลผลเวลา : ").append(start_time).append('\n');
        String_return.append("เสร็จสิ้นเวลา :           ").append(stop_time);
        return String_return.toString();

    }
}
