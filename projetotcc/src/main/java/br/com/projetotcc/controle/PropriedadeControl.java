package br.com.projetotcc.controle;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import br.com.projetotcc.entidade.Propriedade;
import br.com.projetotcc.entidade.TipoAgricultura;
import br.com.projetotcc.persistencia.PropriedadeDao;
import br.com.projetotcc.persistencia.ProprietarioDao;
import br.com.projetotcc.persistencia.TipoAgriculturaDao;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

@ManagedBean(name = "propriedadeControl")
public class PropriedadeControl {

	private Propriedade propriedade;
    private String polygon;
    private List<Propriedade> listaPropriedade = new ArrayList<Propriedade>();
    private List<Propriedade> listaPropriedade1;
    private String polygonSelecionado;
    

    @PostConstruct
    public void init(){
       ListarAgricultura(); 
    }	
	
    public PropriedadeControl()
    {
    	this.propriedade = new Propriedade();
    	this.polygon = "";
    }
    
    
	public List<Propriedade> getListaPropriedade1() {
		return listaPropriedade1;
	}

	public void setListaPropriedade1(List<Propriedade> listaPropriedade1) {
		this.listaPropriedade1 = listaPropriedade1;
	}

	public String getPolygonSelecionado() {
		return polygonSelecionado;
	}

	public void setPolygonSelecionado(String polygonSelecionado) {
		this.polygonSelecionado = polygonSelecionado;
	}
	

	public String getPolygon() {
		return polygon;
	}

	public void setPolygon(String polygon) {
		this.polygon = polygon;
	}
	

    public Propriedade getPropriedade() {
		return propriedade;
	}

	public void setPropriedade(Propriedade propriedade) {
		this.propriedade = propriedade;
	}

	public List<Propriedade> getListaPropriedade() {
		return listaPropriedade;
	}

	public void setListaPropriedade(List<Propriedade> listaPropriedade) {
		this.listaPropriedade = listaPropriedade;
	}

	public void Salvar() {
    	Geometry geom = wktToGeometry(this.polygon);
    	this.propriedade.setPolygon((Polygon) geom);
    	ProprietarioDao daop = new ProprietarioDao();
    	daop.incluir(propriedade.getProprietario());
    	
    	PropriedadeDao dao = new PropriedadeDao();
    	dao.incluir(this.propriedade);
        this.propriedade = new Propriedade();
        this.polygon = "";
    } 

    public void Limpar() {
        this.propriedade = new Propriedade();
        this.polygon = "";
    }

    public List<TipoAgricultura> Listar(){
        
        return new TipoAgriculturaDao().Listar();
    }
    private Geometry wktToGeometry(String wktPoint) {
        WKTReader fromText = new WKTReader();
        Geometry geom = null;
        try {
            geom = fromText.read(wktPoint);
        } catch (ParseException e) {
            throw new RuntimeException("Not a WKT string:" + wktPoint);
        }
        return geom;
    }
    public void ListarAgricultura(){
        
        this.listaPropriedade = new PropriedadeDao().Listar();
    }
    public void save(ActionEvent actionEvent) {  
        FacesContext context = FacesContext.getCurrentInstance();          
        context.addMessage(null, new FacesMessage("Sucesso!!!", "Propriedade Cadastrada com Sucesso!!!"));   
    }
}
