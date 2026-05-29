-- =============================================================================
-- DespensaProyect — datos iniciales (maestros + muestras operativas)
-- =============================================================================

\set ON_ERROR_STOP on
BEGIN;

-- -----------------------------------------------------------------------------
-- Limpieza (orden: hijos → padres; CASCADE para FKs)
-- -----------------------------------------------------------------------------
TRUNCATE TABLE
    transferencia_pago,
    detalle_factura,
    factura,
    detalle_factura_compra,
    factura_compra,
    detalle_orden_compra,
    orden_compra,
    inventario_ajuste,
    detalle_inventario,
    inventario,
    movimiento_inventario,
    tipo_movimiento,
    movimiento_caja,
    tipo_movimiento_caja,
    caja,
    forma_pago,
    historial_precio_producto,
    producto_proveedor,
    producto,
    unidad_medida,
    subcategoria_producto,
    categoria_producto,
    contacto_proveedor,
    proveedor,
    cliente,
    empleado,
    usuario,
    rol,
    ciudad,
    pais
RESTART IDENTITY CASCADE;

-- -----------------------------------------------------------------------------
-- Geografía
-- -----------------------------------------------------------------------------
INSERT INTO pais (id_pais, nombre) VALUES
    (1, 'Paraguay');

INSERT INTO ciudad (id_ciudad, nombre, id_pais) VALUES
    (1, 'Capiatá', 1),
    (2, 'Asunción', 1),
    (3, 'San Lorenzo', 1);

-- -----------------------------------------------------------------------------
-- Roles y seguridad (referencia)
-- -----------------------------------------------------------------------------
INSERT INTO rol (id_rol, nombre) VALUES
    (1, 'ADMIN'),
    (2, 'CAJERO');

-- -----------------------------------------------------------------------------
-- Usuarios
-- -----------------------------------------------------------------------------
INSERT INTO usuario (id_usuario, username, password_hash, nombre, activo, id_rol) VALUES
    (1, 'admin', '{noop}admin123', 'Administrador General', true, 1),
    (2, 'cajero1', '{noop}cajero123', 'Sandra González', true, 2),
    (3, 'cajero2', '{noop}cajero123', 'Carlos Benítez', true, 2);

-- -----------------------------------------------------------------------------
-- Empleados
-- -----------------------------------------------------------------------------
INSERT INTO empleado (id_empleado, nombre, apellido, documento, telefono, id_ciudad, id_usuario) VALUES
    (1, 'Sandra', 'González', '4521789', '0981123456', 1, 2),
    (2, 'Carlos', 'Benítez', '3892456', '0981987654', 1, 3);

-- -----------------------------------------------------------------------------
-- Clientes
-- -----------------------------------------------------------------------------
INSERT INTO cliente (id_cliente, first_name, last_name, document_type, document_number, phone, gender, date_birth, nationality_id_pais, id_ciudad) VALUES
    (1, 'Carlos', 'Zárate', 'CI', '5978456', '0991365478', 'M', '1999-02-27', 1, 1),
    (2, 'María', 'López', 'CI', '4521789', '0981123456', 'F', '1985-06-15', 1, 2),
    (3, 'Comercial', 'San Miguel', 'RUC', '80012345-6', '0985554433', NULL, NULL, 1, 3);

-- -----------------------------------------------------------------------------
-- Proveedores
-- -----------------------------------------------------------------------------
INSERT INTO proveedor (id_proveedor, nombre) VALUES
    (1, 'Conti'),
    (2, 'Paresa'),
    (3, 'Distribuidora Central');

INSERT INTO contacto_proveedor (id_contacto_proveedor, nombre_contacto, telefono, email, id_proveedor) VALUES
    (1, 'Ventas Conti', '021555100', 'ventas@conti.com.py', 1),
    (2, 'Recepción Paresa', '021555200', 'info@paresa.com.py', 2);

-- -----------------------------------------------------------------------------
-- Categorías y Subcategorías
-- -----------------------------------------------------------------------------
INSERT INTO categoria_producto (id_categoria, nombre) VALUES
    (1, 'Comestibles'),
    (2, 'Bebidas'),
    (3, 'Limpieza'),
    (4, 'Lácteos');

