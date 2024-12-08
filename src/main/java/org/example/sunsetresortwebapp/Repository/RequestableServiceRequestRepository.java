package org.example.sunsetresortwebapp.Repository;

import org.example.sunsetresortwebapp.Models.RequestableServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestableServiceRequestRepository extends JpaRepository<RequestableServiceRequest, Long> {
}
