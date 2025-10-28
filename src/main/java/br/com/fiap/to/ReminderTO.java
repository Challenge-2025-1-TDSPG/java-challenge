package br.com.fiap.to;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.time.LocalTime;
@JsonIgnoreProperties({"destinatario", "assunto", "corpo","numberReminder"})
public class ReminderTO {

    // Atributos
    private Long idReminder;
    private Long userId;
    private LocalDate dateReminder;
    private LocalTime timeReminder;
    private String descriptionReminder;

    private String numberReminder;
    private String destinatario;
    private String assunto;
    private String corpo;

    //construtores
    public ReminderTO() {
    }


    //getter e setter
    public Long getIdReminder() {
        return idReminder;
    }

    public void setIdReminder(Long idReminder) {
        this.idReminder = idReminder;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDate getDateReminder() {
        return dateReminder;
    }

    public void setDateReminder(LocalDate dateReminder) {
        this.dateReminder = dateReminder;
    }

    public LocalTime getTimeReminder() {
        return timeReminder;
    }

    public void setTimeReminder(LocalTime timeReminder) {
        this.timeReminder = timeReminder;
    }

    public String getDescriptionReminder() {
        return descriptionReminder;
    }

    public void setDescriptionReminder(String descriptionReminder) {
        this.descriptionReminder = descriptionReminder;
    }

    public String getNumberReminder() {
        return numberReminder;
    }

    public void setNumberReminder(String numberReminder) {
        this.numberReminder = numberReminder;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    public String formatNumber(String number) {
        if (number == null) return null;
        String digits = number.replaceAll("\\D", "");
        if (digits.startsWith("55")) {
            return "+" + digits;
        } else {
            return "+55" + digits;
        }
    }

}