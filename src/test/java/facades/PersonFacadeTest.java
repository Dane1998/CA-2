package facades;

import dto.PersonDTO;
import dto.PersonsDTO;
import entities.Address;
import entities.CityInfo;
import utils.EMF_Creator;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class PersonFacadeTest {

    private static EntityManagerFactory emf;
    private static PersonFacade facade;

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

    public PersonFacadeTest() {

    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = PersonFacade.getPersonFacade(emf);
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

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the code below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {

    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    // TODO: Delete or change this method 
    @Test
    public void testGetAllPersons() {
        List<Person> persons = new ArrayList();
        persons.add(p1);
        persons.add(p2);
        PersonsDTO expResult = new PersonsDTO(persons);
        PersonFacade personFacade = PersonFacade.getPersonFacade(emf);
        PersonsDTO result = new PersonsDTO();
        result = personFacade.getAllPersons();
        assertEquals(expResult.getAll().size(), result.getAll().size());
    }

    //@Disabled
    @Test
    public void testAddPerson() {
        PersonFacade personFacade = PersonFacade.getPersonFacade(emf);
        EntityManager em = emf.createEntityManager();

        int expResult = 3;
        Person per = new Person("test3@test.dk", "Hans", "Hansen");

        per.addPhone(tel1);
        per.setAddress(a1);
        per.addHobby(h1);
        PersonDTO pDTO = new PersonDTO(per);
        personFacade.addPerson(pDTO);
        PersonsDTO result = personFacade.getAllPersons();
        assertEquals(expResult, result.getAll().size());

    }

}
