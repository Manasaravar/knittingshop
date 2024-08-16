package com.korniushin.eshop.model.entities;

import jakarta.persistence.*;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table (name = "users")
public class User implements UserDetails {
    public static final String SEQ_NAME = "user_seq";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME,sequenceName = SEQ_NAME,allocationSize = 1)
    private Long id;
//    @Size(min = 3, message = "Имя пользователя не менее 3 символов")
    @Column(name = "username", nullable = false, unique = true, length = 25)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "phone", length = 11)
    private String phone;

    @Column(name = "archive")
    private boolean archive;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL)
    //@JoinColumn(name = "order_id")
    private Set<Order> orders;

    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL)
    //@JoinColumn(name = "order_id")
    private Set<Reviews> reviews;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.orders = new HashSet<>();
        this.reviews = new HashSet<>();
        Order order = Order.builder()
                .user(this)
                .address("")
                .created(LocalDateTime.now())
                .orderStatus(OrderStatus.CART)
                .updated(LocalDateTime.now())
                .totalPrice(0.0)
                .totalQuantity(0)
                .productsPositions(new HashSet<>())
                .build();
        orders.add(order);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>() {{add(new SimpleGrantedAuthority(role.name()));}};
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
