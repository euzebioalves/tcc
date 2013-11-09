package br.com.projetotcc.controle;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import br.com.projetotcc.entidade.TipoAgricultura;
import br.com.projetotcc.persistencia.JPAUtil;
import br.com.projetotcc.persistencia.TipoAgriculturaDao;


@ManagedBean(name = "controleTipoAgricultura")
public class TipoAgriculturaControl {

	private TipoAgricultura tipoAgricultura;

	public TipoAgricultura getTipoAgricultura() {
		return tipoAgricultura;
	}

	public void setTipoAgricultura(TipoAgricultura tipoAgricultura) {
		this.tipoAgricultura = tipoAgricultura;
	}
	
	public TipoAgriculturaControl()
	{
		this.tipoAgricultura = new TipoAgricultura();
	}
	
	public void Salvar() {

    	EntityManager em = JPAUtil.createEntityManager();
    	EntityTransaction tx = em.getTransaction();
    	tx.begin();
    	TipoAgriculturaDao dao = new TipoAgriculturaDao();
    	dao.incluir(this.tipoAgricultura);
    	this.tipoAgricultura = new TipoAgricultura();
        
    } 
	public void save(ActionEvent actionEvent) {  
        FacesContext context = FacesContext.getCurrentInstance();          
        context.addMessage(null, new FacesMessage("Sucesso!!!", "Tipo Agricultura Cadastrado com Sucesso!!!"));   
    }
}
