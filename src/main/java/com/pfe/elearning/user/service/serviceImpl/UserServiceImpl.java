package com.pfe.elearning.user.service.serviceImpl;

import com.pfe.elearning.common.PageResponse;
import com.pfe.elearning.role.Role;
import com.pfe.elearning.role.RoleType;
import com.pfe.elearning.user.dto.ChangePasswordRequest;
import com.pfe.elearning.user.dto.UserMapper;
import com.pfe.elearning.user.dto.UserRequest;
import com.pfe.elearning.user.dto.UserResponse;
import com.pfe.elearning.user.entity.User;
import com.pfe.elearning.user.repository.UserRepository;
import com.pfe.elearning.user.service.UserService;
import com.pfe.elearning.validator.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ObjectsValidator<UserRequest> validator;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void create(UserRequest request) {
        validator.validate(request);
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setActive(false);
        userRepository.save(user);
    }

    @Override
    public PageResponse<UserResponse> findAll(int page, int size) {
        // Récupérer les utilisateurs depuis le dépôt
        Page<User> pageResult = userRepository.findAll(PageRequest.of(page, size));

        // Filtrer les utilisateurs pour exclure les administrateurs
        List<UserResponse> userResponses = pageResult.getContent().stream()
                .filter(user -> user.getRoles().stream().noneMatch(role -> role.getName().equals("ROLE_ADMIN")))
                .map(userMapper::toUserResponse)
                .collect(Collectors.toList());

        // Construire la réponse de la page avec les utilisateurs filtrés
        return PageResponse.<UserResponse>builder()
                .content(userResponses)
                .totalPages(pageResult.getTotalPages())
                .build();
    }

    @Override
    public UserResponse findById(Integer id) {
        return userRepository.findById(id)
                .map(userMapper::toUserResponse)
                .orElseThrow(() -> new EntityNotFoundException("No user found with the ID: " + id));
    }

    @Override
    public void update(Integer userId, UserRequest request) {
        // Retrieve the user from the database
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

        // Update the user's information with the new values
        existingUser.setFirstname(request.getFirstname());
        existingUser.setLastname(request.getLastname());
        existingUser.setEmail(request.getEmail());
        existingUser.setPassword(request.getPassword());
        existingUser.setGenre(request.getGenre());

        // Save the updated user back to the database
        userRepository.save(existingUser);
    }
    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    public PageResponse<UserResponse> findAllUsersByState(boolean active, int page, int size) {
        Page<User> userPage = userRepository.findAllByActive(active, PageRequest.of(page, size));

        List<UserResponse> userResponses = userPage.getContent()
                .stream()
                .map(userMapper::toUserResponse)
                .collect(Collectors.toList());

        return PageResponse.<UserResponse>builder()
                .content(userResponses)
                .totalPages(userPage.getTotalPages())
                .build();
    }
    @Override
    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {
        if (connectedUser == null) {
            throw new IllegalArgumentException("Principal is null");
        }

        User user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        // check if the current password is correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        // check if the two new passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Passwords do not match");
        }

        // update the password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        // save the new password
        userRepository.save(user);
    }
    @Override
    public User getUserByEmail(String username) {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found with username: " + username));
    }




}
