package server.entities;

import javax.persistence.*;

@Entity
@Table(name = "PERSONS", schema = "JAVADB", catalog = "")
@NamedQueries({
        @NamedQuery(name="Person.findById", query = "select p from PersonsEntity p where p.id = ?1"),
        @NamedQuery(name="Person.findByName", query = "select p from PersonsEntity p where p.name = ?2"),
})
public class PersonsEntity extends AbstractEntity{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)

    private Long id;
    @Column(name = "NAME", nullable = false, length = 15)
    private String name;

    public PersonsEntity(String name){
        this.name=name;
    }

    public PersonsEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}