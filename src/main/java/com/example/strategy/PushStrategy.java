package com.example.strategy;

/**
 * Estrategia concreta para enviar por Push Notification
 */
public class PushStrategy implements NotificacionStrategy {

    @Override
    public void enviar(String mensaje) {
        System.out.println("🔔 Push: " + mensaje);
    }
}
