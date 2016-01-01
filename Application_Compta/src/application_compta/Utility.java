package application_compta;

import java.io.*;
import java.net.*;
import java.util.Properties;


public final class Utility
{
    private static DataInputStream dis;
    public static DataOutputStream dos;
    private static String adresse;
    private static int port;


    public static void InitialisationFlux()
    {      
        FichierProperties();
        
        try
        {
            ApplicationCompta.cliSock = new Socket(adresse, port);
            dis = new DataInputStream(new BufferedInputStream(ApplicationCompta.cliSock.getInputStream()));
            dos = new DataOutputStream(new BufferedOutputStream(ApplicationCompta.cliSock.getOutputStream()));
        }
        catch (IOException e)
        {
            System.err.println("Utility : Erreur de création de la socket, dis et dos (IO) : " + e);
        }
    }
    
    private static void FichierProperties()
    {
        Properties prop = new Properties();
        
        try
        {
            FileInputStream FIS = new FileInputStream("ApplicationCompta.properties");
            prop.load(FIS);
        }
        catch(FileNotFoundException ex)
        {
            try 
            {
                FileOutputStream FOS = new FileOutputStream("ApplicationCompta.properties");
                
                prop.setProperty("Adresse", "192.168.1.4");
                prop.setProperty("Port", "31049");
                              
                try
                {
                    prop.store(FOS, null);
                }
                catch (IOException ex1)
                {
                    System.err.println("Utility : Ecriture properties (IO) : " + ex1.getMessage());
                    System.exit(0);
                }
            } 
            catch (FileNotFoundException ex1) 
            {
                System.err.println("Utility : Properties (FileNotFoundException) : " + ex1.getMessage());
                System.exit(0);
            }
            
        }
        catch(IOException ex)
        {
            System.err.println("Utility : Lecture properties (IO) : " + ex.getMessage());
            System.exit(0);
        }
        
        adresse = prop.getProperty("Adresse");
        port = Integer.parseInt(prop.getProperty("Port"));
    }

    public static void SendMsg(int requete, String chargeUtile)
    {       
        chargeUtile = requete + "#" + chargeUtile;
        int taille = chargeUtile.length();
        String message = String.valueOf(taille) + "#" + chargeUtile;

        try
        {
            dos.write(message.getBytes());
            dos.flush();
        }
        catch(IOException e)
        {
            System.err.println("Utility : Erreur d'envoi de msg (IO) : " + e);
        }
    }

    public static String ReceiveMsg()
    {
        byte b;
        StringBuffer taille = new StringBuffer();
        StringBuffer message = new StringBuffer();

        try
        {
            while ((b = dis.readByte()) != (byte)'#')
            {
                if (b != (byte)'#')
                    taille.append((char)b);
            }

            for (int i = 0; i < Integer.parseInt(taille.toString()); i++)
            {
                b = dis.readByte();
                message.append((char)b);
            }
        }
        catch(IOException e)
        {
            System.err.println("Utility : Erreur de reception de msg (IO) : " + e);
        }

        return message.toString();
    }
}
