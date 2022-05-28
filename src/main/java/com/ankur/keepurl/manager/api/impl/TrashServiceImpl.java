package com.ankur.keepurl.manager.api.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ankur.keepurl.app.exception.RequestNotFoundException;
import com.ankur.keepurl.app.exception.UrlDetailAlreadyExistException;
import com.ankur.keepurl.app.util.AppConstants;
import com.ankur.keepurl.dataaccess.document.Trash;
import com.ankur.keepurl.dataaccess.document.UserLink;
import com.ankur.keepurl.dataaccess.repository.TrashRepository;
import com.ankur.keepurl.dataaccess.repository.UserLinkRepository;
import com.ankur.keepurl.manager.api.TrashService;
import com.ankur.keepurl.manager.api.mapper.TrashMapper;
import com.ankur.keepurl.manager.model.TrashDTO;

@Service
public class TrashServiceImpl implements TrashService {

    private static final Logger logger = LoggerFactory.getLogger(TrashService.class);

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
        logger.info("Cleaning up trash for date {}. {} URLs found", dateToClean, trashLinks.size());
        repository.deleteAll(trashLinks);
    }

}
