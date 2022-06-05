package server.entities;

import javax.persistence.*;

@Entity
@Table(name = "MESSAGES")
@NamedQueries(
        @NamedQuery(name="Person.getMessages", query = "select m.message from MessagesEntity m where m.idSender=?3 and m.idReceiver=?4")
)
public class MessagesEntity extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_M_GENERATOR")
    @SequenceGenerator(name="ID_M_GENERATOR", sequenceName="ID_MESSAGE_SEQ", allocationSize=1)
    @Column(name = "ID", nullable = false)
    private int id;

    @Column(name = "MESSAGE", nullable = false, length = 1000)
    private String message;

    @ManyToOne
    @JoinColumn(name = "id_sender", referencedColumnName = "ID", nullable = false)
    private PersonsEntity idSender;

    @ManyToOne
    @JoinColumn(name = "ID_RECEIVER",referencedColumnName = "ID", nullable = false)
    private PersonsEntity idReceiver;

    @Column(name = "SEEN")
    private boolean seen;

    public MessagesEntity(String message, PersonsEntity idSender, PersonsEntity idReceiver){
        this.message=message;
        this.idReceiver=idReceiver;
        this.idSender=idSender;
    }

    public MessagesEntity(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }
}