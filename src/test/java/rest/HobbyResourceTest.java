package rest;

import entities.Address;
import entities.CityInfo;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import facades.HobbyFacade;
import utils.EMF_Creator;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.parsing.Parser;
import java.net.URI;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
//Uncomment the line below, to temporarily disable this test
//@Disabled

public class HobbyResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
       
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

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
        
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

    @AfterAll
    public static void closeTestServer() {
        //System.in.read();

        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    // Setup the DataBase (used by the test-server and this test) in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the EntityClass used below to use YOUR OWN (renamed) Entity class
    @BeforeEach
    public void setUp() {

    }
    
    @Test
    public void testServerIsUp() {
        System.out.println("Testing is server UP");
        given()
                .when()
                .get("/hobby")
                .then()
                .statusCode(200);
    }

    //This test assumes the database contains two rows
    @Test
    public void testDummyMsg() throws Exception {
        given()
                .contentType("application/json")
                .get("/hobby/").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("msg", equalTo("Hello World"));
    }

    @Test
    public void testGetPersonsByHobby() throws Exception {
        given()
                .contentType("application/json")
                .get("/hobby/persons/" + h1.getName())
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode());
                
    }
}
