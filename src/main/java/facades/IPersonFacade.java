
package facades;

import dto.CityInfoDTO;
import dto.CityInfosDTO;
import dto.PersonDTO;
import dto.PersonsDTO;
import entities.CityInfo;
import entities.Hobby;
import entities.Phone;
import exceptions.PersonNotFoundException;
import java.util.List;

/**
 *
 * @author Dane
 */
public interface IPersonFacade {
    
    public PersonsDTO getAllPersons();
    public PersonDTO getPersonById(long id)throws PersonNotFoundException;
    public PersonDTO getPersonByPhone(int number);
    public PersonDTO addPerson(PersonDTO personDTO);
    public PersonDTO deletePerson(long id);
    public PersonDTO editPerson(PersonDTO personDTO);
    
    
}
