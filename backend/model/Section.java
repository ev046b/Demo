package com.example.backend.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, exclude = {"questions", "tasks"})
@Entity
@Table(name = "sections")
@SequenceGenerator(name = "seqGenerator", sequenceName = "sections_seq", allocationSize = 1, initialValue = 1)
public class Section extends BaseEntity {

    @Column(name = "title", length = 150)
    private String title;

    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Question> questions = new HashSet<>();

    public Section(String title) {
        this.title = title;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions.addAll(questions);
    }

    public void addQuestion(Question question) {
        question.setSection(this);
        getQuestions().add(question);
    }

}
