/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newBean;

/**
 *
 * @author Jerome
 */
public class connexionException extends Exception {
    
    private int numException;
    
    /**
     * Creates a new instance of <code>connexionException</code> without detail
     * message.
     */
    public connexionException() {
    }

    /**
     * Constructs an instance of <code>connexionException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     * @param num the id number of the exception
     */
    public connexionException(String msg, int num) {
        super(msg);
        numException = num;
    }
    
    public int getNumException()
    {
        return numException;
    }
}
