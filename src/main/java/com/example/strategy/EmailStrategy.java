package com.example.strategy;

/**
 * Estrategia concreta para enviar por Email
 */
public class EmailStrategy implements NotificacionStrategy {

    @Override
    public void enviar(String mensaje) {
        System.out.println("📧 Email: " + mensaje);
    }
}
