package org.example.sunsetresortwebapp.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name ="reservable_services")
@PrimaryKeyJoinColumn(name ="reservable_service_id")
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservableResortService extends ResortService {
    @Column(name ="maximum_guests")
    private  int maximumGuests;

    @Column(name = "reservable_service_name")
    private String reservableServiceName;


}
