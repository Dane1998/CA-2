
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.CityInfosDTO;
import facades.AddressFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

/**
 *
 * @author Dane
 */
@Path("Address")
public class AddressResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    private static final AddressFacade FACADE = AddressFacade.getAddressFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    
    @Path("allZipcodes") 
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllZipcodes(){
        CityInfosDTO zipcodes = FACADE.getAllZipcodes();
        return GSON.toJson(zipcodes.getAll());
    }
}
