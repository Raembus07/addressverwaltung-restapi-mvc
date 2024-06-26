/*
 * PersonService.java
 *
 * Creator:
 * 31.05.2024 08:36 josia.schweizer
 *
 * Maintainer:
 * 31.05.2024 08:36 josia.schweizer
 *
 * Last Modification:
 * $Id:$
 *
 * Copyright (c) 2024 ABACUS Research AG, All Rights Reserved
 */
package ch.abacus.restapi;

import ch.abacus.restapi.dto.PersonDTO;
import jakarta.annotation.Nonnull;

import java.util.List;
import java.util.Optional;

public interface PersonService {

  List<PersonDTO> findAllPerson();

  Optional<PersonDTO> findById(@Nonnull final Long id);

  void deleteById(@Nonnull final Long id);

  PersonDTO save(@Nonnull final PersonDTO personDTO);

  PersonDTO update(@Nonnull final Long id, @Nonnull final PersonDTO personDTO);
}