package br.com.fiap.model.dto;

public class ContactHC extends User {
    private String title, inPerson, email, phoneHC, schedule;

    public ContactHC() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInPerson() {
        return inPerson;
    }

    public void setInPerson(String inPerson) {
        this.inPerson = inPerson;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneHC() {
        return phoneHC;
    }

    public void setPhoneHC(String phoneHC) {
        this.phoneHC = formatarTelefone(phoneHC);
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String toString() {
        return String.format(
           "Título: %s%nAtendimento Presencial: %s%nEmail: %s%nTelefone: %s%nHorário: %s%n",
           title, inPerson, email, phoneHC, schedule
        );
    }
}
