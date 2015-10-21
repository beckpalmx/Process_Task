package com.cgc.Util;

/**
 * Created by beckp on 14/10/2558.
 */

import com.sun.org.apache.bcel.internal.generic.RETURN;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Network_Util {

    public String PingNet(String ip,String Package) throws Exception {

        String pingResult = "";

        String pingCmd = "ping " + ip + " -t -l " + Package ;

        try {
            Runtime r = Runtime.getRuntime();
            Process p = r.exec(pingCmd);

            BufferedReader in = new BufferedReader(new
                    InputStreamReader(p.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
                pingResult += inputLine;
            }
            in.close();

        } catch (IOException e) {
            System.out.println(e);
        }

        return "True";

    }


}
