/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lucas
 */
@Entity
@Table(name = "itenspedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Itenspedido.findAll", query = "SELECT i FROM Itenspedido i")
    , @NamedQuery(name = "Itenspedido.findByIdproduto", query = "SELECT i FROM Itenspedido i WHERE i.itenspedidoPK.idproduto = :idproduto")
    , @NamedQuery(name = "Itenspedido.findByIdpedido", query = "SELECT i FROM Itenspedido i WHERE i.itenspedidoPK.idpedido = :idpedido")
    , @NamedQuery(name = "Itenspedido.findByQuantidade", query = "SELECT i FROM Itenspedido i WHERE i.quantidade = :quantidade")
    , @NamedQuery(name = "Itenspedido.findByValorunitario", query = "SELECT i FROM Itenspedido i WHERE i.valorunitario = :valorunitario")
    , @NamedQuery(name = "Itenspedido.findByValortotal", query = "SELECT i FROM Itenspedido i WHERE i.valortotal = :valortotal")})
public class Itenspedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ItenspedidoPK itenspedidoPK;
    @Column(name = "quantidade")
    private Integer quantidade;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valorunitario")
    private Double valorunitario;
    @Column(name = "valortotal")
    private Double valortotal;
    @JoinColumn(name = "idpedido", referencedColumnName = "idpedido", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pedidos pedidos;
    @JoinColumn(name = "idproduto", referencedColumnName = "idproduto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Produtos produtos;

    public Itenspedido() {
    }

    public Itenspedido(ItenspedidoPK itenspedidoPK) {
        this.itenspedidoPK = itenspedidoPK;
    }

    public Itenspedido(int idproduto, int idpedido) {
        this.itenspedidoPK = new ItenspedidoPK(idproduto, idpedido);
    }

    public ItenspedidoPK getItenspedidoPK() {
        return itenspedidoPK;
    }

    public void setItenspedidoPK(ItenspedidoPK itenspedidoPK) {
        this.itenspedidoPK = itenspedidoPK;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorunitario() {
        return valorunitario;
    }

    public void setValorunitario(Double valorunitario) {
        this.valorunitario = valorunitario;
    }

    public Double getValortotal() {
        return valortotal;
    }

    public void setValortotal(Double valortotal) {
        this.valortotal = valortotal;
    }

    public Pedidos getPedidos() {
        return pedidos;
    }

    public void setPedidos(Pedidos pedidos) {
        this.pedidos = pedidos;
    }

    public Produtos getProdutos() {
        return produtos;
    }

    public void setProdutos(Produtos produtos) {
        this.produtos = produtos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itenspedidoPK != null ? itenspedidoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Itenspedido)) {
            return false;
        }
        Itenspedido other = (Itenspedido) object;
        if ((this.itenspedidoPK == null && other.itenspedidoPK != null) || (this.itenspedidoPK != null && !this.itenspedidoPK.equals(other.itenspedidoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Itenspedido[ itenspedidoPK=" + itenspedidoPK + " ]";
    }
    
}
