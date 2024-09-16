package com.laboratorio.persistencia09.dao;

import com.laboratorio.persistencia09.modelo.Persona;
import java.util.List;

public interface PersonaDAO {
    List<Persona> getPersonas();
    Persona buscar(int codigo);
    boolean insertar(String nombres, String apellidos, String fechaNac, String experiencia);
    boolean editar(int codigo, String nombres, String apellidos, String fechaNac, String experiencia);
    boolean eliminar(int codigo);
}
