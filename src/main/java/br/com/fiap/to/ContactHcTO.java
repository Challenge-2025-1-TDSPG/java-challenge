package br.com.fiap.to;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"id", "cpf", "name", "birthDate", "phone","email"})
public class ContactHcTO extends UserTO {
    //atributos

    private Long idHC;
    private String title;
    private String inPerson;
    private String emailhc;
    private String phoneHC;
    private String schedule;

    //construtores
    public ContactHcTO() {
    }

    //setter/getter
    public Long getIdHC() {
        return idHC;
    }

    public void setIdHC(Long idHC) {
        this.idHC = idHC;
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

    public String getEmailhc() {
        return emailhc;
    }

    public void setEmailhc(String email) {
        if (validarEmail(email)) {
            this.emailhc = email;
        } else {
            throw new IllegalArgumentException("Email inválido: " + email);
        }
    }

    public String getPhoneHC() {
        return phoneHC;
    }

    public void setPhoneHC(String phoneHC) {
        this.phoneHC = phoneHC;
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
           title, inPerson, emailhc, phoneHC, schedule
        );
    }
}
