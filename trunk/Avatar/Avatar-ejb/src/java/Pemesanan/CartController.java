/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pemesanan;

import AvatarEntity.Accomodation;
import AvatarEntity.AccomodationJpaController;
import AvatarEntity.Hall;
import AvatarEntity.HallJpaController;
import AvatarEntity.Room;
import AvatarEntity.RoomJpaController;
import AvatarEntity.RoomReservation;
import AvatarEntity.RoomReservationJpaController;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author zulfikar
 */
@Stateless
public class CartController implements CartControllerLocal {

    HashMap<String, String> pemetaanKodeKelasKamar;
    HashMap<String, Double> pemetaanKelasHargaKamarWeekday;
    HashMap<String, Double> pemetaanKelasHargaKamarWeekend;
    HashMap<String, String> pemetaanKodeTipeHall;
    HashMap<String, Double> pemetaanKodeHargaHall;

    public CartController() {
        List<Accomodation> listAcco = (new AccomodationJpaController()).findAccomodationEntities();
        Iterator<Accomodation> iAcco = listAcco.iterator();
        /* syalala buat yang ROOM*/

        pemetaanKodeKelasKamar = new HashMap<String, String>();
        pemetaanKelasHargaKamarWeekday = new HashMap<String, Double>();
        pemetaanKelasHargaKamarWeekend = new HashMap<String, Double>();
        pemetaanKodeTipeHall = new HashMap<String, String>();
        pemetaanKodeHargaHall = new HashMap<String, Double>();

        while (iAcco.hasNext()) {
            Accomodation temp = iAcco.next();
            pemetaanKodeKelasKamar.put(temp.getProductId(), temp.getProductType());
            pemetaanKelasHargaKamarWeekday.put(temp.getProductId(), temp.getWeekdayRate());
            pemetaanKelasHargaKamarWeekend.put(temp.getProductId(), temp.getWeekendRate());
        }

        /*Syalala buat yang HALL*/
        List<Hall> listHall = (new HallJpaController()).findHallEntities();
        Iterator<Hall> iHall = listHall.iterator();

        while (iHall.hasNext()) {
            Hall temp = iHall.next();
            pemetaanKodeTipeHall.put(temp.getProductId(), temp.getProductType());
            pemetaanKodeHargaHall.put(temp.getProductId(), temp.getNormalRate());
        }
    }

    public String getRoomProductType(String product_id) {
        return pemetaanKodeKelasKamar.get(product_id);
    }

    public int getDuration(Date tanggalMulai, Date tanggalSelesai) {
        Calendar iterator = Calendar.getInstance();
        Calendar selesai = Calendar.getInstance();
        iterator.setTime(tanggalMulai);
        selesai.setTime(tanggalSelesai);
        //System.out.println(iterator.DAY_OF_MONTH+"/"+iterator.MONTH+"/"+iterator.YEAR);

        int i = 0;
        selesai.add(Calendar.DAY_OF_MONTH, -1);
        if (tanggalSelesai.before(tanggalMulai)) {
            return 0;
        }
        while (!iterator.after(selesai)) {
            i++;
            iterator.add(Calendar.DAY_OF_MONTH, 1);
        }
        return i;
    }

    public Double getRoomPriceWeekend(String prod_id) {
        return pemetaanKelasHargaKamarWeekend.get(prod_id);
    }

    public Double getRoomPriceWeekday(String prod_id) {
        return pemetaanKelasHargaKamarWeekday.get(prod_id);
    }

    public double getHallPrice(String prod_id) {
        return pemetaanKodeHargaHall.get(prod_id);
    }

    public String getHallType(String prod_id) {
        return pemetaanKodeTipeHall.get(prod_id);
    }

    public double countTotalBill(Date tanggalMasuk, Date tanggalKeluar, double normalRate, double weekendrate) {
        Calendar calMasuk = Calendar.getInstance();
        calMasuk.setTime(tanggalMasuk);

        Calendar calKeluar = Calendar.getInstance();
        calKeluar.setTime(tanggalKeluar);

        calKeluar.add(Calendar.DATE, -1);
        double retval = 0;
        while (!calMasuk.after(calKeluar)) {
            int dayofweek = calMasuk.get(Calendar.DAY_OF_WEEK);
            if (dayofweek == Calendar.SUNDAY || dayofweek == Calendar.SATURDAY) {
                retval += weekendrate;
            } else {
                retval += normalRate;
            }
            calMasuk.add(Calendar.DATE, 1);
        }
        return retval;
    }

    public String generateRoomNumber(String product_id, Date entry_date, Date exit_date) throws Exception {
        String retval;
        List<Room> allRoom = (new RoomJpaController()).findRoomEntities();
        Iterator<Room> iAllRoom = allRoom.iterator();

        //Filter dulu room nya sesuai dengan tipe accomodation yang diinginkan
        while (iAllRoom.hasNext()) {
            Room temp = iAllRoom.next();
            if (!temp.getProductId().getProductId().equals(product_id)) {
                allRoom.remove(temp);
            }
        }

        //Udah gitu filter room reservation sesuai dengan tipe accomodation yang diinginkan
        List<RoomReservation> roomReservations = (new RoomReservationJpaController()).findReservationByEntryDate(entry_date);
        if (roomReservations != null) {
            Iterator<RoomReservation> iRoomReservation = roomReservations.iterator();

            while (iRoomReservation.hasNext()) {
                RoomReservation temp = iRoomReservation.next();
                if (!temp.getRoomNo().getProductId().getProductId().equals(product_id)) {
                    roomReservations.remove(temp);
                }
            }
        }

        //Masukkan ke hashmap semua elemen yang udah terisi, yaitu yang ada di reservationnya.
        HashMap<String, Boolean> hashTerisi = new HashMap<String, Boolean>();
        if (roomReservations != null) {
            Iterator<RoomReservation> iRoomReservation2 = roomReservations.iterator();
            while (iRoomReservation2.hasNext()) {
                RoomReservation temp = iRoomReservation2.next();
                hashTerisi.put(temp.getRoomNo().getRoomNo(), Boolean.TRUE);
            }
        }

        //udah gitu tinggal didapatkan satu nomor kamar kosong pertama yang ditemukan

        iAllRoom = allRoom.iterator();
        while (iAllRoom.hasNext()) {
            Room temp = iAllRoom.next();
            Boolean adakah = hashTerisi.get(temp.getRoomNo());
            if (adakah == null) {
                retval = temp.getRoomNo();
                return retval;
            }
        }
        throw new Exception("Tidak ditemukan kamar yang kosong, mohon maaf");

    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
