ALTER TABLE public.cliente
DROP COLUMN apellido,
DROP COLUMN documento,
DROP COLUMN nombre;

ALTER TABLE public.cliente
RENAME COLUMN telefono TO phone;
