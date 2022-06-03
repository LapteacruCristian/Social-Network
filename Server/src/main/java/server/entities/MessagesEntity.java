package server.entities;

import javax.persistence.*;

@Entity
@Table(name = "MESSAGES")
public class MessagesEntity extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_M_GENERATOR")
    @SequenceGenerator(name="ID_M_GENERATOR", sequenceName="ID_MESSAGE_SEQ", allocationSize=1)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "MESSAGE", nullable = false, length = 1000)
    private String message;

    @ManyToOne
    @JoinColumn(name = "id_sender", referencedColumnName = "ID", nullable = false)
    private PersonsEntity idSender;

    @ManyToOne
    @JoinColumn(name = "ID_RECEIVER",referencedColumnName = "ID", nullable = false)
    private PersonsEntity idReceiver;

    public MessagesEntity(String message, PersonsEntity idReceiver, PersonsEntity idSender){
        this.message=message;
        this.idReceiver=idReceiver;
        this.idSender=idSender;
    }

    public MessagesEntity(){

    }

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