INSERT INTO subcategoria_producto (id_subcategoria, nombre, id_categoria) VALUES
    (1, 'Arroz y fideos', 1),
    (2, 'Gaseosas', 2),
    (3, 'Agua', 2),
    (4, 'Leche', 4),
    (5, 'Limpieza general', 3),
    (6, 'Aceites', 1),
    (7, 'Yerba mate', 1);

-- -----------------------------------------------------------------------------
-- Unidades de medida
-- -----------------------------------------------------------------------------
INSERT INTO unidad_medida (id_unidad, nombre, abreviatura) VALUES
    (1, 'Gramos', 'gr'),
    (2, 'Litros', 'L'),
    (3, 'Kilogramos', 'Kg'),
    (4, 'Unidad', 'U'),
    (5, 'Paquete', 'pq'),
    (6, 'Caja', 'cj'),
    (7, 'Docena', 'dz');

-- -----------------------------------------------------------------------------
-- Marcas
-- -----------------------------------------------------------------------------
INSERT INTO marca (id_marca, nombre) VALUES
    (1, 'Mariela'),
    (2, 'Coca Cola'),
    (3, 'Nestlé'),
    (4, 'Procíen'),
    (5, 'Genérica');

-- -----------------------------------------------------------------------------
-- Productos (código_producto es el código de barras)
-- -----------------------------------------------------------------------------
INSERT INTO producto (id_producto, codigo_producto, nombre, descripcion, precio, stock_actual, id_subcategoria, id_unidad, id_marca, precio_compra, stock_minimo, activo) VALUES
    (1, '7841516131323', 'Arroz Mariela 500g', 'Arroz largo fino', 3500.00, 50.00, 1, 1, 1, 2800.00, 10.00, true),
    (2, '7841236547890', 'Coca Cola 1L', 'Gaseosa cola 1 litro', 12500.00, 30.00, 2, 2, 2, 9800.00, 5.00, true),
    (3, '7841254694512', 'Agua mineral 500ml', 'Agua sin gas', 4500.00, 40.00, 3, 4, 5, 3200.00, 10.00, true),
    (4, '7847415469451', 'Leche entera 1L', 'Leche pasteurizada', 8900.00, 25.00, 4, 2, 3, 7200.00, 5.00, true),
    (5, '7840001234567', 'Fideos spaghetti 500g', 'Pasta seca', 4200.00, 35.00, 1, 1, 1, 3100.00, 10.00, true),
    (6, '7849988776655', 'Detergente líquido 1L', 'Limpieza ropa', 15800.00, 15.00, 5, 2, 4, 12500.00, 3.00, true),
    (7, '7845566778899', 'Aceite de girasol 900ml', 'Aceite comestible', 11200.00, 20.00, 6, 2, 5, 8900.00, 5.00, true),
    (8, '7843344556677', 'Yerba mate 500g', 'Yerba tradicional', 9800.00, 22.00, 7, 1, 5, 7500.00, 5.00, true);

-- -----------------------------------------------------------------------------
-- Vínculo Producto-Proveedor y Costos
-- -----------------------------------------------------------------------------
INSERT INTO producto_proveedor (id_producto_proveedor, id_producto, id_proveedor, codigo_proveedor, precio_costo, activo) VALUES
    (1, 1, 1, 'CONT-ARZ-500', 2800.00, true),
    (2, 2, 1, 'CONT-COCA-1L', 9800.00, true),
    (3, 3, 2, 'PAR-AGUA-500', 3200.00, true),
    (4, 4, 2, 'PAR-LECHE-1L', 7200.00, true),
    (5, 5, 1, 'CONT-FID-500', 3100.00, true),
    (6, 6, 3, 'DC-DET-1L', 12500.00, true),
    (7, 7, 1, 'CONT-ACE-900', 8900.00, true),
    (8, 8, 2, 'PAR-YER-500', 7500.00, true);

