package serveur_compta;

import java.io.*;
import java.security.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;


public final class Crypto
{
    /* DIGEST SALE */
    public static byte[] Digest(String msg, long temps, double aleatoire)
    {
        byte[] digest = null;
        
        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(msg.getBytes());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream bdos = new DataOutputStream(baos);
            bdos.writeLong(temps);
            bdos.writeDouble(aleatoire);
            md.update(baos.toByteArray());
            digest = md.digest();
        }
        catch (IOException ex)
        {
            System.err.println("Crypto : Digest : IOException : " + ex.getMessage());
        }
        catch (NoSuchAlgorithmException ex)
        {
            System.err.println("Crypto : Digest : NoSuchAlgorithmException : " + ex.getMessage());
        }
        
        return digest;
    }
    
    public static SecretKey generateSecretKey()
    {
        String CodeProvider = "BC";
        KeyGenerator cleGen = null;
        
        try
        {
            cleGen = KeyGenerator.getInstance("DES", CodeProvider);
        }
        catch (NoSuchAlgorithmException | NoSuchProviderException ex)
        {
            System.err.println("Crypto : generateSecretKey : NoSuchAlgorithmException ou NoSuchProviderException : " + ex.getMessage());
        }
        
        cleGen.init(new SecureRandom());
        return cleGen.generateKey();
    }
}
