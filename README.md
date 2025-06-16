# Actividad Integral sobre los Principios SOLID

## 1. Descripción del Proyecto y Objetivos

Este proyecto es una refactorización de un sistema de gestión de usuarios en Java, cuyo objetivo principal es aplicar y demostrar una comprensión profunda de los principios SOLID del diseño orientado a objetos. A través de esta actividad, se busca mejorar la modularidad, mantenibilidad, extensibilidad y testabilidad del código base.

**Objetivos específicos:**
* Identificar y corregir violaciones de los principios SOLID en un código existente.
* Refactorizar el código para cumplir con cada uno de los principios.
* Comprender y reflexionar críticamente sobre el impacto de SOLID en la calidad del software.

## 2. Implementación de los Principios SOLID

### 2.1. Principio de Responsabilidad Única (SRP)

**Problema Inicial:**
La clase `UserManager` original violaba el SRP al manejar múltiples responsabilidades: validación de usuario, persistencia en base de datos y envío de notificaciones. Esto generaba un alto acoplamiento y diversas razones para que la clase cambiara.

**Solución de Refactorización:**
Se dividió la clase `UserManager` en componentes con responsabilidades únicas:
* `UserValidator`: Encargada exclusivamente de la validación del formato del email y la fortaleza de la contraseña.
* `UserRepository`: Responsable de las operaciones de guardar los datos del usuario en una "base de datos" simulada (impresión en consola).
* `NotificationService`: Encargada de enviar notificaciones de bienvenida al usuario.
* `UserManager`: Ahora actúa como un orquestador que coordina las operaciones de las clases especializadas.

**Beneficios Obtenidos:**
La modularidad ha mejorado drásticamente. Cada clase tiene ahora una única razón para cambiar, lo que facilita el mantenimiento, las pruebas unitarias y reduce el acoplamiento entre componentes.

### 2.2. Principio Abierto/Cerrado (OCP)

**Concepto y Aplicación:**
El OCP establece que las entidades de software deben estar abiertas para la extensión, pero cerradas para la modificación. Para aplicar este principio en las notificaciones, se introduce una interfaz `NotificationSender` que define el contrato para enviar notificaciones.

**Solución de Refactorización:**
* Se crea la interfaz `NotificationSender` con un método `sendNotification(String recipient, String message)`.
* Se implementan clases concretas como `EmailNotificationSender` y `SMSNotificationSender` que proveen distintas formas de envío de notificaciones.
* `NotificationService` ahora depende de la abstracción `NotificationSender`, permitiendo que se le inyecte cualquier implementación de notificación sin modificar su código.

**Beneficios Obtenidos:**
Permite añadir nuevos tipos de notificación (ej., push notifications) creando nuevas implementaciones de `NotificationSender` sin alterar el código existente. Esto reduce el riesgo de introducir errores en funcionalidades ya operativas y mejora la extensibilidad del sistema.

### 2.3. Principio de Sustitución de Liskov (LSP)

**Concepto y Aplicación:**
El LSP afirma que los objetos de un programa deben poder ser reemplazados por instancias de sus subtipos sin alterar la corrección del programa. Para los usuarios, esto implica que las clases derivadas deben comportarse de manera consistente con su tipo base.

**Solución de Refactorización:**
* Se establece una jerarquía de clases con una clase abstracta `User` (con propiedades básicas como email y password).
* Se definen subtipos como `RegularUser` y `AdminUser` que heredan de `User`.
* Cada subtipo implementa un método abstracto (ej., `hasSpecialPrivileges()`) de manera que respeta el contrato de la clase base, permitiendo que un `RegularUser` o `AdminUser` pueda ser tratado como un `User` sin producir comportamientos inesperados.

**Beneficios Obtenidos:**
Garantiza que la herencia se utiliza correctamente, haciendo que el sistema sea más predecible y robusto. Facilita el uso polimórfico de objetos, mejorando la flexibilidad y la facilidad de mantenimiento de la jerarquía de clases.

### 2.4. Principio de Segregación de Interfaces (ISP)

**Concepto y Aplicación:**
El ISP sugiere que los clientes no deben ser forzados a depender de interfaces que no utilizan. Interfaces grandes y monolíticas obligan a las clases a implementar métodos irrelevantes, aumentando el acoplamiento innecesario.

**Solución de Refactorización:**
* Se dividen las interfaces genéricas en interfaces más pequeñas y específicas del cliente.
* Por ejemplo, en lugar de una única interfaz `UserManagerService` con métodos de validación, persistencia y notificación, se crean interfaces separadas: `Validatable`, `Persistable` y `Notifiable`.
* Las clases (`UserValidator`, `UserRepository`, `NotificationService`) implementan solo las interfaces que realmente necesitan.

**Beneficios Obtenidos:**
Reduce el acoplamiento y mejora la cohesión al asegurar que las clases solo dependan de los métodos que realmente utilizan. Esto simplifica el diseño y hace el sistema más adaptable a cambios en los requisitos específicos de cada cliente.

### 2.5. Principio de Inversión de Dependencias (DIP)

**Concepto y Aplicación:**
El DIP establece que los módulos de alto nivel no deben depender de módulos de bajo nivel; ambos deben depender de abstracciones. Además, las abstracciones no deben depender de los detalles; los detalles deben depender de las abstracciones. En el código original, `UserManager` dependía directamente de implementaciones concretas.

