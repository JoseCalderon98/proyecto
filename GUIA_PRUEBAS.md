# 📚 Guía de Pruebas Paso a Paso - Banking API

Esta guía detalla el flujo completo para probar todas las reglas de negocio del sistema utilizando la nueva colección de Postman: `banking_api_business_rules_collection.json`.

---

## 🔐 Fase 0: Autenticación
1.  **Carpeta:** `0. Autenticación`
2.  **Request:** `Login - ÉXITO`
3.  **Acción:** Clic en **Send**.
4.  **Verificación:** Debes recibir un `200 OK` y el script de Postman actualizará la variable `token`.

---

## 👥 Fase 1: Configuración de Clientes y Usuarios
1.  **Registrar Cliente:**
    - Carpeta `1. Clientes` -> `Crear Cliente Natural - ÉXITO` -> **Send**.
    - Carpeta `1. Clientes` -> `Crear Cliente Empresa - ÉXITO` -> **Send**.
2.  **Crear Usuarios Digitales:**
    - Carpeta `2. Usuarios` -> `Crear Usuario - ÉXITO` (Natural).
    - Carpeta `2. Usuarios` -> `Crear Usuario Empresa - ÉXITO` (Empresa).
    - Carpeta `2. Usuarios` -> `Crear Usuario Analista Interno - ÉXITO` (Analista).

---

## 💰 Fase 2: Cuentas y Limites
1.  **Apertura:** Carpeta `3. Cuentas Bancarias` -> `Abrir Cuenta - ÉXITO`.
2.  **Prueba de Fuego (Regla 3.5):** 
    - Request: `Falla - Sobregiro No Autorizado (Regla 3.5)`.
    - **Resultado esperado:** Código `400` y mensaje de fondos insuficientes.

---

## 📝 Fase 3: Préstamos (Flujo Completo)
1.  **Solicitar:** Carpeta `4. Préstamos` -> `Solicitar Préstamo - ÉXITO`.
2.  **Aprobar:** `4. Préstamos` -> `Aprobar Préstamo - ÉXITO`.
3.  **Desembolsar:** `4. Préstamos` -> `Desembolsar Préstamo - ÉXITO`.

---

## 🏢 Fase 4: Transferencias Empresariales
1.  **Solicitud Alto Monto:** Carpeta `5. Transferencias` -> `Transferencia Alto Monto - En Espera de Aprobación (Regla 7.1/7.2)`.
2.  **Aprobación:** Carpeta `5. Transferencias` -> `Aprobar Transferencia - ÉXITO (Regla 7.3/7.4)`.

---

## 🔍 Fase 5: Bitácora de Auditoría
1.  **Consultar Todo:** Carpeta `4. Préstamos` -> `Consultar Bitácora de Préstamo (Regla 5.7/8.x)`.
2.  **Verificación:** Verifica en el JSON de respuesta que aparezca el usuario que realizó la aprobación/desembolso.

---

### 💡 Recordatorios Importantes:
- **Excepciones:** Las pruebas marcadas como "Falla" están diseñadas para dar error. Esto valida que el sistema está protegiendo las reglas de negocio (ej. un Cajero no puede pedir préstamos).
- **Manejo de Errores:** Todos los errores deben venir en el formato:
  ```json
  { "message": "...", "details": "..." }
  ```
