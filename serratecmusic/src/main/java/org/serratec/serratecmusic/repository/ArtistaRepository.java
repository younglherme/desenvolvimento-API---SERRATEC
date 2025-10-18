package org.serratec.serratecmusic.repository;

import org.serratec.serratecmusic.entity.Artista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Long> {
}