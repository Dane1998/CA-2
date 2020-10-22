package facades;

import dto.PersonsDTO;
import entities.Hobby;
import entities.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class PersonFacade {

    private static PersonFacade instance;
    private static EntityManagerFactory emf;
    private final Person p1 = new Person("b@b.com", " Lasse", "kan ikke huske");
    
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
    
    public Person getById(long id){
        return getEntityManager().find(Person.class,id);
    }

    
    public List<Person> getAll(){
        EntityManager em = emf.createEntityManager();
        try{
            Query query = em.createEntityManager("Person.getAll");
            List<Person> allPersons = query.getResultList();
            return allPersons;
        }finally{
            em.close();
        }
    }
        //return getEntityManager().createQuery("SELECT person FROM Person person", Person.class).getResultList();
    

    
    public PersonsDTO getAllPersons() {
        EntityManager em = getEntityManager();
        try {
            return new PersonsDTO(em.createNamedQuery("Person.getAll").getResultList());
        } finally {
            em.close();
        }
    }
    //TODO
    
    public void addPerson(){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
            em.persist(p1);
            em.getTransaction().commit();
        }finally{
            em.close();
        }
        
    }

    
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
    
    public List<Person> getAll(){
        return getEntityManager().createQuery("SELECT person FROM Person person", Person.class).getResultList();
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

}