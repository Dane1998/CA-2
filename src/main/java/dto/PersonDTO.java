
package dto;

import entities.Address;
import entities.Hobby;
import entities.Person;
import entities.Phone;
     
/**
 *
 * @author Dane
 */
public class PersonDTO {
    
    private Long id;
    private String firstName;
    private String lastName;
    private Address address;
    private Phone phone;
    private Hobby hobby;

    public PersonDTO(Person p) {
        this.id = p.getId();
        this.firstName = p.getFirstName();
        this.lastName = p.getLastName();
        this.address = p.getAddress();
        this.phone = p.getPhone();
        this.hobby = p.getHobby();
    }

    public PersonDTO(String firstName, String lastName, Address address, Phone phone, Hobby hobby) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Hobby getHobby() {
        return hobby;
    }

    public void setHobby(Hobby hobby) {
        this.hobby = hobby;
    }
    
    
}
