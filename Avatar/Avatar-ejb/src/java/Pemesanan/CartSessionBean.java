/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pemesanan;

import AvatarEntity.Hall;
import AvatarEntity.HallJpaController;
import AvatarEntity.HallReservation;
import AvatarEntity.HallReservationJpaController;
import AvatarEntity.Room;
import AvatarEntity.RoomJpaController;
import AvatarEntity.RoomReservation;
import AvatarEntity.RoomReservationJpaController;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.annotation.PostConstruct;

/**
 *
 * @author zulfikar
 */
@Stateful
public class CartSessionBean implements CartSessionBeanLocal, SessionBean {

    private ArrayList<HallSessionInfo> hallCart;
    private ArrayList<RoomSessionInfo> roomCart;
    private HashMap<String, String> MappingRoom_Class;
    private HashMap<String, String> MappingHall_Class;
    public List<Room> listroom = (new RoomJpaController()).findRoomEntities();
    public List<Hall> listhall = (new HallJpaController()).findHallEntities();

    @PostConstruct
    public void initialize() {
        hallCart = new ArrayList<HallSessionInfo>();
        roomCart = new ArrayList<RoomSessionInfo>();
    }

        public void businessMethod() {
    }

    public CartSessionBeanLocal create() throws CreateException {
        return null;
    }

    public void ejbCreate() throws CreateException {
        hallCart = new ArrayList<HallSessionInfo>();
        roomCart = new ArrayList<RoomSessionInfo>();
    }

    public ArrayList<HallSessionInfo> getHallCart() {
        return this.hallCart;
    }

    public ArrayList<RoomSessionInfo> getRoomCart() {
        return this.roomCart;
    }

    public List<Room> getListRoom() {
        return this.listroom;
    }

    public List<Hall> getListHall() {
        return this.listhall;
    }

    public void setListHall(List<Hall> input) {
        this.listhall = input;
    }

    public void setListRoom(List<Room> input) {
        this.listroom = input;
    }

    public void setSessionContext(SessionContext ctx) throws EJBException, RemoteException {
    }

    public void ejbRemove() throws EJBException, RemoteException {
        /*hallCart.clear();
        roomCart.clear();
        MappingRoom_Class.clear();
        listroom.clear();
        listhall.clear();*/
    }

    public void ejbActivate() throws EJBException, RemoteException {
    }

    public void ejbPassivate() throws EJBException, RemoteException {
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    //Menambahkan elemen ke Cart Element untuk hall
    public void addHallCartElement(String product_id, Date useDate, short total, int capacity, int layout_id) {
        HallSessionInfo h = new HallSessionInfo();
        CartController c=new CartController();
        h.product_id = product_id;
        h.product_name=c.getHallType(product_id);
        h.total = total;
        h.use_date = useDate;
        h.layout_id=layout_id;
        h.layout_name=c.getLayoutName(layout_id);
        h.attendees=capacity;
        hallCart.add(h);
    }

    //Menambahkan elemen ke Cart element untuk room
    public void addRoomCartElement(String product_id, Date entry_date, Date exit_date, short total) {
        RoomSessionInfo r = new RoomSessionInfo();
        r.entry_date = entry_date;
        r.exit_date = exit_date;
        r.total = total;
        r.product_id = product_id;
        r.available = checkAvailabilityRoom(r.product_id, r.entry_date, r.exit_date, r.total);
        roomCart.add((r));
    }

    public int getRoomCount() {
        return roomCart.size();
    }

    public int getHallCount() {
        return hallCart.size();
    }

    //Mengecek availability untuk  Room dengan atribut tertentu
    private boolean checkAvailabilityRoom(String room_no, Date entry_date, Date exit_date, short totalNeeded) {
        Calendar current = Calendar.getInstance();
        Calendar end = Calendar.getInstance();

        current.setTime(entry_date);
        end.setTime(exit_date);
        end.add(Calendar.DATE, -1);

        boolean retval = true;

        for (; !current.after(end); current.add(Calendar.DATE, 1)) {
            retval = retval || (countAvailableRoomByType(room_no, entry_date) - totalNeeded >= 0);
        }
        return retval;
    }

    //Mengecek availability untuk hall dengan atribut tertentu
    private boolean checkAvailabilityHall(String product_id, Date useDate, short total) {
        int AvailableHall=countAvailableHallByType(product_id, useDate);
        System.out.println("Menghitung availability Hall. AvailableHall="+AvailableHall+" Total= "+total);
        return (AvailableHall - total >= 0);
    }

    //Menghitung seluruh jumlah ruangan untuk tipe ruangan tertentu
    private int countRoomType(String product_id) {
        int retval = 0;
        for (Room r : listroom) {
            if (r.getProductId().getProductId().equals(product_id)) {
                retval++;
            }
        }
        return retval;
    }

    //Menghitung seluruh jumlah hall untuk tipe hall tertentu
    private int countHallType(String product_id) {
        int retval = 0;
        for (Hall r : listhall) {
            if (r.getProductId().equals(product_id)) {
                retval++;
            }
        }
        return retval;
    }

    //menghiung seluruh jumlah room tertentu untuk room tertentu
    private int countRoomType(String product_id, List<RoomReservation> listroom) {
        int retval = 0;
        for (RoomReservation r : listroom) {
            if (r.getRoomNo().getProductId().getProductId().equals(product_id)) {
                retval++;
            }
        }
        return retval;
    }

    private int countHallType(String product_id, List<HallReservation> listhall) {
        int retval = 0;
        for (HallReservation h : listhall) {
            if (h.getProductId().getProductId().equals(product_id)) {
                retval++;
            }
        }
        return retval;
    }

    //Menghtung seluruh jumlah room yang tersedia, yaitu jumlah room dikurangi
    //yang sudah terpakai
    private int countAvailableRoomByType(String product_id, Date date) {
        int jumlahRoombyProductId = countRoomType(product_id); //ini jumlah roomnya
        List<RoomReservation> listroomreservation = (new RoomReservationJpaController()).findReservationByEntryDate(date);
        int jumlahRoomTerpakai = countRoomType(product_id, listroomreservation);  //ini yang terpakai
        return jumlahRoombyProductId - jumlahRoomTerpakai;
    }

    //Sama seperti kasus di atas, namun untuk hall
    private int countAvailableHallByType(String product_id, Date date) {
        int jumlahHallByHallId = countHallType(product_id);
        List<HallReservation> listhallreservation = (new HallReservationJpaController()).findReservationByUseDate(date);
        int jumlahHallTerpakai = countHallType(product_id, listhallreservation);
        return jumlahHallByHallId - jumlahHallTerpakai;
    }

    //Membangkitkan mapping Room ke tipe roomnya.
    private void generateRoomTypeandNumberMapping() {
        for (Room r : listroom) {
            MappingRoom_Class.put(r.getRoomNo(), r.getProductId().getProductId());
        }
    }

    private void generateHallTypeandNumberMapping() {
        for (Hall h : listhall) {
            MappingHall_Class.put(h.getProductId(), h.getProductType());
        }
    }
}
