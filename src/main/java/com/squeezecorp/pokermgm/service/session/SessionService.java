package com.squeezecorp.pokermgm.service.session;

import com.squeezecorp.pokermgm.dto.CreateSessionRequestDTO;
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
    public Optional<Session> updateSession(Long id, CreateSessionRequestDTO dto) {
        Optional<Session> optionalSession = sessionRepository.findById(id);
        if (optionalSession.isPresent()) {
            Session existingSession = optionalSession.get();
            existingSession.setLocal(dto.getLocal());
            existingSession.setData_hora(dto.getData_hora());
            existingSession.setNumero_sessao(dto.getNumero_sessao());
            existingSession.setData_hora_fim(dto.getData_hora_fim());
            return Optional.of(sessionRepository.save(existingSession));
        } else {
            return Optional.empty();
        }
    }

    public Optional<Session> findById(Long id) {
        return null;
    }

    public void save(Session existingSession) {
    }
}

