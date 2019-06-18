package sk.fri.uniza.api;

import org.hibernate.validator.constraints.NotEmpty;
import sk.fri.uniza.core.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@DiscriminatorValue("Person")
public class Person extends User {
    @NotEmpty
    private String FirstName;

    @NotEmpty
    private String LastName;

    @NotEmpty
    private String email;

    @OneToMany(mappedBy = "owner",fetch = FetchType.EAGER)
    private Set<Phone> phoneNumbers;

    @ManyToMany
    @JoinTable(
            name = "Person_City",
            joinColumns = { @JoinColumn(name = "person_id") },
            inverseJoinColumns = { @JoinColumn(name = "city_id") }
    )
    private List<City> followedCities;


    public Person(Person other) {
        super(other);
        this.FirstName = other.FirstName;
        this.LastName = other.LastName;
        this.email = other.email;
        this.phoneNumbers = other.phoneNumbers;
    }

    public Person() {

    }

    public Person(String userName, Set<String> roles, String password, String firstName, String lastName, String email) {
        super(userName, roles, password);
        FirstName = firstName;
        LastName = lastName;
        this.email = email;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Set<Phone> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(Set<Phone> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public List<City> getFollowedCities() {
        return followedCities;
    }

    public Paged<List<City>> getFollowedCities(int limit, int page) {
        ArrayList<City> pagedList = new ArrayList<>();

        for(int i = (page - 1) * limit; i < (page * limit); i++){
            pagedList.add(followedCities.get(i));
        }

        return new Paged<>(page, limit, pagedList.size(), pagedList);
    }

    public void setFollowedCities(List<City> followedCities) {
        this.followedCities = followedCities;
    }

    public void addFollowedCity(City city){
        if (!followedCities.contains(city)){
            followedCities.add(city);
        }
    }

    public void removeFollowedCity(City city) {
        followedCities.remove(city);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Person person = (Person) o;

        if (!FirstName.equals(person.FirstName)) return false;
        if (!LastName.equals(person.LastName)) return false;
        if (!email.equals(person.email)) return false;
        return Objects.equals(phoneNumbers, person.phoneNumbers);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + FirstName.hashCode();
        result = 31 * result + LastName.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + (phoneNumbers != null ? phoneNumbers.hashCode() : 0);
        return result;
    }


}
