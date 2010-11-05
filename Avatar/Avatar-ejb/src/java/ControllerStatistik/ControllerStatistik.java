/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ControllerStatistik;

import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author Christian
 */
@Local
public interface ControllerStatistik {
    public void buatStatistik(Date from, Date to);
}
