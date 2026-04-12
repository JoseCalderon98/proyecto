# EVALUACION 2 - proyecto

## Informacion general
- Estudiante(s): Jose Calderon (usuario GitHub: JoseCalderon98)
- Rama evaluada: **develop** (commit más reciente del estudiante: 2026-04-09 18:08)
- Commit evaluado: f0f3be1 (2026-04-09)
- Fecha: 2026-04-11
- Nota: Se re-evaluó sobre la rama `develop` que tiene enums completos. La rama `main` evaluada anteriormente usaba `String` para estados.

---

## Tabla de calificacion

| # | Criterio | Peso | Puntaje (1-5) | Parcial |
|---|---|---|---|---|
| 1 | Modelado de dominio | 20% | 3 | 0.60 |
| 2 | Modelado de puertos | 20% | 1 | 0.20 |
| 3 | Modelado de servicios de dominio | 20% | 1 | 0.20 |
| 4 | Enums y estados | 10% | 5 | 0.50 |
| 5 | Reglas de negocio criticas | 10% | 1 | 0.10 |
| 6 | Bitacora y trazabilidad | 5% | 2 | 0.10 |
| 7 | Estructura interna de dominio | 10% | 3 | 0.30 |
| 8 | Calidad tecnica base en domain | 5% | 4 | 0.20 |
| | **Total base** | | | **2.20** |

### Calculo
Nota base = (3*20 + 1*20 + 1*20 + 5*10 + 1*10 + 2*5 + 3*10 + 4*5) / 100 = 220 / 100 = **2.20**

---

## Penalizaciones aplicadas

No se aplican penalizaciones. Los estados ahora usan enums propios. `BankingApplication.java` está en el paquete `com.banking`, no en `domain/`.

---

## Nota final
**2.2 / 5.0**

---

## Hallazgos

### Criterio 1 - Modelado de dominio (3/5)
- Entidades organizadas por subdominio: `account/BankAccount`, `account/BankingProduct`, `audit/LogRecord`, `client/Client`, `client/CorporateClient`, `client/IndividualClient`, `loan/Loan`, `transfer/Transfer`, `user/User`.
- Jerarquía de clientes: `Client → CorporateClient / IndividualClient`. ✓
- `Loan.java` ahora usa `LoanType loanType` y `LoanStatus loanStatus` (enums). ✓
- Falta: `BankAccount` sin campo de número de cuenta semántico explícito; sin métodos de negocio en entidades.
- `BankingApplication.java` correctamente ubicada en `com.banking`, NO en domain.

### Criterio 2 - Modelado de puertos (1/5)
- **No existe ninguna interfaz de puerto en el dominio.**

### Criterio 3 - Servicios de dominio (1/5)
- **No existe ninguna clase de servicio de dominio.**

### Criterio 4 - Enums y estados (5/5)
- 10 enums en develop: `AccountStatus`, `AccountType`, `Currency`, `LoanStatus`, `LoanType`, `TransferStatus`, `UserRole`, `UserStatus`, `ClientStatus`, `OperationType`.
- Cobertura completa de todos los estados del sistema bancario.
- Mejora significativa respecto a la rama `main` donde se usaban `String`.

### Criterio 5 - Reglas de negocio criticas (1/5)
- Sin servicios no hay reglas implementadas.

### Criterio 6 - Bitacora y trazabilidad (2/5)
- `LogRecord` con `OperationType` enum — mejor estructura que antes.
- Sin puerto ni servicio que la utilice.

### Criterio 7 - Estructura interna de dominio (3/5)
- Organización por subdominio (`account/`, `audit/`, `client/`, `loan/`, `transfer/`, `user/`): correcta.
- Los enums dentro de cada subdominio (ej. `account/AccountStatus`) son buena decisión de diseño.
- Falta: `ports/`, `services/`.

### Criterio 8 - Calidad tecnica (4/5)
- Nomenclatura en inglés consistente.
- Enums bien definidos y organizados.
- `BankingApplication.java` en paquete correcto (no en domain).

---

## Recomendaciones
1. Implementar `domain/ports/` con interfaces por agregado: `BankAccountPort`, `ClientPort`, `LoanPort`, `TransferPort`, `UserPort`.
2. Implementar `domain/services/` con los casos de uso del enunciado: `CreateLoan`, `ApproveLoan`, `CreateTransfer`, `ApproveTransfer`, `ExpireTransfers`.
3. Agregar métodos de negocio en las entidades: `BankAccount.debit()`, `BankAccount.credit()`, `Loan.approve()`, etc.
4. Enriquecer `LogRecord` con `timestamp`, `userId`, `role`, `operationType`, `detail`.


