package org.example.sunsetresortwebapp.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name ="requestable_services")
@PrimaryKeyJoinColumn(name ="requestable_services_id")
public class RequestableResortService extends ResortService {
    private String requestName;
    private String requestDescription;

    public RequestableResortService(String requestName, String requestDescription) {
        this.requestName = requestName;
        this.requestDescription = requestDescription;
    }
    public RequestableResortService() {}
    public String getRequestName() {
        return requestName;
    }


    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }

    public String getRequestDescription() {
        return requestDescription;
    }

    public void setRequestDescription(String requestDescription) {
        this.requestDescription = requestDescription;
    }
}
