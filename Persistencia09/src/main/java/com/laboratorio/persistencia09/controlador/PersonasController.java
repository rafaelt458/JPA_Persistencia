package com.laboratorio.persistencia09.controlador;

import com.laboratorio.persistencia09.dao.PersonaDAO;
import com.laboratorio.persistencia09.modelo.Persona;
import com.laboratorio.persistencia09.modelo.PersonaDB;
import com.laboratorio.persistencia09.modelo.PersonaRequest;
import com.laboratorio.persistencia09.persistencia.ConnectionPoolManager;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "PersonasController", urlPatterns = {"/PersonasController"})
public class PersonasController extends HttpServlet {
    private static final Logger log = Logger.getLogger(PersonasController.class.getName());
    private PersonaDB personaDB;
    
    @Inject
    private PersonaDAO personaDAO;

    @Override
    public void init() {
        personaDB = new PersonaDB();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        if (accion == null) {
            accion = "N/A";
        }

        log.log(Level.INFO, "Se est\u00e1 ejecutando el servlet. Acci\u00f3n: {0}", accion);
        
        String resultado;
        String mensaje;
        
        switch (accion) {
            case "agregar":
                crearPersona(request, response);
                break;
            case "guardar":
                resultado = validarPersona(request);
                if (resultado.isEmpty()) {
                    log.log(Level.INFO, "Se procede a crear la persona!");
                    if (guardarPersona(request)) {
                        mensaje = "Se han guardado los datos de la persona correctamente";
                    } else {
                        mensaje = "Se ha presentado un error al guardar los datos de la persona";
                    }                    
                    listarPersonas(mensaje, request, response);
                } else {
                    mostrarErrores(resultado, request, response);
                }
                break;
            case "editar":
                if (!editarPersona(request, response)) {
                    mensaje = "Se ha presentado en error al recuperar los datos de la persona";
                    listarPersonas(mensaje, request, response);
                }
                break;
            case "eliminar":
                if (eliminarPersona(request)) {
                    mensaje = "Se han eliminado los datos de la persona correctamente";
                } else {
                    mensaje = "Se ha presentado un error al eliminar los datos de la persona";
                }
                listarPersonas(mensaje, request, response);
                break;
            case "listar":
            default:
                listarPersonas(null, request, response);
                break;
        }
    }
    
    private void listarPersonas(String mensaje, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        List<Persona> personas;

        try {
            personas = personaDAO.getPersonas();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error recuperando la lista de personas!");
            personas = new ArrayList<>();
        }

        request.setAttribute("lista_personas", personas);
        request.setAttribute("mensaje", mensaje);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/personas.jsp");
        dispatcher.forward(request, response);
    }
    
    private void crearPersona(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        
        PersonaRequest persona = new PersonaRequest();
        
        request.setAttribute("persona", persona);
        request.setAttribute("errores", null);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/formulariopersona.jsp");
        dispatcher.forward(request, response);
    }
    
    private boolean editarPersona(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
     
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        log.log(Level.INFO, "Codigo: {0}", codigo);
        
        Persona persona;
        
        try {
            persona = personaDAO.buscar(codigo);
            if (persona == null) {
                return false;
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error recuperando los datos de la persona!");
            return false;
        }
        
        PersonaRequest personaRequest = new PersonaRequest(persona);
        
        request.setAttribute("persona", personaRequest);
        request.setAttribute("errores", null);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/formulariopersona.jsp");
        dispatcher.forward(request, response);
        
        return true;
    }
    
    private String validarPersona(HttpServletRequest request) {
        String nombres = request.getParameter("nombres");
        String apellidos = request.getParameter("apellidos");
        String fechaNac = request.getParameter("fechaNac");
        log.log(Level.INFO, "Fecha: {0}", fechaNac);
        String experiencia = request.getParameter("experiencia");
        
        return personaDB.validar(nombres, apellidos, fechaNac, experiencia);
    }
    
    private void mostrarErrores(String errores, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        String nombres = request.getParameter("nombres");
        String apellidos = request.getParameter("apellidos");
        String fechaNac = request.getParameter("fechaNac");
        log.log(Level.INFO, "Fecha: {0}", fechaNac);
        String experiencia = request.getParameter("experiencia");
        
        PersonaRequest persona = new PersonaRequest(codigo, nombres, apellidos, fechaNac, experiencia);
        
        request.setAttribute("persona", persona);
        request.setAttribute("errores", errores);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/formulariopersona.jsp");
        dispatcher.forward(request, response);
    }
    
    private boolean guardarPersona(HttpServletRequest request) {
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        String nombres = request.getParameter("nombres");
        String apellidos = request.getParameter("apellidos");
        String fechaNac = request.getParameter("fechaNac");
        log.log(Level.INFO, "Fecha: {0}", fechaNac);
        String experiencia = request.getParameter("experiencia");
        
        if (codigo == 0) {
            try {
                return personaDAO.insertar(nombres, apellidos, fechaNac, experiencia);
            } catch (Exception ex) {
                log.log(Level.SEVERE, "Error guardando los datos de la persona!");
                log.log(Level.SEVERE, "Error: " + ex.getMessage());
                return false;
            }
        } else {
            try {
                return personaDAO.editar(codigo, nombres, apellidos, fechaNac, experiencia);
            } catch (Exception e) {
                log.log(Level.SEVERE, "Error guardando los datos de la persona!");
                return false;
            }
        }
    }
    
    private boolean eliminarPersona(HttpServletRequest request) {
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        try {
            return personaDAO.eliminar(codigo);
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error eliminado los datos de la persona!");
            return false;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}