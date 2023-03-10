package org.example;

import java.io.*;
import java.net.*;
import java.util.*;

public class crudeClientU {

    public static void main(String argv[]) {
        Scanner in = new Scanner(System.in);
        String M = "helo martin" + "\r\n";
        String mailCommand = "mail from: ";
        String mailrecp = "rcpt to: <";


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
                } else if ((serverMsg.equals("250 Nice to meet you"))) {
                    System.out.println("Please enter your Email adress:");
                    s = in.nextLine();
                    //writer.write(s);
                    writer.write(mailCommand + s + "\r\n");
                    //writer.write(mailCommand + s + "\r\n" );


                    writer.flush();

                } else if ((serverMsg.equals("250 New message started"))) {
                    System.out.println("Please enter a desired email receptient:");
                    String b = in.nextLine();
                    //writer.write(s);
                    String mailrep = "rcpt to: ";
                    String kartoffel = mailrecp + b + ">";
                    writer.write(kartoffel + "\r\n");

                    writer.newLine();

                    writer.flush();

                } else if (serverMsg.startsWith("354")) {

                    boolean run = true;

                    while (run) {
                        if (in.hasNext(".")) {
                            run = false;
                            writer.flush();
                        }

                        if (in.hasNext("\r\n")) {
                            s = "";
                            writer.write(s + "\r\n");
                            writer.flush();
                        }

                        s = in.nextLine();
                        writer.write(s + "\r\n");
                        writer.flush();
                    }
                } else {
                    s = in.nextLine();
                    writer.write(s + "\r\n");
                    writer.flush();
                }
            }

        } catch (UnknownHostException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}