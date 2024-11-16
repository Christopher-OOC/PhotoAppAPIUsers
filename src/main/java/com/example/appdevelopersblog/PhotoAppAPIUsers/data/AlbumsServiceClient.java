package com.example.appdevelopersblog.PhotoAppAPIUsers.data;

import com.example.appdevelopersblog.PhotoAppAPIUsers.model.AlbumResponseModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "albums-ws")
public interface AlbumsServiceClient {

    @GetMapping(value = "/users/{id}/albums")
    List<AlbumResponseModel> getAlbums(@PathVariable("id") String id);

}
