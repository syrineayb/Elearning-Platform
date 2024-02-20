package com.pfe.elearning.controller;

import com.pfe.elearning.common.PageResponse;
import com.pfe.elearning.dto.request.DomainRequest;
import com.pfe.elearning.dto.response.DomainResponse;
import com.pfe.elearning.service.DomainService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/domains")
@RequiredArgsConstructor
public class DomainController {
    private final DomainService domainService;
    @PostMapping
    public ResponseEntity<Long> save(
            @RequestBody @Valid DomainRequest domainRequest
    ) {
        return ResponseEntity
                .accepted()
                .body(domainService.save(domainRequest));
    }
    @GetMapping("/{domain-id}")
    @PreAuthorize("hasAnyRole('ADMIN','CANDIDATE')")
    public ResponseEntity<DomainResponse> findById(
            @PathVariable("domain-id") Long domainId
    ) {
        return ResponseEntity.ok(domainService.findById(domainId));
    }
    @GetMapping
    public ResponseEntity<PageResponse<DomainResponse>> findAll(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "4", required = false) int size
    ) {
        return ResponseEntity.ok(domainService.findAll(page, size));
    }




}
