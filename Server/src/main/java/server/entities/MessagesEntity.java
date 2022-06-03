package server.entities;

import javax.persistence.*;

@Entity
@Table(name = "MESSAGES")
public class MessagesEntity extends AbstractEntity{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "MESSAGE", nullable = false, length = 1000)
    private String message;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_SENDER", nullable = false)
    private PersonsEntity idSender;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_RECEIVER", nullable = false)
    private PersonsEntity idReceiver;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PersonsEntity getIdSender() {
        return idSender;
    }

    public void setIdSender(PersonsEntity idSender) {
        this.idSender = idSender;
    }

    public PersonsEntity getIdReceiver() {
        return idReceiver;
    }

    public void setIdReceiver(PersonsEntity idReceiver) {
        this.idReceiver = idReceiver;
    }

}