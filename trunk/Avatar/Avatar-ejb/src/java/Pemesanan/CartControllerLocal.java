/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Pemesanan;

import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author zulfikar
 */
@Local
public interface CartControllerLocal {

    public String getRoomProductType(String product_id);

    public int getDuration(Date tanggalMulai, Date tanggalSelesai);

    public Double getRoomPriceWeekday(String prod_id);

    public Double getRoomPriceWeekend(String prod_id);

    double getHallPrice(String prod_id);

    String getHallType(String prod_id);

    public double countTotalBill(Date tanggalMasuk, Date tanggalKeluar, double normalRate, double weekendrate);

    public String generateRoomNumber(String product_id, Date entry_date, Date exit_date) throws Exception;

    String getLayoutName(int parameter);

}
