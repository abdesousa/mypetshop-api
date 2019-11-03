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
import com.mypetshop.api.dto.ApiErrorDTO;
import com.mypetshop.api.dto.MainDTO;
import com.mypetshop.api.dto.ProductDTO;
import com.mypetshop.api.persistence.model.Product;
import com.mypetshop.api.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value="/cart/product", description="Operations pertaining to products in My PetShop Online Store")
@CrossOrigin()
@RestController
public class ProductController {
	
    private Messages messages;
    private ProductService service;
    private ModelMapper modelMapper;
   
    @Autowired
    public ProductController(ProductService service,ModelMapper modelMapper, Messages messages) {
        this.service = service;
        this.modelMapper = modelMapper;
        this.messages = messages;
    }    
    
    @ApiOperation(value = "View a list of available products", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 400, message = "002 - Product list is empty."),

    })
    @GetMapping("/cart/product")
    @ResponseBody
    public ResponseEntity<?> list() {
    	
    	List<Product> products = service.list();
    	  
    	
		return new ResponseEntity<List<MainDTO>>(
				products.stream().map(s -> convertToDto(s)).collect(Collectors.toList()), HttpStatus.OK); 
		
    	
    }

    @ApiOperation(value = "View the information of a product", response = ProductDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "001 - Product doesn't exist!"),

    })
    @GetMapping("/cart/product/{id}")
    @ResponseBody
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
    	
    	Optional<Product> product = service.getById(Optional.ofNullable(id));
    	  
    	return new ResponseEntity<MainDTO>(convertToDto(product.get()), HttpStatus.OK); 
    	
    }
	
    @ApiOperation(value = "Insert a product into MY Pet Shop.", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully insert a product."),
            @ApiResponse(code = 400, message = "003 - Error while inserting a new product."),

    })
    @PostMapping("/cart/product")
    @ResponseBody
    public ResponseEntity<?> insert(@RequestBody ProductDTO productDTO) throws ParseException {
    	
    	Optional<Product> product = Optional.ofNullable(this.convertToEntity(productDTO));
       	
    	Optional<Product> productReturn = service.save(product);
    	  
    	if (productReturn.isPresent()) {  		
    		return new ResponseEntity<MainDTO>(this.convertToDto(product.get()), HttpStatus.OK); 
    		
    	} else {
    		
    		return new ResponseEntity<MainDTO>(new ApiErrorDTO(messages.get("msg.product.create.detail")), HttpStatus.BAD_REQUEST); 
    		
    	}
    }
    
    @ApiOperation(value = "Update a product on the MY Pet Shop.", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully update a product."),
            @ApiResponse(code = 400, message = "004 - Error while updating a product."),

    })
    @PutMapping("/cart/product")
    @ResponseBody
    public ResponseEntity<?> update(@RequestBody ProductDTO productDTO) throws ParseException {
    	
    	Optional<Product> product = Optional.ofNullable(this.convertToEntity(productDTO));
       	
    	Optional<Product> productReturn = service.save(product);
    	  
    	if (productReturn.isPresent()) {  		
    		return new ResponseEntity<MainDTO>(this.convertToDto(product.get()), HttpStatus.OK); 
    		
    	} else {
    		
    		return new ResponseEntity<MainDTO>(new ApiErrorDTO(messages.get("msg.product.update.detail")), HttpStatus.BAD_REQUEST); 
    		
    	}
    }
    
    @ApiOperation(value = "Delete a product on the MY Pet Shop.", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully delete a product."),
            @ApiResponse(code = 400, message = "005 - Error while deleting a product."),

    })
    @DeleteMapping("/cart/product/{id}")
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
    	
       	Optional<Product> product = Optional.of(new Product(id)); 
       	
    	service.delete(product);
    	
		return new ResponseEntity<MainDTO>(this.convertToDto(product.get()), HttpStatus.OK); 

    }
    
    private ProductDTO convertToDto(Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }
    
    private Product convertToEntity(ProductDTO dto) throws ParseException {
        return modelMapper.map(dto, Product.class);
    }
}
