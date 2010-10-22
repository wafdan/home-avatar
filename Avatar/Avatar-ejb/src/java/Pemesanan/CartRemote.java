/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pemesanan;

import javax.ejb.Remote;
import java.rmi.RemoteException;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;

/**
 *
 * @author zulfikar
 */
@Remote
public interface CartRemote {

    public void setSessionContext(SessionContext ctx) throws EJBException, RemoteException;

    public void ejbRemove() throws EJBException, RemoteException;

    public void ejbActivate() throws EJBException, RemoteException;

    public void ejbPassivate() throws EJBException, RemoteException;
}
