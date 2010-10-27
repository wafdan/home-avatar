/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package webservice.model;

import java.sql.Time;

/**
 *
 * @author USER
 */
public class Facility {

    private String product_id;
    private String product_type;
    private String product_description;
    private int max_pax;
    private String normal_entry;
    private String normal_exit;
    private double weekday_rate;
    private double weekend_rate;
    private String tolerate_early;
    private String tolerate_late;

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public int getMax_pax() {
        return max_pax;
    }

    public void setMax_pax(int max_pax) {
        this.max_pax = max_pax;
    }

    public String getNormal_entry() {
        return normal_entry;
    }

    public void setNormal_entry(String normal_entry) {
        this.normal_entry = normal_entry;
    }

    public String getNormal_exit() {
        return normal_exit;
    }

    public void setNormal_exit(String normal_exit) {
        this.normal_exit = normal_exit;
    }

    public double getWeekday_rate() {
        return weekday_rate;
    }

    public void setWeekday_rate(double weekday_rate) {
        this.weekday_rate = weekday_rate;
    }

    public double getWeekend_rate() {
        return weekend_rate;
    }

    public void setWeekend_rate(double weekend_rate) {
        this.weekend_rate = weekend_rate;
    }

    public String getTolerate_early() {
        return tolerate_early;
    }

    public void setTolerate_early(String tolerate_early) {
        this.tolerate_early = tolerate_early;
    }

    public String getTolerate_late() {
        return tolerate_late;
    }

    public void setTolerate_late(String tolerate_late) {
        this.tolerate_late = tolerate_late;
    }
}
