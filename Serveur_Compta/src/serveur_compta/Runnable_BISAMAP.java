package serveur_compta;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.MessageDigest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKey;
import library_compta.*;
import newBean.BeanBDAccess;
import newBean.connexionException;
import newBean.requeteException;


public class Runnable_BISAMAP implements Runnable
{
    private Socket CSocket = null;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;
    private BeanBDAccess beanOracle;
    private String fonction;
    private SecretKey CleSecreteChiffrement, CleSecreteHMAC;
    
    
    public Runnable_BISAMAP(Socket s)
    {
        CSocket = s;

        try
        {
            dis = new DataInputStream(new BufferedInputStream(CSocket.getInputStream()));
            dos = new DataOutputStream(new BufferedOutputStream(CSocket.getOutputStream()));
        }
        catch(IOException e)
        {
            System.err.println("Runnable_BISAMAP : Host non trouvé : " + e);
        }
        
        beanOracle = new BeanBDAccess();
        try
        {
            beanOracle.connexionOracle("localhost", 1521, "COMPTA", "COMPTA", "XE");
        }
        catch (ClassNotFoundException ex)
        {
            System.err.println("Runnable_BISAMAP : Class not found " + ex.getMessage());
        }
        catch (SQLException ex)
        {
            System.err.println("Runnable_BISAMAP : SQL Exception (Oracle)" + ex.getMessage()); 
        }
        catch (connexionException ex)
        {
            System.err.println("Runnable_BISAMAP : " + ex.getNumException() + " -- " + ex.getMessage());
        }
    }

    
    /* RUNNABLE : BOUCLE DU SERVEUR + APPEL DES METHODES SUIVANT LE SERVICE DEMANDE */
    @Override
    public void run()
    {
        try
        {
            String[] parts = (ReceiveMsg()).split("#");

            if(Integer.parseInt(parts[0]) == ProtocoleBISAMAP.LOGIN)
            {
                if(!login())
                    return;
            }
            else
            {
                SendMsg("NON#Requete invalide");
                return;
            }

            boolean terminer = false;

            while(!terminer)
            {
                parts = ReceiveMsg().split("#");
                switch (Integer.parseInt(parts[0]))
                {                          
                    case ProtocoleBISAMAP.GET_NEXT_BILL :
                        getNextBill();
                        break;

                    case ProtocoleBISAMAP.VALIDATE_BILL :
                        validateBill(parts);
                        break;

                    case ProtocoleBISAMAP.LIST_BILLS :
                        listBills(parts);
                        break;

                    case ProtocoleBISAMAP.SEND_BILLS :
                        sendBills(parts);
                        break;

                    case ProtocoleBISAMAP.REC_PAY :
                        recPay();
                        break;

                    case ProtocoleBISAMAP.LIST_WAITING :
                        listWaiting(parts);
                        break;

                    case ProtocoleBISAMAP.COMPUTE_SAL :
                        computeSal(parts);
                        break;

                    case ProtocoleBISAMAP.VALIDATE_SAL :
                        validateSal(parts);
                        break;

                    case ProtocoleBISAMAP.LOGOUT :
                        terminer = true;
                        break;

                    default :
                        terminer = true;
                        break;
                }
            }

            System.out.println("Runnable_BISAMAP : Run : Fin du runnable");
        }
        catch (NumberFormatException ex)
        {
        }
        
        try
        {
            CSocket.close();
        }
        catch (IOException ex)
        {
            System.err.println("Runnable_BISAMAP : Run : Erreur de close : " + ex.getMessage());
        }
    }
    
    
    /* LOGIN (à partir de BD_COMPTA) + handshake */
    private boolean login()
    {
        try
        {
            // Lecture des données
            String user = dis.readUTF();
            long temps = dis.readLong();
            double aleatoire = dis.readDouble();
            int longueur = dis.readInt();
            byte[] pwdClient = new byte[longueur];
            dis.readFully(pwdClient);

            // Récupération du mot de passe dans la base de données
            String passwordDB = null;

            ResultSet rs = beanOracle.selection("PASSWORD, FONCTION", "PERSONNEL", "LOGIN = '" + user + "'");
            while (rs.next())
            {
                passwordDB = rs.getString(1);
                fonction = rs.getString(2);
            }
            
            // L'employé doit être comptable ou chef-comptable
            if(!(fonction.equalsIgnoreCase("comptable") || fonction.equalsIgnoreCase("chef-comptable")))
            {
                SendMsg("NON#Mauvaise fonction de l'employe");
                System.out.println("Runnable_BISAMAP : Login : Le client " + user + " est refusé (mauvaise fonction)");
                return false;
            }
            
            // Comparaison
            if (!(MessageDigest.isEqual(pwdClient, Crypto.SaltDigest(passwordDB, temps, aleatoire))))
            {
                SendMsg("NON#Mauvais mot de passe");
                System.out.println("Runnable_BISAMAP : Login : Le client " + user + " est refusé (mauvais password)");
                return false;
            }
            
            // Handshake
            CleSecreteChiffrement = Crypto.GenerateSecretKey();
            CleSecreteHMAC = Crypto.GenerateSecretKey();
            byte[] CleSecreteChiffrementChiffreeAsym = Crypto.AsymCrypt(CleSecreteChiffrement.getEncoded(), "KSServeurCompta.p12", "azerty", "AppCompta");
            byte[] CleSecreteHMACChiffreeAsym = Crypto.AsymCrypt(CleSecreteHMAC.getEncoded(), "KSServeurCompta.p12", "azerty", "AppCompta");
            
            // Réponse
            SendMsg("OUI");
            dos.writeInt(CleSecreteChiffrementChiffreeAsym.length); 
            dos.write(CleSecreteChiffrementChiffreeAsym);
            dos.writeInt(CleSecreteHMACChiffreeAsym.length);
            dos.write(CleSecreteHMACChiffreeAsym);
            dos.flush();
            
            System.out.println("Runnable_BISAMAP : Login : Le client " + user + " est connecté au serveur");
            return true;
        }
        catch (IOException ex)
        {
            SendMsg("NON#Erreur interne au serveur");
            System.err.println("Runnable_BISAMAP : Login : IOException : " + ex.getMessage());
        }
        catch (SQLException ex)
        {
            SendMsg("NON#Erreur interne au serveur");
            System.err.println("Runnable_BISAMAP : Login : SQLexception : " + ex.getMessage());
        }
        
        return false;
    }

    
    /* RECUPERATION DE LA PLUS ANCIENNE FACTURE PAS ENCORE VALIDEE (flag = 0) */
    private void getNextBill()
    {
        try
        {
            ResultSet rs = beanOracle.selection("*", "FACTURES", "FLAG_FACT_VALIDEE = 0 ORDER BY MOIS_ANNEE");
            if(!rs.next())
            {
                SendMsg("NON#Pas de facture disponible");
                System.err.println("Runnable_BISAMAP : getNextBill : Pas de facture dispo");
                return;
            }
            
            String i = rs.getString("ID_FACTURE");
            String s = rs.getString("ID_SOCIETE");
            String ma = rs.getString("MOIS_ANNEE");
            double th = rs.getDouble("TOTAL_HTVA");
            double tt = rs.getDouble("TOTAL_TVAC");
            int ffv = rs.getInt("FLAG_FACT_VALIDEE");
            String l = rs.getString("LOGIN");
            int ffe = rs.getInt("FLAG_FACT_ENVOYEE");
            String me = rs.getString("MOYEN_ENVOI");
            int ffp = rs.getInt("FLAG_FACT_PAYEE");
            Facture facture = new Facture (i, s, ma, th, tt, ffv, l, ffe, me, ffp);
                
            byte[] factureToCrypt = Convert.ObjectToByteArray(facture);
            byte[] factureCryptee = Crypto.SymCrypt(CleSecreteChiffrement, factureToCrypt);
                
            SendMsg("OUI");
            dos.writeInt(factureCryptee.length); 
            dos.write(factureCryptee);
            dos.flush();
            System.out.println("Runnable_BISAMAP : getNextBill : Facture envoyée au client");
        }
        catch (SQLException ex)
        {
            SendMsg("NON#Erreur interne au serveur");
            System.err.println("Runnable_BISAMAP : getNextBill : SQLexception : " + ex.getMessage());
        }
        catch (IOException ex)
        {
            SendMsg("NON#Erreur interne au serveur");
            System.err.println("Runnable_BISAMAP : getNextBill : IOException : " + ex.getMessage());
        }
    }
                
    
    /* VALIDATION OU INVALIDATION DE LA FACTURE RECUPEREE PAR getNextVill */
    /* IN : IdFacture#FlagFactValidee */
    private void validateBill(String[] parts)
    {
        try
        {
            int longueur = dis.readInt();
            byte[] signature = new byte[longueur];
            dis.readFully(signature);
            
            String toSign = ProtocoleBISAMAP.VALIDATE_BILL + parts[1] + "#" + parts[2];
            boolean comparaisonSignature = Crypto.CompareSignature(toSign.getBytes(), "KSServeurCompta.p12", "azerty", "AppCompta", signature);
            
            if(comparaisonSignature == false)
            {
                SendMsg("NON#Signature non verifiee");
                System.err.println("Runnable_BISAMAP : validateBill : Signature non vérifiée");
                return;
            }
            
            HashMap map = new HashMap();
            map.put("FLAG_FACT_VALIDEE", parts[2]);
            beanOracle.miseAJour("FACTURES", map, "ID_FACTURE = '" + parts[1] + "'");
            SendMsg("OUI#Facture modifiee");
            System.out.println("Runnable_BISAMAP : validateBill : Facture modifiée");
        }
        catch (requeteException ex)
        {
            SendMsg("NON#Erreur interne au serveur");
            System.err.println("Runnable_BISAMAP : validateBill : requeteException : " + ex.getMessage());
        }
        catch (IOException ex)
        {
            SendMsg("NON#Erreur interne au serveur");
            System.err.println("Runnable_BISAMAP : validateBill : IOException : " + ex.getMessage());
        }
    }
    
    
    /* LISTER TOUTES LES FACTURES SUIVANT DES CRITERES */
    /* IN : IdSociete#DateMin#DateMax */
    private void listBills(String[] parts)
    {
        try
        {
            int longueur = dis.readInt();
            byte[] signature = new byte[longueur];
            dis.readFully(signature);
            
            String toSign = ProtocoleBISAMAP.LIST_BILLS + parts[1] + "#" + parts[2] + "#" + parts[3];
            boolean comparaisonSignature = Crypto.CompareSignature(toSign.getBytes(), "KSServeurCompta.p12", "azerty", "AppCompta", signature);
            
            if(comparaisonSignature == false)
            {
                SendMsg("NON#Signature non verifiee");
                System.err.println("Runnable_BISAMAP : listBills : Signature non vérifiée");
                return;
            }
            
            ResultSet rs = beanOracle.selection("*", "FACTURES", "ID_SOCIETE = '" + parts[1]  + "' AND MOIS_ANNEE >= '" + parts[2] + "' AND MOIS_ANNEE <= '" + parts[3] + "'");
            if(!rs.next())
            {
                SendMsg("NON#Pas de facture disponible");
                System.err.println("Runnable_BISAMAP : listBills : Pas de facture dispo");
                return;
            }
            
            List<Facture> listFactures = new ArrayList<Facture>();
            rs.beforeFirst();
            while(rs.next())
            {
                String i = rs.getString("ID_FACTURE");
                String s = rs.getString("ID_SOCIETE");
                String ma = rs.getString("MOIS_ANNEE");
                double th = rs.getDouble("TOTAL_HTVA");
                double tt = rs.getDouble("TOTAL_TVAC");
                int ffv = rs.getInt("FLAG_FACT_VALIDEE");
                String l = rs.getString("LOGIN");
                int ffe = rs.getInt("FLAG_FACT_ENVOYEE");
                String me = rs.getString("MOYEN_ENVOI");
                int ffp = rs.getInt("FLAG_FACT_PAYEE");
                
                Facture facture = new Facture (i, s, ma, th, tt, ffv, l, ffe, me, ffp);
                listFactures.add(facture);
            }

            byte[] listToCrypt = Convert.ObjectToByteArray(listFactures);
            byte[] listCryptee = Crypto.SymCrypt(CleSecreteChiffrement, listToCrypt);
            
            SendMsg("OUI#Factures envoyées");
            dos.writeInt(listCryptee.length);
            dos.write(listCryptee);
            dos.flush();
            System.out.println("Runnable_BISAMAP : listBills : Factures envoyées");
        }
        catch (IOException ex)
        {
            SendMsg("NON#Erreur interne au serveur");
            System.err.println("Runnable_BISAMAP : listBills : IOException : " + ex.getMessage());
        }
        catch (SQLException ex)
        {
            SendMsg("NON#Erreur interne au serveur");
            System.err.println("Runnable_BISAMAP : listBills : SQLException : " + ex.getMessage());
        }
    }
    

