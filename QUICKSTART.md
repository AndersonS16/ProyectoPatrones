# GUÍA RÁPIDA DE REFERENCIA

## Estructura Completa del Proyecto

```
demo/
├── pom.xml                              # Configuración Maven
├── README.md                            # Documentación completa
├── QUICKSTART.md                        # Este archivo
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/example/
│   │           ├── App.java                          ✅ EJECUTABLE PRINCIPAL
│   │           │
│   │           ├── strategy/
│   │           │   ├── NotificacionStrategy.java      ◄── Interface
│   │           │   ├── EmailStrategy.java             ◄── Implementación
│   │           │   ├── SMSStrategy.java               ◄── Implementación
│   │           │   └── PushStrategy.java              ◄── Implementación
│   │           │
│   │           ├── observer/
│   │           │   ├── Observer.java                  ◄── Interface
│   │           │   └── Usuario.java                   ◄── Observador
│   │           │
│   │           └── singleton/
│   │               └── SistemaNotificaciones.java     ◄── Singleton + Subject
│   │
│   └── test/
│       └── java/
│           └── com/example/
│               └── AppTest.java
│
└── target/
    └── classes/                          # Archivos compilados
```

---

## ⚡ Inicio Rápido

### Compilar:
```bash
cd demo

# Opción 1: Con javac (si Java está instalado)
javac -d target/classes -sourcepath src/main/java ^
  src/main/java/com/example/*.java ^
  src/main/java/com/example/strategy/*.java ^
  src/main/java/com/example/observer/*.java ^
  src/main/java/com/example/singleton/*.java
```

### Ejecutar:
```bash
java -cp target/classes com.example.App
```

**Flujo:**
1. Se ejecuta la demostración automática (4 eventos)
2. Se muestra información de los patrones
3. **NUEVO:** Se accede al menú interactivo para experimentar con el sistema

---

## 🎮 MODO INTERACTIVO

Después de la demostración, el programa te presenta un menú interactivo:

```
============================================================
📱 MODO INTERACTIVO - MENÚ PRINCIPAL
============================================================
1. ➕ Agregar nuevo usuario
2. 📢 Enviar evento a todos
3. 🔄 Cambiar estrategia de un usuario
4. 👥 Mostrar usuarios registrados
5. ❌ Salir

Selecciona una opción (1-5):
```

### Opción 1: ➕ Agregar nuevo usuario

Permite crear un usuario nuevo y elegir su estrategia inicial:

```
Ingresa el nombre del usuario: María González
Selecciona la estrategia inicial:
1. 📧 Email
2. 💬 SMS
3. 🔔 Push
Elige (1-3): 2
✅ Usuario 'María González' agregado con estrategia: SMS
   Total de usuarios: 4
```

### Opción 2: 📢 Enviar evento a todos

Elige entre eventos predefinidos o crea uno personalizado:

```
Eventos predefinidos:
1. 🛒 Compra realizada
2. 🎁 Promoción especial
3. 🚚 Pedido enviado
4. 📧 Confirmación de contacto
5. 📝 Evento personalizado
Elige (1-5): 5
Ingresa el mensaje personalizado: ¡Bienvenido a nuestro sistema!

============================================================
📨 ENVIANDO EVENTO
============================================================

👤 Usuario: Juan García
📧 Email: ¡Bienvenido a nuestro sistema!

👤 Usuario: María González
💬 SMS: ¡Bienvenido a nuestro sistema!
...
```

### Opción 3: 🔄 Cambiar estrategia de un usuario

Permite modificar cómo un usuario recibe notificaciones:

```
Usuarios disponibles:
1. Juan García
2. Ana López
3. Carlos Ruiz
4. María González
Elige el número del usuario: 4
Selecciona la nueva estrategia:
1. 📧 Email
2. 💬 SMS
3. 🔔 Push
Elige (1-3): 1
✅ Estrategia actualizada para 'María González': Email
```

### Opción 4: 👥 Mostrar usuarios registrados

Lista todos los usuarios y sus estrategias actuales:

```
👥 USUARIOS REGISTRADOS
1. Juan García - Estrategia: Email
2. Ana López - Estrategia: Push
3. Carlos Ruiz - Estrategia: Push
4. María González - Estrategia: Email
```

### Opción 5: ❌ Salir

Cierra la aplicación de forma ordenada.



## 🔗 Relaciones entre Clases

### 1. STRATEGY (Patrón de Comportamiento)
```
Usuario
  │
  └─> estrategia: NotificacionStrategy
         │
         ├─> EmailStrategy
         ├─> SMSStrategy
         └─> PushStrategy
```

