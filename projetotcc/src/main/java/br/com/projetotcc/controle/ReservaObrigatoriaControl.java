package br.com.projetotcc.controle;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.ol4jsf.util.WKTFeaturesCollection;
import br.com.projetotcc.entidade.Propriedade;
import br.com.projetotcc.entidade.ReservaObrigatoria;
import br.com.projetotcc.persistencia.AgriculturaDao;
import br.com.projetotcc.persistencia.PropriedadeDao;
import br.com.projetotcc.persistencia.ReservaObrigatoriaDao;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

@ManagedBean(name = "reservaObrigatoriaControl")
public class ReservaObrigatoriaControl {

	private ReservaObrigatoria reservaObrigatoria;
    private String polygon;
    private List<ReservaObrigatoria> listaReservaObrigatoria = new ArrayList<ReservaObrigatoria>();
    private String polygonSelecionado;
    private String pesquisa;
    
    
    private List<Propriedade> listaPropriedade = new ArrayList<Propriedade>();
    private String wktsPropriedade;
    private ReservaObrigatoria reservaObrigatoriaPesquisa;
    private String areaSelecionada;
    private String msg;
    private String wkts;
    
    @PostConstruct
    public void init(){
       ListarReservaObrigatoria();
       ListarPropriedade();
    }	
	
    public ReservaObrigatoriaControl()
    {
    	this.reservaObrigatoria = new ReservaObrigatoria();
    }
	
    public void Salvar() {
    	Geometry geom = wktToGeometry(this.polygon);
    	this.reservaObrigatoria.setPolygon((Polygon) geom);

    	ReservaObrigatoriaDao dao = new ReservaObrigatoriaDao();
    	dao.incluir(this.reservaObrigatoria);
        this.reservaObrigatoria = new ReservaObrigatoria();
        this.polygon = "";
    } 

    public void Limpar() {
        this.reservaObrigatoria = new ReservaObrigatoria();
        this.polygon = "";
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
    public void ListarReservaObrigatoria(){
    	this.listaReservaObrigatoria = new ReservaObrigatoriaDao().Listar();
    	WKTFeaturesCollection<String> wktFeatures = new WKTFeaturesCollection<String>();
    	List<String> ls = new ArrayList<String>();
    	for (int i = 0; i < this.listaReservaObrigatoria.size(); i++) {
			 ls.add(this.listaReservaObrigatoria.get(i).getPolygon().toString());
		}
    	wktFeatures.addAllFeatures(ls);
    	this.wkts = wktFeatures.toMap();
        
    }
    
    public void ListarPropriedade(){
    	this.listaPropriedade = new PropriedadeDao().Listar();
    	WKTFeaturesCollection<String> wktFeatures = new WKTFeaturesCollection<String>();
    	List<String> ls = new ArrayList<String>();
    	for (int i = 0; i < this.listaPropriedade.size(); i++) {
			 ls.add(this.listaPropriedade.get(i).getPolygon().toString());
		}
    	wktFeatures.addAllFeatures(ls);
    	this.wktsPropriedade = wktFeatures.toMap();
        
    }
    
    public void save(ActionEvent actionEvent) {  
        FacesContext context = FacesContext.getCurrentInstance();          
        context.addMessage(null, new FacesMessage("Sucesso!!!", "Reserva Obrigatória Cadastrada com Sucesso!!!"));   
    }
    
    public void Consultar(){
        
       try {
    	   this.reservaObrigatoriaPesquisa = new ReservaObrigatoria();
           this.areaSelecionada = "";        
           ReservaObrigatoriaDao dao = new ReservaObrigatoriaDao();
           this.reservaObrigatoriaPesquisa = dao.Consultar(this.pesquisa);
           this.CalcularArea(this.pesquisa);
           this.pesquisa = "";
	} catch (Exception e) {
		this.reservaObrigatoriaPesquisa = new ReservaObrigatoria();
        this.areaSelecionada = ""; 
	}
       
    }
    public void CalcularArea(String ponto){
    	Double area = Double.parseDouble(new AgriculturaDao().CalcularArea(ponto));    	
    	area = area / 10000;
    	this.areaSelecionada = area.toString();
    	
    }


	public ReservaObrigatoria getReservaObrigatoria() {
		return reservaObrigatoria;
	}


	public void setReservaObrigatoria(ReservaObrigatoria reservaObrigatoria) {
		this.reservaObrigatoria = reservaObrigatoria;
	}


	public String getPolygon() {
		return polygon;
	}


	public void setPolygon(String polygon) {
		this.polygon = polygon;
	}


	public List<ReservaObrigatoria> getListaReservaObrigatoria() {
		return listaReservaObrigatoria;
	}


	public void setListaReservaObrigatoria(
			List<ReservaObrigatoria> listaReservaObrigatoria) {
		this.listaReservaObrigatoria = listaReservaObrigatoria;
	}


	public String getPolygonSelecionado() {
		return polygonSelecionado;
	}


	public void setPolygonSelecionado(String polygonSelecionado) {
		this.polygonSelecionado = polygonSelecionado;
	}


	public String getPesquisa() {
		return pesquisa;
	}


	public void setPesquisa(String pesquisa) {
		this.pesquisa = pesquisa;
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


	public ReservaObrigatoria getReservaObrigatoriaPesquisa() {
		return reservaObrigatoriaPesquisa;
	}


	public void setReservaObrigatoriaPesquisa(
			ReservaObrigatoria reservaObrigatoriaPesquisa) {
		this.reservaObrigatoriaPesquisa = reservaObrigatoriaPesquisa;
	}


	public String getAreaSelecionada() {
		return areaSelecionada;
	}


	public void setAreaSelecionada(String areaSelecionada) {
		this.areaSelecionada = areaSelecionada;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	public String getWkts() {
		return wkts;
	}


	public void setWkts(String wkts) {
		this.wkts = wkts;
	}
   
}
