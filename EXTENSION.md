🔧 Guía de Extensión del Proyecto: Arquitectura Escalable
Este documento detalla el procedimiento técnico para extender las capacidades del sistema, aprovechando la flexibilidad que nos brindan los patrones de diseño implementados para asegurar que el software sea robusto y fácil de mantener a largo plazo.

📱 Implementación de una Nueva Estrategia de Envío
Siguiendo el Patrón Strategy, podemos añadir nuevos canales de comunicación sin alterar la lógica de negocio existente.

Paso 1: Definición de la clase concreta
Creamos la clase en la ruta: src/main/java/com/example/strategy/WhatsAppStrategy.java. Esta debe implementar nuestra interfaz de estrategia para mantener el polimorfismo.

Java
package com.example.strategy;

/**
 * Implementación de la estrategia para envíos vía WhatsApp.
 */
public class WhatsAppStrategy implements NotificacionStrategy {
    
    @Override
    public void enviar(String mensaje) {
        // Simulación de envío mediante API de mensajería
        System.out.println("💬 WhatsApp: " + mensaje);
    }
}
Paso 2: Inyección de la estrategia en el Cliente
Podemos asignar la estrategia al instanciar un usuario o cambiarla dinámicamente en tiempo de ejecución (Runtime).

Java
// Ejemplo en App.java

// Instanciación con la nueva estrategia
Usuario javi = new Usuario("Javier", new WhatsAppStrategy());

// Cambio dinámico de comportamiento según la necesidad
usuario.setEstrategia(new WhatsAppStrategy());
Paso 3: Despliegue y ejecución
Para compilar y correr los cambios desde la consola:

Bash
javac -d target/classes -sourcepath src/main/java ^
  src/main/java/com/example/strategy/WhatsAppStrategy.java

java -cp target/classes com.example.App