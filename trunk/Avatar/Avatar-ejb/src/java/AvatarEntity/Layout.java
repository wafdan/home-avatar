/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package AvatarEntity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Christian
 */
@Entity
@Table(name = "layout")
@NamedQueries({
    @NamedQuery(name = "Layout.findAll", query = "SELECT l FROM Layout l"),
    @NamedQuery(name = "Layout.findByLayoutNo", query = "SELECT l FROM Layout l WHERE l.layoutNo = :layoutNo"),
    @NamedQuery(name = "Layout.findByLayoutName", query = "SELECT l FROM Layout l WHERE l.layoutName = :layoutName")})
public class Layout implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "layout_no")
    private Integer layoutNo;
    @Basic(optional = false)
    @Column(name = "layout_name")
    private String layoutName;

    public Layout() {
    }

    public Layout(Integer layoutNo) {
        this.layoutNo = layoutNo;
    }

    public Layout(Integer layoutNo, String layoutName) {
        this.layoutNo = layoutNo;
        this.layoutName = layoutName;
    }

    public Integer getLayoutNo() {
        return layoutNo;
    }

    public void setLayoutNo(Integer layoutNo) {
        this.layoutNo = layoutNo;
    }

    public String getLayoutName() {
        return layoutName;
    }

    public void setLayoutName(String layoutName) {
        this.layoutName = layoutName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (layoutNo != null ? layoutNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Layout)) {
            return false;
        }
        Layout other = (Layout) object;
        if ((this.layoutNo == null && other.layoutNo != null) || (this.layoutNo != null && !this.layoutNo.equals(other.layoutNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AvatarEntity.Layout[layoutNo=" + layoutNo + "]";
    }

}
