# GUÍA RÁPIDA DE REFERENCIA: Arquitectura y Uso
# 📦 Estructura del Proyecto (File Tree)
He organizado el código siguiendo las convenciones de Maven para separar la lógica de negocio de las pruebas, facilitando el mantenimiento y la escalabilidad.

Plaintext
demo/
├── pom.xml                      # Configuración de dependencias (Maven)
├── README.md                    # Documentación técnica
├── src/
│   ├── main/java/com/example/
│   │   ├── App.java             <-- ENTRY POINT (Main + Modo Interactivo)
│   │   ├── strategy/            <-- Lógica de algoritmos intercambiables
│   │   ├── observer/            <-- Interfaz de escucha y clase Usuario
│   │   └── singleton/           <-- Control centralizado (SistemaNotificaciones)
│   └── test/java/com/example/   <-- Pruebas unitarias
└── target/classes/              <-- Bytecode compilado
⚡ Comandos de Consola (Workflow)
Para compilar y ejecutar manualmente sin depender de un IDE:

Compilación manual:

Bash
javac -d target/classes -sourcepath src/main/java ^
  src/main/java/com/example/*.java ^
  src/main/java/com/example/strategy/*.java ^
  src/main/java/com/example/observer/*.java ^
  src/main/java/com/example/singleton/*.java
Ejecución del programa:

Bash
java -cp target/classes com.example.App
⚠️ IMPORTANTE: Asegúrate de estar en el directorio "demo" antes de ejecutar estos comandos.

Plaintext
cd ProyectoPatrones\demo
java -cp target/classes com.example.App

🔗 Mapeo de Patrones Aplicados
He diseñado el sistema basándome en la interacción de tres patrones clave para asegurar que el backend sea flexible:

1. STRATEGY (Algoritmos Intercambiables)
Propósito: Encapsular los métodos de envío (Email, SMS, Push).

Flexibilidad: El Usuario no sabe cómo se envía el mensaje, solo llama a estrategia.enviar(). Esto permite cambiar el canal en tiempo real mediante setEstrategia().

2. OBSERVER (Notificación Automática)
Propósito: Mantener sincronizados a todos los usuarios con el centro de control.

Flexibilidad: El SistemaNotificaciones (Sujeto) solo conoce una lista de Observers. Al llamar a notificar(), todos los usuarios reaccionan automáticamente.

3. SINGLETON (Instancia Única)
Propósito: Garantizar un único punto de acceso al sistema de notificaciones.

Flexibilidad: Evitamos inconsistencias de datos al asegurar que no existan dos "centros de control" operando al mismo tiempo.

🎮 Guía del Modo Interactivo
El programa incluye un menú para experimentar con la escalabilidad del diseño en tiempo de ejecución:

➕ Agregar Usuario: Instancia un nuevo Usuario y lo registra en el Observer list.

📢 Enviar Evento: Dispara el método notificar() del Singleton, activando las estrategias de cada observador.

🔄 Cambiar Estrategia: Demuestra el patrón Strategy modificando el objeto de comportamiento de un usuario específico sin afectar a los demás.

👥 Listar Usuarios: Consulta el estado actual de la memoria del Singleton.

---
🐛 Solución de Problemas (Troubleshooting)
En caso de encontrar errores durante la ejecución o compilación, revisa las siguientes causas y soluciones técnicas:

Error: ClassNotFoundException: com.example.App
Análisis: El JRE no logra localizar el punto de entrada. Generalmente ocurre por una ruta de ejecución inconsistente con la estructura del paquete.

Solución: Asegúrate de estar posicionado en la raíz del módulo (directorio demo).

Bash
# ❌ INCORRECTO - Ejecución fuera del contexto del módulo
C:\...\ProyectoPatrones> java -cp target/classes com.example.App

# ✅ CORRECTO - Ejecución desde el directorio del proyecto
C:\...\ProyectoPatrones\demo> java -cp target/classes com.example.App
Error: No such file or directory target/classes
Análisis: Falta de artefactos compilados. El directorio de salida no ha sido generado.

Solución: Ejecutar el ciclo de compilación completo para regenerar el bytecode:


¡Documentación técnica finalizada y modo interactivo operativo! 🎉