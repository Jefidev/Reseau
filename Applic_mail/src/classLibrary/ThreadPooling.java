/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classLibrary;

import applic_mail.inboxPanel;

/**
 *
 * @author John
 */
public class ThreadPooling extends Thread{
    
    private inboxPanel panel;
    
    public ThreadPooling(inboxPanel ip)
    {
        panel = ip;
    }
    
    
    @Override
    public void run()
    {
        while(true)
        {
            try {
                //Thread.sleep(240000);//Toutes les 4 minutes
                Thread.sleep(30000);
            } catch (InterruptedException ex) {
                System.err.println("Sleep interrompu");
            }
            
            panel.refreshMailList();
        }
    }
    
}
