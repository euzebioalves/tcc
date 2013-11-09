package br.com.projetotcc.entidade;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import br.com.projetotcc.persistencia.UsuarioDao;

public class teste {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
	        Usuario us = new Usuario();
	        us.setUsername("carlos");
	        us.setPassword("123");
	        
	        UsuarioDao daou = new UsuarioDao();
	       // daou.incluir(us);
	        
	        
	    }catch (Throwable ex) {
	        
	    }

	}

}
