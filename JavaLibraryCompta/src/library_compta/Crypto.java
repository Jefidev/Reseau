package library_compta;

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
import java.security.Signature;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;


public final class Crypto
{
    /* CHIFFREMENT ASYMETRIQUE (INTEGRITE) */
    public static byte[] AsymCrypt(byte[] tocrypt, String fichierKS, String mdpKS, String aliasCertif)
    {
        try          
        {     
            KeyStore ks = KeyStore.getInstance("PKCS12", "BC");
            ks.load(new FileInputStream(GetPathFichier(fichierKS)), mdpKS.toCharArray());
            X509Certificate certif = (X509Certificate)ks.getCertificate(aliasCertif);
            PublicKey ClePublique = certif.getPublicKey();
            Cipher chiffrement = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
            chiffrement.init(Cipher.ENCRYPT_MODE, ClePublique);
            return chiffrement.doFinal(tocrypt);     
        }
        catch (KeyStoreException ex)
        {
            System.err.println("Crypto : AsymCrypt : KeyStoreException : " + ex.getMessage());
        }
        catch (NoSuchAlgorithmException ex)
        {
            System.err.println("Crypto : AsymCrypt : NoSuchAlgorithmException : " + ex.getMessage());
        }
        catch (NoSuchProviderException ex)
        {
            System.err.println("Crypto : AsymCrypt : NoSuchProviderException : " + ex.getMessage());
        }
        catch (NoSuchPaddingException ex)
        {
            System.err.println("Crypto : AsymCrypt : NoSuchPaddingException : " + ex.getMessage());
        }
        catch (InvalidKeyException ex)
        {
            System.err.println("Crypto : AsymCrypt : InvalidKeyException : " + ex.getMessage());
        }
        catch (IllegalBlockSizeException ex)
        {
            System.err.println("Crypto : AsymCrypt : IllegalBlockSizeException : " + ex.getMessage());
        }
        catch (BadPaddingException ex)
        {
            System.err.println("Crypto : AsymCrypt : BadPaddingException : " + ex.getMessage());
        }
        catch (IOException ex)
        {
            System.err.println("Crypto : AsymCrypt : IOException : " + ex.getMessage());
        }
        catch (CertificateException ex)
        {
            System.err.println("Crypto : AsymCrypt : CertificateException : " + ex.getMessage());
        }
        
        return null;
    }
    
