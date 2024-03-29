package com.studies.course.services;

import com.studies.course.entities.User;
import com.studies.course.repositories.UserRepository;
import com.studies.course.resources.exceptions.ResourceNotFoundException;
import com.studies.course.services.exceptions.DatabaseException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(Long id){
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public User insert(User user){
        return userRepository.save(user);
    }

    public void delete(Long id){
        try {
            userRepository.deleteById(id);
        }catch (EmptyResultDataAccessException ex){
            throw new ResourceNotFoundException(id);
        }catch (DataIntegrityViolationException ex){
            throw new DatabaseException(ex.getMessage());
        }
    }

    public User update(Long id, User user){
        try {
            User entity = userRepository.getReferenceById(id);
            updateData(entity, user);
            return userRepository.save(entity);
        }catch (EntityNotFoundException ex){
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(User entity, User user) {
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        entity.setPhone(user.getPhone());
    }
}
