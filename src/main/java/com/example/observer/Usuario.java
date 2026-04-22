package com.example.observer;

import com.example.strategy.NotificacionStrategy;

/**
 * Clase Usuario que implementa el patrón Observer
 * Cada usuario recibe notificaciones y puede cambiar su estrategia
 * dinámicamente
 */
public class Usuario implements Observer {

    private String nombre;
    private NotificacionStrategy estrategia;

    /**
     * Constructor del Usuario
     * 
     * @param nombre     El nombre del usuario
     * @param estrategia La estrategia inicial para recibir notificaciones
     */
    public Usuario(String nombre, NotificacionStrategy estrategia) {
        this.nombre = nombre;
        this.estrategia = estrategia;
    }

    /**
     * Implementación de actualizar del Observer
     * Se llama cuando hay una nueva notificación
     */
    @Override
    public void actualizar(String mensaje) {
        System.out.println("\n👤 Usuario: " + nombre);
        estrategia.enviar(mensaje);
    }

    /**
     * Cambiar la estrategia de notificación dinámicamente
     * 
     * @param estrategia La nueva estrategia a utilizar
     */
    public void setEstrategia(NotificacionStrategy estrategia) {
        this.estrategia = estrategia;
    }

    /**
     * Obtener el nombre del usuario
     * 
     * @return El nombre del usuario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtener la estrategia actual del usuario
     * 
     * @return La estrategia de notificación actual
     */
    public NotificacionStrategy getEstrategia() {
        return estrategia;
    }
}
