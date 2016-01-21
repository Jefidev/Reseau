package library_compta;

import java.io.Serializable;


public class RecPayAuth implements Serializable
{
    public RecPayClass data;
    public byte[] hmac;
    
    public RecPayAuth(RecPayClass d, byte[] h)
    {
        data = d;
        hmac = h;
    }
}
