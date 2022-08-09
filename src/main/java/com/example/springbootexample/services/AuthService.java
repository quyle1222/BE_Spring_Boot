package com.example.springbootexample.services;

import com.example.springbootexample.dto.ApiRepository;
import com.example.springbootexample.dto.EmployeeDTO;
import com.example.springbootexample.dto.UserDTO;
import com.example.springbootexample.jwt.CustomUserDetails;
import com.example.springbootexample.jwt.JwtTokenProvider;
import com.example.springbootexample.jwt.WebSecurityConfig;
import com.example.springbootexample.models.User;
import com.example.springbootexample.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService  {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CommonService commonService;
    @Autowired
    JwtTokenProvider tokenProvider;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    WebSecurityConfig webSecurityConfig;
    public ApiRepository createUser(UserDTO user) {
        ApiRepository apiRepository = new ApiRepository();
        try {
            userRepository.saveUser(user.getUsername(),webSecurityConfig.passwordEncoder().encode(user.getPassword()).toString());
            apiRepository.setSuccess(true);
            apiRepository.setData(null);
            apiRepository.setMessage("Create user success");
        } catch (Exception error) {
            apiRepository.setMessage(error.getMessage());
        }
        return apiRepository;
    }

    public ApiRepository login (UserDTO user){
        ApiRepository repository = new ApiRepository();
        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
            User userDB = userRepository.findByUsername(user.getUsername());
            EmployeeDTO data = new EmployeeDTO();
            data.setToken(jwt);
            data.setUserId(userDB.getUser_id());
            data.setEmpId(userDB.getEmp_id());
            repository.setData(data);
        }catch (Exception e){
            repository.setMessage(e.getMessage());
        }
     return repository;
    }
}
