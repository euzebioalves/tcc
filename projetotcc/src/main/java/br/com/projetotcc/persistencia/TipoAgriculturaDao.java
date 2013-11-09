package br.com.projetotcc.persistencia;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;


import br.com.projetotcc.entidade.TipoAgricultura;

public class TipoAgriculturaDao {

	public void incluir(TipoAgricultura tipoAgricultura) {
		EntityManager em = JPAUtil.createEntityManager();
    	EntityTransaction tx = em.getTransaction();
    	tx.begin();
		em.persist(tipoAgricultura);
		tx.commit();
		em.close();
	}
	public List<TipoAgricultura> Listar() {
		EntityManager em = JPAUtil.createEntityManager();
    	EntityTransaction tx = em.getTransaction();
    	tx.begin();
		@SuppressWarnings("unchecked")
		List<TipoAgricultura> lstRetorno = em.createQuery("select t from TipoAgricultura as t").getResultList();
		tx.commit();
		em.close();
		return lstRetorno;
	}
}
