package com.example.demo3.model;

import java.util.List;

public class ContacteMultipleDTO {
    private UtilizatorDTO utilizator;
    private List<ContactDTO> contacte;

    public ContacteMultipleDTO() {
    }

    public ContacteMultipleDTO(UtilizatorDTO utilizator, List<ContactDTO> contacte) {
        this.utilizator = utilizator;
        this.contacte = contacte;
    }

    public UtilizatorDTO getUtilizator() {
        return utilizator;
    }

    public void setUtilizator(UtilizatorDTO utilizator) {
        this.utilizator = utilizator;
    }

    public List<ContactDTO> getContacte() {
        return contacte;
    }

    public void setContacte(List<ContactDTO> contacte) {
        this.contacte = contacte;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("  Utilizator: ").append(utilizator).append(",\n");
        sb.append("  Contacte:\n");
        if (contacte != null && !contacte.isEmpty()) {
            for (ContactDTO contact : contacte) {
                sb.append("    - ").append(contact).append("\n");
            }
        } else {
            sb.append("    (fără contacte)\n");
        }
        return sb.toString();
    }
}
