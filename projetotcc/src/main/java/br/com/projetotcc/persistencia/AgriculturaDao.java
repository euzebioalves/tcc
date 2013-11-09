package br.com.projetotcc.persistencia;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import br.com.projetotcc.entidade.Agricultura;

public class AgriculturaDao {

	public void incluir(Agricultura agricultura) {
		EntityManager em = JPAUtil.createEntityManager();
    	EntityTransaction tx = em.getTransaction();
    	tx.begin();
		em.persist(agricultura);
		tx.commit();
		em.close();
	}
	public List<Agricultura> Listar() {
		EntityManager em = JPAUtil.createEntityManager();
    	EntityTransaction tx = em.getTransaction();
    	tx.begin();
		@SuppressWarnings("unchecked")
		List<Agricultura> lstRetorno = em.createQuery("select t from Agricultura as t").getResultList();
		tx.commit();
		em.close();
		return lstRetorno;
	}
	public Agricultura Consultar(String ponto) {
		EntityManager em = JPAUtil.createEntityManager();
    	EntityTransaction tx = em.getTransaction();
    	tx.begin();
	
		String id = em.createNativeQuery("select a.idAgricultura from agricultura a where ST_Within('"+ponto+"', a.polygon)").getSingleResult().toString();
		
		@SuppressWarnings("unchecked")
		List<Agricultura> lstRetorno = em.createQuery("select t from Agricultura as t where t.idAgricultura = ?").setParameter(1, Integer.parseInt(id)).getResultList();
		tx.commit();
		em.close();
		return lstRetorno.get(0);
	}
	
	public String CalcularArea(String ponto) {
		EntityManager em = JPAUtil.createEntityManager();
    	EntityTransaction tx = em.getTransaction();
    	tx.begin();
	
		String area = em.createNativeQuery("select round(cast (area(a.polygon)* 10000 as numeric),3) from agricultura a where ST_Within('"+ponto+"', a.polygon)").getSingleResult().toString();
		tx.commit();
		em.close();
		return area;
	}
}