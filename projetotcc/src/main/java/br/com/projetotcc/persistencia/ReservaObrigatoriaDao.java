package br.com.projetotcc.persistencia;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import br.com.projetotcc.entidade.ReservaObrigatoria;

public class ReservaObrigatoriaDao {

	public void incluir(ReservaObrigatoria reservaObrigatoria) {
		EntityManager em = JPAUtil.createEntityManager();
    	EntityTransaction tx = em.getTransaction();
    	tx.begin();
		em.persist(reservaObrigatoria);
		tx.commit();
		em.close();
	}
	public List<ReservaObrigatoria> Listar() {
		EntityManager em = JPAUtil.createEntityManager();
    	EntityTransaction tx = em.getTransaction();
    	tx.begin();
		@SuppressWarnings("unchecked")
		List<ReservaObrigatoria> lstRetorno = em.createQuery("select r from ReservaObrigatoria as r").getResultList();
		tx.commit();
		em.close();
		return lstRetorno;
	}
	public ReservaObrigatoria Consultar(String ponto) {
		EntityManager em = JPAUtil.createEntityManager();
    	EntityTransaction tx = em.getTransaction();
    	tx.begin();
	
		String id = em.createNativeQuery("select r.idReservaObrigatoria from reservaObrigatoria r where ST_Within('"+ponto+"', a.polygon)").getSingleResult().toString();
		
		@SuppressWarnings("unchecked")
		List<ReservaObrigatoria> lstRetorno = em.createQuery("select r from ReservaObrigatoria as r where r.idReservaObrigatoria = ?").setParameter(1, Integer.parseInt(id)).getResultList();
		tx.commit();
		em.close();
		return lstRetorno.get(0);
	}
}
