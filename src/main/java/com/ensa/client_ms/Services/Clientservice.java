package com.ensa.client_ms.Services;

import com.ensa.client_ms.entities.Client;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


//interface de definition des methodes abstraites to implements
public interface Clientservice {

    //post :add/create client
    public Client saveClient(Client client);

    public Client LoadClientByFirstname(String firstname);

    //get: list client
    public List<Client> getAllClients();

    //get: client by id
    public Client getClientById(Long id);

    //put: update client
    public void updateClient(Long id,Client client);

    //delete client by id
    public void deleteClient(Long id);


    public byte[] getPhoto(Long id) throws IOException;

    //public interface MultipartFile extends InputStreamSource, is A representation of an uploaded file received in a multipart request.
    public void uploadPhoto(MultipartFile file, Long id) throws IOException;




}
