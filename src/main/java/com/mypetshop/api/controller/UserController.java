package com.mypetshop.api.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mypetshop.api.components.Messages;
import com.mypetshop.api.dto.ErrorDTO;
import com.mypetshop.api.dto.MainDTO;
import com.mypetshop.api.dto.UserDTO;
import com.mypetshop.api.persistence.model.User;
import com.mypetshop.api.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value="/cart/user", description="Operations pertaining to users in My PetShop Online Store")
@CrossOrigin()
@RestController
public class UserController {
	
    private Messages messages;
    private UserService service;
    private ModelMapper modelMapper;
   
    @Autowired
    public UserController(UserService service,ModelMapper modelMapper, Messages messages) {
        this.service = service;
        this.modelMapper = modelMapper;
        this.messages = messages;
    }    
    
    @ApiOperation(value = "View a list of available users", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "002 - User list is empty."),

    })
    @GetMapping("/cart/user")
    @ResponseBody
    public ResponseEntity<?> list() {
    	
    	List<User> users = service.list();
    	  
		return new ResponseEntity<List<MainDTO>>(
				users.stream().map(s -> convertToDto(s)).collect(Collectors.toList()), HttpStatus.OK); 
	
    }

    @ApiOperation(value = "View the information of a user", response = UserDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "001 - User doesn't exist!"),

    })
    @GetMapping("/cart/user/{id}")
    @ResponseBody
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
    	
    	Optional<User> user = service.getById(Optional.ofNullable(id));
    	  
    	if (user.isPresent()) {  		
    		return new ResponseEntity<MainDTO>(convertToDto(user.get()), HttpStatus.OK); 
    		
    	} else {
    		
    		return new ResponseEntity<MainDTO>(new ErrorDTO(messages.get("msg.user.notexists.code"),messages.get("msg.user.notexists.detail")), HttpStatus.BAD_REQUEST); 
    		
    	}
    }
	
    @ApiOperation(value = "Insert a user into MY Pet Shop.", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully insert a user."),
            @ApiResponse(code = 401, message = "003 - Error while inserting a new user."),

    })
    @PostMapping("/cart/user")
    @ResponseBody
    public ResponseEntity<?> insert(@RequestBody UserDTO userDTO) throws ParseException {
    	
    	Optional<User> user = Optional.ofNullable(this.convertToEntity(userDTO));
       	
    	Optional<User> userReturn = service.save(user);
    	  
    	if (userReturn.isPresent()) {  		
    		return new ResponseEntity<MainDTO>(this.convertToDto(user.get()), HttpStatus.OK); 
    		
    	} else {
    		
    		return new ResponseEntity<MainDTO>(new ErrorDTO(messages.get("msg.user.create.code"),messages.get("msg.user.create.detail")), HttpStatus.BAD_REQUEST); 
    		
    	}
    }
    
    @ApiOperation(value = "Update a user on the MY Pet Shop.", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully update a user."),
            @ApiResponse(code = 401, message = "004 - Error while updating a user."),

    })
    @PutMapping("/cart/user")
    @ResponseBody
    public ResponseEntity<?> update(@RequestBody UserDTO userDTO) throws ParseException {
    	
    	Optional<User> user = Optional.ofNullable(this.convertToEntity(userDTO));
       	
    	Optional<User> userReturn = service.save(user);
    	  
    	if (userReturn.isPresent()) {  		
    		return new ResponseEntity<MainDTO>(this.convertToDto(user.get()), HttpStatus.OK); 
    		
    	} else {
    		
    		return new ResponseEntity<MainDTO>(new ErrorDTO(messages.get("msg.user.update.code"),messages.get("msg.user.update.detail")), HttpStatus.BAD_REQUEST); 
    		
    	}
    }
    
    @ApiOperation(value = "Delete a user on the MY Pet Shop.", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully delete a user."),
            @ApiResponse(code = 401, message = "005 - Error while deleting a user."),

    })
    @DeleteMapping("/cart/user")
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
    	
       	Optional<User> user = Optional.of(new User(id)); 
       	
    	service.delete(user);
    	
		return new ResponseEntity<MainDTO>(this.convertToDto(user.get()), HttpStatus.OK); 

    }
    
    private UserDTO convertToDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
    
    private User convertToEntity(UserDTO dto) throws ParseException {
        return modelMapper.map(dto, User.class);
    }
}
