/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package AvatarEntity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Christian
 */
@Entity
@Table(name = "other_services")
@NamedQueries({
    @NamedQuery(name = "OtherServices.findAll", query = "SELECT o FROM OtherServices o"),
    @NamedQuery(name = "OtherServices.findByProductId", query = "SELECT o FROM OtherServices o WHERE o.productId = :productId"),
    @NamedQuery(name = "OtherServices.findByPricingUnit", query = "SELECT o FROM OtherServices o WHERE o.pricingUnit = :pricingUnit"),
    @NamedQuery(name = "OtherServices.findByUnitPrice", query = "SELECT o FROM OtherServices o WHERE o.unitPrice = :unitPrice")})
public class OtherServices implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "product_id")
    private String productId;
    @Basic(optional = false)
    @Column(name = "pricing_unit")
    private String pricingUnit;
    @Basic(optional = false)
    @Column(name = "unit_price")
    private double unitPrice;

    public OtherServices() {
    }

    public OtherServices(String productId) {
        this.productId = productId;
    }

    public OtherServices(String productId, String pricingUnit, double unitPrice) {
        this.productId = productId;
        this.pricingUnit = pricingUnit;
        this.unitPrice = unitPrice;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getPricingUnit() {
        return pricingUnit;
    }

    public void setPricingUnit(String pricingUnit) {
        this.pricingUnit = pricingUnit;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
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
        if (!(object instanceof OtherServices)) {
            return false;
        }
        OtherServices other = (OtherServices) object;
        if ((this.productId == null && other.productId != null) || (this.productId != null && !this.productId.equals(other.productId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AvatarEntity.OtherServices[productId=" + productId + "]";
    }

}
