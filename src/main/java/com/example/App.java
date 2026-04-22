package com.example;

import com.example.singleton.SistemaNotificaciones;
import com.example.observer.Usuario;
import com.example.strategy.*;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase principal - Demostración del Sistema de Notificaciones
 * Implementa los patrones: Observer, Strategy y Singleton
 * 
 * 🏗️ PROYECTO: Sistema de Notificaciones para Tienda Online
 * Una tienda genera eventos (compra, promoción, envío)
 * Los usuarios reciben notificaciones según su estrategia seleccionada
 */
public class App {
    // Mapa para almacenar usuarios y poder cambiar sus estrategias
    private static Map<String, Usuario> usuariosMap = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("🛒 SISTEMA DE NOTIFICACIONES PARA TIENDA ONLINE");
        System.out.println("=".repeat(60) + "\n");

        // Obtener la instancia única del sistema (Singleton)
        SistemaNotificaciones sistema = SistemaNotificaciones.getInstancia();

        // Crear usuarios con diferentes estrategias iniciales
        Usuario juan = new Usuario("Juan García", new EmailStrategy());
        Usuario ana = new Usuario("Ana López", new SMSStrategy());
        Usuario carlos = new Usuario("Carlos Ruiz", new PushStrategy());

        // Agregar usuarios al sistema
        System.out.println("✅ Registrando usuarios en el sistema...");
        sistema.agregarUsuario(juan);
        sistema.agregarUsuario(ana);
        sistema.agregarUsuario(carlos);

        usuariosMap.put("juan", juan);
        usuariosMap.put("ana", ana);
        usuariosMap.put("carlos", carlos);

        System.out.println("   Total de usuarios: " + sistema.cantidadUsuarios() + "\n");

        // 🛒 Evento 1: Compra Realizada
        System.out.println("=".repeat(60));
        System.out.println("🛒 EVENTO 1: COMPRA REALIZADA");
        System.out.println("=".repeat(60));
        sistema.notificar("Tu compra fue exitosa. Total: $99.99 🛍️");

        // 🎁 Evento 2: Promoción Especial
        System.out.println("\n" + "=".repeat(60));
        System.out.println("🎁 EVENTO 2: PROMOCIÓN ESPECIAL");
        System.out.println("=".repeat(60));
        System.out.println("\n⚙️  Ana cambia su preferencia a notificaciones Push\n");

        // Cambiar estrategia dinámicamente (demuestra la flexibilidad de Strategy)
        ana.setEstrategia(new PushStrategy());

        sistema.notificar("🔥 ¡50% de descuento solo hoy! Código: PROMO50");

        // 🚚 Evento 3: Pedido Enviado
        System.out.println("\n" + "=".repeat(60));
        System.out.println("🚚 EVENTO 3: PEDIDO ENVIADO");
        System.out.println("=".repeat(60));
        System.out.println("\n⚙️  Juan ahora prefiere recibir por SMS\n");

        // Cambiar estrategia dinámicamente
        juan.setEstrategia(new SMSStrategy());

        sistema.notificar("Tu pedido está en camino 📦 Entrega estimada: Mañana");

        // 📧 Evento 4: Confirmación de Contacto
        System.out.println("\n" + "=".repeat(60));
        System.out.println("📧 EVENTO 4: CONFIRMACIÓN DE CONTACTO");
        System.out.println("=".repeat(60));
        sistema.notificar("Confirma tu email para recibir novedades exclusivas");

        System.out.println("\n" + "=".repeat(60));
        System.out.println("✨ FIN DE LA DEMOSTRACIÓN");
        System.out.println("=".repeat(60) + "\n");

        // Información sobre los patrones utilizados
        mostrarInformacionPatrones();

