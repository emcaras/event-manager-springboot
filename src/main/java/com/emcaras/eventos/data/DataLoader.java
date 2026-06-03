package com.emcaras.eventos.data;

import com.emcaras.eventos.domain.*;
import com.emcaras.eventos.repository.CategoryRepository;
import com.emcaras.eventos.repository.RoleRepository;
import com.emcaras.eventos.repository.SpeakerRepository;
import com.emcaras.eventos.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Transactional
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final SpeakerRepository speakerRepository;
    private final CategoryRepository categoryRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        Role adminRole = roleRepository.findRoleByName("ROLE_ADMIN")
                .orElseGet( () -> {
                    Role newRole = new Role();
                    newRole.setName("ROLE_ADMIN");
                    return roleRepository.save(newRole);
                });

        Role userRole = roleRepository.findRoleByName("ROLE_USER")
                .orElseGet( () -> {
                    Role newRole = new Role();
                    newRole.setName("ROLE_USER");
                    return roleRepository.save(newRole);
                });

        User userAdmin = userRepository.findUserByUsername("emcaras")
                .orElseGet(() -> {
                    User user1 = new User();
                    user1.setName("Emiliano Carranza Ramos");
                    user1.setEmail("emilianocarranzar7@gmail.com");
                    user1.setUsername("emcaras");
                    user1.setPassword(passwordEncoder.encode("1234"));

                    Set<Role> adminRoles = new HashSet<>();
                    adminRoles.add(adminRole);
                    adminRoles.add(userRole);

                    user1.setRoles(adminRoles);

                    return userRepository.save(user1);
                });

        User user = userRepository.findUserByUsername("user")
                .orElseGet(() -> {
                    User user1 = new User();
                    user1.setName("Usuario normal");
                    user1.setEmail("usuario@gmail.com");
                    user1.setUsername("user");
                    user1.setPassword(passwordEncoder.encode("1234"));

                    Set<Role> userRoles = new HashSet<>();
                    userRoles.add(userRole);

                    user1.setRoles(userRoles);

                    return userRepository.save(user1);
                });

        // --- 4. Crear y Guardar Categorías si no existen ---
        if (!categoryRepository.existsByName("Conferencia")) {
            Category conferencia = new Category(null, "Conferencia", "Eventos de gran escala con múltiples oradores.");
            categoryRepository.save(conferencia);
        }
        if (!categoryRepository.existsByName("Taller")) {
            Category taller = new Category(null, "Taller", "Eventos interactivos y prácticos.");
            categoryRepository.save(taller);
        }
        if (!categoryRepository.existsByName("Webinar")) {
            Category webinar = new Category(null, "Webinar", "Seminarios online en vivo.");
            categoryRepository.save(webinar);
        }

        // --- 5. Crear y Guardar Oradores si no existen ---
        if (!speakerRepository.existsByEmail("john.doe@example.com")) {
            Speaker john = new Speaker(null, "John Doe", "john.doe@example.com", "Experto en desarrollo de software.", new HashSet<>());
            speakerRepository.save(john);
        }
        if (!speakerRepository.existsByEmail("jane.smith@example.com")) {
            Speaker jane = new Speaker(null, "Jane Smith", "jane.smith@example.com", "Especialista en marketing digital.", new HashSet<>());
            speakerRepository.save(jane);
        }
    }
}