**Solución de Refactorización:**
* Se definen interfaces para los servicios de bajo nivel: `UserValidationService`, `UserPersistenceService` y `UserNotificationService`.
* Las clases concretas (`UserValidator`, `UserRepository`, `NotificationService`) implementan estas interfaces.
* La clase `UserManager` (módulo de alto nivel) no instancia directamente estas clases, sino que recibe sus implementaciones a través de inyección de dependencias (por ejemplo, a través del constructor).

**Beneficios Obtenidos:**
Desacopla los módulos de alto nivel de los de bajo nivel, haciendo que el sistema sea más flexible, testeable y fácil de mantener. Permite la sustitución de implementaciones sin afectar el código de alto nivel.

## 3. Estructura del Proyecto

El proyecto está organizado de la siguiente manera:
.
├── src
│   └── main
│       └── java
│           ├── Old                  # Código original antes de la refactorización
│           │   └── UserManager.java
│           └── org.Rengifo_Corregido # Código refactorizado aplicando SOLID
│               ├── Main.java
│               ├── NotificationService.java
│               ├── UserManager.java
│               ├── UserRepository.java
│               └── UserValidator.java
├── .gitignore
├── pom.xml
└── README.md

## 4. Requisitos y Ejecución

### Requisitos:
* Java Development Kit (JDK) 8 o superior.
* Apache Maven para la gestión del proyecto.
* Un entorno de desarrollo (IDE) como IntelliJ IDEA, Eclipse o VS Code con soporte para Java.

### Pasos para la ejecución:
1.  Clonar el repositorio:
    ```bash
    git clone [https://github.com/AlexxRen/Actividad-Integral-sobre-los-Principios-SOLID.git](https://github.com/AlexxRen/Actividad-Integral-sobre-los-Principios-SOLID.git)
    ```
2.  Navegar al directorio del proyecto:
    ```bash
    cd Actividad-Integral-sobre-los-Principios-SOLID
    ```
3.  Compilar el proyecto (opcional si usa IDE):
    ```bash
    mvn compile
    ```
4.  Ejecutar la clase `Main` para observar el funcionamiento del `UserManager` refactorizado con casos de prueba válidos e inválidos:
    ```bash
    mvn exec:java -Dexec.mainClass="org.Rengifo_Corregido.Main"
    ```

## 5. Salida Esperada del Proyecto Refactorizado

El flujo principal de agregar un usuario debe seguir funcionando correctamente:
* Validación del email y la contraseña.
* Guardado del usuario en la base de datos (simulado en consola).
* Envío de una notificación de bienvenida (simulado en consola).

Ejemplo de salida esperada:
=== Testing with valid user ===
Saving user to the database...
Email: example@domain.com
Password: password123
Sending welcome email to example@domain.com
User successfully added and notified.

=== Testing with invalid email ===
Invalid email or password. User not added.

=== Testing with invalid password ===
Invalid email or password. User not added.

=== Testing with both invalid ===
Invalid email or password. User not added.

## 6. Reflexión Personal sobre los Principios SOLID

### 6.1. ¿Cuál fue el principio más desafiante de aplicar y por qué?
El Principio de Inversión de Dependencias (DIP) resultó ser el más desafiante de aplicar. Aunque la inyección de dependencias en el constructor es conceptualmente sencilla, comprender cómo abstraer correctamente cada dependencia para que los módulos de alto nivel no dependan de los de bajo nivel, sino de abstracciones, requirió un análisis cuidadoso del flujo de control y las responsabilidades. El reto residió en identificar las interfaces adecuadas y asegurar que la lógica de negocio principal no estuviera ligada a detalles de implementación, lo cual a veces implicaba repensar la estructura de la aplicación.

### 6.2. ¿Cómo crees que SOLID mejora el diseño de software?
La aplicación de los principios SOLID mejora fundamentalmente el diseño de software al fomentar la creación de sistemas más robustos, flexibles y mantenibles. Permiten construir una arquitectura modular donde cada componente tiene una responsabilidad clara y puede evolucionar de forma independiente. Esto se traduce en una reducción significativa del acoplamiento, facilitando las pruebas unitarias y la integración continua. En última instancia, un código que adhiere a SOLID es más fácil de entender, depurar y extender, lo que optimiza el ciclo de vida del desarrollo y reduce la deuda técnica a largo plazo.

### 6.3. ¿Qué principio SOLID aplicarías específicamente en futuros o actuales proyectos y por qué?
En futuros o actuales proyectos, priorizaría la aplicación rigurosa del Principio de Responsabilidad Única (SRP) y el Principio de Inversión de Dependencias (DIP). El SRP es el cimiento, ya que obliga a un diseño modular desde el inicio, evitando clases "Dios" que concentran demasiadas funciones. Por su parte, el DIP es crucial para la flexibilidad y la testabilidad, al garantizar que los componentes de alto nivel no estén acoplados a detalles de implementación. La combinación de SRP y DIP establece una base sólida para un código limpio, desacoplado y fácil de mantener, lo que a su vez facilita la implementación de los demás principios SOLID de manera más natural.

---
**Autor:** [Alexander Rengifo, Jorge Moncayo, Hayland Montalvo,Juan Alvarez,Daniel Salguero]
**Materia:** Validación y Verificación de Software
**Docente:** [Roldán Correa, Fernando José]
**Fecha:** [15/06/2025]

