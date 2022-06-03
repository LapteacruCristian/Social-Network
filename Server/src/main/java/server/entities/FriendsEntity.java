package server.entities;

import javax.persistence.*;

@Entity
@Table(name = "FRIENDS")
public class FriendsEntity extends AbstractEntity{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "ID_FRIEND_1", nullable = false)
    private Integer idFriend1;

    @Column(name = "ID_FRIEND_2", nullable = false)
    private Integer idFriend2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdFriend1() {
        return idFriend1;
    }

    public void setIdFriend1(Integer idFriend1) {
        this.idFriend1 = idFriend1;
    }

    public Integer getIdFriend2() {
        return idFriend2;
    }

    public void setIdFriend2(Integer idFriend2) {
        this.idFriend2 = idFriend2;
    }

}