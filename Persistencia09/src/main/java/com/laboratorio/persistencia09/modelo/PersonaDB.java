package com.laboratorio.persistencia09.modelo;

public class PersonaDB {
    public String validar(String nombres, String apellidos, String fechaNac, String experiencia) {
        StringBuilder resultado = new StringBuilder("");
        
        if (nombres.isEmpty()) {
            resultado.append("<p>Los nombres no pueden estar vacíos.</p>");
        } else  {
            if (nombres.length() < 2) {
                resultado.append("<p>Los nombres deben tener al menos 2 caracteres.</p>");
            }
        }
        
        if (apellidos.isEmpty()) {
            resultado.append("<p>Los apellidos no pueden estar vacíos.</p>");
        } else  {
            if (nombres.length() < 2) {
                resultado.append("<p>Los apellidos deben tener al menos 2 caracteres.</p>");
            }
        }
        
        if (fechaNac.isEmpty()) {
            resultado.append("<p>La fecha de nacimiento no puede estar vacía.</p>");
        } else  {
            if (!fechaNac.matches("^(19|20)(((([02468][048])|([13579][26]))-02-29)|(\\d{2})-((02-((0[1-9])|1\\d|2[0-8]))|((((0[13456789])|1[012]))-((0[1-9])|((1|2)\\d)|30))|(((0[13578])|(1[02]))-31)))$")) {
                resultado.append("<p>La fecha tiene un formato incorrecto.</p>");
            }
        }
        
        if (experiencia.isEmpty()) {
            resultado.append("<p>La experiencia no puede estar vacía.</p>");
        } else  {
            if (!experiencia.matches("^[0-9]+$")) {
                resultado.append("<p>La experiencia debe ser un número.</p>");
            }
        }
        
        return resultado.toString();
    }
}