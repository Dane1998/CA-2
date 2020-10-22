package facades;

import dto.PersonDTO;
import dto.PersonsDTO;
import entities.CityInfo;
import entities.Hobby;
import entities.Person;
import exceptions.PersonNotFoundException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class PersonFacade implements IPersonFacade{

    private static PersonFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private PersonFacade() {
        
    }
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static PersonFacade getPersonFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }  
    
    @Override 
    public PersonDTO getPersonById(long id) throws PersonNotFoundException{
        EntityManager em = getEntityManager();
        Person person = em.find(Person.class, id);
            if(person == null) {
                    throw new PersonNotFoundException(String.format("Could not find person with provided id: ", id ));
                } else {
                try {
                    return new PersonDTO(person);
                }finally{
                    em.close();
                }
            }
                       
        
    }


    @Override
    public PersonsDTO getAllPersons() {
        EntityManager em = getEntityManager();
        try {
            return new PersonsDTO(em.createNamedQuery("Person.getAll").getResultList());
        } finally {
            em.close();
        }
    }
    //TODO
   /* @Override
    public Person add(Person person){
        EntityManager em = getEntityManager();
        
    }*/

    
    public Person edit(Person person){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(person);
            em.getTransaction().commit();
            return person;
        }finally{
            em.close();
        }
    }

    
    public Person delete(long id){
        EntityManager em = getEntityManager();
        Person p = em.find(Person.class, id);
        if(p != null){
            try{
                em.getTransaction().begin();
                em.remove(p);
                em.getTransaction().commit();
            }finally{
                em.close();
            }
        }
        return p; 
    }

    public Person getByPhone(String phone){
        return getEntityManager().createQuery("SELECT person FROM Person person JOIN person.phones phone WHERE phone.number = :number", Person.class).setParameter("number",phone).getSingleResult();
        
    }
    public List<Person> getPersonsByCity(String zip){
        return getEntityManager().createQuery("SELECT person FROM Person person JOIN person.address a WHERE a.cityInfo.zip = :zip",Person.class).setParameter("zip",zip).getResultList();
        
    }

    @Override
    public PersonDTO getPersonByPhone(int number) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getPersoncountByHobby(Hobby hobby) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PersonDTO getPersonsByHobby(Hobby hobby) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getAllZipcodes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PersonDTO getPersonByZipcode(CityInfo zipcode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PersonDTO addPerson(PersonDTO personDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PersonDTO deletePerson(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PersonDTO editPerson(PersonDTO personDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}