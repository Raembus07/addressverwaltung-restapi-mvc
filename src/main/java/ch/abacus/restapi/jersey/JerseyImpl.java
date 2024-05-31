/*
 * PersonRepository.java
 *
 * Creator:
 * 31.05.2024 09:03 josia.schweizer
 *
 * Maintainer:
 * 31.05.2024 09:03 josia.schweizer
 *
 * Last Modification:
 * $Id:$
 *
 * Copyright (c) 2024 ABACUS Research AG, All Rights Reserved
 */
package ch.abacus.restapi.jersey;

import ch.abacus.common.RestapiConst;
import ch.abacus.restapi.PersonService;
import ch.abacus.restapi.dto.PersonDTO;
import jakarta.annotation.Nonnull;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

public class JerseyImpl implements PersonService {

  private Client client = ClientBuilder.newClient();

  @Override
  public List<PersonDTO> findAllPerson() {
    return client.target(RestapiConst.REST_URL)
        .request(MediaType.APPLICATION_JSON)
        .get(new GenericType<List<PersonDTO>>() {
        });
  }

  @Override
  public Optional<PersonDTO> findById(@Nonnull Long id) {
    return Optional.ofNullable(client.target(RestapiConst.REST_URL)
                                   .path(String.valueOf(id))
                                   .request(MediaType.APPLICATION_JSON)
                                   .get(PersonDTO.class));
  }

  @Override
  public void deleteById(@Nonnull Long id) {
    client.target(RestapiConst.REST_URL)
        .path(String.valueOf(id))
        .request(MediaType.APPLICATION_JSON)
        .delete();
  }

  @Override
  public PersonDTO save(@Nonnull PersonDTO personDTO) {
    try (Response response = client.target(RestapiConst.REST_URL)
        .request(MediaType.APPLICATION_JSON)
        .post(Entity.entity(personDTO, MediaType.APPLICATION_JSON))) {
      return response.readEntity(PersonDTO.class);
    }
  }

  @Override
  public PersonDTO update(@Nonnull Long id, @Nonnull PersonDTO personDTO) {
    return client.target(RestapiConst.REST_URL)
        .path(String.valueOf(id))
        .request(MediaType.APPLICATION_JSON)
        .put(Entity.entity(personDTO, MediaType.APPLICATION_JSON), PersonDTO.class);
  }
}