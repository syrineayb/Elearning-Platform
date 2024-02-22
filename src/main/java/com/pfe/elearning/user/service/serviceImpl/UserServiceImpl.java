package com.pfe.elearning.user.service.serviceImpl;

import com.pfe.elearning.common.PageResponse;
import com.pfe.elearning.user.dto.mapper.UserMapper;
import com.pfe.elearning.user.dto.response.UserResponse;
import com.pfe.elearning.user.repository.UserRepository;
import com.pfe.elearning.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public PageResponse<UserResponse> findAll(int page, int size) {
        var pageResult = this.userRepository.findAll(PageRequest.of(page, size));
        System.out.println("Total Elements: " + pageResult.getTotalElements());
        System.out.println("Number of Elements: " + pageResult.getNumberOfElements());
        System.out.println("Content Size: " + pageResult.getContent().size());
        return PageResponse.<UserResponse>builder()
                .content(
                        pageResult.getContent()
                                .stream()
                                .map(userMapper::toUserResponseDto)
                                .toList()
                )
                .totalPages(pageResult.getTotalPages())
                .build();
    }
}
