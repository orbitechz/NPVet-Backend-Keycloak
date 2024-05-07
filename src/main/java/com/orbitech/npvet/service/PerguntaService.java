package com.orbitech.npvet.service;

import com.orbitech.npvet.dto.AnamneseDTO;
import com.orbitech.npvet.dto.PerguntaDTO;
import com.orbitech.npvet.entity.Pergunta;
import com.orbitech.npvet.repository.PerguntaRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class PerguntaService {

    private final PerguntaRepository perguntaRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public PerguntaService(PerguntaRepository perguntaRepository) {
        this.perguntaRepository = perguntaRepository;
    }

    public PerguntaDTO getById(Long id) {
        Pergunta pergunta = perguntaRepository.findById(id).orElse(null);
        Assert.notNull(pergunta, String.format("O ID = %s solicitado não foi encontrado no banco de dados.", id));
        return toPerguntaDTO(pergunta);
    }

    public List<PerguntaDTO> getAll() {
        List<PerguntaDTO> perguntaDTOS = perguntaRepository.findAll()
                .stream()
                .map(this::toPerguntaDTO)
                .toList();

        if (perguntaDTOS.isEmpty()) {
            return Collections.emptyList();
        }

        return perguntaDTOS;
    }

    public PerguntaDTO create(PerguntaDTO perguntaDTO) {

        if (perguntaRepository.existsByEnunciado(perguntaDTO.getEnunciado())) {
            throw new DataIntegrityViolationException("O enunciado deve ser único.");
        }
        return toPerguntaDTO(perguntaRepository.save(toPergunta(perguntaDTO)));
    }

    public PerguntaDTO update(Long id, PerguntaDTO perguntaDTO) {

        Pergunta existingPergunta = perguntaRepository.findById(id).orElse(null);
        Assert.notNull(existingPergunta, String.format("O ID = %s solicitado não foi encontrado no banco de dados.", id));

        if (!id.equals(perguntaDTO.getId())) {
            throw new IllegalArgumentException("O ID na URL não corresponde ao ID no corpo da requisição.");
        }

        if (perguntaRepository.existsByEnunciado(perguntaDTO.getEnunciado())) {
            throw new DataIntegrityViolationException("O enunciado deve ser único.");
        }

        return toPerguntaDTO(perguntaRepository.save(toPergunta(perguntaDTO)));
    }

    @Transactional
    public PerguntaDTO delete(Long id){
        PerguntaDTO perguntaById = getById(id);
        perguntaById.delete();
        return toPerguntaDTO(perguntaRepository.save(toPergunta(perguntaById)));
    }

    @Transactional
    public PerguntaDTO activate(Long id){
        PerguntaDTO perguntaById = getById(id);
        perguntaById.activate();
        return toPerguntaDTO(perguntaRepository.save(toPergunta(perguntaById)));
    }

    public PerguntaDTO toPerguntaDTO(Pergunta pergunta) {
        return modelMapper.map(pergunta, PerguntaDTO.class);
    }

    public Pergunta toPergunta(PerguntaDTO perguntaDTO) {
        return modelMapper.map(perguntaDTO, Pergunta.class);
    }
}