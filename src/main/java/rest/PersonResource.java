package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.CityInfoDTO;
import dto.CityInfosDTO;
import dto.PersonDTO;
import dto.PersonsDTO;
import entities.CityInfo;
import entities.Person;
import utils.EMF_Creator;
import facades.PersonFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("person")
public class PersonResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    private static final PersonFacade FACADE = PersonFacade.getPersonFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @Path("allPersons")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllPersons() {
       
        PersonsDTO persons = FACADE.getAllPersons();
        return GSON.toJson(persons.getAll());
    }
    
    @POST 
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String addPerson(String person){
        PersonDTO p = GSON.fromJson(person, PersonDTO.class);
        PersonDTO pAdded = FACADE.addPerson(p);
        return GSON.toJson(pAdded);
    } 
    
     @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<PersonDTO> getAlL() {
    List<PersonDTO> dto = new ArrayList();
    
    for(Person p : FACADE.getAll()){
        dto.add(new PersonDTO(p));
    }
    return dto;
    }
    
    
    
     
    
}


