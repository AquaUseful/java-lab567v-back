package org.psu.lab567.service;

import java.io.IOException;
import java.util.Collection;

import javax.persistence.EntityNotFoundException;

import org.apache.catalina.User;
import org.psu.lab567.model.BinFile;
import org.psu.lab567.model.News;
import org.psu.lab567.pojo.NewNewsRequest;
import org.psu.lab567.pojo.PatchNewsRequest;
import org.psu.lab567.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.NonNull;

@Service
public class NewsService {
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private BinFileService binFileService;

    public News getById(@NonNull Long id) {
        final News news = newsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());
        return news;
    }

    public void add(@NonNull NewNewsRequest request) throws IOException {
        BinFile picture = null;
        if ((request.getPicture() != null) && (request.getPicture().getSize() > 0)) {
            picture = binFileService.createFromMultipart(request.getPicture());
        }
        final News news = new News(request.getTitle(),
                request.getContent(),
                picture);
        newsRepository.save(news);
    }

    public void patchById(@NonNull Long id, @NonNull PatchNewsRequest patch) throws IOException {
        News news = getById(id);
        if (patch.getRemovePicture() != null) {
            newsRepository.deleteById(news.getPicture().getId());
            news.setPicture(null);
        } else if ((patch.getPicture() != null) && (patch.getPicture().getSize() > 0)) {
            if (news.getPicture() == null) {
                final BinFile newPicture = binFileService.createFromMultipart(patch.getPicture());
                news.setPicture(newPicture);
            } else {
                binFileService.updateFromMultipart(news.getPicture(), patch.getPicture());
            }
        }
        news.setTitle(patch.getTitle());
        news.setContent(patch.getContent());
        newsRepository.save(news);
    }

    public void deleteById(@NonNull Long id) {
        final News news = getById(id);
        newsRepository.deleteById(id);
        if (news.getPicture() != null) {
            binFileService.delete(news.getPicture());
        }
    }

    public Collection<News> getAll() {
        final Collection<News> news = newsRepository.findAll();
        return news;
    }
}
