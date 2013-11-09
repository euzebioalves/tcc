package br.com.projetotcc.entidade;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class TipoAgricultura {

	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "geradorTipoAgricultura")
	@SequenceGenerator(name = "geradorTipoAgricultura", sequenceName = "geradorTipoAgricultura", initialValue = 1, allocationSize = 1)
    private int id_tipo_agricultura;
	private String descricao;

	
	public void setId_tipo_agricultura(int id_tipo_agricultura) {
		this.id_tipo_agricultura = id_tipo_agricultura;
	}

	public int getId_tipo_agricultura() {
		return id_tipo_agricultura;
	}

	public String getDescricao() {
		return descricao;
	}

	

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
