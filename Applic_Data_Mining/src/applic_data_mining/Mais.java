/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applic_data_mining;

/**
 *
 * @author Jerome
 */
public final class Mais {
    
    private int Hauteur;
    private int HauteurJ7;
    private String Parcelle;
    private String Enracinement;
    private int Masse;
    private int NbrGrains;
    
    /*-------------------CONSTRUCTEURS------------------------------*/
    public Mais(int h, int hj7, String p, String e, int m, int ng)
    {
        setHauteur(h);
        setHauteurJ7(hj7);
        setParcelle(p);
        setEnracinement(e);
        setMasse(m);
        setNbrGrains(ng);
    }
    
    /*------------------GETTER/SETTER---------------------------*/
    
    //HAUTEUR
    public void setHauteur(int h)
    {
        Hauteur = h;
    }
    
    public int getHauteur()
    {
        return Hauteur;
    }
    
    
    //HAUTEUR J7
    public void setHauteurJ7(int hj7)
    {
        HauteurJ7 = hj7;
    }
    
    public int getHauteurJ7()
    {
        return HauteurJ7;
    }
    
    
    //PARCELLE
    public void setParcelle(String p)
    {
        Parcelle = p;
    }
    
    public String getParcelle()
    {
        return Parcelle;
    }
    
    
    //ENRACINEMENT
    public void setEnracinement(String e)
    {
        Enracinement = e;
    }
    
    public String getEnracinement()
    {
        return Enracinement;
    }
    
    //MASSE
    public void setMasse(int m)
    {
        Masse = m;
    }
    
    public int getMasse()
    {
        return Masse;
    }
    
    //NBR GRAIN
    public void setNbrGrains(int ng)
    {
        NbrGrains = ng;
    }
    
    public int getNbrGrains()
    {
        return NbrGrains;
    }
    
    
    
    @Override
    public String toString()
    {
        return "H: " + Hauteur + "  Hj7: " + HauteurJ7 + "  p: " + Parcelle + "  e: "+ Enracinement + "  m: "+ Masse + "  ng: "+NbrGrains;
    }
}