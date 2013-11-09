package br.com.projetotcc.controle;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.ol4jsf.util.WKTFeaturesCollection;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import br.com.projetotcc.entidade.Agricultura;
import br.com.projetotcc.entidade.Propriedade;
import br.com.projetotcc.entidade.TipoAgricultura;
import br.com.projetotcc.persistencia.AgriculturaDao;
import br.com.projetotcc.persistencia.PropriedadeDao;
import br.com.projetotcc.persistencia.TipoAgriculturaDao;


@ManagedBean(name = "cadastro")
@SessionScoped
public class AgriculturaControl {

	private Agricultura agricultura;
    private String polygon;
    private List<Agricultura> listaAgricultura = new ArrayList<Agricultura>();
    private String polygonSelecionado;
    private String pesquisa;
    
    
    private List<Propriedade> listaPropriedade = new ArrayList<Propriedade>();
    private String wktsPropriedade;
    private Agricultura agriculturaPesquisa;
    private String areaSelecionada;
    private String msg;
    private String wkts;
    
    @PostConstruct
    public void init(){
       ListarAgricultura(); 
       ListarPropriedade();
    }	
	
	public List<Propriedade> getListaPropriedade() {
		return listaPropriedade;
	}

	public void setListaPropriedade(List<Propriedade> listaPropriedade) {
		this.listaPropriedade = listaPropriedade;
	}

	public String getWktsPropriedade() {
		return wktsPropriedade;
	}

	public void setWktsPropriedade(String wktsPropriedade) {
		this.wktsPropriedade = wktsPropriedade;
	}

	public String getWkts() {
		return wkts;
	}

	public void setWkts(String wkts) {
		this.wkts = wkts;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getAreaSelecionada() {
		return areaSelecionada;
	}

	public void setAreaSelecionada(String areaSelecionada) {
		this.areaSelecionada = areaSelecionada;
	}

	public String getPesquisa() {
		return pesquisa;
	}

	public Agricultura getAgriculturaPesquisa() {
		return agriculturaPesquisa;
	}

	public void setAgriculturaPesquisa(Agricultura agriculturaPesquisa) {
		this.agriculturaPesquisa = agriculturaPesquisa;
	}

	public void setPesquisa(String pesquisa) {
		this.pesquisa = pesquisa;
	}

	public String getPolygonSelecionado() {
		return polygonSelecionado;
	}

	public void setPolygonSelecionado(String polygonSelecionado) {
		this.polygonSelecionado = polygonSelecionado;
	}

	public List<Agricultura> getListaAgricultura() {
		return listaAgricultura;
	}
	
	public void setListaAgricultura(List<Agricultura> listaAgricultura) {
		this.listaAgricultura = listaAgricultura;
	}

	public String getPolygon() {
		return polygon;
	}

	public void setPolygon(String polygon) {
		this.polygon = polygon;
	}
	
	public AgriculturaControl() {
        this.agricultura = new Agricultura(); 
        this.polygon = "";
    }

    public void setAgricultura(Agricultura agricultura) {
        this.agricultura = agricultura;
    }

    public Agricultura getAgricultura() {
        return agricultura;
    }

    public void Salvar() {
    	Geometry geom = wktToGeometry(this.polygon);
    	this.agricultura.setPolygon((Polygon) geom);

    	AgriculturaDao dao = new AgriculturaDao();
    	dao.incluir(this.agricultura);
        this.agricultura = new Agricultura();
        this.polygon = "";
    } 

    public void Limpar() {
        this.agricultura = new Agricultura();
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
    	this.listaAgricultura = new AgriculturaDao().Listar();
    	WKTFeaturesCollection<String> wktFeatures = new WKTFeaturesCollection<String>();
    	List<String> ls = new ArrayList<String>();
    	for (int i = 0; i < this.listaAgricultura.size(); i++) {
			 ls.add(this.listaAgricultura.get(i).getPolygon().toString());
		}
    	wktFeatures.addAllFeatures(ls);
    	this.setWkts(wktFeatures.toMap());
        
    }
    
    public void ListarPropriedade(){
    	this.listaPropriedade = new PropriedadeDao().Listar();
    	WKTFeaturesCollection<String> wktFeatures = new WKTFeaturesCollection<String>();
    	List<String> ls = new ArrayList<String>();
    	for (int i = 0; i < this.listaPropriedade.size(); i++) {
			 ls.add(this.listaPropriedade.get(i).getPolygon().toString());
		}
    	wktFeatures.addAllFeatures(ls);
    	this.setWktsPropriedade(wktFeatures.toMap());
        
    }
    
    public void save(ActionEvent actionEvent) {  
        FacesContext context = FacesContext.getCurrentInstance();          
        context.addMessage(null, new FacesMessage("Sucesso!!!", "Agricultura Cadastrada com Sucesso!!!"));   
    }
    
    public void Consultar(){
        
       try {
    	   this.agriculturaPesquisa = new Agricultura();
           this.areaSelecionada = "";        
           AgriculturaDao dao = new AgriculturaDao();
           this.agriculturaPesquisa = dao.Consultar(this.pesquisa);
           this.CalcularArea(this.pesquisa);
           this.pesquisa = "";
	} catch (Exception e) {
		this.agriculturaPesquisa = new Agricultura();
        this.areaSelecionada = ""; 
	}
       
    }
    public void CalcularArea(String ponto){
    	String area = new AgriculturaDao().CalcularArea(ponto);    	
    	
    	this.areaSelecionada = area.toString();
    	
    }
    public void Dados() {  
    	 AgriculturaDao dao = new AgriculturaDao();
    	this.agriculturaPesquisa = dao.Consultar(this.pesquisa);
        this.CalcularArea(this.pesquisa);
        FacesContext context = FacesContext.getCurrentInstance();          
        context.addMessage(null, new FacesMessage("Sucesso!!!", this.areaSelecionada+"asdf"));   
    }
    
    public void SelecionarUnica(){
    	this.wktsPropriedade = this.polygonSelecionado;
    }
}
