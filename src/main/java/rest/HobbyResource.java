
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.PersonsDTO;
import facades.HobbyFacade;
import facades.PersonFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

/**
 *
 * @author Dane
 */
@Path("hobby")
public class HobbyResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    private static final HobbyFacade FACADE = HobbyFacade.getHobbyFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    
    @GET
    @Path("personcount/{hobby}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getPersoncountByHobby(@PathParam("hobby")String hobby){
        long persons = FACADE.getPersoncountByHobby(hobby);
        return "Persons: " + persons;
    }
    
    @GET
    @Path("persons/{hobby}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getPersonsByHobby(@PathParam("hobby") String hobby){
        PersonsDTO persons = FACADE.getPersonsByHobby(hobby);
        return GSON.toJson(persons.getAll());
    }
}
