package serveur_compta;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyException;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;


public final class Crypto
{
    /* ASYMETRIQUE */
    public byte[] asymCrypt(byte[] tocrypt)
    {
        try          
        {     
            KeyStore ks = KeyStore.getInstance("JKS");
            ks.load(new FileInputStream(getNomCompletFichier(Kstore)),mdpKeystore.toCharArray());
            X509Certificate certif = (X509Certificate)ks.getCertificate(alias);
            PublicKey ClePublique = certif.getPublicKey();
            Cipher chiffrement = Cipher.getInstance("RSA/ECB/PKCS1Padding","BC");
            chiffrement.init(Cipher.ENCRYPT_MODE, ClePublique);
            return chiffrement.doFinal(tocrypt);     
        }
        return null;
    }
    
    /*public byte[] asymDecrypt(byte []todecrypt)
    {
        try          
        {             
            KeyStore ks = KeyStore.getInstance("JKS");
            ks.load(new FileInputStream(getNomCompletFichier(Kstore)),mdpKeystore.toCharArray());
            Enumeration en = ks.aliases(); 
            PrivateKey Clepriv=(PrivateKey)ks.getKey(alias,mdpPrivate.toCharArray());
            System.out.println(" *** Cle privee recuperee = " + Clepriv.toString()); 
            Cipher chiffrement= Cipher.getInstance("RSA/ECB/PKCS1Padding","BC");
            chiffrement.init(Cipher.DECRYPT_MODE, Clepriv);
            return chiffrement.doFinal(todecrypt);             
        }        

       return null;
    }*/
    
    
    /* DIGEST SALE */
    public static byte[] saltDigest(String msg, long temps, double aleatoire)
    {       
        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(msg.getBytes());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream bdos = new DataOutputStream(baos);
            bdos.writeLong(temps);
            bdos.writeDouble(aleatoire);
            md.update(baos.toByteArray());
            byte[] digest = md.digest();
            return digest;
        }
        catch (IOException ex)
        {
            System.err.println("Crypto : Digest : IOException : " + ex.getMessage());
        }
        catch (NoSuchAlgorithmException ex)
        {
            System.err.println("Crypto : Digest : NoSuchAlgorithmException : " + ex.getMessage());
        }
        
        return null;
    }
    
    
    /* HMAC */
    
    
    /* SIGNATURE */
    
    
    /* SYMETRIQUE */
    public static SecretKey generateSecretKey()
    {        
        try
        {
            KeyGenerator cleGen = KeyGenerator.getInstance("DES", "BC");
            cleGen.init(new SecureRandom());
            return cleGen.generateKey();
        }
        catch (NoSuchAlgorithmException | NoSuchProviderException ex)
        {
            System.err.println("Crypto : generateSecretKey : NoSuchAlgorithmException ou NoSuchProviderException : " + ex.getMessage());
        }
        
        return null;
    }
    
    public static byte[] symCrypt(SecretKey cle, byte[] tocrypt)
    {
        try
        {
            Cipher chiffrement = Cipher.getInstance("DES/ECB/PKCS5Padding","BC"); 
            chiffrement.init(Cipher.ENCRYPT_MODE, cle); 
            byte[] texteCrypte = chiffrement.doFinal(tocrypt);
            return texteCrypte;
        }
        catch (NoSuchAlgorithmException | NoSuchProviderException ex)      
        {
            System.err.println("Crypto : symCrypt : NoSuchAlgorithmException ou NoSuchProviderException : " + ex.getMessage());
        }
        catch (IllegalBlockSizeException ex)
        {
            System.err.println("Crypto : symCrypt : IllegalBlockSizeException : " + ex.getMessage());
        }
        catch (BadPaddingException ex)
        {
            System.err.println("Crypto : symCrypt : BadPaddingException : " + ex.getMessage());
        }
        catch (NoSuchPaddingException ex)
        {
            System.err.println("Crypto : symCrypt : NoSuchPaddingException : " + ex.getMessage());
        }
        catch (InvalidKeyException ex)
        {
            System.err.println("Crypto : symCrypt : InvalidKeyException : " + ex.getMessage());
        }
        
        return null; 
    }
    
    public static byte[] symDecrypt(SecretKey cle, byte[] todecrypt)
    {
        try
        {
            Cipher chiffrementD = Cipher.getInstance("DES/ECB/PKCS5Padding", "BC");
            chiffrementD.init(Cipher.DECRYPT_MODE,cle); 
            byte[] texteDecrypte = chiffrementD.doFinal(todecrypt);
            return texteDecrypte;
        }
        catch (NoSuchAlgorithmException | NoSuchProviderException ex)      
        {
            System.err.println("Crypto : symDecrypt : NoSuchAlgorithmException ou NoSuchProviderException : " + ex.getMessage());
        }
        catch (IllegalBlockSizeException ex)
        {
            System.err.println("Crypto : symDecrypt : IllegalBlockSizeException : " + ex.getMessage());
        }
        catch (BadPaddingException ex)
        {
            System.err.println("Crypto : symDecrypt : BadPaddingException : " + ex.getMessage());
        }
        catch (NoSuchPaddingException ex)
        {
            System.err.println("Crypto : symDecrypt : NoSuchPaddingException : " + ex.getMessage());
        }
        catch (InvalidKeyException ex)
        {
            System.err.println("Crypto : symDecrypt : InvalidKeyException : " + ex.getMessage());
        }
        
        return null; 
    }
}
