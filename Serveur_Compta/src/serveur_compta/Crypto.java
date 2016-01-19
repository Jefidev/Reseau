package serveur_compta;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
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
    public static byte[] asymCrypt(byte[] tocrypt, String fichierKS, String mdpKS, String aliasCertif)
    {
        try          
        {     
            KeyStore ks = KeyStore.getInstance("PKCS12", "BC");
            ks.load(new FileInputStream(getPathFichier(fichierKS)), mdpKS.toCharArray());
            X509Certificate certif = (X509Certificate)ks.getCertificate(aliasCertif);
            PublicKey ClePublique = certif.getPublicKey();
            Cipher chiffrement = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
            chiffrement.init(Cipher.ENCRYPT_MODE, ClePublique);
            return chiffrement.doFinal(tocrypt);     
        }
        catch (KeyStoreException ex)
        {
            System.err.println("Crypto : asymCrypt : KeyStoreException : " + ex.getMessage());
        }
        catch (NoSuchAlgorithmException ex)
        {
            System.err.println("Crypto : asymCrypt : NoSuchAlgorithmException : " + ex.getMessage());
        }
        catch (NoSuchProviderException ex)
        {
            System.err.println("Crypto : asymCrypt : NoSuchProviderException : " + ex.getMessage());
        }
        catch (NoSuchPaddingException ex)
        {
            System.err.println("Crypto : asymCrypt : NoSuchPaddingException : " + ex.getMessage());
        }
        catch (InvalidKeyException ex)
        {
            System.err.println("Crypto : asymCrypt : InvalidKeyException : " + ex.getMessage());
        }
        catch (IllegalBlockSizeException ex)
        {
            System.err.println("Crypto : asymCrypt : IllegalBlockSizeException : " + ex.getMessage());
        }
        catch (BadPaddingException ex)
        {
            System.err.println("Crypto : asymCrypt : BadPaddingException : " + ex.getMessage());
        }
        catch (IOException ex)
        {
            System.err.println("Crypto : asymCrypt : IOException : " + ex.getMessage());
        }
        catch (CertificateException ex)
        {
            System.err.println("Crypto : asymCrypt : CertificateException : " + ex.getMessage());
        }
        
        return null;
    }
    
    public static byte[] asymDecrypt(byte []todecrypt, String fichierKS, String mdpKS, String mdpPrivate, String alias)
    {
        try          
        {             
            KeyStore ks = KeyStore.getInstance("PKCS12", "BC");
            ks.load(new FileInputStream(getPathFichier(fichierKS)), mdpKS.toCharArray());
            PrivateKey Clepriv = (PrivateKey)ks.getKey(alias, mdpPrivate.toCharArray()); 
            Cipher chiffrement= Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
            chiffrement.init(Cipher.DECRYPT_MODE, Clepriv);
            return chiffrement.doFinal(todecrypt); 
        }
        catch (KeyStoreException ex)
        {
            System.err.println("Crypto : asymDecrypt : KeyStoreException : " + ex.getMessage());
        }
        catch (NoSuchProviderException ex)
        {
            System.err.println("Crypto : asymDecrypt : NoSuchProviderException : " + ex.getMessage());
        }
        catch (FileNotFoundException ex)
        {
            System.err.println("Crypto : asymDecrypt : FileNotFoundException : " + ex.getMessage());
        }
        catch (IOException ex)
        {
            System.err.println("Crypto : asymDecrypt : IOException : " + ex.getMessage());
        }
        catch (NoSuchAlgorithmException ex)
        {
            System.err.println("Crypto : asymDecrypt : NoSuchAlgorithmException : " + ex.getMessage());
        }
        catch (CertificateException ex)
        {
            System.err.println("Crypto : asymDecrypt : CertificateException : " + ex.getMessage());
        }
        catch (NoSuchPaddingException ex)
        {
            System.err.println("Crypto : asymDecrypt : NoSuchPaddingException : " + ex.getMessage());
        }
        catch (IllegalBlockSizeException ex)
        {
            System.err.println("Crypto : asymDecrypt : IllegalBlockSizeException : " + ex.getMessage());
        }
        catch (BadPaddingException ex)
        {
            System.err.println("Crypto : asymDecrypt : BadPaddingException : " + ex.getMessage());
        }
        catch (InvalidKeyException ex)
        {
            System.err.println("Crypto : asymDecrypt : InvalidKeyException : " + ex.getMessage());
        }
        catch (UnrecoverableKeyException ex)
        {
            System.err.println("Crypto : asymDecrypt : UnrecoverableKeyException : " + ex.getMessage());
        }        

        return null;
    }
    
    
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
            System.err.println("Crypto : saltDigest : IOException : " + ex.getMessage());
        }
        catch (NoSuchAlgorithmException ex)
        {
            System.err.println("Crypto : saltDigest : NoSuchAlgorithmException : " + ex.getMessage());
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
    
    
    /* CHEMIN FICHIER */
    private static String getPathFichier(String f)
    {
        return System.getProperty("user.dir") + System.getProperty("file.separator") + f;
    }
}
