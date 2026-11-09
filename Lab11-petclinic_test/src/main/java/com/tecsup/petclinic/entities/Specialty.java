package com.tecsup.petclinic.entities;

public class Specialty {
    private Long id;
    private String name;
    private String office;
    private Integer openHour;
    private Integer closeHour;

    public Specialty() {}
    public Specialty(String name) { this.name = name; }
    public Specialty(Long id, String name, String office, Integer openHour, Integer closeHour) {
        this.id = id; this.name = name; this.office = office; this.openHour = openHour; this.closeHour = closeHour;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getOffice() { return office; }
    public void setOffice(String office) { this.office = office; }
    public Integer getOpenHour() { return openHour; }
    public void setOpenHour(Integer openHour) { this.openHour = openHour; }
    public Integer getCloseHour() { return closeHour; }
    public void setCloseHour(Integer closeHour) { this.closeHour = closeHour; }
}
