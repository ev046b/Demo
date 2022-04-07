package com.example.backend.controller;

import com.example.backend.config.MapperUtil;
import com.example.backend.dto.SectionDto.Request.Create;
import com.example.backend.dto.SectionDto.Response.Public;
import com.example.backend.model.Section;
import com.example.backend.model.Status;
import com.example.backend.repository.SectionRepository;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/sections")
public class SectionController extends AbstractRestController<Section, Create, Public, SectionRepository> {

    public SectionController(SectionRepository repository) {
        super(repository);
    }

    @Override
    public Collection<Public> toResponseCollection(Collection<Section> collection) {
        return MapperUtil.convertCollection(collection, this::convertToResponseDto);
    }

    @Override
    public Page<Public> toResponsePage(Page<Section> page) {
        return MapperUtil.convertPages(page, this::convertToResponseDto);
    }

    private Public convertToResponseDto(Section section) {
        return toResponseDto(section);
    }

    @Override
    public Section toEntity(Create requestDto) {
        return super.getModelMapper().map(requestDto, Section.class);
    }

    @Override
    public Public toResponseDto(Section entity) {
        return super.getModelMapper().map(entity, Public.class);
    }

    @Override
    public Section validation(Create requestDto) {
        Section model = new Section(requestDto.getTitle());
        model.setStatus(Status.ACTIVE);
        List<Section> sections = repository.findAllByTitle(requestDto.getTitle());

        if (Objects.isNull(model.getTitle())) {
            model.setStatus(Status.TITLE_NULL_NOT_SAVED);
        }

        if (!sections.isEmpty()) {
            model = sections.get(0);
            model.setStatus(Status.DUPLICATE_NOT_SAVED);
        }

        return model;
    }

    @Override
    public Public save(Create requestDto) {
        Section model = validation(requestDto);
        if (model.getStatus().equals(Status.ACTIVE))
            model = repository.save(model);

        return toResponseDto(model);
    }
}
