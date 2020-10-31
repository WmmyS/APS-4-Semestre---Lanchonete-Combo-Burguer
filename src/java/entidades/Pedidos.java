/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lucas
 */
@Entity
@Table(name = "pedidos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedidos.findAll", query = "SELECT p FROM Pedidos p")
    , @NamedQuery(name = "Pedidos.findByIdpedido", query = "SELECT p FROM Pedidos p WHERE p.idpedido = :idpedido")
    , @NamedQuery(name = "Pedidos.findByDatapedido", query = "SELECT p FROM Pedidos p WHERE p.datapedido = :datapedido")
    , @NamedQuery(name = "Pedidos.findByHorapedido", query = "SELECT p FROM Pedidos p WHERE p.horapedido = :horapedido")
    , @NamedQuery(name = "Pedidos.findByValorpedido", query = "SELECT p FROM Pedidos p WHERE p.valorpedido = :valorpedido")})
public class Pedidos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpedido")
    private Integer idpedido;
    @Column(name = "datapedido")
    @Temporal(TemporalType.DATE)
    private Date datapedido;
    @Column(name = "horapedido")
    @Temporal(TemporalType.TIME)
    private Date horapedido;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valorpedido")
    private Double valorpedido;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedidos")
    private Collection<Itenspedido> itenspedidoCollection;
    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente")
    @ManyToOne(optional = false)
    private Cliente idcliente;

    public Pedidos() {
    }

    public Pedidos(Integer idpedido) {
        this.idpedido = idpedido;
    }

    public Integer getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(Integer idpedido) {
        this.idpedido = idpedido;
    }

    public Date getDatapedido() {
        return datapedido;
    }

    public void setDatapedido(Date datapedido) {
        this.datapedido = datapedido;
    }

    public Date getHorapedido() {
        return horapedido;
    }

    public void setHorapedido(Date horapedido) {
        this.horapedido = horapedido;
    }

    public Double getValorpedido() {
        return valorpedido;
    }

    public void setValorpedido(Double valorpedido) {
        this.valorpedido = valorpedido;
    }

    @XmlTransient
    public Collection<Itenspedido> getItenspedidoCollection() {
        return itenspedidoCollection;
    }

    public void setItenspedidoCollection(Collection<Itenspedido> itenspedidoCollection) {
        this.itenspedidoCollection = itenspedidoCollection;
    }

    public Cliente getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Cliente idcliente) {
        this.idcliente = idcliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpedido != null ? idpedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedidos)) {
            return false;
        }
        Pedidos other = (Pedidos) object;
        if ((this.idpedido == null && other.idpedido != null) || (this.idpedido != null && !this.idpedido.equals(other.idpedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Pedidos[ idpedido=" + idpedido + " ]";
    }
    
}
