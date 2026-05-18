INSERT INTO proveedor
(id_proveedor, nombre)
VALUES(nextval('proveedor_id_proveedor_seq'::regclass), 'Conti');

INSERT INTO unidad_medida
(id_unidad, nombre, abreviatura)
VALUES(nextval('unidad_medida_id_unidad_seq'::regclass), 'Gramos', 'gr');

INSERT INTO categoria_producto
(id_categoria, nombre)
VALUES(nextval('categoria_producto_id_categoria_seq'::regclass), 'Comestibles');

