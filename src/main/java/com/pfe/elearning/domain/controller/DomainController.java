package com.pfe.elearning.domain.controller;

import com.pfe.elearning.common.PageResponse;
import com.pfe.elearning.domain.dto.request.DomainRequest;
import com.pfe.elearning.domain.dto.response.DomainResponse;
import com.pfe.elearning.domain.service.DomainService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/domains")
@RequiredArgsConstructor
public class DomainController {
    private final DomainService domainService;
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Long> save(@RequestBody @Valid DomainRequest domainRequest) {
        return ResponseEntity.ok(domainService.save(domainRequest));
    }

    @GetMapping("/{domainId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<DomainResponse> findById(@PathVariable("domainId") Long domainId) {
        DomainResponse domainResponse = domainService.findById(domainId);
        if (domainResponse != null && domainResponse.getId() != null) {
            return ResponseEntity.ok(domainResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<PageResponse<DomainResponse>> findAll(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "4", required = false) int size) {
        return ResponseEntity.ok(domainService.findAll(page, size));
    }

}
