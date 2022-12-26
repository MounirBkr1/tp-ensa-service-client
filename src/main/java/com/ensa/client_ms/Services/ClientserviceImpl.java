package com.ensa.client_ms.Services;

import com.ensa.client_ms.Exceptions.RessourceNotFoundException;
import com.ensa.client_ms.dao.ClientRepository;
import com.ensa.client_ms.entities.Client;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.io.IOException;
import java.util.List;


@Service
public class ClientserviceImpl implements Clientservice {


    private ClientRepository clientRepository;

    public ClientserviceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    @Override
    public Client saveClient(Client client) {
        //this 2 lines are supplementaires
        Client client1=clientRepository.findByFirstname(client.getFirstname());
        if(client1 != null) throw new RuntimeException("User already exists");


        //user existe pas , creer un user
        Client  new_client= new Client();
        new_client.setFirstname(client.getFirstname());
        new_client.setLastname(client.getLastname());
        new_client.setTelephone(client.getTelephone());
        new_client.setEmail(client.getEmail());
        clientRepository.save(new_client);

        return new_client;
    }

    @Override
    public Client LoadClientByFirstname(String firstname) {
        return clientRepository.findByFirstname(firstname);
    }



    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();

        /*
        or
        List<Client> list_clients = new ArrayList<Client>();
        clientRepository.findAll().forEach(c -> list_clients.add(c));
        return list_clients;

         */
    }

    @Override
    public Client getClientById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(()-> new RessourceNotFoundException("cours avec l'id" + id + "n'existe pas"));
    }

    @Override
    public void updateClient(Long id,Client clientUpdate) {
        Client client=clientRepository.findById(id)
                .orElseThrow(()->new RessourceNotFoundException("cours avec l'id" + id + "n'existe pas"));

        //set params (passed by RequestBody) of client
        client.setFirstname(clientUpdate.getFirstname());
        client.setLastname(clientUpdate.getLastname());
        client.setTelephone(clientUpdate.getTelephone());
        client.setEmail(clientUpdate.getEmail());

        Client updatedClient = clientRepository.save(client);

    }

    @Override
    public  void deleteClient(Long id) {
        //clientRepository.deleteById(id);
        //or:
        Client client=clientRepository.findById(id)
                .orElseThrow(()->new RessourceNotFoundException("cours avec l'id" + id + "n'existe pas"));

        clientRepository.delete(client);
    }

    //***********PHOTO**********************************************

    //System.getProperty("user.home") => renvoi vers ce path: C:\Users\miirb
    public static String uploadDirectory= System.getProperty("user.home")+"/coursPhoto/";

    @Override
    public byte[] getPhoto(Long id) throws IOException {
        //get element by id
        Client c= clientRepository.findById(id).get();
        String img= c.getImage();


        //return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/coursPhoto/"+c.getImage()));
        return Files.readAllBytes(Paths.get(this.uploadDirectory+c.getImage()));

    }

    @Override
    public void uploadPhoto(MultipartFile file, Long id) throws IOException {
        //MultipartFile: A representation of an uploaded file received in a multipart request.
        Client c= clientRepository.findById(id).get();

        c.setImage(file.getOriginalFilename()); //save with name
        System.out.println(file.getOriginalFilename());
        //Files.write(Paths.get(this.uploadDirectory+c.getId()),file.getBytes());
        Files.write(Paths.get(this.uploadDirectory+ file.getOriginalFilename()),file.getBytes());

        //c.setImageName(id+".png"); //save with id

        clientRepository.save(c);
    }


}
