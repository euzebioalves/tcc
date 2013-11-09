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
public class Propriedade {

	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "geradorPropriedade")
	@SequenceGenerator(name = "geradorPropriedade", sequenceName = "geradorPropriedade", initialValue = 1, allocationSize = 1)
	private int idPropriedade;
	@Type(type="org.hibernate.spatial.GeometryType")
    private Polygon polygon;
    private String nome;
    private String informacoes;
    
    @ManyToOne
	@JoinColumn(name = "proprietario")
    private Proprietario proprietario;

    
    public Propriedade()
    {
    	this.proprietario = new Proprietario();
    }
    
	public int getIdPropriedade() {
		return idPropriedade;
	}

	public void setIdPropriedade(int idPropriedade) {
		this.idPropriedade = idPropriedade;
	}

	public Polygon getPolygon() {
		return polygon;
	}

	public void setPolygon(Polygon polygon) {
		this.polygon = polygon;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getInformacoes() {
		return informacoes;
	}

	public void setInformacoes(String informacoes) {
		this.informacoes = informacoes;
	}

	public Proprietario getProprietario() {
		return proprietario;
	}

	public void setProprietario(Proprietario proprietario) {
		this.proprietario = proprietario;
	}
    
    
}
