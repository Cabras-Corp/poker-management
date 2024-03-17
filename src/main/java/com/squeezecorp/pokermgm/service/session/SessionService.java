package com.squeezecorp.pokermgm.service.session;

import com.squeezecorp.pokermgm.dto.session.CreateSessionRequestDTO;
import com.squeezecorp.pokermgm.dto.session.UpdateSessionRequestDTO;
import com.squeezecorp.pokermgm.model.Session;
import com.squeezecorp.pokermgm.repository.SessionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Void> deleteSession(Long id) {
        Optional<Session> sessionOptional = sessionRepository.findById(id);
        sessionOptional.ifPresent(session -> sessionRepository.deleteById(id));
        return sessionOptional.map(session -> ResponseEntity.ok().<Void>build())
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Transactional
    public Optional<Session> updateSession(Long id, UpdateSessionRequestDTO dto) {
        return Optional.ofNullable(dto)
                .flatMap(d -> sessionRepository.findById(id)
                        .map(existingSession -> {
                            existingSession.setLocal(d.getLocal());
                            existingSession.setStartDateTime(d.getStartDateTime());
                            existingSession.setEndDateTime(d.getEndDateTime());
                            existingSession.setNumberSession(d.getNumberSession());
                            existingSession.setBalance(d.getBalance());
                            return sessionRepository.save(existingSession);
                        }));
    }
}