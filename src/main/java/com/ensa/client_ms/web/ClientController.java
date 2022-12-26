package com.ensa.client_ms.web;

import com.ensa.client_ms.Services.Clientservice;
import com.ensa.client_ms.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class ClientController {


    private final Clientservice clientservice;

    public ClientController(Clientservice clientservice) {
        this.clientservice = clientservice;
    }

    //get/read list client
    @GetMapping("/client")
    public List<Client> getAllclients(){
        return clientservice.getAllClients();
    }


    //get/read client by id
    @GetMapping("client/{id}")
    public Client getClientById(@PathVariable Long id){
        return clientservice.getClientById(id);
    }


    //post/create client
    @PostMapping("/client")
    public Client addClient(@RequestBody Client client){
        return clientservice.saveClient(client);
    }


    //update/put client
    @PutMapping("/client/{id}")
    public void updateClient(@PathVariable("id") Long id,@RequestBody Client client){
         clientservice.updateClient(id,client);
    }


    //delete client
    @DeleteMapping("client/{id}")
    public void deleteClient(@PathVariable Long id){
        clientservice.deleteClient(id);
    }

    /*
    other way to delete client

    @DeleteMapping("client/{id}")
    public String deleteClient2(@PathVariable Long id){
        clientservice.deleteClient(id);
        return "Deleted Successfully";
    } */


    //**************PHOTO***************************************

    @GetMapping(path="/photoCours/{id}",produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getPhoto(@PathVariable("id") Long id) throws IOException {
        return clientservice.getPhoto(id);

    }

    //POST photo : update photo
    @PostMapping(path="/uploadPhoto/{id}")
    public void uploadPhoto(MultipartFile file, @PathVariable Long id) throws IOException {
        clientservice.uploadPhoto(file,id);
    }




}
