package facades;

import dto.PersonDTO;
import dto.PersonsDTO;
import entities.Hobby;
import entities.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Dane
 */
public class HobbyFacade implements IHobbyFacade {

    private static HobbyFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private HobbyFacade() {

    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static HobbyFacade getHobbyFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new HobbyFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public long getPersoncountByHobby(String hobby) {
        EntityManager em = getEntityManager();
        try {
            Query personCount = em.createQuery("SELECT COUNT(p) FROM Person p JOIN p.hobbies h WHERE h.name =:hobby");
            personCount.setParameter("hobby", hobby);
            long result = (long) personCount.getSingleResult();
            return result;
        } finally {
            em.close();

        }
    }

    @Override
    public PersonsDTO getPersonsByHobby(String hobby) {
        EntityManager em = getEntityManager();
        try {
            Query personList = em.createQuery("SELECT p FROM Person p JOIN p.hobbies h WHERE h.name =:hobby", Person.class);
            personList.setParameter("hobby", hobby);
            List<Person> persons = personList.getResultList();
            PersonsDTO allPersons = new PersonsDTO(persons);
            return allPersons;
        } finally {
            em.close();
        }
    }
}
