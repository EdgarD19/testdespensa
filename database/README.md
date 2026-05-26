# Base de datos — DespensaProyect

## Orden recomendado (desarrollo)

1. Levantar PostgreSQL:
   ```bash
   docker-compose up -d
   ```

2. Crear/actualizar tablas con Spring Boot (una vez):
   ```bash
   ./mvnw spring-boot:run
   ```
   (o desplegar en Railway; `spring.jpa.hibernate.ddl-auto=update`)

3. Cargar **todos los datos maestros y de ejemplo**:
   ```bash
   docker exec -i pos_postgres psql -U posuser -d posdb < database/seed-data.sql
   ```

   En Windows (PowerShell), desde la raíz del repo:
   ```powershell
   Get-Content database\seed-data.sql | docker exec -i pos_postgres psql -U posuser -d posdb
   ```

## Contenido de `seed-data.sql`

Incluye datos en:

| Módulo | Tablas |
|--------|--------|
| Geografía | `pais`, `ciudad` |
| Personal | `rol_empleado`, `empleado` |
| Seguridad | `rol`, `permiso`, `rol_permiso`, `usuario`, `usuario_rol` |
| Catálogo | `proveedor`, `unidad_medida`, `categoria_producto`, `subcategoria_producto` |
| Productos | `producto`, `producto_proveedor` (con stock y códigos de barras) |
| Clientes | `cliente`, `contacto_cliente`, `contacto_proveedor` |
| Referencia | `tipo_movimiento`, `tipo_movimiento_caja`, `forma_pago` |

**IDs fijos útiles para el frontend:** ciudad `1` (Capiatá), país `1` (Paraguay), categorías `1–4`, proveedores `1–3`, productos `1–8` con stock.

## Migraciones manuales

Ejecutar después del esquema base, si aplica:

- `migrations/V2_alter_table_delete_columns-clients.sql`
- `migrations/V3_add_fechas_historial_precio.sql`

## Backup / restore

- `backup.bat` / `restore.bat`
- Dumps en `backup/`
