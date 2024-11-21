package org.example.soutnance.controller;


import lombok.RequiredArgsConstructor;
import org.example.soutnance.dto.response.ArbaresResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ArbareController {

//    private  final ArbreService arbreService;


    public ResponseEntity<ArbaresResponse>  createArbare(){
        return null;
    }




}
