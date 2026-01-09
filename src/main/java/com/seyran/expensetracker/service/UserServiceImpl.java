package com.seyran.expensetracker.service;


import com.seyran.expensetracker.dto.request.response.UserUpdateRequest;
import com.seyran.expensetracker.exception.BadRequestException;
import com.seyran.expensetracker.exception.NotFoundException;
import com.seyran.expensetracker.model.User;
import com.seyran.expensetracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        user.setCreatedAt(LocalDateTime.now());
        if(userRepository.existsByEmail(user.getEmail())){
            throw new BadRequestException("Email already in use");
        }
        return userRepository.save(user);

    }
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(()-> new NotFoundException("User with id " + id + " not found"));
    }
    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(()-> new NotFoundException("User with email " + email + " not found"));
    }
    @Override
    public User update(Long id, UserUpdateRequest request){
        User userToUpdate = userRepository.findById(id).orElseThrow(()-> new NotFoundException("User with id " + id + " not found"));
        if(request.getEmail()!=null && !userToUpdate.getEmail().equals(request.getEmail())) {
            if (userRepository.existsByEmail(request.getEmail())) {
                throw new BadRequestException("Email already in use");
            }
            userToUpdate.setEmail(request.getEmail());
        }
        if(request.getPassword()!=null) {
            userToUpdate.setPassword(request.getPassword());
        }
            return userRepository.save(userToUpdate);
    }

    @Override
    public void deleteUser(Long id) {
        if(!userRepository.existsById(id)) {
            throw new NotFoundException("User with id " + id + " not found");
        }
        userRepository.deleteById(id);
    }

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable );
    }
}
