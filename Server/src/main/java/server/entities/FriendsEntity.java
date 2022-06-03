package server.entities;

import javax.persistence.*;

@Entity
@Table(name = "FRIENDS")
public class FriendsEntity extends AbstractEntity{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_FRIEND_1", nullable = false)
    private PersonsEntity idFriend1;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_FRIEND_2", nullable = false)
    private PersonsEntity idFriend2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PersonsEntity getIdFriend1() {
        return idFriend1;
    }

    public void setIdFriend1(PersonsEntity idFriend1) {
        this.idFriend1 = idFriend1;
    }

    public PersonsEntity getIdFriend2() {
        return idFriend2;
    }

    public void setIdFriend2(PersonsEntity idFriend2) {
        this.idFriend2 = idFriend2;
    }

}