**Uso:**
```java
Usuario user = new Usuario("Ana", new EmailStrategy());
user.setEstrategia(new SMSStrategy());  // Cambiar en tiempo real
```

---

### 2. OBSERVER (Patrón de Comportamiento)
```
SistemaNotificaciones (Subject)
  │
  └─> usuarios: List<Observer>
         │
         ├─> Usuario (anaLópez)
         ├─> Usuario (juanGarcía)
         └─> Usuario (carlosRuiz)
```

**Uso:**
```java
SistemaNotificaciones sistema = SistemaNotificaciones.getInstancia();
sistema.agregarUsuario(user1);
sistema.agregarUsuario(user2);
sistema.notificar("Mensaje");  // Notifica a todos
```

---

### 3. SINGLETON (Patrón Creacional)
```
SistemaNotificaciones
  ├─ instancia: static ← Única en memoria
  └─ getInstancia(): SistemaNotificaciones
     └─> Siempre retorna la misma instancia
```

**Uso:**
```java
// Ambas referencias son la MISMA instancia
SistemaNotificaciones s1 = SistemaNotificaciones.getInstancia();
SistemaNotificaciones s2 = SistemaNotificaciones.getInstancia();
assert s1 == s2;  // ✅ Verdadero
```

---

## 📋 Clases Principales

### NotificacionStrategy (Interface)
```java
public interface NotificacionStrategy {
    void enviar(String mensaje);
}
```
✅ Define cómo enviar mensajes

---

### Observer (Interface)
```java
public interface Observer {
    void actualizar(String mensaje);
}
```
✅ Define qué hace un observador cuando se notifica

---

### Usuario (Observador Concreto)
```java
public class Usuario implements Observer {
    private String nombre;
    private NotificacionStrategy estrategia;
    
    @Override
    public void actualizar(String mensaje) {
        estrategia.enviar(mensaje);
    }
}
```
✅ Usuario que recibe notificaciones con su estrategia elegida

---

### SistemaNotificaciones (Singleton + Subject)
```java
public class SistemaNotificaciones {
    private static SistemaNotificaciones instancia;
    private List<Observer> usuarios;
    
    public static SistemaNotificaciones getInstancia() {
        if (instancia == null) {
            instancia = new SistemaNotificaciones();
        }
        return instancia;
    }
    
    public void notificar(String mensaje) {
        for (Observer u : usuarios) {
            u.actualizar(mensaje);
        }
    }
}
```
✅ Sistema central que notifica a todos los observadores

---

## 🎬 Flujo de Ejecución

```
1. INICIO (App.main)
   ↓
2. Obtener SistemaNotificaciones (Singleton)
   ↓
3. Crear Usuarios con Estrategias
   ├─ Juan → EmailStrategy
   ├─ Ana → SMSStrategy
   └─ Carlos → PushStrategy
   ↓
4. Registrar Usuarios (agregarUsuario)
   ↓
5. DEMOSTRACIÓN AUTOMÁTICA (4 eventos)
   ├─ Evento: COMPRA
   │  ├─ Juan recibe por EMAIL
   │  ├─ Ana recibe por SMS
   │  └─ Carlos recibe por PUSH
   │
   ├─ Evento: PROMOCIÓN
   │  ├─ Ana cambia estrategia: SMS → PUSH
   │  ├─ Juan recibe por EMAIL (sin cambios)
   │  ├─ Ana recibe por PUSH (nuevo)
   │  └─ Carlos recibe por PUSH (sin cambios)
   │
   ├─ Evento: PEDIDO ENVIADO
   │  ├─ Juan cambia estrategia: EMAIL → SMS
   │  ├─ Juan recibe por SMS (nuevo)
   │  ├─ Ana recibe por PUSH (sin cambios)
   │  └─ Carlos recibe por PUSH (sin cambios)
   │
   └─ Evento: CONFIRMACIÓN
      ├─ Juan recibe por SMS
      ├─ Ana recibe por PUSH
      └─ Carlos recibe por PUSH
   ↓
6. Mostrar información de patrones de diseño
   ↓
7. MODO INTERACTIVO
   ├─ Menú principal
   ├─ Usuario puede: agregar usuarios, enviar eventos, cambiar estrategias
   └─ Vuelve al menú hasta seleccionar SALIR
```



---

## 🧪 Casos de Uso

### Caso 1: Agregar un nuevo usuario (a través del código)
```java
Usuario pedro = new Usuario("Pedro López", new PushStrategy());
SistemaNotificaciones.getInstancia().agregarUsuario(pedro);
```

