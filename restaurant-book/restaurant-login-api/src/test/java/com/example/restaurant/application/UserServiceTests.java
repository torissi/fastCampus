package com.example.restaurant.application;

import com.example.restaurant.domain.User;
import com.example.restaurant.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class UserServiceTests {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        userService = new UserService(userRepository, passwordEncoder);
    }

    @Test
    public void authenticateWithValidAttributes() {
        String email = "tester@example.com";
        String password = "test";

        final User mockUser = User.builder()
                .email(email)
                .build();
        given(userRepository.findByEmail(email)).willReturn(Optional.of(mockUser));

        given(passwordEncoder.matches(any(), any())).willReturn(true);

        User user = userService.authenticate(email, password);
        assertThat(user.getEmail()).isEqualTo(email);
    }

    @Test
    public void authenticateWithNotExistedEmail() {
        String email = "xxx@example.com";
        String password = "test";

        given(userRepository.findByEmail(email)).willReturn(Optional.empty());

        assertThatThrownBy(() -> {
            userService.authenticate(email, password);
        }).isInstanceOf(EmailNotExistedException.class);
    }

    @Test
    public void authenticateWithWrongPassword() {
        String email = "tester@example.com";
        String password = "xxxx";

        User mockUser = User.builder().email(email).build();

        given(userRepository.findByEmail(email)).willReturn(Optional.of(mockUser));

        given(passwordEncoder.matches(any(), any())).willReturn(false);

        assertThatThrownBy(() -> {
            userService.authenticate(email, password);
        }).isInstanceOf(PasswordWrongException.class);
    }
}