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

    private Integer id;
    @Column(name = "NAME", nullable = false, length = 15)
    private String name;

    public PersonsEntity(Integer id,String name){
        this.id=id;this.name=name;
    }

    public PersonsEntity() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}