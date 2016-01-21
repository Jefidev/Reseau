package library_compta;

import java.io.Serializable;


public class RecPay implements Serializable
{
    public String idFacture;
    public double montant;
    public String compte;
    
    public RecPay(String i, double m, String c)
    {
        idFacture = i;
        montant = m;
        compte = c;
    }
    
}
