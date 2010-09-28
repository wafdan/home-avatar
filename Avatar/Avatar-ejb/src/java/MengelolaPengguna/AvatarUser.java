/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MengelolaPengguna;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author zulfikar
 */
@Entity
@Table(name = "user")
@NamedQueries({
    @NamedQuery(name = "AvatarUser.findAll", query = "SELECT a FROM AvatarUser a"),
    @NamedQuery(name = "AvatarUser.findByUsername", query = "SELECT a FROM AvatarUser a WHERE a.username = :username"),
    @NamedQuery(name = "AvatarUser.findByName", query = "SELECT a FROM AvatarUser a WHERE a.name = :name")})
public class AvatarUser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Lob
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @Lob
    @Column(name = "email")
    private String email;

    public AvatarUser() {
    }

    public AvatarUser(String username) {
        this.username = username;
    }

    public AvatarUser(String username, String name, String password, String email) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AvatarUser)) {
            return false;
        }
        AvatarUser other = (AvatarUser) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MengelolaPengguna.AvatarUser[username=" + username + "]";
    }

}
