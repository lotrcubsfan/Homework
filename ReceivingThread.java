/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing.threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Tim
 */
public class ReceivingThread implements Runnable {

    private Thread t;
    private String threadName;
    private Socket server;
    private InputStream is;
    private InputStreamReader isr;
    private BufferedReader br;

    ReceivingThread(String name, Socket server) throws IOException {
        threadName = name;
        System.out.println("Creating " + threadName);
        this.server = server;
        try {
            InputStream is = this.server.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
        } catch (IOException e) {
            System.out.println("IO Exception");
        } 
    }

    public void run() {
        System.out.println("Running " + threadName);
        try {
            while (true) { 
                // Let the thread sleep for a while. 
//                Thread.sleep(50);
                String fromServer = br.readLine();
                if(fromServer != null){
                    System.out.println("From Server: " + fromServer); ;
                } 
            }
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
