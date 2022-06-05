package server.entities;

import javax.persistence.*;

@Entity
@Table(name = "PERSONS", schema = "JAVADB", catalog = "")
@NamedQueries({
        @NamedQuery(name="Person.findAll", query = "select p from PersonsEntity p "),
        @NamedQuery(name="Person.findById", query = "select p from PersonsEntity p where p.id = ?1"),
        @NamedQuery(name="Person.findByName", query = "select p from PersonsEntity p where p.name = ?2"),
        @NamedQuery(name="Person.setIsLogged", query = "update PersonsEntity p set p.logged=?3"),
        @NamedQuery(name="Person.getOnlineUsers", query = "select p from PersonsEntity p where p.logged=true"),
})
public class PersonsEntity extends AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_P_GENERATOR")
    @SequenceGenerator(name="ID_P_GENERATOR", sequenceName="ID_PERSON_SEQ", allocationSize=1)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "NAME", nullable = false, length = 15)
    private String name;

    @Column(name = "LOGGED")
    private boolean logged;

    public PersonsEntity(String name){
        this.name=name;
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

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean isLogged) {
        this.logged = isLogged;
    }

    @Override
    public String toString() {
        return "PersonsEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isLogged=" + logged +
                '}';
    }
}