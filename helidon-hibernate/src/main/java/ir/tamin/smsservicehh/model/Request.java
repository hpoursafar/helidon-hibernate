package ir.tamin.smsservicehh.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

@Entity
@Table(name = "REQ_REQUEST")
public class Request implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    private Long id;
    @NotNull
    @Column(name = "REFCODE")
    private String refCode;

    @Column(name = "USERNAME")
    private String userName;

    @Column(name = "request_comment")
    private String comment;

    @Column(name = "DELIVER_CODE")
    private String deliverCode;

    @Column(name = "REFRENCEID")
    private String refrenceid;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRefCode() {
        return refCode;
    }

    public void setRefCode(String refCode) {
        this.refCode = refCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDeliverCode() {
        return deliverCode;
    }

    public void setDeliverCode(String deliverCode) {
        this.deliverCode = deliverCode;
    }

    public String getRefrenceid() {
        return refrenceid;
    }

    public void setRefrenceid(String refrenceid) {
        this.refrenceid = refrenceid;
    }
}
