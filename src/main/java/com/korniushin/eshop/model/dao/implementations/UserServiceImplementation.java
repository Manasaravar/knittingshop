package com.korniushin.eshop.model.dao.implementations;

import com.korniushin.eshop.model.dao.repositories.UserRepository;
import com.korniushin.eshop.model.dao.interfaces.UserService;
import com.korniushin.eshop.model.entities.Order;
import com.korniushin.eshop.model.entities.OrderStatus;
import com.korniushin.eshop.model.entities.Role;
import com.korniushin.eshop.model.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> all() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
       if (userRepository.findByUsername(user.getUsername()).isEmpty()) {

           Set<Order> orders = new HashSet<>();
           Order order = Order.builder()
                   .user(user)
                   .address("")
                   .created(LocalDateTime.now())
                   .orderStatus(OrderStatus.CART)
                   .updated(LocalDateTime.now())
                   .totalPrice(0.0)
                   .totalQuantity(0)
                   .productsPositions(new HashSet<>())
                   .build();
           orders.add(order);

           user.setPassword(passwordEncoder.encode(user.getPassword()));
           user.setRole(Role.ROLE_CLIENT);
           user.setOrders(orders);

           return userRepository.save(user);
       }
       return null;

    }

    @Override
    public User update(User user) {
        User userToUpdate = userRepository.findById(user.getId()).get();
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setPhone(user.getPhone());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(userToUpdate);
    }


    @Override
    public boolean deleteById(Long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
    @Override
    public User edit(User user) {
        return userRepository.save(user);
    }



}
