package com.linkedin.learning.otrareunionmas;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.persistence.NoResultException;

import com.linkedin.learning.otrareunionmas.dao.ReunionDao;
import com.linkedin.learning.otrareunionmas.dominio.Reunion;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ReunionDao dao = new ReunionDao();
        List<Reunion> reuniones = dao.getAll();
        System.out.println("*** 1:" + reuniones);
        
        Reunion reunion = new Reunion(LocalDateTime.now(), "Prueba");
        dao.save(reunion);
        
        reuniones = dao.getAll();
        System.out.println("*** 2:" + reuniones);
        
        reunion = new Reunion(LocalDateTime.now().plus(2, ChronoUnit.DAYS), "Reunion pasado mañana");
        dao.save(reunion);
        
        try {
        	System.out.println("Tu proxima reunion es: " + dao.proxiamReunion());
        }catch (NoResultException e) {
        	System.out.println("No tienes una reunion prevista");
		}
        
        Reunion reunionManiana = new Reunion(LocalDateTime.now().plus(1, ChronoUnit.DAYS), "Reunion de mañana");
        dao.save(reunionManiana);
        
        System.out.println("Reuniones para mañana: " + dao.reunionesManiana());
    }
}
