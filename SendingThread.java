/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing.threads;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Tim
 */
public class SendingThread implements Runnable {

    private Thread t;
    private String threadName;
    private Socket server;
    private OutputStream os;
    private OutputStreamWriter osw;
    private BufferedWriter bw;

    SendingThread(String name, Socket server) throws IOException {
        threadName = name;
        System.out.println("Creating " + threadName);
        this.server = server;
        try {
            OutputStream os = this.server.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            bw = new BufferedWriter(osw);
        } catch (IOException e) {

        } 
    }

    public void run() {
        System.out.println("Running " + threadName);
        try {
            while (true) {

                // Let the thread sleep for a while. 
                    Thread.sleep(50);
                System.out.print(">> ");
                Scanner input = new Scanner(System.in);
                String message = input.nextLine();
                bw.write(message + "\n");
                bw.flush();
                Thread.sleep(50); 
                
            }
        } catch (InterruptedException e) {
            System.out.println("Thread " + threadName + " interrupted.");
        }
        catch(IOException e){
            System.out.println("IO Exception");
        }
        System.out.println("Thread " + threadName + " exiting.");
    }//end of run method

    public void start() {
        System.out.println("Starting " + threadName);
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }//end of start method

}
