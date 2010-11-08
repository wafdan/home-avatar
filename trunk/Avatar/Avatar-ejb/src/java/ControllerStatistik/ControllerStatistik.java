/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ControllerStatistik;

import java.util.Date;
import javax.ejb.Local;
import org.jfree.chart.JFreeChart;

/**
 *
 * @author Christian
 */
@Local
public interface ControllerStatistik {
    public JFreeChart buatStatistik();
    public JFreeChart buatStatistik(Date from, Date to, int mode);
    public void print(); //TODO: eliminate
}
