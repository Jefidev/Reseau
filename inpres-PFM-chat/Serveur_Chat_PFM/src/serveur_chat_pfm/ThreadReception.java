/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur_chat_pfm;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.util.ArrayList;


/**
 *
 * @author Jerome
 */
public class ThreadReception extends Thread{
    
    private MulticastSocket socketGroupe;
    private ArrayList<message> listMessage;
    
    public ThreadReception(MulticastSocket s)
    {
        socketGroupe = s;
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
                this.interrupt();
                continue;
            }
            
            String str = new String(buf).trim();
            String[] parts = str.split("#");
            
            message msg = new message(parts[2], parts[1], parts[0]);
            
            if(!msg.getTag().equals("Tous") && !msg.getTag().equals("Infos") && msg.getTag().charAt(0) != 'R')
            {
                if(parts.length < 4)    // La question n'a pas de digest
                    return;
                
                if(Integer.parseInt(parts[3]) != hashFunction(parts[2]))
                {
                    System.err.println("Message corrompu reçu ! ");
                    return;
                }
            }
            else if(msg.getTag().charAt(0) == 'R')
                msg.setTag(msg.getTag().substring(1));
            
            System.out.println(msg.getMessage());
        }
    }
    
    public int hashFunction(String message)
    {
        int hashValue = 0;
        
        for(int i = 0; i < message.length(); i++)
            hashValue += (int)message.charAt(i);
        
        return hashValue%67;
    }
}
