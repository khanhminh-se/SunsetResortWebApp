package org.example.sunsetresortwebapp.Services;

import org.example.sunsetresortwebapp.DTO.RequestableServiceDTO;
import org.example.sunsetresortwebapp.Models.RequestableResortService;
import org.example.sunsetresortwebapp.Repository.RequestableResortServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestableResortServiceService {
    private final RequestableResortServiceRepository requestableResortServiceRepository;
    @Autowired
    public RequestableResortServiceService(RequestableResortServiceRepository requestableResortServiceRepository) {
        this.requestableResortServiceRepository = requestableResortServiceRepository;
    }
    public RequestableResortService createRequestableResortService(RequestableServiceDTO requestableServiceDTO) {
        RequestableResortService requestableResortService = new RequestableResortService();
        requestableResortService.setRequestDescription(requestableServiceDTO.description());
        requestableResortService.setRequestName(requestableServiceDTO.name());
        requestableResortService.setPrice((double) requestableServiceDTO.price());
        return requestableResortServiceRepository.save(requestableResortService);
    }
    public List<RequestableResortService> getAllRequestableResortServices() {
        return requestableResortServiceRepository.findAll();
    }
    public RequestableResortService getRequestableResortServiceById(Long id) {
        return requestableResortServiceRepository.findById(id).orElse(null);
    }
    public void deleteRequestableResortServiceById(Long id) {
        requestableResortServiceRepository.deleteById(id);
    }
    public void updateRequestableResortServiceById(Long id, RequestableResortService requestableResortService) {
        requestableResortServiceRepository.save(requestableResortService);
    }

}
