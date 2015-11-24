package ServeurPoolThreads;

import java.io.*;
import java.util.Properties;


public class LancementServeur
{
    private static int port;
    private static int nbThreads;
    
    public static void main(String[] args)
    {
        FichierProperties();
        ServeurDataAnalysis sb = new ServeurDataAnalysis(port, new ListeTaches(), nbThreads);
        sb.start();
    }
    
    private static void FichierProperties()
    {
        Properties prop = new Properties();
        
        try
        {
            FileInputStream FIS = new FileInputStream("ServeurDataAnalysis.properties");
            prop.load(FIS);
        }
        catch(FileNotFoundException ex)
        {
            try 
            {
                FileOutputStream FOS = new FileOutputStream("ServeurDataAnalysis.properties");
                
                prop.setProperty("Port", "31049");
                prop.setProperty("NbThreads", "5");
                              
                try
                {
                    prop.store(FOS, null);
                }
                catch (IOException ex1)
                {
                    System.err.println("LancementServeur : Ecriture properties (IO) : " + ex1.getMessage());
                    System.exit(0);
                }
            } 
            catch (FileNotFoundException ex1) 
            {
                System.err.println("LancementServeur : Properties (FileNotFoundException) : " + ex1.getMessage());
                System.exit(0);
            }
            
        }
        catch(IOException ex)
        {
            System.err.println("LancementServeur : Lecture properties (IO) : " + ex.getMessage());
            System.exit(0);
        }
        
        port = Integer.parseInt(prop.getProperty("Port"));
        nbThreads = Integer.parseInt(prop.getProperty("NbThreads"));
    }
}
