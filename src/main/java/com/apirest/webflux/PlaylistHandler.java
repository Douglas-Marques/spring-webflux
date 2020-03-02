package com.apirest.webflux;

import com.apirest.webflux.document.Playlist;
import com.apirest.webflux.services.PlaylistService;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

//@Component
public class PlaylistHandler {

    private final PlaylistService playlistService;

    //@Autowired
    public PlaylistHandler(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    public Mono<ServerResponse> findAll(ServerRequest serverRequest) {
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(this.playlistService.findAll(), Playlist.class);
    }

    public Mono<ServerResponse> findById(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(this.playlistService.findById(id), Playlist.class);
    }

    public Mono<ServerResponse> save(ServerRequest serverRequest) {
        final Mono<Playlist> playlist = serverRequest.bodyToMono(Playlist.class);
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(playlist.flatMap(this.playlistService::save), Playlist.class));
    }

}
