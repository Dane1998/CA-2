/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.PersonsDTO;
import entities.Address;
import entities.CityInfo;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

/**
 *
 * @author Dane
 */
@Disabled
public class HobbyFacadeTest {
    
    private static EntityManagerFactory emf;
    private static HobbyFacade facade;

    private static Person p1 = new Person("test@test.dk", "Daniel", "Poulsen");
    private static Person p2 = new Person("test2@test.dk", "Jan", "Hansen");
    private static Hobby h1 = new Hobby("Golf", "null", "bold", "udendørs");
    private static Hobby h2 = new Hobby("Fodbold", "null", "bold", "udendørs");
    private static CityInfo c1 = new CityInfo("2970", "Hørsholm");
    private static CityInfo c2 = new CityInfo("2990", "Nivå");
    private static Address a1 = new Address("Ørnevej", c1);
    private static Address a2 = new Address("Fuglevej", c2);
    private static Phone tel1 = new Phone("12345678");
    private static Phone tel2 = new Phone("87654321");
    
    public HobbyFacadeTest() {

    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = HobbyFacade.getHobbyFacade(emf);
        EntityManager em = emf.createEntityManager();

        try {
        p1.addHobby(h1);
        p2.addHobby(h2);
        p1.addPhone(tel1);
        p2.addPhone(tel2);
        p1.setAddress(a1);
        p2.setAddress(a2);

        em.getTransaction().begin();
        em.persist(h1);
        em.persist(h2);
        em.persist(c1);
        em.persist(c2);
        em.persist(p1);
        em.persist(p2);
        em.getTransaction().commit();
        } finally {
            em.close();
        }
       
    }
    
    @Test
    public void testGetPersoncountByHobby(){
    HobbyFacade hobbyFacade = HobbyFacade.getHobbyFacade(emf);
    int expResult = 1;
    long result = hobbyFacade.getPersoncountByHobby(h1.getName());
    assertEquals(expResult, result);
    }
    
    @Test 
    public void testGetPersonsByHobby(){
        HobbyFacade hobbyFacade = HobbyFacade.getHobbyFacade(emf);     
        int expResult = 1;
        PersonsDTO result = hobbyFacade.getPersonsByHobby("golf");
        assertEquals(expResult, result.getAll().size());
    }
}
