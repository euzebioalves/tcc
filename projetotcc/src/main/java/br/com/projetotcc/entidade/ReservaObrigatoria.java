package br.com.projetotcc.entidade;

import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import org.hibernate.annotations.Type;
import com.vividsolutions.jts.geom.Polygon;

@Entity
public class ReservaObrigatoria {

	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "geradorReservaObrigatoria")
	@SequenceGenerator(name = "geradorReservaObrigatoria", sequenceName = "geradorReservaObrigatoria", initialValue = 1, allocationSize = 1)
	private int idReservaObrigatoria;
	@Type(type="org.hibernate.spatial.GeometryType")
    private Polygon polygon;
    private String observacoes;
	public int getIdReservaObrigatoria() {
		return idReservaObrigatoria;
	}
	public void setIdReservaObrigatoria(int idReservaObrigatoria) {
		this.idReservaObrigatoria = idReservaObrigatoria;
	}
	public Polygon getPolygon() {
		return polygon;
	}
	public void setPolygon(Polygon polygon) {
		this.polygon = polygon;
	}
	public String getObservacoes() {
		return observacoes;
	}
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
    
    
}
