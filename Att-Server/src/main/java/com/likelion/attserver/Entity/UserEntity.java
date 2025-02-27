package com.likelion.attserver.Entity;

import com.likelion.attserver.DTO.UserDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "User")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    private Long id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role; // enum 타입으로 선언

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<AttendanceEntity> attendance;

    public enum Role {
        ADMIN, // 운영자
        STUDENT // 학생
    }

    public static UserDTO toDTO(UserEntity userEntity) {
        return UserDTO.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .role(userEntity.getRole())
                .build();
    }
}
