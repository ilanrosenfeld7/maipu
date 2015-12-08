package clases;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMF {
	public static final EntityManagerFactory em = Persistence.createEntityManagerFactory("abmh");
	
	public static EntityManagerFactory getEMF(){
		return em;
	}
}
