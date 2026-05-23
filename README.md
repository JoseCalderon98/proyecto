# Banking Application - Refactorización de Dominio

Este proyecto contiene un sistema de gestión bancaria que ha sido mejorado siguiendo principios de diseño estructurado. 

## Características Incorporadas

1. **Uso de Enums**: Se reemplazaron Strings por enums fuertemente tipados (`AccountStatus`, `AccountType`, `LoanStatus`, `LoanType`, `TransferStatus`, `UserRole`, `UserStatus`, `Currency`, `OperationType`, `ClientStatus`).
2. **Sistema Monetario Seguro**: Se implementó `BigDecimal` para todos los montos monetarios (`BankAccount`, `Loan`, `Transfer`), evitando los problemas de coma flotante de `double`.
3. **Manejo de Fechas Moderno**: Se reemplazó la antigua API `java.util.Date` por `java.time.LocalDateTime` y `java.time.LocalDate` para una mayor precisión e inmutabilidad en las fechas.
4. **Relaciones en Clientes**: Se estructuró el modelo de clientes a través de la herencia de `Client` (padre) para `IndividualClient` y `CorporateClient` agregando todos los campos obligatorios.
5. **Auditoría Flexible**: Utilización de `Map<String, Object>` en `detailData` para los registros del log (`LogRecord`), permitiendo trazabilidad flexible.
6. **Lógica de Negocio Centralizada**: Incorporación de métodos nativos en el dominio con las validaciones correspondientes (ej. `deposit`, `withdraw`, `block`, y `approve`).

## Tecnologías Utilizadas
- Java 17+
- Spring Boot 3.2.4 (Data JPA, Web, MongoDB)
- Maven
