
package facades;

import dto.PersonDTO;
import dto.PersonsDTO;
import entities.CityInfo;
import entities.Hobby;
import exceptions.PersonNotFoundException;

/**
 *
 * @author Dane
 */
public interface IPersonFacade {
    
    public PersonsDTO getAllPersons();
    public PersonDTO getPersonById(long id)throws PersonNotFoundException;
    public PersonDTO getPersonByPhone(int number);
    public int getPersoncountByHobby(Hobby hobby);
    public PersonDTO getPersonsByHobby(Hobby hobby);
    public int getAllZipcodes();
    public PersonDTO getPersonByZipcode(CityInfo zipcode);
    public PersonDTO addPerson(PersonDTO personDTO);
    public PersonDTO deletePerson(long id);
    public PersonDTO editPerson(PersonDTO personDTO);
    
    
}
