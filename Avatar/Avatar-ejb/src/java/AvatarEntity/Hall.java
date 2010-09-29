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
@Table(name = "hall")
@NamedQueries({
    @NamedQuery(name = "Hall.findAll", query = "SELECT h FROM Hall h"),
    @NamedQuery(name = "Hall.findByProductId", query = "SELECT h FROM Hall h WHERE h.productId = :productId"),
    @NamedQuery(name = "Hall.findByNormalRate", query = "SELECT h FROM Hall h WHERE h.normalRate = :normalRate"),
    @NamedQuery(name = "Hall.findByNormalRateUnit", query = "SELECT h FROM Hall h WHERE h.normalRateUnit = :normalRateUnit"),
    @NamedQuery(name = "Hall.findByStart", query = "SELECT h FROM Hall h WHERE h.start = :start"),
    @NamedQuery(name = "Hall.findByEndTime", query = "SELECT h FROM Hall h WHERE h.endTime = :endTime"),
    @NamedQuery(name = "Hall.findByOverchargeUnit", query = "SELECT h FROM Hall h WHERE h.overchargeUnit = :overchargeUnit"),
    @NamedQuery(name = "Hall.findByOverchargeRate", query = "SELECT h FROM Hall h WHERE h.overchargeRate = :overchargeRate")})
public class Hall implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "product_id")
    private String productId;
    @Basic(optional = false)
    @Column(name = "normal_rate")
    private double normalRate;
    @Basic(optional = false)
    @Column(name = "normal_rate_unit")
    private String normalRateUnit;
    @Basic(optional = false)
    @Column(name = "start")
    @Temporal(TemporalType.TIME)
    private Date start;
    @Basic(optional = false)
    @Column(name = "end_time")
    @Temporal(TemporalType.TIME)
    private Date endTime;
    @Basic(optional = false)
    @Column(name = "overcharge_unit")
    private String overchargeUnit;
    @Basic(optional = false)
    @Column(name = "overcharge_rate")
    private double overchargeRate;

    public Hall() {
    }

    public Hall(String productId) {
        this.productId = productId;
    }

    public Hall(String productId, double normalRate, String normalRateUnit, Date start, Date endTime, String overchargeUnit, double overchargeRate) {
        this.productId = productId;
        this.normalRate = normalRate;
        this.normalRateUnit = normalRateUnit;
        this.start = start;
        this.endTime = endTime;
        this.overchargeUnit = overchargeUnit;
        this.overchargeRate = overchargeRate;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public double getNormalRate() {
        return normalRate;
    }

    public void setNormalRate(double normalRate) {
        this.normalRate = normalRate;
    }

    public String getNormalRateUnit() {
        return normalRateUnit;
    }

    public void setNormalRateUnit(String normalRateUnit) {
        this.normalRateUnit = normalRateUnit;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getOverchargeUnit() {
        return overchargeUnit;
    }

    public void setOverchargeUnit(String overchargeUnit) {
        this.overchargeUnit = overchargeUnit;
    }

    public double getOverchargeRate() {
        return overchargeRate;
    }

    public void setOverchargeRate(double overchargeRate) {
        this.overchargeRate = overchargeRate;
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
        if (!(object instanceof Hall)) {
            return false;
        }
        Hall other = (Hall) object;
        if ((this.productId == null && other.productId != null) || (this.productId != null && !this.productId.equals(other.productId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AvatarEntity.Hall[productId=" + productId + "]";
    }

}