        // Modo interactivo
        ejecutarModoInteractivo(sistema);
    }

    /**
     * Muestra información sobre los patrones utilizados
     */
    private static void mostrarInformacionPatrones() {
        System.out.println("\n📚 PATRONES DE DISEÑO UTILIZADOS:\n");

        System.out.println("1️⃣  OBSERVER (Patrón Comportamental)");
        System.out.println("   ├─ El SistemaNotificaciones actúa como Subject");
        System.out.println("   ├─ Los Usuarios actúan como Observadores");
        System.out.println("   └─ Cuando hay un evento, todos los observadores son notificados\n");

        System.out.println("2️⃣  STRATEGY (Patrón Comportamental)");
        System.out.println("   ├─ Cada Usuario elige cómo recibir mensajes");
        System.out.println("   ├─ Estrategias: Email, SMS, Push");
        System.out.println("   └─ La estrategia se puede cambiar dinámicamente\n");

        System.out.println("3️⃣  SINGLETON (Patrón Creacional)");
        System.out.println("   ├─ Solo existe una instancia de SistemaNotificaciones");
        System.out.println("   ├─ Se accede a través de getInstancia()");
        System.out.println("   └─ Garantiza un punto central único\n");

        /*
         * System.out.println("✅ VENTAJAS:");
         * System.out.println("   ✓ Código modular y organizado");
         * System.out.println("   ✓ Fácil de mantener y extender");
         * System.out.println("   ✓ Separación de responsabilidades");
         * System.out.
         * println("   ✓ Escalable (agregar nuevas estrategias es sencillo)\n");
         */
    }

    /**
     * Modo interactivo para agregar usuarios, eventos y cambiar estrategias
     */
    private static void ejecutarModoInteractivo(SistemaNotificaciones sistema) {
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n" + "=".repeat(60));
            System.out.println("📱 MODO INTERACTIVO - MENÚ PRINCIPAL");
            System.out.println("=".repeat(60));
            System.out.println("1. ➕ Agregar nuevo usuario");
            System.out.println("2. 📢 Enviar evento a todos");
            System.out.println("3. 🔄 Cambiar estrategia de un usuario");
            System.out.println("4. 👥 Mostrar usuarios registrados");
            System.out.println("5. ❌ Salir");
            System.out.print("\nSelecciona una opción (1-5): ");

            try {
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer

                switch (opcion) {
                    case 1:
                        agregarNuevoUsuario(sistema);
                        break;
                    case 2:
                        enviarEvento(sistema);
                        break;
                    case 3:
                        cambiarEstrategia();
                        break;
                    case 4:
                        mostrarUsuarios();
                        break;
                    case 5:
                        continuar = false;
                        System.out.println("\n👋 ¡Hasta luego!");
                        break;
                    default:
                        System.out.println("❌ Opción inválida, intenta de nuevo.");
                }
            } catch (Exception e) {
                System.out.println("❌ Error: Ingresa un número válido.");
                scanner.nextLine(); // Limpiar buffer en caso de error
            }
        }
        scanner.close();
    }

    /**
     * Agrega un nuevo usuario al sistema
     */
    private static void agregarNuevoUsuario(SistemaNotificaciones sistema) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("➕ AGREGAR NUEVO USUARIO");
        System.out.println("=".repeat(60));

        System.out.print("Ingresa el nombre del usuario: ");
        String nombre = scanner.nextLine();

        if (nombre.trim().isEmpty()) {
            System.out.println("❌ El nombre no puede estar vacío.");
            return;
        }

        System.out.println("\nSelecciona la estrategia inicial:");
        System.out.println("1. 📧 Email");
        System.out.println("2. 💬 SMS");
        System.out.println("3. 🔔 Push");
        System.out.print("Elige (1-3): ");

        int estrategiaOp = scanner.nextInt();
        scanner.nextLine();

        NotificacionStrategy estrategia = null;
        String estrategiaNombre = "";

        switch (estrategiaOp) {
            case 1:
                estrategia = new EmailStrategy();
                estrategiaNombre = "Email";
                break;
            case 2:
                estrategia = new SMSStrategy();
                estrategiaNombre = "SMS";
                break;
            case 3:
                estrategia = new PushStrategy();
                estrategiaNombre = "Push";
                break;
            default:
                System.out.println("❌ Opción inválida.");
                return;
        }

        Usuario nuevoUsuario = new Usuario(nombre, estrategia);
        sistema.agregarUsuario(nuevoUsuario);
        usuariosMap.put(nombre.toLowerCase(), nuevoUsuario);

        System.out.println("\n✅ Usuario '" + nombre + "' agregado con estrategia: " + estrategiaNombre);
        System.out.println("   Total de usuarios: " + sistema.cantidadUsuarios());
    }

    /**
     * Envía un evento a todos los usuarios
     */
    private static void enviarEvento(SistemaNotificaciones sistema) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("📢 ENVIAR EVENTO");
        System.out.println("=".repeat(60));

        System.out.println("Eventos predefinidos:");
        System.out.println("1. 🛒 Compra realizada");
        System.out.println("2. 🎁 Promoción especial");
        System.out.println("3. 🚚 Pedido enviado");
        System.out.println("4. 📧 Confirmación de contacto");
        System.out.println("5. 📝 Evento personalizado");
        System.out.print("Elige (1-5): ");

        int eventoOp = scanner.nextInt();
        scanner.nextLine();

        String mensaje = "";

        switch (eventoOp) {
            case 1:
                mensaje = "Tu compra fue exitosa. Total: $99.99 🛍️";
                break;
            case 2:
                mensaje = "🔥 ¡50% de descuento solo hoy! Código: PROMO50";
                break;
            case 3:
                mensaje = "Tu pedido está en camino 📦 Entrega estimada: Mañana";
                break;
            case 4:
                mensaje = "Confirma tu email para recibir novedades exclusivas";
                break;
            case 5:
                System.out.print("Ingresa el mensaje personalizado: ");
                mensaje = scanner.nextLine();
                break;
            default:
                System.out.println("❌ Opción inválida.");
                return;
        }

        System.out.println("\n" + "=".repeat(60));
        System.out.println("📨 ENVIANDO EVENTO");
        System.out.println("=".repeat(60));
        sistema.notificar(mensaje);
    }

    /**
     * Cambia la estrategia de un usuario
     */
    private static void cambiarEstrategia() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("🔄 CAMBIAR ESTRATEGIA");
        System.out.println("=".repeat(60));

        if (usuariosMap.isEmpty()) {
            System.out.println("❌ No hay usuarios registrados.");
            return;
        }

        System.out.println("Usuarios disponibles:");
        int i = 1;
        for (String nombre : usuariosMap.keySet()) {
            System.out.println(i + ". " + usuariosMap.get(nombre).getNombre());
            i++;
        }

        System.out.print("Elige el número del usuario: ");
        int usuarioOp = scanner.nextInt();
        scanner.nextLine();

        String[] usuarios = usuariosMap.keySet().toArray(new String[0]);
        if (usuarioOp < 1 || usuarioOp > usuarios.length) {
            System.out.println("❌ Opción inválida.");
            return;
        }

        Usuario usuario = usuariosMap.get(usuarios[usuarioOp - 1]);

        System.out.println("\nSelecciona la nueva estrategia:");
        System.out.println("1. 📧 Email");
        System.out.println("2. 💬 SMS");
        System.out.println("3. 🔔 Push");
        System.out.print("Elige (1-3): ");

        int estrategiaOp = scanner.nextInt();
        scanner.nextLine();

        String estrategiaNombre = "";

        switch (estrategiaOp) {
            case 1:
                usuario.setEstrategia(new EmailStrategy());
                estrategiaNombre = "Email";
                break;
            case 2:
                usuario.setEstrategia(new SMSStrategy());
                estrategiaNombre = "SMS";
                break;
            case 3:
                usuario.setEstrategia(new PushStrategy());
                estrategiaNombre = "Push";
                break;
            default:
                System.out.println("❌ Opción inválida.");
                return;
        }

        System.out.println("\n✅ Estrategia actualizada para '" + usuario.getNombre() +
                "': " + estrategiaNombre);
    }

    /**
     * Muestra todos los usuarios registrados
     */
    private static void mostrarUsuarios() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("👥 USUARIOS REGISTRADOS");
        System.out.println("=".repeat(60));

        if (usuariosMap.isEmpty()) {
            System.out.println("❌ No hay usuarios registrados.");
            return;
        }

        int i = 1;
        for (String nombre : usuariosMap.keySet()) {
            Usuario usuario = usuariosMap.get(nombre);
            String estrategia = usuario.getEstrategia().getClass().getSimpleName()
                    .replace("Strategy", "");
            System.out.println(i + ". " + usuario.getNombre() + " - Estrategia: " + estrategia);
            i++;
        }
    }
}
