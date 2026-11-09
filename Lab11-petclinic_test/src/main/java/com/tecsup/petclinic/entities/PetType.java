package com.tecsup.petclinic.entities;

public class PetType {
    private Long id;
    private String name;
    public PetType() {}
    public PetType(String name) { this.name = name; }
    public PetType(Long id, String name) { this.id = id; this.name = name; }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
