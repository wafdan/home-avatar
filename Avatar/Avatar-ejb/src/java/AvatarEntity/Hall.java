/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package AvatarEntity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
    @NamedQuery(name = "Hall.findByProductType", query = "SELECT h FROM Hall h WHERE h.productType = :productType"),
    @NamedQuery(name = "Hall.findByNormalRate", query = "SELECT h FROM Hall h WHERE h.normalRate = :normalRate"),
    @NamedQuery(name = "Hall.findByNormalRateUnit", query = "SELECT h FROM Hall h WHERE h.normalRateUnit = :normalRateUnit"),
    @NamedQuery(name = "Hall.findByStartTime", query = "SELECT h FROM Hall h WHERE h.startTime = :startTime"),
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
    @Column(name = "product_type")
    private String productType;
    @Lob
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "normal_rate")
    private double normalRate;
    @Basic(optional = false)
    @Column(name = "normal_rate_unit")
    private String normalRateUnit;
    @Basic(optional = false)
    @Column(name = "start_time")
    @Temporal(TemporalType.TIME)
    private Date startTime;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private Collection<HallReservation> hallReservationCollection;

    public Hall() {
    }

    public Hall(String productId) {
        this.productId = productId;
    }

    public Hall(String productId, String productType, double normalRate, String normalRateUnit, Date startTime, Date endTime, String overchargeUnit, double overchargeRate) {
        this.productId = productId;
        this.productType = productType;
        this.normalRate = normalRate;
        this.normalRateUnit = normalRateUnit;
        this.startTime = startTime;
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

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
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

    public Collection<HallReservation> getHallReservationCollection() {
        return hallReservationCollection;
    }

    public void setHallReservationCollection(Collection<HallReservation> hallReservationCollection) {
        this.hallReservationCollection = hallReservationCollection;
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
