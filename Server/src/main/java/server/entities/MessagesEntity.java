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

    @Column(name = "ID_SENDER", nullable = false)
    private Integer idSender;

    @Column(name = "ID_RECEIVER", nullable = false)
    private Integer idReceiver;

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

    public Integer getIdSender() {
        return idSender;
    }

    public void setIdSender(Integer idSender) {
        this.idSender = idSender;
    }

    public Integer getIdReceiver() {
        return idReceiver;
    }

    public void setIdReceiver(Integer idReceiver) {
        this.idReceiver = idReceiver;
    }

}