    public static byte[] AsymDecrypt(byte []todecrypt, String fichierKS, String mdpKS, String mdpPrivate, String aliasKeyPair)
    {
        try          
        {             
            KeyStore ks = KeyStore.getInstance("PKCS12", "BC");
            ks.load(new FileInputStream(GetPathFichier(fichierKS)), mdpKS.toCharArray());
            PrivateKey ClePrivee = (PrivateKey)ks.getKey(aliasKeyPair, mdpPrivate.toCharArray());
            Cipher chiffrement= Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
            chiffrement.init(Cipher.DECRYPT_MODE, ClePrivee);
            return chiffrement.doFinal(todecrypt); 
        }
        catch (KeyStoreException ex)
        {
            System.err.println("Crypto : AsymDecrypt : KeyStoreException : " + ex.getMessage());
        }
        catch (NoSuchProviderException ex)
        {
            System.err.println("Crypto : AsymDecrypt : NoSuchProviderException : " + ex.getMessage());
        }
        catch (FileNotFoundException ex)
        {
            System.err.println("Crypto : AsymDecrypt : FileNotFoundException : " + ex.getMessage());
        }
        catch (IOException ex)
        {
            System.err.println("Crypto : AsymDecrypt : IOException : " + ex.getMessage());
        }
        catch (NoSuchAlgorithmException ex)
        {
            System.err.println("Crypto : AsymDecrypt : NoSuchAlgorithmException : " + ex.getMessage());
        }
        catch (CertificateException ex)
        {
            System.err.println("Crypto : AsymDecrypt : CertificateException : " + ex.getMessage());
        }
        catch (NoSuchPaddingException ex)
        {
            System.err.println("Crypto : AsymDecrypt : NoSuchPaddingException : " + ex.getMessage());
        }
        catch (IllegalBlockSizeException ex)
        {
            System.err.println("Crypto : AsymDecrypt : IllegalBlockSizeException : " + ex.getMessage());
        }
        catch (BadPaddingException ex)
        {
            System.err.println("Crypto : AsymDecrypt : BadPaddingException : " + ex.getMessage());
        }
        catch (InvalidKeyException ex)
        {
            System.err.println("Crypto : AsymDecrypt : InvalidKeyException : " + ex.getMessage());
        }
        catch (UnrecoverableKeyException ex)
        {
            System.err.println("Crypto : AsymDecrypt : UnrecoverableKeyException : " + ex.getMessage());
        }        

        return null;
    }
    
    
    /* DIGEST SALE */
    public static byte[] SaltDigest(String msg, long temps, double aleatoire)
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
            System.err.println("Crypto : SaltDigest : IOException : " + ex.getMessage());
        }
        catch (NoSuchAlgorithmException ex)
        {
            System.err.println("Crypto : SaltDigest : NoSuchAlgorithmException : " + ex.getMessage());
        }
        
        return null;
    }
    
    
    /* HMAC (AUTHENTIFICATION) */
    public static byte[] CreateHMAC(SecretKey cleSecrete, byte[] toHMAC)
    {
        try
        {
            Mac hmac = Mac.getInstance("HMAC-MD5", "BC");
            hmac.init(cleSecrete);
            hmac.update(toHMAC);
            return hmac.doFinal();
        }
        catch (NoSuchAlgorithmException ex)
        {
            System.err.println("Crypto : CreateHMAC : NoSuchAlgorithmException : " + ex.getMessage());
        }
        catch (NoSuchProviderException ex)
        {
            System.err.println("Crypto : CreateHMAC : NoSuchProviderException : " + ex.getMessage());
        }
        catch (InvalidKeyException ex)
        {
            System.err.println("Crypto : CreateHMAC : InvalidKeyException : " + ex.getMessage());
        }
        
        return null;
    }
    
    public static boolean CompareHMAC(SecretKey cleSecrete, byte[] toHMAC, byte[] hmacRemote)
    {
        try
        {
            Mac hmacLocal = Mac.getInstance("HMAC-MD5", "BC");
            hmacLocal.init(cleSecrete);
            hmacLocal.update(toHMAC);
            byte[] hmacLocalBytes = hmacLocal.doFinal();
            return MessageDigest.isEqual(hmacRemote, hmacLocalBytes);
        }
        catch (NoSuchAlgorithmException ex)
        {
            System.err.println("Crypto : CompareHMAC : NoSuchAlgorithmException : " + ex.getMessage());
        }
        catch (NoSuchProviderException ex)
        {
            System.err.println("Crypto : CompareHMAC : NoSuchProviderException : " + ex.getMessage());
        }
        catch (InvalidKeyException ex)
        {
            System.err.println("Crypto : CompareHMAC : InvalidKeyException : " + ex.getMessage());
        }
        
        return false;
    }
    
    
    /* SIGNATURE (AUTHENTIFICATION) */
    public static byte[] CreateSignature(byte[] toSign, String fichierKS, String mdpKS, String mdpPrivate, String aliasKeyPair)
    {
        try          
        {
            KeyStore ks = KeyStore.getInstance("PKCS12", "BC");
            ks.load(new FileInputStream(GetPathFichier(fichierKS)), mdpKS.toCharArray()); 
            PrivateKey ClePrivee =(PrivateKey)ks.getKey(aliasKeyPair, mdpPrivate.toCharArray()); 
            Signature s = Signature.getInstance("SHA1withRSA","BC"); 
            s.initSign(ClePrivee);
            s.update(toSign);            
            byte[] signature = s.sign();
            return signature;
        }
        catch (KeyStoreException ex)
        {
            System.err.println("Crypto : CreateSignature : KeyStoreException : " + ex.getMessage());
        }
        catch (NoSuchProviderException ex)
        {
            System.err.println("Crypto : CreateSignature : NoSuchProviderException : " + ex.getMessage());
        }
        catch (IOException ex)
        {
            System.err.println("Crypto : CreateSignature : IOException : " + ex.getMessage());
        }
        catch (NoSuchAlgorithmException ex)
        {
            System.err.println("Crypto : CreateSignature : NoSuchAlgorithmException : " + ex.getMessage());
        }
        catch (CertificateException ex)
        {
            System.err.println("Crypto : CreateSignature : CertificateException : " + ex.getMessage());
        }
        catch (UnrecoverableKeyException ex)
        {
            System.err.println("Crypto : CreateSignature : UnrecoverableKeyException : " + ex.getMessage());
        }
        catch (InvalidKeyException ex)
        {
            System.err.println("Crypto : CreateSignature : InvalidKeyException : " + ex.getMessage());
        }
        catch (SignatureException ex)
        {
            System.err.println("Crypto : CreateSignature : SignatureException : " + ex.getMessage());
        }       

        return null;
    }
        
    public static boolean CompareSignature(byte[] toSign, String fichierKS, String mdpKS, String aliasCertif, byte[] signature)
    {
        try          
        {     
            KeyStore ks = KeyStore.getInstance("PKCS12", "BC");
            ks.load(new FileInputStream(GetPathFichier(fichierKS)), mdpKS.toCharArray());
            X509Certificate certif = (X509Certificate)ks.getCertificate(aliasCertif);
            PublicKey cléPublique = certif.getPublicKey();
            Signature s = Signature.getInstance("SHA1withRSA", "BC");
            s.initVerify(cléPublique);
            s.update(toSign);
            return s.verify(signature);
        }
        catch (KeyStoreException ex)
        {
            System.err.println("Crypto : CompareSignature : KeyStoreException : " + ex.getMessage());
        }
        catch (NoSuchProviderException ex)
        {
            System.err.println("Crypto : CompareSignature : NoSuchProviderException : " + ex.getMessage());
        }
        catch (IOException ex)
        {
            System.err.println("Crypto : CompareSignature : IOException : " + ex.getMessage());
        }
        catch (NoSuchAlgorithmException ex)
        {
            System.err.println("Crypto : CompareSignature : NoSuchAlgorithmException : " + ex.getMessage());
        }
        catch (CertificateException ex)
        {
            System.err.println("Crypto : CompareSignature : CertificateException : " + ex.getMessage());
        }
        catch (InvalidKeyException ex)
        {
            System.err.println("Crypto : CompareSignature : InvalidKeyException : " + ex.getMessage());
        }
        catch (SignatureException ex)
        {
            System.err.println("Crypto : CompareSignature : SignatureException : " + ex.getMessage());
        }

        return false;
    }
    
        
    /* CHIFFREMENT SYMETRIQUE (INTEGRITE) */
    public static SecretKey GenerateSecretKey()
    {        
        try
        {
            KeyGenerator cleGen = KeyGenerator.getInstance("DES", "BC");
            cleGen.init(new SecureRandom());
            return cleGen.generateKey();
        }
        catch (NoSuchAlgorithmException ex)
        {
            System.err.println("Crypto : GenerateSecretKey : NoSuchAlgorithmException : " + ex.getMessage());
        }
        catch (NoSuchProviderException ex)
        {
            System.err.println("Crypto : GenerateSecretKey : NoSuchProviderException : " + ex.getMessage());
        }
        
        return null;
    }
    
    public static byte[] SymCrypt(SecretKey cle, byte[] tocrypt)
    {
        try
        {
            Cipher chiffrement = Cipher.getInstance("DES/ECB/PKCS5Padding","BC"); 
            chiffrement.init(Cipher.ENCRYPT_MODE, cle); 
            byte[] texteCrypte = chiffrement.doFinal(tocrypt);
            return texteCrypte;
        }
        catch (NoSuchAlgorithmException ex)      
        {
            System.err.println("Crypto : SymCrypt : NoSuchAlgorithmException : " + ex.getMessage());
        }
        catch (NoSuchProviderException ex)      
        {
            System.err.println("Crypto : SymCrypt : NoSuchProviderException : " + ex.getMessage());
        }
        catch (IllegalBlockSizeException ex)
        {
            System.err.println("Crypto : SymCrypt : IllegalBlockSizeException : " + ex.getMessage());
        }
        catch (BadPaddingException ex)
        {
            System.err.println("Crypto : SymCrypt : BadPaddingException : " + ex.getMessage());
        }
        catch (NoSuchPaddingException ex)
        {
            System.err.println("Crypto : SymCrypt : NoSuchPaddingException : " + ex.getMessage());
        }
        catch (InvalidKeyException ex)
        {
            System.err.println("Crypto : SymCrypt : InvalidKeyException : " + ex.getMessage());
        }
        
        return null; 
    }
    
    public static byte[] SymDecrypt(SecretKey cle, byte[] todecrypt)
    {
        try
        {
            Cipher chiffrementD = Cipher.getInstance("DES/ECB/PKCS5Padding", "BC");
            chiffrementD.init(Cipher.DECRYPT_MODE,cle); 
            byte[] texteDecrypte = chiffrementD.doFinal(todecrypt);
            return texteDecrypte;
        }
        catch (NoSuchAlgorithmException ex)      
        {
            System.err.println("Crypto : SymDecrypt : NoSuchAlgorithmException : " + ex.getMessage());
        }
        catch (NoSuchProviderException ex)      
        {
            System.err.println("Crypto : SymDecrypt : NoSuchProviderException : " + ex.getMessage());
        }
        catch (IllegalBlockSizeException ex)
        {
            System.err.println("Crypto : SymDecrypt : IllegalBlockSizeException : " + ex.getMessage());
        }
        catch (BadPaddingException ex)
        {
            System.err.println("Crypto : SymDecrypt : BadPaddingException : " + ex.getMessage());
        }
        catch (NoSuchPaddingException ex)
        {
            System.err.println("Crypto : SymDecrypt : NoSuchPaddingException : " + ex.getMessage());
        }
        catch (InvalidKeyException ex)
        {
            System.err.println("Crypto : SymDecrypt : InvalidKeyException : " + ex.getMessage());
        }
        
        return null; 
    }
    
    
    /* CHEMIN FICHIER */
    private static String GetPathFichier(String f)
    {
        return System.getProperty("user.dir") + System.getProperty("file.separator") + ".." + System.getProperty("file.separator") + f;
    }
}
