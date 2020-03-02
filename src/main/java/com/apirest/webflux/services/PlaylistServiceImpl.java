package com.apirest.webflux.services;

import com.apirest.webflux.document.Playlist;
import com.apirest.webflux.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PlaylistServiceImpl implements PlaylistService {

    private final PlaylistRepository playlistRepository;

    @Autowired
    public PlaylistServiceImpl(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    @Override
    public Flux<Playlist> findAll() {
        return this.playlistRepository.findAll();
    }

    @Override
    public Mono<Playlist> findById(String id) {
        return this.playlistRepository.findById(id);
    }

    @Override
    public Mono<Playlist> save(Playlist playlist) {
        return this.playlistRepository.save(playlist);
    }

}
