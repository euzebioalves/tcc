package br.com.projetotcc.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	 final private String url = "jdbc:postgresql://localhost:5432/postgis20";  
	   final private String usuario = "postgres";  
	   final private String senha = "1234";  
	   final private String driver = "org.postgresql.Driver";  
	   Connection con;  
	   public Connection conectar() {  
	     try {  
	       Class.forName(driver);  
	     } catch (ClassNotFoundException cnfe) {  
	       
	       System.out.println("Driver não encontrado!!");  
	     }  
	     try {  
	       con = DriverManager.getConnection(url, usuario, senha);  
	       //Conseguiu conectar...  
	     } catch (SQLException se) {  
	       System.out.println("Não foi possivel conectar");  
	       
	     }  
	     return con;  
	   }  
}
