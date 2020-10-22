
package facades;

import dto.PersonDTO;
import dto.PersonsDTO;
import entities.Hobby;

/**
 *
 * @author Dane
 */
public interface IHobbyFacade {
    
    public long getPersoncountByHobby(String hobby);
    public PersonsDTO getPersonsByHobby(String hobby);
}
