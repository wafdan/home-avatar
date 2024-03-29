/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ControllerStatistik;

import AvatarEntity.Hall;
import AvatarEntity.HallJpaController;
import AvatarEntity.HallReservation;
import AvatarEntity.HallReservationJpaController;
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
public class ControllerStatistikHall implements ControllerStatistik {
    private LinkedHashMap<Hall,LinkedHashMap<Date,Integer>> tabelPeriodik;

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
        HallJpaController hjpa = new HallJpaController();
        Calendar fromCal = Calendar.getInstance();
        fromCal.setTime(from);
        Calendar toCal = Calendar.getInstance();
        toCal.setTime(to);
        Calendar current = Calendar.getInstance();
        tabelPeriodik = new LinkedHashMap<Hall, LinkedHashMap<Date, Integer>>();
        LinkedHashMap<Date,Integer> temphash;
        for (Hall item : hjpa.findHallEntities()) {
            temphash = new LinkedHashMap<Date,Integer>();
            current.setTime(fromCal.getTime());
            while (!current.after(toCal)) {
                temphash.put(current.getTime(), 0);
                current.add(Calendar.DATE, 1);
            }
            tabelPeriodik.put(item, temphash);
        }
        //Ambil data reservasi
        HallReservationJpaController hrjpa = new HallReservationJpaController();
        for(HallReservation item : hrjpa.findByPeriod(fromCal.getTime(), toCal.getTime())) {
            if (tabelPeriodik.get(item.getProductId()).containsKey(item.getUseDate())) {
                tabelPeriodik.get(item.getProductId()).put(item.getUseDate(),
                        tabelPeriodik.get(item.getProductId()).get(item.getUseDate()) +
                        item.getAttendees());
            }
        }
        
        //bentuk series dari seluruh jenis kamar
        DateFormat dateOnly = new SimpleDateFormat("d");
        DateFormat std = new SimpleDateFormat("dd MMM yyyy");
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series;
        for(Map.Entry<Hall,LinkedHashMap<Date,Integer>> hall : tabelPeriodik.entrySet()) {
            series = new XYSeries(hall.getKey().getProductType());
            for (Map.Entry<Date,Integer> date : hall.getValue().entrySet()) {
                series.add(date.getKey().getTime(), date.getValue());
            }
            dataset.addSeries(series);
        }
        chart = ChartFactory.createXYLineChart("Periodical Statistics of Package by Pax Number "  + std.format(from) + " - " + std.format(to),
                "Date", "Pax Number", dataset, PlotOrientation.VERTICAL, true, true, false);
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public JFreeChart buatRekap(Date from, Date to) {
        JFreeChart chart = null;
        Hashtable<Hall,Double> arrayAvg = new Hashtable<Hall,Double>();
        Hashtable<Hall,Integer> arrayMax = new Hashtable<Hall,Integer>();
        Hashtable<Hall,Integer> arrayMin = new Hashtable<Hall,Integer>();
        Calendar fromCal = Calendar.getInstance();
        fromCal.setTime(from);
        Calendar toCal = Calendar.getInstance();
        toCal.setTime(to);
        //Inisialisasi kosong
        HallJpaController hjpa = new HallJpaController();
        HallReservationJpaController hrjpa = new HallReservationJpaController();
        //Perhitungan rataaan, maksimum, dan minimum hadirin
        float sum, count, max, min; // penghitung
        for (Hall hall : hjpa.findHallEntities()) {
            sum = 0; count = 0; //inisialisasi penghitung per jenis kamar
            max = 0; min = Float.POSITIVE_INFINITY;
            for (HallReservation hr : hrjpa.findByPeriod(hall, from, to)) {
                if (max < hr.getAttendees()) max = hr.getAttendees();
                if (min > hr.getAttendees()) min = hr.getAttendees();
                sum += hr.getAttendees();
                count++;
            }
            if (min == Float.POSITIVE_INFINITY) min = 0;
            arrayMax.put(hall, (int) max); arrayMin.put(hall, (int) min);
            arrayAvg.put(hall, Double.valueOf(sum/count));
        }
        DateFormat std = new SimpleDateFormat("dd MMM yyyy");
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<Hall,Integer> item : arrayMin.entrySet()) {
            dataset.addValue(item.getValue(), "minimum", item.getKey().getProductType());
        }
        for (Map.Entry<Hall,Double> item : arrayAvg.entrySet()) {
            dataset.addValue(item.getValue(), "average", item.getKey().getProductType());
        }
        for (Map.Entry<Hall,Integer> item : arrayMax.entrySet()) {
            dataset.addValue(item.getValue(), "maximum", item.getKey().getProductType());
        }
        chart = ChartFactory.createBarChart("General Statistics of Event Attendees " + std.format(from) + " - " + std.format(to),
                "Event Category", "Pax Number", dataset, PlotOrientation.VERTICAL, true, true, false);
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
