package com.example.strategy;

/**
 * Estrategia concreta para enviar por SMS
 */
public class SMSStrategy implements NotificacionStrategy {

    @Override
    public void enviar(String mensaje) {
        System.out.println("📱 SMS: " + mensaje);
    }
}
