/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package AvatarEntity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Christian
 */
@Entity
@Table(name = "accomodation")
@NamedQueries({
    @NamedQuery(name = "Accomodation.findAll", query = "SELECT a FROM Accomodation a"),
    @NamedQuery(name = "Accomodation.findByProductId", query = "SELECT a FROM Accomodation a WHERE a.productId = :productId"),
    @NamedQuery(name = "Accomodation.findByMaxPax", query = "SELECT a FROM Accomodation a WHERE a.maxPax = :maxPax"),
    @NamedQuery(name = "Accomodation.findByNormalEntry", query = "SELECT a FROM Accomodation a WHERE a.normalEntry = :normalEntry"),
    @NamedQuery(name = "Accomodation.findByNormalExit", query = "SELECT a FROM Accomodation a WHERE a.normalExit = :normalExit"),
    @NamedQuery(name = "Accomodation.findByWeekdayRate", query = "SELECT a FROM Accomodation a WHERE a.weekdayRate = :weekdayRate"),
    @NamedQuery(name = "Accomodation.findByWeekendRate", query = "SELECT a FROM Accomodation a WHERE a.weekendRate = :weekendRate"),
    @NamedQuery(name = "Accomodation.findByToleranceEarly", query = "SELECT a FROM Accomodation a WHERE a.toleranceEarly = :toleranceEarly"),
    @NamedQuery(name = "Accomodation.findByToleranceLate", query = "SELECT a FROM Accomodation a WHERE a.toleranceLate = :toleranceLate")})
public class Accomodation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "product_id")
    private String productId;
    @Basic(optional = false)
    @Column(name = "max_pax")
    private int maxPax;
    @Basic(optional = false)
    @Column(name = "normal_entry")
    @Temporal(TemporalType.TIME)
    private Date normalEntry;
    @Basic(optional = false)
    @Column(name = "normal_exit")
    @Temporal(TemporalType.TIME)
    private Date normalExit;
    @Basic(optional = false)
    @Column(name = "weekday_rate")
    private double weekdayRate;
    @Basic(optional = false)
    @Column(name = "weekend_rate")
    private double weekendRate;
    @Basic(optional = false)
    @Column(name = "tolerance_early")
    @Temporal(TemporalType.TIME)
    private Date toleranceEarly;
    @Basic(optional = false)
    @Column(name = "tolerance_late")
    @Temporal(TemporalType.TIME)
    private Date toleranceLate;

    public Accomodation() {
    }

    public Accomodation(String productId) {
        this.productId = productId;
    }

    public Accomodation(String productId, int maxPax, Date normalEntry, Date normalExit, double weekdayRate, double weekendRate, Date toleranceEarly, Date toleranceLate) {
        this.productId = productId;
        this.maxPax = maxPax;
        this.normalEntry = normalEntry;
        this.normalExit = normalExit;
        this.weekdayRate = weekdayRate;
        this.weekendRate = weekendRate;
        this.toleranceEarly = toleranceEarly;
        this.toleranceLate = toleranceLate;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getMaxPax() {
        return maxPax;
    }

    public void setMaxPax(int maxPax) {
        this.maxPax = maxPax;
    }

    public Date getNormalEntry() {
        return normalEntry;
    }

    public void setNormalEntry(Date normalEntry) {
        this.normalEntry = normalEntry;
    }

    public Date getNormalExit() {
        return normalExit;
    }

    public void setNormalExit(Date normalExit) {
        this.normalExit = normalExit;
    }

    public double getWeekdayRate() {
        return weekdayRate;
    }

    public void setWeekdayRate(double weekdayRate) {
        this.weekdayRate = weekdayRate;
    }

    public double getWeekendRate() {
        return weekendRate;
    }

    public void setWeekendRate(double weekendRate) {
        this.weekendRate = weekendRate;
    }

    public Date getToleranceEarly() {
        return toleranceEarly;
    }

    public void setToleranceEarly(Date toleranceEarly) {
        this.toleranceEarly = toleranceEarly;
    }

    public Date getToleranceLate() {
        return toleranceLate;
    }

    public void setToleranceLate(Date toleranceLate) {
        this.toleranceLate = toleranceLate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productId != null ? productId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Accomodation)) {
            return false;
        }
        Accomodation other = (Accomodation) object;
        if ((this.productId == null && other.productId != null) || (this.productId != null && !this.productId.equals(other.productId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AvatarEntity.Accomodation[productId=" + productId + "]";
    }

}
