package library_compta;

import java.io.Serializable;


public class RecPayAuth implements Serializable
{
    public RecPay data;
    public byte[] hmac;
    
    public RecPayAuth(RecPay d, byte[] h)
    {
        data = d;
        hmac = h;
    }
}