-- -----------------------------------------------------------------------------
-- Formas de Pago
-- -----------------------------------------------------------------------------
INSERT INTO forma_pago (id_forma_pago, descripcion) VALUES
    (1, 'Efectivo'),
    (2, 'Transferencia');

-- -----------------------------------------------------------------------------
-- Rubros (categorias de proveedor)
-- -----------------------------------------------------------------------------
INSERT INTO rubro (id_rubro, nombre) VALUES
    (1, 'Alimentos y Bebidas'),
    (2, 'Limpieza'),
    (3, 'Higiene Personal'),
    (4, 'Electrónica'),
    (5, 'Ferretería'),
    (6, 'Indumentaria'),
    (7, 'Papelería'),
    (8, 'Muebles'),
    (9, 'Farmacia'),
    (10, 'Otros');

-- -----------------------------------------------------------------------------
-- Tipos de Movimiento (Caja e Inventario)
-- -----------------------------------------------------------------------------
INSERT INTO tipo_movimiento_caja (id_tipo_mov_caja, nombre) VALUES
    (1, 'INGRESO'),
    (2, 'EGRESO');

INSERT INTO tipo_movimiento (id_tipo_movimiento, nombre, descripcion) VALUES
    (1, 'COMPRA', 'Ingreso por compra a proveedor'),
    (2, 'VENTA', 'Egreso por venta a cliente'),
    (3, 'AJUSTE', 'Ajuste manual de inventario');

-- -----------------------------------------------------------------------------
-- Sincronizar secuencias
-- -----------------------------------------------------------------------------
SELECT setval('pais_id_pais_seq', (SELECT COALESCE(MAX(id_pais), 1) FROM pais));
SELECT setval('ciudad_id_ciudad_seq', (SELECT COALESCE(MAX(id_ciudad), 1) FROM ciudad));
SELECT setval('rol_id_rol_seq', (SELECT COALESCE(MAX(id_rol), 1) FROM rol));
SELECT setval('usuario_id_usuario_seq', (SELECT COALESCE(MAX(id_usuario), 1) FROM usuario));
SELECT setval('empleado_id_empleado_seq', (SELECT COALESCE(MAX(id_empleado), 1) FROM empleado));
SELECT setval('cliente_id_cliente_seq', (SELECT COALESCE(MAX(id_cliente), 1) FROM cliente));
SELECT setval('proveedor_id_proveedor_seq', (SELECT COALESCE(MAX(id_proveedor), 1) FROM proveedor));
SELECT setval('contacto_proveedor_id_contacto_proveedor_seq', (SELECT COALESCE(MAX(id_contacto_proveedor), 1) FROM contacto_proveedor));
SELECT setval('categoria_producto_id_categoria_seq', (SELECT COALESCE(MAX(id_categoria), 1) FROM categoria_producto));
SELECT setval('subcategoria_producto_id_subcategoria_seq', (SELECT COALESCE(MAX(id_subcategoria), 1) FROM subcategoria_producto));
SELECT setval('unidad_medida_id_unidad_seq', (SELECT COALESCE(MAX(id_unidad), 1) FROM unidad_medida));
SELECT setval('marca_id_marca_seq', (SELECT COALESCE(MAX(id_marca), 1) FROM marca));
SELECT setval('producto_id_producto_seq', (SELECT COALESCE(MAX(id_producto), 1) FROM producto));
SELECT setval('producto_proveedor_id_producto_proveedor_seq', (SELECT COALESCE(MAX(id_producto_proveedor), 1) FROM producto_proveedor));
SELECT setval('forma_pago_id_forma_pago_seq', (SELECT COALESCE(MAX(id_forma_pago), 1) FROM forma_pago));
SELECT setval('rubro_id_rubro_seq', (SELECT COALESCE(MAX(id_rubro), 1) FROM rubro));
SELECT setval('tipo_movimiento_caja_id_tipo_mov_caja_seq', (SELECT COALESCE(MAX(id_tipo_mov_caja), 1) FROM tipo_movimiento_caja));
SELECT setval('tipo_movimiento_id_tipo_movimiento_seq', (SELECT COALESCE(MAX(id_tipo_movimiento), 1) FROM tipo_movimiento));

COMMIT;
