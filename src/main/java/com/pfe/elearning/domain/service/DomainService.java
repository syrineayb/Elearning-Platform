package com.pfe.elearning.domain.service;

import com.pfe.elearning.common.PageResponse;
import com.pfe.elearning.domain.dto.request.DomainRequest;
import com.pfe.elearning.domain.dto.response.DomainResponse;

public interface DomainService {
    Long save(DomainRequest subject);
    DomainResponse findById(Long id);
    PageResponse<DomainResponse> findAll(int page, int size);
    void deleteById(Long id);
}
