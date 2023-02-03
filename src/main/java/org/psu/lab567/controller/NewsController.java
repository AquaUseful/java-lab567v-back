package org.psu.lab567.controller;

import java.io.IOException;
import java.util.Collection;

import javax.validation.Valid;

import org.psu.lab567.model.News;
import org.psu.lab567.pojo.NewNewsRequest;
import org.psu.lab567.pojo.PatchNewsRequest;
import org.psu.lab567.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.NonNull;

@RestController
@RequestMapping(path = "news")
public class NewsController {
    @Autowired
    NewsService newsService;

    @GetMapping(path = "all")
    public ResponseEntity<Collection<News>> getAll() {
        final Collection<News> news = newsService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(news);
    }

    @GetMapping(path = "{id}/picture")
    public ResponseEntity<byte[]> getPicture(@NonNull @PathVariable("id") Long id) {
        final News news = newsService.getById(id);
        if (news.getPicture() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            final byte[] picture = news.getPicture().getContent();
            return ResponseEntity.status(HttpStatus.OK).body(picture);
        }
    }

    @PreAuthorize("hasAuthority('EDITOR')")
    @PostMapping(path = "")
    public ResponseEntity<Void> addNews(@NonNull @ModelAttribute @Valid NewNewsRequest request) throws IOException {
        newsService.add(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PreAuthorize("hasAuthority('EDITOR')")
    @PatchMapping(path = "{id}")
    public ResponseEntity<Void> updateNews(@NonNull @PathVariable("id") Long id,
            @NonNull @ModelAttribute @Valid PatchNewsRequest patch) throws IOException {
        newsService.patchById(id, patch);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PreAuthorize("hasAuthority('EDITOR')")
    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> deleteNews(@NonNull @PathVariable("id") Long id) {
        newsService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
