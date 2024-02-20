package com.pfe.elearning.service;

import com.pfe.elearning.common.PageResponse;
import com.pfe.elearning.dto.request.DomainRequest;
import com.pfe.elearning.dto.response.DomainResponse;
import com.pfe.elearning.entity.Domain;

public interface DomainService {
    Long save(DomainRequest subject);
    DomainResponse findById(Long id);
    PageResponse<DomainResponse> findAll(int page, int size);
    void deleteById(Long id);
}
