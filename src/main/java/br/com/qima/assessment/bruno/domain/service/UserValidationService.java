package br.com.qima.assessment.bruno.domain.service;

import br.com.qima.assessment.bruno.domain.entity.AutenticationEntity;
import br.com.qima.assessment.bruno.domain.exception.InvalidUserException;
import br.com.qima.assessment.bruno.domain.exception.UserNotFoundException;
import br.com.qima.assessment.bruno.domain.repository.UserRepository;
import br.com.qima.assessment.bruno.presentation.dto.AutenticationInfoDto;
import br.com.qima.assessment.bruno.presentation.mapper.AutenticationInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserValidationService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder passwordValidator;

  public void validate(AutenticationInfoDto user) {
    userRepository.findByUsername(user.getUsername())
        .ifPresentOrElse(u -> {
          if (!passwordValidator.matches(user.getPassword(), u.getPassword())) {
            throw new InvalidUserException("Invalid username or password");
          }
        }, () -> {
          throw new UserNotFoundException("User not found");
        });
  }

  public void createUser(AutenticationInfoDto user) {
    AutenticationEntity entity = AutenticationInfoMapper.toEntity(user);
    entity.setPassword(passwordValidator.encode(user.getPassword()));
    userRepository.save(entity);
  }
}
