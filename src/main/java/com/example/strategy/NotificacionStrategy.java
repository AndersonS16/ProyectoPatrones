package com.example.strategy;

/**
 * Interfaz Strategy - Define la forma genérica de enviar notificaciones
 */
public interface NotificacionStrategy {
    void enviar(String mensaje);
}
