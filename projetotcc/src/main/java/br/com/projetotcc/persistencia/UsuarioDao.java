package br.com.projetotcc.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.projetotcc.entidade.Usuario;

public class UsuarioDao {

	public void incluir(Usuario usuario) {
		EntityManager em = JPAUtil.createEntityManager();
    	EntityTransaction tx = em.getTransaction();
    	tx.begin();
		em.persist(usuario);
		tx.commit();
		em.close();
	}
}
