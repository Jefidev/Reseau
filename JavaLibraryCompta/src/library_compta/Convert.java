package library_compta;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public final class Convert
{
    public static byte[] ObjectToByteArray(Object obj)
    {
        try
        {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            return baos.toByteArray();
        }
        catch (IOException ex)
        {
            System.err.println("Convert : ObjectToByteArray : IOException : " + ex.getMessage());
        }
        
        return null;
    }
    
    public static Object ByteArrayToObject(byte[] byteArray)
    {
        try
        {
            ByteArrayInputStream bais = new ByteArrayInputStream(byteArray);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        }
        catch (IOException ex)
        {
            System.err.println("Convert : ByteArrayToObject : IOException : " + ex.getMessage());
        }
        catch (ClassNotFoundException ex)
        {
            System.err.println("Convert : ByteArrayToObject : ClassNotFoundException : " + ex.getMessage());
        }
        
        return null;
    }
}
