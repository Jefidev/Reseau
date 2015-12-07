package classLibrary;

import applic_mail.inboxPanel;


public class ThreadPooling extends Thread
{    
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
            try
            {
                //Thread.sleep(240000); // Toutes les 4 minutes
                Thread.sleep(30000);    // 30 secondes pour tester plus rapidement
            }
            catch (InterruptedException ex)
            {
                System.err.println("Sleep interrompu");
            }
            
            panel.refreshMailList();
        }
    }
}
