package com.tecsup.petclinic.entities;

import java.time.LocalDate;

public class Visit {
    private Long id;
    private Integer petId;
    private LocalDate visitDate;
    private String description;

    public Visit() {}
    public Visit(Integer petId, LocalDate visitDate, String description) {
        this.petId = petId; this.visitDate = visitDate; this.description = description;
    }
    public Visit(Long id, Integer petId, LocalDate visitDate, String description) {
        this(petId, visitDate, description); this.id = id;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Integer getPetId() { return petId; }
    public void setPetId(Integer petId) { this.petId = petId; }
    public LocalDate getVisitDate() { return visitDate; }
    public void setVisitDate(LocalDate visitDate) { this.visitDate = visitDate; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
