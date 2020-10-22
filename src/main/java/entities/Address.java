
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @NamedQuery(name = "Address.deleteAllRows", query = "DELETE FROM Address"),
    @NamedQuery(name = "Address.getAll", query = "SELECT a FROM Address a")
})
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToMany(mappedBy = "address")
    private List<Person> persons;
    
    private String street;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    private CityInfo cityInfo;
    
    

    public Address(String street, CityInfo cityInfo) {
        this.street = street;
        this.cityInfo = cityInfo;
        persons = new ArrayList<>();
    }
    
    public Address() {
    }

    public void addPerson(Person person) {
        if (person != null) {
            persons.add(person);
        }
    }
    
    public void setCityInfo(CityInfo cityInfo){
        if(cityInfo != null){
            this.cityInfo = cityInfo;
            cityInfo.addAddress(this);
        }else{
            this.cityInfo = null;
        }
    }
    
    public CityInfo getCityInfo(){
        return cityInfo;
    }
    
    public List<Person> getPerson(){
        return persons;
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
