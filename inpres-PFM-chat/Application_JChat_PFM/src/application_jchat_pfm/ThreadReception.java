/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application_jchat_pfm;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author Jerome
 */
public class ThreadReception extends Thread{
    
    private MulticastSocket socketGroupe;
    private ArrayList<message> listMessage;
    private jchat_GUI GUI;
    
    public ThreadReception(MulticastSocket s, ArrayList<message> m, jchat_GUI j)
    {
        socketGroupe = s;
        listMessage = m;
        GUI = j;
    }
    
    public void run()
    {
        while(!isInterrupted())
        {
            byte[]buf = new byte[1000];
            DatagramPacket dtg = new DatagramPacket(buf, buf.length);
            try {
                socketGroupe.receive(dtg);
            } catch (IOException ex) {
                System.err.println("Erreur de réception " + ex);
            }
            
            String str = new String(buf).trim();
            String[] parts = str.split("#");
            
            message msg = new message(parts[2], parts[1], parts[0]);
            listMessage.add(msg);
            GUI.refreshDisplay();
        }
    }
}