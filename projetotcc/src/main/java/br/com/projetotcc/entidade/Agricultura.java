package br.com.projetotcc.entidade;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Type;

import com.vividsolutions.jts.geom.Polygon;

@Entity
public class Agricultura {
    
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "geradorAgricultura")
	@SequenceGenerator(name = "geradorAgricultura", sequenceName = "geradorAgricultura", initialValue = 1, allocationSize = 1)
	private int idAgricultura;
	@Type(type="org.hibernate.spatial.GeometryType")
    private Polygon polygon;
    private String observacoes;
    
    @ManyToOne
	@JoinColumn(name = "tipo_agricultura")
    private TipoAgricultura tipo_agricultura;

    public Agricultura()
    {
        this.tipo_agricultura = new TipoAgricultura();
    }
    
    public void setTipo_agricultura(TipoAgricultura tipo_agricultura) {
        this.tipo_agricultura = tipo_agricultura;
    }

    public TipoAgricultura getTipo_agricultura() {
        return tipo_agricultura;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public int getIdAgricultura() {
        return idAgricultura;
    }

    public Polygon getPolygon() {
        return polygon;
    }

    public void setIdAgricultura(int idAgricultura) {
        this.idAgricultura = idAgricultura;
    }

    public void setPolygon(Polygon polygon) {
        this.polygon = polygon;
    }
}
