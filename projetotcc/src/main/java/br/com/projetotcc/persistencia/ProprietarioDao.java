package br.com.projetotcc.persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.projetotcc.entidade.Proprietario;

public class ProprietarioDao {
	public void incluir(Proprietario proprietario) {
		EntityManager em = JPAUtil.createEntityManager();
    	EntityTransaction tx = em.getTransaction();
    	tx.begin();
		em.persist(proprietario);
		tx.commit();
		em.close();
	}
	public List<Proprietario> Listar() {
		EntityManager em = JPAUtil.createEntityManager();
    	EntityTransaction tx = em.getTransaction();
    	tx.begin();
		@SuppressWarnings("unchecked")
		List<Proprietario> lstRetorno = em.createQuery("select p from Proprietario as p").getResultList();
		tx.commit();
		em.close();
		return lstRetorno;
	}
}
