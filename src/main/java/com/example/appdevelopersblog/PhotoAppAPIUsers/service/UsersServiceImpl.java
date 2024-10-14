package com.example.appdevelopersblog.PhotoAppAPIUsers.service;

import com.example.appdevelopersblog.PhotoAppAPIUsers.data.UserEntity;
import com.example.appdevelopersblog.PhotoAppAPIUsers.data.UsersRepository;
import com.example.appdevelopersblog.PhotoAppAPIUsers.shared.UserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UsersServiceImpl implements UsersService {

    private UsersRepository usersRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UsersServiceImpl(
            UsersRepository usersRepository,
            BCryptPasswordEncoder bCryptPasswordEncoder
    ) {
        this.usersRepository = usersRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public UserDto createUser(UserDto userDetails) {

        userDetails.setUserId(UUID.randomUUID().toString());
        userDetails.setEncryptedPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class);

        UserEntity save = usersRepository.save(userEntity);

        UserDto userDto = modelMapper.map(save, UserDto.class);

        return userDto;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = usersRepository.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return new User(
                user.getEmail(),
                user.getEncryptedPassword(),
                true,
                true,
                true,
                true,
                new ArrayList<>());
    }
}
