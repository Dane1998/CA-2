
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
    private List<Phone> phone; 
    private List<Hobby> hobby;

    public PersonDTO(Person p) {
        this.id = p.getId();
        this.firstName = p.getFirstName();
        this.lastName = p.getLastName();
        this.street = p.getAddress().getStreet();
        this.zipCode = p.getAddress().getCityInfo().getZipCode();
        this.city = p.getAddress().getCityInfo().getCity();
        this.phone = p.getPhones();
        this.hobby = p.getHobbies();
    }

    public PersonDTO(String firstName, String lastName, String email, String street, String zipCode, String city, List<Phone> phone, List<Hobby> hobby) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.street = street;
        this.zipCode = zipCode;
        this.city = city;
        this.phone = phone;
        this.hobby = hobby;
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

    public List<Phone> getPhone() {
        return phone;
    }

    public void setPhone(List<Phone> phone) {
        this.phone = phone;
    }

    public List<Hobby> getHobby() {
        return hobby;
    }

    public void setHobby(List<Hobby> hobby) {
        this.hobby = hobby;
    }

}
