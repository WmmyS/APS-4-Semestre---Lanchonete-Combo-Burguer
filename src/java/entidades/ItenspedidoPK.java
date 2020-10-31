/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author lucas
 */
@Embeddable
public class ItenspedidoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idproduto")
    private int idproduto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idpedido")
    private int idpedido;

    public ItenspedidoPK() {
    }

    public ItenspedidoPK(int idproduto, int idpedido) {
        this.idproduto = idproduto;
        this.idpedido = idpedido;
    }

    public int getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(int idproduto) {
        this.idproduto = idproduto;
    }

    public int getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(int idpedido) {
        this.idpedido = idpedido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idproduto;
        hash += (int) idpedido;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItenspedidoPK)) {
            return false;
        }
        ItenspedidoPK other = (ItenspedidoPK) object;
        if (this.idproduto != other.idproduto) {
            return false;
        }
        if (this.idpedido != other.idpedido) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.ItenspedidoPK[ idproduto=" + idproduto + ", idpedido=" + idpedido + " ]";
    }
    
}