    private void sendBills(String[] parts)
    {
    }
       
    
    /* ENREGISTRER LE PAIEMENT D'UNE FACTURE */
    private void recPay()
    {
        try
        {
            int longueur = dis.readInt();
            byte[] crypte = new byte[longueur];
            dis.readFully(crypte);

            byte[] rpaDecrypte = Crypto.SymDecrypt(CleSecreteChiffrement, crypte);
            RecPayAuth rpa = (RecPayAuth)Convert.ByteArrayToObject(rpaDecrypte);
            
            byte[] toHMAC = Convert.ObjectToByteArray(rpa.data);
            boolean comparaisonHMAC = Crypto.CompareHMAC(CleSecreteHMAC, toHMAC, rpa.hmac);
            
            if(comparaisonHMAC == false)
            {
                SendMsg("NON#Comparaison des HMACs ratee !");
                System.err.println("Runnable_BISAMAP : recPay : HMACs différents");
                return;
            }
            
            ResultSet rs = beanOracle.selection("*", "FACTURES", "ID_FACTURE = '" + rpa.data.idFacture  + "'");
            if(!rs.next())
            {
                SendMsg("NON#Le numero de facture n'existe pas !");
                System.err.println("Runnable_BISAMAP : recPay : Numéro de facture inexistant");
                return;
            }
            
            if(rpa.data.montant != rs.getDouble("TOTAL_TVAC"))
            {
                SendMsg("NON#Le montant paye differe du montant attendu !");
                System.err.println("Runnable_BISAMAP : recPay : Le montant à payer diffère du montant attendu");
                return;
            }
            
            HashMap map = new HashMap();
            map.put("FLAG_FACT_PAYEE", "1");
            beanOracle.miseAJour("FACTURES", map, "ID_FACTURE = '" + rpa.data.idFacture + "'");
            SendMsg("OUI#Facture modifiee (payee)");
            System.out.println("Runnable_BISAMAP : recPay : Facture modifiée (payée)");
        }
        catch (IOException ex)
        {
            SendMsg("NON#Erreur interne au serveur");
            System.err.println("Runnable_BISAMAP : recPay : IOException : " + ex.getMessage());
        }
        catch (SQLException ex)
        {
            SendMsg("NON#Erreur interne au serveur");
            System.err.println("Runnable_BISAMAP : recPay : SQLException : " + ex.getMessage());
        }
        catch (requeteException ex)
        {
            SendMsg("NON#Erreur interne au serveur");
            System.err.println("Runnable_BISAMAP : recPay : requeteException : " + ex.getMessage());
        }
    }
         
    
    /* ENVOYER A L'APP LES FACTURES NON PAYEES SUIVANT UN CRITERE */
    /* IN : NumCritere#(IdSociete) */
    private void listWaiting(String[] parts)
    {
        try
        {
            int longueur = dis.readInt();
            byte[] signature = new byte[longueur];
            dis.readFully(signature);
            
            String toSign = ProtocoleBISAMAP.LIST_BILLS + parts[1] + "#" + parts[2] + "#" + parts[3];
            boolean comparaisonSignature = Crypto.CompareSignature(toSign.getBytes(), "KSServeurCompta.p12", "azerty", "AppCompta", signature);
            
            if(comparaisonSignature == false)
            {
                SendMsg("NON#Signature non verifiee");
                System.err.println("Runnable_BISAMAP : listWaiting : Signature non vérifiée");
                return;
            }
            
            String where = "FLAG_FACT_PAYEE = 0";
            if(parts[1].equals("2")) // Depuis plus d'un mois
            {
                String date = new SimpleDateFormat("yyyy/MM").format(new Date());
                where += " AND MOIS_ANNEE < '" + date + "'";
            }
            else if(parts[1].equals("3"))   // Société
                where += " AND ID_SOCIETE = '" + parts[2]  + "'";
            
            ResultSet rs = beanOracle.selection("*", "FACTURES",  where);
            if(!rs.next())
            {
                SendMsg("NON#Pas de facture disponible");
                System.err.println("Runnable_BISAMAP : listWaiting : Pas de facture dispo");
                return;
            }
            
            List<Facture> listFactures = new ArrayList<Facture>();
            rs.beforeFirst();
            while(rs.next())
            {
                String i = rs.getString("ID_FACTURE");
                String s = rs.getString("ID_SOCIETE");
                String ma = rs.getString("MOIS_ANNEE");
                double th = rs.getDouble("TOTAL_HTVA");
                double tt = rs.getDouble("TOTAL_TVAC");
                int ffv = rs.getInt("FLAG_FACT_VALIDEE");
                String l = rs.getString("LOGIN");
                int ffe = rs.getInt("FLAG_FACT_ENVOYEE");
                String me = rs.getString("MOYEN_ENVOI");
                int ffp = rs.getInt("FLAG_FACT_PAYEE");
                
                Facture facture = new Facture (i, s, ma, th, tt, ffv, l, ffe, me, ffp);
                listFactures.add(facture);
            }
            
            byte[] list = Convert.ObjectToByteArray(listFactures);
            
            SendMsg("OUI#Factures envoyées");
            dos.writeInt(list.length);
            dos.write(list);
            dos.flush();
            System.out.println("Runnable_BISAMAP : listWaiting : Factures envoyées");
        }
        catch (IOException ex)
        {
            SendMsg("NON#Erreur interne au serveur");
            System.err.println("Runnable_BISAMAP : listWaiting : IOException : " + ex.getMessage());
        }
        catch (SQLException ex)
        {
            SendMsg("NON#Erreur interne au serveur");
            System.err.println("Runnable_BISAMAP : listWaiting : SQLException : " + ex.getMessage());
        }
    }
            
    
    private void computeSal(String[] parts)
    {
    }
    
    
    private void validateSal(String[] parts)
    {
    }
    
    
    /* Envoi d'un message au client */
    public void SendMsg(String msg)
    {
        String chargeUtile = msg;
        int taille = chargeUtile.length();
        StringBuffer message = new StringBuffer(String.valueOf(taille) + "#" + chargeUtile);
            
        try
        {               
            dos.write(message.toString().getBytes());
            dos.flush();
        }
        catch(IOException e)
        {
            System.err.println("Runnable_BISAMAP : SendMsg : Erreur d'envoi de msg (IO) : " + e.getMessage());
        }
    }
    
    
    /* Réception d'un message du client */
    public String ReceiveMsg()
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
            System.err.println("Runnable_BISAMAP : ReceiveMsg : Erreur de reception de msg (IO) : " + e.getMessage());
        }
            
        return message.toString();
    }
}
