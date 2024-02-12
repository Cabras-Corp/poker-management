package com.squeezecorp.pokermgm.service.session;

import com.squeezecorp.pokermgm.dto.CreateSessionRequestDTO;
import com.squeezecorp.pokermgm.dto.UpdateSessionRequestDTO;
import com.squeezecorp.pokermgm.model.Session;
import com.squeezecorp.pokermgm.repository.SessionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class SessionService {
    private final SessionRepository sessionRepository;

    @Autowired
    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public List<Session> findAllSessions() {
        return sessionRepository.findAll();
    }

    public Optional<Session> findSessionById(Long id) {
        return sessionRepository.findById(id);
    }

    @Transactional
    public Session createSession(CreateSessionRequestDTO dto) {
        Session session = new Session(dto);
        return sessionRepository.save(session);
    }

    @Transactional
    public void deleteSession(Long id) {
        sessionRepository.deleteById(id);
    }

    @Transactional
    public Optional<Session> updateSession(Long id, UpdateSessionRequestDTO dto) {
        Optional<Session> optionalSession = sessionRepository.findById(id);
        if (optionalSession.isPresent()) {
            Session existingSession = optionalSession.get();
            existingSession.setLocal(dto.getLocal());
            existingSession.setDate_time(dto.getDate_time());
            existingSession.setNumber_session(dto.getNumber_session());
            existingSession.setEnd_date_time(dto.getEnd_date_time());
            return Optional.of(sessionRepository.save(existingSession));
        } else {
            return Optional.empty();
        }
    }
}

