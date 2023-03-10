package org.example;

import java.io.*;
import java.net.*;
import java.util.*;

public class crudeClientU {

    public static void main(String argv[]) {
        Scanner in = new Scanner(System.in);
        String M = "helo martin" +"\r\n" ;

        try {
            Socket socketClient = new Socket("datacomm.bhsi.xyz", 2526);

            System.out.println("Client: " + "Connection Established");

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(socketClient.getInputStream()));

            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));

            String s, serverMsg;


            while ((serverMsg = reader.readLine()) != null) {
                System.out.println(serverMsg);
               if ((serverMsg.startsWith("220"))) {

                    writer.write(M);

                    writer.flush();

                    //System.out.println(s + "k");
                }
                if ((serverMsg.equals("250 Nice to meet you"))) {
                    System.out.println("Please enter your Email adress:");
                    s = in.nextLine();
                    String mailCommand ="mail from:";
                    writer.write(mailCommand + s );

                    //writer.flush();
                }
                if ((serverMsg.equals("250 New message started"))) {
                    System.out.println("Please enter a desired email receptient:");
                    String B = in.nextLine();
                    String mailCommand ="rcpt to :";
                    writer.write( mailCommand + "<" + B + ">");
                    //System.out.println(B);
                    //writer.flush();
                }


                if (serverMsg.startsWith("354")) {
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


                } else {
                    s = in.nextLine();
                    writer.write(s + "\r\n");
                    writer.flush();
                }



            }

            } catch(UnknownHostException ex){
                throw new RuntimeException(ex);
            } catch(IOException ex){


            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }



