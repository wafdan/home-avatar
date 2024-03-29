/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ControllerStatistik;

import AvatarEntity.Accomodation;
import AvatarEntity.AccomodationJpaController;
import AvatarEntity.Room;
import AvatarEntity.RoomReservation;
import AvatarEntity.RoomReservationJpaController;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.ejb.Stateless;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Christian
 */
@Stateless
public class ControllerStatistikRoom implements ControllerStatistik {
    private LinkedHashMap<Accomodation,LinkedHashMap<Date,Integer>> tabelPeriodik;

    public JFreeChart buatStatistik() {
        Calendar fromCal = Calendar.getInstance();
        int year = fromCal.get(Calendar.YEAR); int mo = fromCal.get(Calendar.MONTH);
        fromCal.setTimeInMillis(0);
        fromCal.set(year, mo, 1, 0, 0, 0);
        Calendar toCal = Calendar.getInstance();
        int dayamount = toCal.getActualMaximum(Calendar.DAY_OF_MONTH);
        toCal.setTimeInMillis(0);
        toCal.set(year, mo, dayamount, 0, 0, 0);
        return buatStatistik(fromCal.getTime(), toCal.getTime(), 0);
    }

    public JFreeChart buatPeriodik(Date from, Date to) {
        JFreeChart chart; //untuk nilai kembali
        //Inisialisasi kosong
        AccomodationJpaController ajpa = new AccomodationJpaController();
        Calendar fromCal = Calendar.getInstance();
        fromCal.setTime(from);
        Calendar toCal = Calendar.getInstance();
        toCal.setTime(to);
        Calendar current = Calendar.getInstance();
        tabelPeriodik = new LinkedHashMap<Accomodation,LinkedHashMap<Date,Integer>>();
        LinkedHashMap<Date,Integer> temphash;
        for (Accomodation item : ajpa.findAccomodationEntities()) {
            temphash = new LinkedHashMap<Date,Integer>();
            current.setTime(fromCal.getTime());
            while (!current.after(toCal)) {
                temphash.put(current.getTime(), 0);
                current.add(Calendar.DATE,1);
            }
            tabelPeriodik.put(item, temphash);

        }
        //Ambil data reservasi
        RoomReservationJpaController rrjpa = new RoomReservationJpaController();
        for(RoomReservation item : rrjpa.findByPeriod(fromCal.getTime(), toCal.getTime())) {
            Calendar entryCal = Calendar.getInstance();
            entryCal.setTime(item.getEntryDate());
            Calendar exitCal = Calendar.getInstance();
            exitCal.setTime(item.getExitDate());
            current.setTime(entryCal.getTime());
            while (current.before(exitCal)) {
                if (tabelPeriodik.get(item.getRoomNo().getProductId()).
                        containsKey(current.getTime())) {
                    tabelPeriodik.get(item.getRoomNo().getProductId()).put(current.getTime(),
                            tabelPeriodik.get(item.getRoomNo().getProductId()).get(current.getTime()) + 1);
                }
                current.add(Calendar.DATE, 1);
            }
        }

        //bentuk series dari seluruh jenis kamar
        DateFormat dateOnly = new SimpleDateFormat("d");
        DateFormat std = new SimpleDateFormat("dd MMM yyyy");
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series;
        for(Map.Entry<Accomodation,LinkedHashMap<Date,Integer>> acc : tabelPeriodik.entrySet()) {
            series = new XYSeries(acc.getKey().getProductType());
            for (Map.Entry<Date,Integer> date : acc.getValue().entrySet()) {
                series.add(date.getKey().getTime(), date.getValue());
            }
            dataset.addSeries(series);
        }
        chart = ChartFactory.createXYLineChart("Periodical Statistics of Room Usage " + std.format(from) + " - " + std.format(to),
                "Date", "Ammount of Occupancy", dataset, PlotOrientation.VERTICAL, true, true, false);
        XYItemRenderer renderer = new XYLineAndShapeRenderer();
        DecimalFormat decfor = new DecimalFormat();
        renderer.setBaseItemLabelGenerator(new StandardXYItemLabelGenerator());
        renderer.setBaseItemLabelsVisible(true);
        chart.getXYPlot().setRenderer(renderer);
        DateAxis da = new DateAxis();
        da.setDateFormatOverride(dateOnly);
        da.setTickUnit(new DateTickUnit(DateTickUnitType.DAY, 1));
        NumberAxis na = new NumberAxis();
        na.setNumberFormatOverride(decfor);
        na.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        chart.getXYPlot().setDomainAxis(da);
        chart.getXYPlot().setRangeAxis(na);
        return chart;
    }


    public void print() {
        for(Map.Entry<Accomodation,LinkedHashMap<Date,Integer>> acc : tabelPeriodik.entrySet()) {
            System.out.println("Accomodation is " + acc.getKey().getProductId());
            for (Map.Entry<Date,Integer> date : acc.getValue().entrySet()) {
                System.out.println(" > " + date.getKey() + " (" + date.getKey().getTime() + ") : " + date.getValue());
            }
        }
    }

    public JFreeChart buatRekap(Date from, Date to) {
        JFreeChart chart = null;
        Hashtable<Accomodation,Double> arrayAvg = new Hashtable<Accomodation,Double>();
        Hashtable<Accomodation,Integer> arrayMax = new Hashtable<Accomodation,Integer>();
        Hashtable<Accomodation,Integer> arrayMin = new Hashtable<Accomodation,Integer>();
        Calendar fromCal = Calendar.getInstance();
        fromCal.setTime(from);
        Calendar toCal = Calendar.getInstance();
        toCal.setTime(to);
        //Inisialisasi kosong
        AccomodationJpaController ajpa = new AccomodationJpaController();
        RoomReservationJpaController rrjpa = new RoomReservationJpaController();
        //Perhitungan rataaan, maksimum, dan minimum lama tinggal
        float length, sum, count, max, min; // penghitung
        for (Accomodation acc : ajpa.findAccomodationEntities()) {
            sum = 0; count = 0; //inisialisasi penghitung per jenis kamar
            max = 0; min = Float.POSITIVE_INFINITY;
            for (Room rm : acc.getRoomCollection()) {
                for (RoomReservation rr : rrjpa.findByPeriod(rm, from, to)) {
                    length = (int) ((rr.getExitDate().getTime() - rr.getEntryDate().getTime()) / 86400000);
                    if (max < length) max = length;
                    if (min > length) min = length;
                    sum += length;
                    count++;
                }
            }
            if (min == Float.POSITIVE_INFINITY) min = 0;
            arrayMax.put(acc, (int) max); arrayMin.put(acc, (int) min);
            arrayAvg.put(acc, Double.valueOf(sum/count));
        }
        DateFormat std = new SimpleDateFormat("dd MMM yyyy");
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<Accomodation,Integer> acc : arrayMin.entrySet()) {
            dataset.addValue(acc.getValue(), "minimum", acc.getKey().getProductType());
        }
        for (Map.Entry<Accomodation,Double> acc : arrayAvg.entrySet()) {
            dataset.addValue(acc.getValue(), "average", acc.getKey().getProductType());
        }
        for (Map.Entry<Accomodation,Integer> acc : arrayMax.entrySet()) {
            dataset.addValue(acc.getValue(), "maximum", acc.getKey().getProductType());
        }
        chart = ChartFactory.createBarChart("General Statistics of Length of Stay " + std.format(from) + " - " + std.format(to),
                "Room Category", "Length of Stay", dataset, PlotOrientation.VERTICAL, true, true, false);
        CategoryItemRenderer renderer = new BarRenderer();
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setBaseItemLabelsVisible(true);
        NumberAxis na = new NumberAxis();
        DecimalFormat decfor = new DecimalFormat();
        na.setNumberFormatOverride(decfor);
        na.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        chart.getCategoryPlot().setRangeAxis(na);
        chart.getCategoryPlot().setRenderer(renderer);
        return chart;
    }

    public JFreeChart buatStatistik(Date from, Date to, int mode) {
        JFreeChart chart;
        switch (mode) {
            case 0: chart = buatPeriodik(from, to); break;
            case 1: chart = buatRekap(from, to); break;
            default: chart = null;
        }
        return chart;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

}
