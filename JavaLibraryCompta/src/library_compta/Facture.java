package library_compta;


public class Facture
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
}
