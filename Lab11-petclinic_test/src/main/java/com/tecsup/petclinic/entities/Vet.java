package com.tecsup.petclinic.entities;

public class Vet {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private boolean active = true;

    public Vet() {}
    public Vet(String firstName, String lastName) { this.firstName = firstName; this.lastName = lastName; }
    public Vet(Long id, String firstName, String lastName, String email, String phone, boolean active) {
        this.id = id; this.firstName = firstName; this.lastName = lastName; this.email = email; this.phone = phone; this.active = active;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    @Override public String toString() { return "Vet{id=" + id + ", firstName='" + firstName + "', lastName='" + lastName + "'}"; }
}
