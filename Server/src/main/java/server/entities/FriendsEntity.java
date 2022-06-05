package server.entities;

import javax.persistence.*;

@Entity
@Table(name = "FRIENDS")
@NamedQueries({
        @NamedQuery(name = "Friend.getOnlineFriends", query = "select f.idFriend2 from FriendsEntity f where f.idFriend1 = ?2 and f.idFriend2.logged=true"),
        @NamedQuery(name = "Friend.isFriend", query = "select f.idFriend2 from FriendsEntity f where f.idFriend2=?2"),
})
public class FriendsEntity extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_F_GENERATOR")
    @SequenceGenerator(name="ID_F_GENERATOR", sequenceName="ID_FRIEND_SEQ", allocationSize=1)
    @Column(name = "ID", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_friend_1", referencedColumnName = "ID", nullable = false)
    private PersonsEntity idFriend1;

    @ManyToOne
    @JoinColumn(name = "id_friend_2", referencedColumnName = "ID", nullable = false)
    private PersonsEntity idFriend2;

    public FriendsEntity(PersonsEntity f1, PersonsEntity f2){
        this.idFriend1=f1;
        this.idFriend2=f2;
    }
    public FriendsEntity(){

    }
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