package library_compta;

import java.io.Serializable;


public class Facture implements Serializable
{
    public String IdFacture;
    public String IdSociete;
    public String MoisAnnee;
    public double TotalHTVA;
    public double TotalTVAC;
    public int FlagFactValidee;
    public String Login;
    public int FlagFactEnvoyee;
    public String MoyenEnvoi;
    public int FlagFactPayee;

    
    public Facture(String i, String s, String ma, double th, double tt, int ffv, String l, int ffe, String me, int ffp)
    {
        IdFacture = i;
        IdSociete = s;
        MoisAnnee = ma;
        TotalHTVA = th;
        TotalTVAC = tt;
        FlagFactValidee = ffv;
        Login = l;
        FlagFactEnvoyee = ffe;
        MoyenEnvoi = me;
        FlagFactPayee = ffp;
    }
    
    
    public String toString()
    {
        String msg = "Id facture = " + IdFacture + "; Id société = " + IdSociete + "; Mois/année = " + MoisAnnee;
        msg += "; Total HTVA = " + TotalHTVA + "; Total TVAC = " + TotalTVAC + "; Comptable = " + Login;
        msg += "; Moyen d'envoi = " + MoyenEnvoi;
        
        if(FlagFactValidee == 0)
            msg += "; Validée ? Pas encore";
        else if(FlagFactValidee == 1)
            msg += "; Validée ? Oui";
        else
            msg += "; Validée ? Refusée";
        
        if(FlagFactEnvoyee == 0)
            msg += "; Envoyée ? Non";
        else
            msg += "; Envoyée ? Oui";
        
        if(FlagFactPayee == 0)
            msg += "; Payée ? Non";
        else
            msg += "; Payée ? Oui";
        
        return msg;
    }
}
