/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
