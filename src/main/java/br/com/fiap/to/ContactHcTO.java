package br.com.fiap.to;

public class ContactHcTO extends UserTO {
    private String title, inPerson, email, phoneHC, schedule;
    private Long idHC;

    public ContactHcTO() {
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (validarEmail(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Email inválido: " + email);
        }
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
