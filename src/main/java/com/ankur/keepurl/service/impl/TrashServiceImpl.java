package com.ankur.keepurl.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ankur.keepurl.dataaccess.document.Trash;
import com.ankur.keepurl.dataaccess.document.UserLink;
import com.ankur.keepurl.dataaccess.repository.TrashRepository;
import com.ankur.keepurl.dataaccess.repository.UserLinkRepository;
import com.ankur.keepurl.dto.TrashDTO;
import com.ankur.keepurl.exception.RequestNotFoundException;
import com.ankur.keepurl.exception.UrlDetailAlreadyExistException;
import com.ankur.keepurl.service.TrashService;
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
    private UserLinkRepository userLinkDAO;

    @Override
    public void moveToTrash(UserLink userLink) {
        Trash trashLink = mapper.mapLinkToTrash(userLink);
        repository.save(trashLink);
    }

    @Override
    @Transactional
    public void restore(String id, String user) {
        if (userLinkDAO.existsByIdAndUser(id, user)) {
            throw new UrlDetailAlreadyExistException();
        }
        Trash trashLink = deleteTrash(id, user);
        userLinkDAO.save(mapper.mapTrashToLink(trashLink));
    }

    @Override
    public List<TrashDTO> getAllLinks(String user) {
        return repository.findByUser(user).stream()
                .map(mapper::mapEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(String id, String user) {
        deleteTrash(id, user);
    }

    private Trash deleteTrash(String id, String user) {
        Optional<Trash> trashLink = repository.findByIdAndUser(id, user);
        if (!trashLink.isPresent()) {
            throw new RequestNotFoundException(AppConstants.URL_NOTFOUND_MSG);
        }
        repository.delete(trashLink.get());
        return trashLink.get();
    }

    @Override
    @Transactional
    public void trashCleanup() {
        LocalDate dateToClean = LocalDate.now().minusDays(5);
        List<Trash> trashLinks = repository.findByDateLessThanEqual(dateToClean);
        log.info("Cleaning up trash for date {}. {} URLs found", dateToClean, trashLinks.size());
        repository.deleteAll(trashLinks); 
    }

}
