package org.example;

import java.io.*;
import java.net.*;
import java.util.*;

public class crudeClientU {

    public static void main(String argv[])
    {
        Scanner in = new Scanner(System.in);

        try{
            Socket socketClient= new Socket("datacomm.bhsi.xyz",2526);

            System.out.println("Client: "+"Connection Established");

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(socketClient.getInputStream()));

            BufferedWriter writer=
                    new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));

            String s,serverMsg;


            while((serverMsg = reader.readLine()) != null){
                System.out.println(serverMsg);

                if(serverMsg.startsWith("354")){
                    s = in.nextLine();
                    writer.write(s + "\r\n");
                    writer.flush();

                    s = in.nextLine();
                    writer.write(s + "\r\n");
                    writer.flush();

                    s = in.nextLine();
                    writer.write(s + "\r\n");
                    writer.flush();

                    s = in.nextLine();
                    writer.write(s + "\r\n");
                    writer.flush();

                    s = in.nextLine();
                    writer.write(s + "\r\n");
                    writer.flush();

                    s = in.nextLine();
                    writer.write(s + "\r\n");
                    writer.flush();


                }
                else {
                    s = in.nextLine();
                    writer.write(s + "\r\n");
                    writer.flush();
                }


            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}


