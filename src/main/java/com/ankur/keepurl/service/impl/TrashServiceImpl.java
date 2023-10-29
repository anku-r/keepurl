package com.ankur.keepurl.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ankur.keepurl.annotation.Cached;
import com.ankur.keepurl.annotation.EvictAll;
import com.ankur.keepurl.annotation.EvictCache;
import com.ankur.keepurl.dataaccess.document.Trash;
import com.ankur.keepurl.dataaccess.document.UserLink;
import com.ankur.keepurl.dataaccess.repository.TrashRepository;
import com.ankur.keepurl.dto.TrashDTO;
import com.ankur.keepurl.exception.RequestNotFoundException;
import com.ankur.keepurl.service.TrashService;
import com.ankur.keepurl.service.UserLinkService;
import com.ankur.keepurl.service.mapper.TrashMapper;
import com.ankur.keepurl.utility.AppConstants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TrashServiceImpl implements TrashService {

    @Autowired
    private TrashRepository repository;

    @Autowired
    private TrashMapper mapper;

    @Autowired
    private UserLinkService userLinkService;

    @Override
    @EvictCache(keyArgumentIndex = 0)
    public void moveToTrash(String user, UserLink userLink) {
        Trash trashLink = mapper.mapLinkToTrash(userLink);
        repository.save(trashLink);
    }

    @Override
    @Transactional
    @EvictCache(keyArgumentIndex = 1)
    public void restore(String id, String user) {
        Trash trashLink = deleteTrash(id, user);
        userLinkService.createUrl(mapper.mapEntityToLinkDto(trashLink), user);
    }

    @Override
    @Cached(keyArgumentIndex = 0)
    public List<TrashDTO> getAllLinks(String user) {
        return repository.findByUser(user).stream()
                .map(mapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    @EvictCache(keyArgumentIndex = 1)
    public void delete(String id, String user) {
        deleteTrash(id, user);
    }

    public Trash deleteTrash(String id, String user) {
        Optional<Trash> trashLink = repository.findByIdAndUser(id, user);
        if (!trashLink.isPresent()) {
            throw new RequestNotFoundException(AppConstants.URL_NOTFOUND_MSG);
        }
        repository.delete(trashLink.get());
        return trashLink.get();
    }

    @Override
    @Transactional
    @EvictAll
    public void trashCleanup() {
        LocalDate dateToClean = LocalDate.now().minusDays(5);
        List<Trash> trashLinks = repository.findByDateLessThanEqual(dateToClean);
        log.info("Cleaning up trash for date {}. {} URLs found", dateToClean, trashLinks.size());
        repository.deleteAll(trashLinks); 
    }

}
