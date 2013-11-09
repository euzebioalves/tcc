package br.com.projetotcc.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.projetotcc.entidade.Autorizacao;


public class AutorizacaoDao {

	public void incluir(Autorizacao autorizacao) {
		EntityManager em = JPAUtil.createEntityManager();
    	EntityTransaction tx = em.getTransaction();
    	tx.begin();
		em.persist(autorizacao);
		tx.commit();
		em.close();
	}
}