**O interactivamente:** Opción 1 → Ingresa nombre → Elige estrategia

---

### Caso 2: Cambiar preferencia de usuario (a través del código)
```java
juan.setEstrategia(new PushStrategy());  // De Email a Push
```

**O interactivamente:** Opción 3 → Elige usuario → Elige nueva estrategia

---

### Caso 3: Enviar notificación a todos (a través del código)
```java
SistemaNotificaciones.getInstancia()
    .notificar("Mensaje importante para todos");
```

**O interactivamente:** Opción 2 → Elige evento predefinido o personalizado

---

### Caso 4: Crear nueva estrategia personalizada (en código)
```java
public class TelegramStrategy implements NotificacionStrategy {
    @Override
    public void enviar(String mensaje) {
        System.out.println("📲 Telegram: " + mensaje);
    }
}

// Usar:
Usuario user = new Usuario("David", new TelegramStrategy());
SistemaNotificaciones.getInstancia().agregarUsuario(user);
```

---

### Caso 5: Flujo completo interactivo
1. Ejecutar programa → Ve demostración
2. Opción 1 → Agrega usuario "Sofia" con estrategia Email
3. Opción 2 → Envía evento personalizado "¡Bienvenida Sofia!"
4. Opción 4 → Ve todos los usuarios registrados
5. Opción 3 → Cambia estrategia de Sofia a SMS
6. Opción 2 → Envía evento de prueba
7. Opción 4 → Verifica cambios
8. Opción 5 → Sale del programa



---

## ✅ Checklist de Validación

- ✅ Interfaz `NotificacionStrategy` define el contrato
- ✅ 3 Estrategias implementadas: Email, SMS, Push
- ✅ Interfaz `Observer` define el contrato
- ✅ Clase `Usuario` implementa Observer
- ✅ `SistemaNotificaciones` implementa Singleton
- ✅ Constructor privado en SistemaNotificaciones
- ✅ `getInstancia()` retorna instancia única
- ✅ Se pueden agregar/remover observadores dinámicamente
- ✅ Se pueden cambiar estrategias en tiempo de ejecución
- ✅ La ejecución muestra todos los eventos correctamente
- ✅ **NUEVO:** Modo interactivo con menú de opciones
- ✅ **NUEVO:** Agregar usuarios interactivamente
- ✅ **NUEVO:** Enviar eventos personalizados
- ✅ **NUEVO:** Cambiar estrategias en el menú
- ✅ **NUEVO:** Visualizar usuarios registrados



---

## 📚 Archivos Clave

| Archivo | Líneas | Propósito |
|---------|--------|----------|
| `App.java` | ~300 | Clase ejecutable con demostración + modo interactivo |
| `NotificacionStrategy.java` | 5 | Interface base |
| `EmailStrategy.java` | 10 | Implementación Email |
| `SMSStrategy.java` | 10 | Implementación SMS |
| `PushStrategy.java` | 10 | Implementación Push |
| `Observer.java` | 5 | Interface base |
| `Usuario.java` | 50 | Usuario observador con getter de estrategia |
| `SistemaNotificaciones.java` | 50 | Singleton + Notificador |



---

## 🎓 Conceptos Aplicados

### Strategy Pattern
- ✅ Encapsular algoritmos intercambiables
- ✅ Cambiar algoritmos en tiempo de ejecución
- ✅ Facilita agregar nuevas estrategias

### Observer Pattern
- ✅ Crear mecanismo de notificación automática
- ✅ Desacoplar sujeto de observadores
- ✅ Múltiples observadores notificados en paralelo

### Singleton Pattern
- ✅ Una única instancia en toda la aplicación
- ✅ Punto central de acceso
- ✅ Inicialización lazy

---

## 🚀 Próximos Pasos (Opcional)

### ✅ Ya Implementado
- ✅ Modo interactivo con menú de opciones
- ✅ Agregar usuarios dinámicamente
- ✅ Cambiar estrategias en tiempo de ejecución
- ✅ Enviar eventos personalizados

### 📋 Futuras Mejoras
1. Agregar más estrategias (WhatsApp, Telegram, Discord, etc.)
2. Crear tests unitarios
3. Agregar persistencia (guardar usuarios en archivo/BD)
4. Crear interfaz gráfica (JavaFX o Swing)
5. Implementar logging con Log4j
6. Agregar filtración de eventos por usuario
7. Historial de notificaciones
8. Sistema de perfiles de usuario

---

**¡Proyecto completado con modo interactivo! 🎉**

