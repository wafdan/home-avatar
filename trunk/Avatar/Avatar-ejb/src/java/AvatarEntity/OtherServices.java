/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package AvatarEntity;

import java.io.Serializable;
import java.util.Collection;
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

/**
 *
 * @author Christian
 */
@Entity
@Table(name = "other_services")
@NamedQueries({
    @NamedQuery(name = "OtherServices.findAll", query = "SELECT o FROM OtherServices o"),
    @NamedQuery(name = "OtherServices.findByProductId", query = "SELECT o FROM OtherServices o WHERE o.productId = :productId"),
    @NamedQuery(name = "OtherServices.findByProductType", query = "SELECT o FROM OtherServices o WHERE o.productType = :productType"),
    @NamedQuery(name = "OtherServices.findByPricingUnit", query = "SELECT o FROM OtherServices o WHERE o.pricingUnit = :pricingUnit"),
    @NamedQuery(name = "OtherServices.findByUnitPrice", query = "SELECT o FROM OtherServices o WHERE o.unitPrice = :unitPrice"),
    @NamedQuery(name = "OtherServices.findByPublished", query = "SELECT o FROM OtherServices o WHERE o.published = :published"),
    @NamedQuery(name = "OtherServices.findPublished", query = "SELECT o FROM OtherServices o WHERE o.published = 1")})
public class OtherServices implements Serializable {
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
    @Lob
    @Column(name = "image")
    private String image;
    @Basic(optional = false)
    @Column(name = "pricing_unit")
    private String pricingUnit;
    @Basic(optional = false)
    @Column(name = "unit_price")
    private double unitPrice;
    @Basic(optional = false)
    @Column(name = "published")
    private boolean published;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private Collection<OtherServicesReservation> otherServicesReservationCollection;

    public OtherServices() {
    }

    public OtherServices(String productId) {
        this.productId = productId;
    }

    public OtherServices(String productId, String productType, String pricingUnit, double unitPrice, boolean published) {
        this.productId = productId;
        this.productType = productType;
        this.pricingUnit = pricingUnit;
        this.unitPrice = unitPrice;
        this.published = published;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public boolean getPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public Collection<OtherServicesReservation> getOtherServicesReservationCollection() {
        return otherServicesReservationCollection;
    }

    public void setOtherServicesReservationCollection(Collection<OtherServicesReservation> otherServicesReservationCollection) {
        this.otherServicesReservationCollection = otherServicesReservationCollection;
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
