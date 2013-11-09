package br.com.projetotcc.persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import br.com.projetotcc.entidade.Propriedade;

public class PropriedadeDao {

	public void incluir(Propriedade propriedade) {
		EntityManager em = JPAUtil.createEntityManager();
    	EntityTransaction tx = em.getTransaction();
    	tx.begin();
		em.persist(propriedade);
		tx.commit();
		em.close();
	}
	public List<Propriedade> Listar() {
		EntityManager em = JPAUtil.createEntityManager();
    	EntityTransaction tx = em.getTransaction();
    	tx.begin();
		@SuppressWarnings("unchecked")
		List<Propriedade> lstRetorno = em.createQuery("select p from Propriedade as p").getResultList();
		tx.commit();
		em.close();
		return lstRetorno;
	}
}
