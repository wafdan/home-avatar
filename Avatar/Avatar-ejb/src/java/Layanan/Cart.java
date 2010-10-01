/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Layanan;

import java.util.HashSet;
import java.util.List;

/**
 *
 * @author kamoe
 */
public class Cart {
    List<Integer> type;
    List<Object> obj;

    public Cart() {
        
    }

    public void addCart(int _type, Object _obj) {
        obj.add(_obj);
        type.add(_type);
    }

    public void deleteCart(Object _obj) {
        type.remove(type.indexOf(_obj));
        obj.remove(_obj);
    }

    public boolean isOnCart(Object _obj) {
        boolean b = false;
        if (obj != null) {
            b = obj.contains(_obj);
        }
        return b;
    }

    public int count() {
        int n = obj.size();
        if (n <= 0) {
            return 0;
        } else {
            return n;
        }
    }

}
