package com.example.singleton;

import com.example.observer.Observer;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase SistemaNotificaciones - Implementa el patrón Singleton y Subject
 * Es el punto central que gestiona todos los observadores y notificaciones
 */
public class SistemaNotificaciones {

    /**
     * Instancia única del sistema (Singleton)
     */
    private static SistemaNotificaciones instancia;

    /**
     * Lista de usuarios observadores
     */
    private List<Observer> usuarios;

    /**
     * Constructor privado para asegurar que solo hay una instancia
     */
    private SistemaNotificaciones() {
        usuarios = new ArrayList<>();
    }

    /**
     * Obtener la instancia única del sistema
     * 
     * @return La instancia del SistemaNotificaciones
     */
    public static SistemaNotificaciones getInstancia() {
        if (instancia == null) {
            instancia = new SistemaNotificaciones();
        }
        return instancia;
    }

    /**
     * Agregar un usuario observador se la lista
     * 
     * @param usuario El usuario a agregar
     */
    public void agregarUsuario(Observer usuario) {
        usuarios.add(usuario);
    }

    /**
     * Remover un usuario observador de la lista
     * 
     * @param usuario El usuario a remover
     */
    public void removerUsuario(Observer usuario) {
        usuarios.remove(usuario);
    }

    /**
     * Notificar a todos los usuarios observadores
     * 
     * @param mensaje El mensaje a enviar
     */
    public void notificar(String mensaje) {
        for (Observer usuario : usuarios) {
            usuario.actualizar(mensaje);
        }
    }

    /**
     * Obtener la cantidad de usuarios registrados
     * 
     * @return El número de usuarios
     */
    public int cantidadUsuarios() {
        return usuarios.size();
    }
}
