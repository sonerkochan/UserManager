package com.example.usermanager.service;

import com.example.usermanager.dto.UserDTO;
import com.example.usermanager.model.User;
import com.example.usermanager.repository.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setDateOfBirth(user.getDateOfBirth());
        dto.setPhoneNumber(user.getPhoneNumber());
        return dto;
    }

    public User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setDateOfBirth(userDTO.getDateOfBirth());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        return user;
    }

    public List<User> searchUsers(String searchTerm, String sortBy, String order) {
        Sort.Direction direction = order.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, sortBy);

        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return userRepository.findAll(sort);
        }

        return userRepository.findBySearchTerm(searchTerm, sort);
    }

    public List<User> getPaginatedUsers(int page, int size) {
        int offset = page * size;
        return userRepository.findUsersWithPagination(offset, size);
    }

    public int getTotalPages(int size) {
        long totalUsers = userRepository.count();
        return (int) Math.ceil((double) totalUsers / size);
    }
}