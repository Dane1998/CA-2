package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author Dane
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Person.deleteAllRows", query = "DELETE FROM Person"),
    @NamedQuery(name = "Person.getAll", query = "SELECT p FROM Person p")
})

public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String firstName;
    private String lastName;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Address address;

    @OneToMany(mappedBy = "person", cascade = {CascadeType.PERSIST})
    private List<Phone> phones;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Hobby> hobbies;
    

    public Person(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phones = new ArrayList();
        this.hobbies = new ArrayList();
    }

    public Person() {
    }

    public void setAddress(Address address) {
        if (address != null) {
            this.address = address;
            address.addPerson(this);
        } else {
            this.address = null;
        }
    }

    public void addPhone(Phone phone) {
        if (phone != null) {
            this.phones.add(phone);
            phone.setPerson(this);
        }
    }

    public void addHobby(Hobby hobby) {
        if (hobby != null) {
            this.hobbies.add(hobby);
            hobby.addPerson(this);
        }
    }
    
    public void setPhone(Phone phone){
        if(phone != null){
            phones.add(phone);
        }
    }

    public List<Phone> getPhones(){
        return phones;
    }
    
    public List<Hobby> getHobbies (){
        return hobbies;
    }
    public Address getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
