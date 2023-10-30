package mate.academy.service;

import mate.academy.dto.user.UserRegistrationRequestDto;
import mate.academy.dto.user.UserResponseDto;
import mate.academy.exception.RegistrationException;
import mate.academy.model.User;

public interface UserService {

    User getUser(Long userId);

    UserResponseDto register(UserRegistrationRequestDto request) throws RegistrationException;

}
