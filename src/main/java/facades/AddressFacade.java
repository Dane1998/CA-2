
package facades;

import dto.CityInfosDTO;
import dto.PersonDTO;
import entities.CityInfo;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Dane
 */
public class AddressFacade implements IAddressFacade{
    
    
    private static AddressFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private AddressFacade() {

    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static AddressFacade getAddressFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new AddressFacade();
        }
        return instance;
    }


    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    
    
    
    
    @Override
    public CityInfosDTO getAllZipcodes() {
        EntityManager em = getEntityManager();
        try {
            return new CityInfosDTO(em.createNamedQuery("CityInfo.getAll").getResultList());
        } finally {
            em.close();
        }
    }

    @Override
    public PersonDTO getPersonByZipcode(CityInfo zipcode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
