
package dto;

import entities.Address;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import java.util.List;
     
/**
 *
 * @author Dane
 */
public class PersonDTO {
    
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String street;
    private String zipCode;
    private String city;
    private List<Phone> phoneNumber; 
    private List<Hobby> hobby;
    private String pNumber;
    private String hobbyName;

    public PersonDTO(Person person) {
        this.id = person.getId();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.street = person.getAddress().getStreet();
        this.zipCode = person.getAddress().getCityInfo().getZipCode();
        this.city = person.getAddress().getCityInfo().getCity();       
        this.hobby = person.getHobbies();
    }

    public PersonDTO(String firstName, String lastName, String email, String street, String zipCode, String city, String pNumber, String hobbyName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.street = street;
        this.zipCode = zipCode;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.hobbyName = hobbyName;
    }

    public String getpNumber() {
        return pNumber;
    }

    public void setpNumber(String pNumber) {
        this.pNumber = pNumber;
    }
    
    

    public String getHobbyName() {
        return hobbyName;
    }

    public void setHobbyName(String hobbyName) {
        this.hobbyName = hobbyName;
    }


    public PersonDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Phone> getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhone(List<Phone> phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Hobby> getHobby() {
        return hobby;
    }

    public void setHobby(List<Hobby> hobby) {
        this.hobby = hobby;
    }

}
