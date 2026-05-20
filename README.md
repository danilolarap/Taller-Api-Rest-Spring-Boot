Pruebas de Funcionamiento y Persistencia

1. Creación de un Préstamo Activo
Prueba inicial desde el cliente HTTP (`POST`) que confirma la creación correcta de un nuevo préstamo en el sistema, retornando un estado exitoso y el estado `"ACTIVO"` en el JSON:

<img width="1365" height="719" alt="image" src="https://github.com/user-attachments/assets/f4d48dc4-ded3-4890-bfc2-017ec5088856" />

2. Sincronización en la Base de Datos (Clúster Cloud)
Verificación directa desde el panel de **MongoDB Atlas** dentro de la colección `loans`. Se comprueba que los IDs se enlazaron correctamente y que el registro inicial quedó guardado de forma persistente en la nube:

<img width="1365" height="598" alt="image" src="https://github.com/user-attachments/assets/eeaeabe4-d5f6-4962-84c9-bf81686766df" />

3. Flujo Completo de Devolución (Cambio de Estado)
Prueba final del endpoint de devolución (`POST`). Al procesar la solicitud, el sistema valida las reglas de negocio y actualiza con éxito el ciclo de vida del préstamo, cambiando su estado a `"DEVUELTO"`:

<img width="1365" height="721" alt="image" src="https://github.com/user-attachments/assets/e84d0c02-0f75-44cc-add0-239a5b9df510" />
