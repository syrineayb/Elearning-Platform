package com.pfe.elearning.domain.service.ServiceImpl;

import com.pfe.elearning.common.PageResponse;
import com.pfe.elearning.domain.dto.mapper.DomainMapper;
import com.pfe.elearning.domain.dto.request.DomainRequest;
import com.pfe.elearning.domain.dto.response.DomainResponse;
import com.pfe.elearning.domain.entity.Domain;
import com.pfe.elearning.domain.repository.DomainRepository;
import com.pfe.elearning.domain.service.DomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DomainServiceImpl implements DomainService {
    private final DomainRepository domainRepository;
    private final DomainMapper mapper;
    @Override
    public Long save(DomainRequest d) {
        Domain domain = mapper.toDomain(d);
        return this.domainRepository.save(domain).getId();
    }

    @Override
    public DomainResponse findById(Long id) {
        return this.domainRepository.findById(id)
                .map(mapper::toDomainDto)
                .orElse(new DomainResponse());
    }

    @Override
    public PageResponse<DomainResponse> findAll(int page, int size) {
        var pageResult = this.domainRepository.findAll(PageRequest.of(page, size));
        return PageResponse.<DomainResponse>builder()
                .content(
                        pageResult.getContent()
                                .stream()
                                .map(mapper::toDomainDto)
                                .toList()
                )
                .totalPages(pageResult.getTotalPages())
                .build();
    }


    @Override
    public void deleteById(Long id) {
        this.domainRepository.deleteById(id);
    }
}
