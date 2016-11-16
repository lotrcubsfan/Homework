/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing.threads;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tim
 */
public class TestingThreads {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SendingThread t1;
        
        ReceivingThread t2;
        try {
            Socket server = new Socket("localhost", 45322);
            t1 = new SendingThread("Thread 1", server);
            t1.start();
            t2 = new ReceivingThread("Thread 2", server);
            t2.start();
        } catch (IOException ex) {
            Logger.getLogger(TestingThreads.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
}
