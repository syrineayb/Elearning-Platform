package com.pfe.elearning.dto.mapper;

import com.pfe.elearning.dto.request.DomainRequest;
import com.pfe.elearning.dto.response.DomainResponse;
import com.pfe.elearning.entity.Domain;
import org.springframework.stereotype.Service;

@Service
public class DomainMapper {
    public DomainResponse toDomainDto(Domain domain) {
        return DomainResponse.builder()
                .title(domain.getTitle())
                .build();
    }
    public Domain toDomain(DomainRequest domain) {
        if(domain == null) {
            return Domain.builder().build();
        }
        return Domain.builder()
                .id(domain.getId())
                .title(domain.getTitle())
                .build();
    }
}
