# EVALUACIÓN - proyecto

## Información General
- **Estudiante(s):** José Calderón (JoseCalderon98)
- **Rama evaluada:** main (no existe rama develop)
- **Fecha de evaluación:** 2026-03-23

---

## Tabla de Calificación

| # | Criterio | Peso | Puntaje (1–5) | Nota ponderada |
|---|---|---|---|---|
| 1 | Modelado de dominio | 25% | 3 | 0.75 |
| 2 | Relaciones entre entidades | 15% | 3 | 0.45 |
| 3 | Uso de Enums ⚠️ | 15% | 1 | 0.15 |
| 4 | Manejo de estados | 5% | 1 | 0.05 |
| 5 | Tipos de datos | 5% | 1 | 0.05 |
| 6 | Separación Usuario vs Cliente | 10% | 4 | 0.40 |
| 7 | Bitácora | 5% | 2 | 0.10 |
| 8 | Reglas básicas de negocio | 5% | 1 | 0.05 |
| 9 | Estructura del proyecto | 10% | 3 | 0.30 |
| 10 | Repositorio | 10% | 1 | 0.10 |
| **TOTAL** | | **100%** | | **2.40** |

## Penalizaciones
- Ninguna (código en inglés, nombres correctos).

## Bonus
- +2: Herencia correcta (`Client` → `CorporateClient` / `IndividualClient`) ✓ → +0.2
- +2: Código limpio y consistente → +0.2
- +1: Nombres claros y en inglés → +0.1
- **Total: +0.5**

## Nota Final: 2.9 / 5.0

---

## Análisis por Criterio

### 1. Modelado de dominio — 3/5
Entidades presentas: `BankAccount`, `BankingProduct`, `LogRecord`, `Client` (abstracta), `CorporateClient`, `IndividualClient`, `Loan`, `Transfer`, `User`, `SystemRole`. Las entidades correctas existen, pero están muy incompletas. `Client` es una clase abstracta vacía (sin ningún campo). `IndividualClient` solo tiene `birthDate`. `SystemRole` es una clase en lugar de enum. No hay enums en todo el proyecto.

### 2. Relaciones entre entidades — 3/5
`User` tiene referencia a `Client client` ✓ — correctamente relacionado. `BankAccount.holderId`, `Loan.applicantClientId`, `LogRecord.affectedProductId` son referencias en String.  `Transfer` tiene `originAccount` / `destinationAccount` como Strings. Las referencias existen pero no son objetos tipados; no se aprovecha el polimorfismo.

### 3. Uso de Enums ⚠️ — 1/5
**No existe ningún enum en el proyecto.** Todos los estados y tipos se manejan como `String`:
- `BankAccount`: `accountType`, `accountStatus`, `currency` → todos `String`
- `Loan`: `loanType`, `loanStatus` → `String`
- `Transfer`: `transferStatus` → `String`
- `User`: `systemRole`, `userStatus` → `String`
- `LogRecord`: `operationType`, `userRole` → `String`
- `SystemRole` es una **clase** (no enum) con `roleId` e `int description`.

### 4. Manejo de estados — 1/5
Todos los estados (`accountStatus`, `loanStatus`, `transferStatus`, `userStatus`) son campos `String` sin validación, sin transiciones de estado y sin métodos que verifiquen el estado actual antes de operar.

### 5. Tipos de datos — 1/5
- **Montos monetarios:** `double` en `BankAccount.currentBalance`, `Loan.requestedAmount`, `Loan.approvedAmount`, `Transfer.amount` — debería ser `BigDecimal`.
- **Fechas:** `java.util.Date` en todas las entidades — debería ser `LocalDate` / `LocalDateTime`.
- **Tasas:** `double interestRate` — debería ser `BigDecimal`.

### 6. Separación Usuario vs Cliente — 4/5
`User` y `Client` son entidades separadas en paquetes distintos (`domain/user` y `domain/client`) ✓. `User` tiene una referencia directa `Client client` ✓. La jerarquía `Client → CorporateClient / IndividualClient` está presente ✓. Penalización: `Client` está vacía y no aporta campos comunes como nombre, identificación o estado.

### 7. Bitácora — 2/5
`LogRecord` existe ✓. Tiene `operationType`, `userRole`, `affectedProductId`, `detailData`. Sin embargo:
- `detailData` es `String` en lugar de `Map<String, Object>` para datos flexibles.
- `operationType` y `userRole` son `String` en lugar de enums.
- `java.util.Date` en lugar de `LocalDateTime`.
- No hay un campo de seguimiento de a quién le pertenece la operación (userId como `int`, no como referencia).

### 8. Reglas básicas de negocio — 1/5
Todas las clases son POJOs puros con getters/setters generados. No hay ningún método de negocio: no hay `deposit()`, `withdraw()`, `approve()`, `block()`, validaciones en constructores ni lógica de dominio.

### 9. Estructura del proyecto — 3/5
La separación por subdominios dentro de `domain/` es correcta y sigue principios DDD: `domain/account`, `domain/audit`, `domain/client`, `domain/loan`, `domain/transfer`, `domain/user` ✓. Sin embargo, el proyecto no tiene estructura de build (no hay `pom.xml`, no hay `src/main/java`, no es un proyecto Maven/Gradle). Las clases están directamente en `domain/` sin proyecto ejecutable.

### 10. Repositorio — 1/5
- **Nombre:** `proyecto` — nombre genérico, no sigue el formato de la materia.
- **README:** No existe.
- **Commits:** Solo 2 commits ("First commit", "Refactor: Reorganize project with DDD structure"). Sin formato ADD/CHG.
- **Ramas:** Solo `main`, no existe `develop`.
- **Tag:** No hay tag de entrega.

---

## Fortalezas
- Estructura DDD de los paquetes bien organizada (un subdirectorio por subdominio).
- `User` correctamente separado de `Client` con referencia directa.
- Jerarquía de clientes: `Client` → `CorporateClient` / `IndividualClient`.
- Todas las entidades principales del dominio están presentes.
- Código limpio y con nombres consistentes en inglés.

## Oportunidades de mejora
- **Crear enums** para todos los estados y tipos: `AccountStatus`, `AccountType`, `LoanStatus`, `LoanType`, `TransferStatus`, `UserRole`, `UserStatus`, `Currency`, `OperationType`. Este es el criterio más crítico.
- **Usar `BigDecimal`** para todos los montos monetarios.
- **Usar `LocalDate`/`LocalDateTime`** en lugar de `java.util.Date`.
- **Agregar campos a `Client`**: nombre completo, identificación, estado del cliente (activo/inactivo/bloqueado).
- **Agregar campos a `IndividualClient`**: nombre, número de identificación, además de `birthDate`.
- **Cambiar `SystemRole` de clase a enum**: `ADMIN`, `TELLER`, `SUPERVISOR`, `AUDITOR`.
- **Usar `Map<String, Object>`** en `LogRecord.detailData` para datos flexibles de auditoría.
- **Agregar lógica de negocio**: métodos `deposit()`, `withdraw()`, `approve()`, `block()` con validaciones.
- **Crear proyecto Maven/Gradle** con `pom.xml` y estructura `src/main/java/`.
- **Crear README**, rama `develop`, y al menos un tag de entrega.
