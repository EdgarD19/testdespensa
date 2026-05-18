-- Migración V3: Agregar campos de fecha de vigencia al histórico de precios
-- Propósito: Permitir saber exactamente cuándo estuvo vigente cada precio

-- Agregar columna fecha_desde (cuándo empezó a regir ese precio)
ALTER TABLE public.historial_precio_producto
ADD COLUMN fecha_desde TIMESTAMP WITHOUT TIME ZONE;

-- Agregar columna fecha_hasta (cuándo dejó de regir, NULL = vigente)
ALTER TABLE public.historial_precio_producto
ADD COLUMN fecha_hasta TIMESTAMP WITHOUT TIME ZONE;

-- Establecer fecha_desde = fecha_cambio para registros existentes
UPDATE public.historial_precio_producto
SET fecha_desde = fecha_cambio
WHERE fecha_desde IS NULL;

-- Los registros más recientes de cada producto/proveedor quedan como vigentes (fecha_hasta NULL)
-- Los registros anteriores se actualizan con fecha_hasta = fecha_desde del siguiente registro
WITH siguientes_registros AS (
    SELECT
        h1.id_historial_precio_producto,
        h2.fecha_cambio
    FROM public.historial_precio_producto h1
    LEFT JOIN LATERAL (
        SELECT fecha_cambio
        FROM public.historial_precio_producto h2
        WHERE h2.id_producto = h1.id_producto
          AND (h2.id_proveedor = h1.id_proveedor OR (h2.id_proveedor IS NULL AND h1.id_proveedor IS NULL))
          AND h2.fecha_cambio > h1.fecha_cambio
        ORDER BY h2.fecha_cambio ASC
        LIMIT 1
    ) h2 ON true
    WHERE h2.fecha_cambio IS NOT NULL
)
UPDATE public.historial_precio_producto h
SET fecha_hasta = sr.fecha_cambio
FROM siguientes_registros sr
WHERE h.id_historial_precio_producto = sr.id_historial_precio_producto;

-- Hacer fecha_desde NOT NULL (ya que todos los registros deberían tener valor)
ALTER TABLE public.historial_precio_producto
ALTER COLUMN fecha_desde SET NOT NULL;

-- Crear índice para consultas por vigencia
CREATE INDEX idx_historial_precio_vigencia
ON public.historial_precio_producto(id_producto, id_proveedor, fecha_hasta);

-- Comentario de documentación
COMMENT ON COLUMN public.historial_precio_producto.fecha_desde IS 'Fecha desde la cual este precio estuvo vigente';
COMMENT ON COLUMN public.historial_precio_producto.fecha_hasta IS 'Fecha hasta la cual este precio estuvo vigente (NULL = actualmente vigente)';
