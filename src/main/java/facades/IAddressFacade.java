
package facades;

import dto.CityInfosDTO;
import dto.PersonDTO;
import entities.CityInfo;

/**
 *
 * @author Dane
 */
public interface IAddressFacade {
    
    public CityInfosDTO getAllZipcodes();
    public PersonDTO getPersonByZipcode(CityInfo zipcode);
}
