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
public class requeteException extends Exception {
    
    private int numErreur;
    
    /**
     * Creates a new instance of <code>requeteException</code> without detail
     * message.
     */
    public requeteException() {
    }

    /**
     * Constructs an instance of <code>requeteException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     * @param num error number
     */
    public requeteException(String msg, int num) {
        super(msg);
        numErreur = num;
    }
    
    public int getNumErreur()
    {
        return numErreur;
    }
}
