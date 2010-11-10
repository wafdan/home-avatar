/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Pemesanan;

/**
 *
 * @author zulfikar
 */
public class RoomNotEnoughException extends Exception {

    /**
     * Creates a new instance of <code>RoomNotEnoughException</code> without detail message.
     */
    public RoomNotEnoughException() {
    }


    /**
     * Constructs an instance of <code>RoomNotEnoughException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public RoomNotEnoughException(String msg) {
        super(msg);
    }
}
