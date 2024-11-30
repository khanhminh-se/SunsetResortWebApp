package org.example.sunsetresortwebapp.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;

@Entity
@Table(name ="reservable_services")
@PrimaryKeyJoinColumn(name ="reservable_service_id")
@EqualsAndHashCode(callSuper = true)
public class ReservableResortService extends ResortService {
    @Column(name ="quantity")
    private  int availableQuantity;

    public ReservableResortService(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }
    public ReservableResortService() {}

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }
}
