
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author Dane
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Address.deleteAllRows", query = "DELETE FROM Address"),
    @NamedQuery(name = "Address.getAll", query = "SELECT a FROM Address a")
})
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String AdditionalInfo;
    
    @OneToMany(mappedBy = "address")
    private List<Person> persons;

    public Address(String street, String AdditionalInfo) {
        this.street = street;
        this.AdditionalInfo = AdditionalInfo;
        persons = new ArrayList<>();
    }
    
    public Address() {
    }

    public void addPerson(Person person) {
        if (person != null) {
            persons.add(person);
        }
    }
    
    public List<Person> getPerson(){
        return persons;
    }

    public String getAdditionalInfo() {
        return AdditionalInfo;
    }

    public void setAdditionalInfo(String AdditionalInfo) {
        this.AdditionalInfo = AdditionalInfo;
    }
 
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