## Informacion general
- Estudiante(s): Jose Calderon (usuario GitHub: JoseCalderon98)
- Rama evaluada: main
- Commit evaluado: 4461d3adb4c174e73ee1a0386a11d038b089a2b1
- Fecha: 2026-04-11
- Nota: Solo existe rama `main`; no hay rama `develop`. No existe `README.md` en el repositorio.

---

## Tabla de calificacion

| # | Criterio | Peso | Puntaje (1-5) | Parcial |
|---|---|---|---|---|
| 1 | Modelado de dominio | 20% | 2 | 0.40 |
| 2 | Modelado de puertos | 20% | 1 | 0.20 |
| 3 | Modelado de servicios de dominio | 20% | 1 | 0.20 |
| 4 | Enums y estados | 10% | 1 | 0.10 |
| 5 | Reglas de negocio criticas | 10% | 1 | 0.10 |
| 6 | Bitacora y trazabilidad | 5% | 1 | 0.05 |
| 7 | Estructura interna de dominio | 10% | 2 | 0.20 |
| 8 | Calidad tecnica base en domain | 5% | 3 | 0.15 |
| | **Total base** | | | **1.40** |

### Calculo
Nota base = (2*20 + 1*20 + 1*20 + 1*10 + 1*10 + 1*5 + 2*10 + 3*5) / 100 = 140 / 100 = **1.40**

---

## Penalizaciones aplicadas

| Penalizacion | Porcentaje | Motivo |
|---|---|---|
| Estados en String | -10% | `loanType`, `loanStatus` declarados como `String` en `Loan.java`; mismo patron en otras entidades |

Nota tras penalizacion: 1.40 × 0.90 = **1.26**

---

## Nota final
**1.3 / 5.0**

---

## Hallazgos

### Criterio 1 - Modelado de dominio (2/5)
- Entidades en `domain/`: organizadas por subdominio: `account/BankAccount`, `account/BankingProduct`, `audit/LogRecord`, `client/Client`, `client/CorporateClient`, `client/IndividualClient`, `loan/Loan`, `transfer/Transfer`, `user/SystemRole, User`.
- Buena organizacion por subdominio.
- Solo 10 archivos java totales — implementacion muy minima.
- **Problema:** `Loan.java` usa `loanType` y `loanStatus` como `String`, no enums.
- `LogRecord` es muy basico; no tiene estructura de datos detallados.
- Falta: `BankAccount` no tiene estado (enum), numero unico explicito.

### Criterio 2 - Modelado de puertos (1/5)
- **No existe ninguna interfaz de puerto en el dominio.**

### Criterio 3 - Servicios de dominio (1/5)
- **No existe ninguna clase de servicio de dominio.**

### Criterio 4 - Enums y estados (1/5)
- Solo `SystemRole` en `domain/user/` podria ser un enum.
- Todos los estados de `Loan` son `String` — confirmado en el codigo fuente.
- No hay enums para `TipoCuenta`, `EstadoCuenta`, `EstadoTransferencia`, `Moneda`, etc.

### Criterio 5 - Reglas de negocio criticas (1/5)
- Sin servicios no hay reglas implementadas.

### Criterio 6 - Bitacora y trazabilidad (1/5)
- `LogRecord` existe como entidad basica.
- Sin estructura de datos flexible, sin puerto, sin servicio.

### Criterio 7 - Estructura interna de dominio (2/5)
- La organizacion por subdominio (`account/`, `client/`, `loan/`, etc.) es una buena decision.
- Falta: `ports/`, `services/`, `enums/`.

### Criterio 8 - Calidad tecnica (3/5)
- Nomenclatura en ingles consistente.
- La organizacion por subdominio es correcta.
- El uso de `String` para estados es la debilidad principal.

---

## Recomendaciones
1. Crear enums para todos los estados: `LoanStatus`, `LoanType`, `AccountStatus`, `AccountType`, `TransferStatus`, `Currency`.
2. Implementar `domain/ports/` con interfaces de contrato por agregado.
3. Implementar `domain/services/` con los casos de uso del enunciado.
4. Agregar numero de cuenta unico como campo semantico en `BankAccount`.
5. Enriquecer `LogRecord` con `timestamp`, `userId`, `role`, `operationType` y datos de detalle.
6. Crear `README.md` con los nombres de los integrantes.
