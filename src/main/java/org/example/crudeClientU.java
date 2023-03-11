package org.example;

import java.io.*;
import java.net.*;
import java.util.*;

public class crudeClientU {

    public static void main(String argv[]) {
        Scanner in = new Scanner(System.in);
        String startSyntax     = "helo martin";
        String senderSyntax    = "mail from: ";
        String recipientSyntax = "rcpt to: ";
        String messageSyntax   = "data";
        String quitSyntax      = "quit";

        try {

            Socket socketClient = new Socket("datacomm.bhsi.xyz", 2552);

            System.out.println("[CONNECTION] Connection Initiating...\n");

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(socketClient.getInputStream()));

            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));

            String s, serverMsg;

            while ((serverMsg = reader.readLine()) != null) {
                System.out.println(serverMsg);
                if (serverMsg.startsWith("220 Welcome")) {
                    System.out.println("[SERVER RESPONSE] Connection Established\n");
                    writer.write(startSyntax + "\r\n");
                    writer.flush();

                } else if (serverMsg.startsWith("250 comit.dev")) {
                    System.out.println("[SERVER RESPONSE] Successful Login");
                    System.out.println("\nPlease enter the sender email address");
                    String senderInput = in.nextLine();
                    writer.write(senderSyntax + senderInput + "\r\n");
                    writer.flush();

                } else if (serverMsg.startsWith("250 2.1.0")) {
                    System.out.println("\nPlease enter a recipient email address");
                    String recipientInput = in.nextLine();
                    writer.write(recipientSyntax + recipientInput + "\r\n");
                    writer.flush();

                } else if (serverMsg.startsWith("250 2.1.5")) {
                    writer.write(messageSyntax + "\r\n");
                    writer.flush();
                    System.out.print("\n");

                } else if (serverMsg.startsWith("354")) {
                    System.out.println("[SERVER RESPONSE] Sender and Recipient Email Addresses Confirmed");
                    System.out.println("\n[SERVER RESPONSE] Proceed with the Email Content");

                    // taking input for mail subject
                    System.out.println("Please enter the mail subject");
                    Scanner subject = new Scanner(System.in);
                    String mailSubject = subject.nextLine();

                    // taking input for mail content
                    System.out.println("\nPlease enter the mail content");
                    System.out.println("End the message with a \".\"");
                    Scanner body = new Scanner(System.in);
                    String mailContent = "";

                    boolean inputMessage = true;
                    while(inputMessage) {
                        if (body.hasNext(".")) {
                            inputMessage = false;
                        }
                        mailContent = mailContent + "\n" + body.nextLine();
                    }
                    String mail = "Subject: " + mailSubject + "\n" + mailContent;

                    writer.write(mail + "\r\n");
                    writer.flush();
                    System.out.print("\n");

                } else if (serverMsg.startsWith("250 2.0.0 Ok: queued")) {
                    writer.write(quitSyntax + "\r\n");
                    writer.flush();
                    System.out.println("[SERVER RESPONSE] Success! Email is being sent out to Recipient");
                    System.out.print("\n");

                } else if (serverMsg.startsWith("221 2.0.0")) {
                    System.out.println("[CONNECTION] Connection Terminated");

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