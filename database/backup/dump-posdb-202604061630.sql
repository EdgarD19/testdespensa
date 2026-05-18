--
-- PostgreSQL database dump
--

-- Dumped from database version 16.12 (Debian 16.12-1.pgdg13+1)
-- Dumped by pg_dump version 16.2

-- Started on 2026-04-06 17:30:56

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 5 (class 2615 OID 16856)
-- Name: public; Type: SCHEMA; Schema: -; Owner: posuser
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO posuser;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 270 (class 1259 OID 17230)
-- Name: caja; Type: TABLE; Schema: public; Owner: posuser
--

CREATE TABLE public.caja (
    id_caja integer NOT NULL,
    fecha_apertura timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    fecha_cierre timestamp without time zone,
    monto_inicial numeric(12,2),
    monto_final numeric(12,2),
    estado character varying(50),
    id_empleado bigint
);


ALTER TABLE public.caja OWNER TO posuser;

--
-- TOC entry 269 (class 1259 OID 17229)
-- Name: caja_id_caja_seq; Type: SEQUENCE; Schema: public; Owner: posuser
--

CREATE SEQUENCE public.caja_id_caja_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.caja_id_caja_seq OWNER TO posuser;

--
-- TOC entry 3913 (class 0 OID 0)
-- Dependencies: 269
-- Name: caja_id_caja_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: posuser
--

ALTER SEQUENCE public.caja_id_caja_seq OWNED BY public.caja.id_caja;


--
-- TOC entry 226 (class 1259 OID 16916)
-- Name: categoria_producto; Type: TABLE; Schema: public; Owner: posuser
--

CREATE TABLE public.categoria_producto (
    id_categoria bigint NOT NULL,
    nombre character varying(100) NOT NULL
);


ALTER TABLE public.categoria_producto OWNER TO posuser;

--
-- TOC entry 225 (class 1259 OID 16915)
-- Name: categoria_producto_id_categoria_seq; Type: SEQUENCE; Schema: public; Owner: posuser
--

CREATE SEQUENCE public.categoria_producto_id_categoria_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.categoria_producto_id_categoria_seq OWNER TO posuser;

--
-- TOC entry 3914 (class 0 OID 0)
-- Dependencies: 225
-- Name: categoria_producto_id_categoria_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: posuser
--

ALTER SEQUENCE public.categoria_producto_id_categoria_seq OWNED BY public.categoria_producto.id_categoria;


--
-- TOC entry 218 (class 1259 OID 16866)
-- Name: ciudad; Type: TABLE; Schema: public; Owner: posuser
--

CREATE TABLE public.ciudad (
    id_ciudad bigint NOT NULL,
    nombre character varying(100) NOT NULL,
    id_pais bigint
);


ALTER TABLE public.ciudad OWNER TO posuser;

--
-- TOC entry 217 (class 1259 OID 16865)
-- Name: ciudad_id_ciudad_seq; Type: SEQUENCE; Schema: public; Owner: posuser
--

CREATE SEQUENCE public.ciudad_id_ciudad_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.ciudad_id_ciudad_seq OWNER TO posuser;

--
-- TOC entry 3915 (class 0 OID 0)
-- Dependencies: 217
-- Name: ciudad_id_ciudad_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: posuser
--

ALTER SEQUENCE public.ciudad_id_ciudad_seq OWNED BY public.ciudad.id_ciudad;


--
-- TOC entry 250 (class 1259 OID 17079)
-- Name: cliente; Type: TABLE; Schema: public; Owner: posuser
--

CREATE TABLE public.cliente (
    id_cliente bigint NOT NULL,
    phone character varying(30),
    id_ciudad bigint,
    document_type character varying(255),
    document_number character varying(255),
    first_name character varying(255),
    last_name character varying(255),
    date_birth timestamp(6) without time zone,
    nationality_id_pais bigint,
    gender character varying(255)
);


ALTER TABLE public.cliente OWNER TO posuser;

--
-- TOC entry 249 (class 1259 OID 17078)
-- Name: cliente_id_cliente_seq; Type: SEQUENCE; Schema: public; Owner: posuser
--

CREATE SEQUENCE public.cliente_id_cliente_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.cliente_id_cliente_seq OWNER TO posuser;

--
-- TOC entry 3916 (class 0 OID 0)
-- Dependencies: 249
-- Name: cliente_id_cliente_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: posuser
--

ALTER SEQUENCE public.cliente_id_cliente_seq OWNED BY public.cliente.id_cliente;


--
-- TOC entry 252 (class 1259 OID 17096)
-- Name: contacto_cliente; Type: TABLE; Schema: public; Owner: posuser
--

CREATE TABLE public.contacto_cliente (
    id_contacto_cliente bigint NOT NULL,
    id_cliente bigint,
    nombre_contacto character varying(100),
    telefono character varying(50),
    email character varying(100)
);


ALTER TABLE public.contacto_cliente OWNER TO posuser;

--
-- TOC entry 251 (class 1259 OID 17095)
-- Name: contacto_cliente_id_contacto_cliente_seq; Type: SEQUENCE; Schema: public; Owner: posuser
--

CREATE SEQUENCE public.contacto_cliente_id_contacto_cliente_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.contacto_cliente_id_contacto_cliente_seq OWNER TO posuser;

--
-- TOC entry 3917 (class 0 OID 0)
-- Dependencies: 251
-- Name: contacto_cliente_id_contacto_cliente_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: posuser
--

ALTER SEQUENCE public.contacto_cliente_id_contacto_cliente_seq OWNED BY public.contacto_cliente.id_contacto_cliente;


--
-- TOC entry 234 (class 1259 OID 16949)
-- Name: contacto_proveedor; Type: TABLE; Schema: public; Owner: posuser
--

CREATE TABLE public.contacto_proveedor (
    id_contacto_proveedor bigint NOT NULL,
    id_proveedor bigint,
    nombre_contacto character varying(100),
    telefono character varying(50),
    email character varying(100)
);


ALTER TABLE public.contacto_proveedor OWNER TO posuser;

--
-- TOC entry 233 (class 1259 OID 16948)
-- Name: contacto_proveedor_id_contacto_proveedor_seq; Type: SEQUENCE; Schema: public; Owner: posuser
--

CREATE SEQUENCE public.contacto_proveedor_id_contacto_proveedor_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.contacto_proveedor_id_contacto_proveedor_seq OWNER TO posuser;

--
-- TOC entry 3918 (class 0 OID 0)
-- Dependencies: 233
-- Name: contacto_proveedor_id_contacto_proveedor_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: posuser
--

ALTER SEQUENCE public.contacto_proveedor_id_contacto_proveedor_seq OWNED BY public.contacto_proveedor.id_contacto_proveedor;


--
-- TOC entry 260 (class 1259 OID 17161)
-- Name: detalle_factura; Type: TABLE; Schema: public; Owner: posuser
--

CREATE TABLE public.detalle_factura (
    id_detalle_factura bigint NOT NULL,
    id_factura bigint,
    id_producto bigint,
    cantidad numeric(10,2),
    precio_unitario numeric(10,2)
);


ALTER TABLE public.detalle_factura OWNER TO posuser;

--
-- TOC entry 266 (class 1259 OID 17198)
-- Name: detalle_factura_compra; Type: TABLE; Schema: public; Owner: posuser
--

CREATE TABLE public.detalle_factura_compra (
    id_detalle_factura_compra bigint NOT NULL,
    id_factura_compra bigint,
    id_producto bigint,
    cantidad numeric(10,2),
    precio_unitario numeric(10,2)
);


ALTER TABLE public.detalle_factura_compra OWNER TO posuser;

--
-- TOC entry 265 (class 1259 OID 17197)
-- Name: detalle_factura_compra_id_detalle_factura_compra_seq; Type: SEQUENCE; Schema: public; Owner: posuser
--

CREATE SEQUENCE public.detalle_factura_compra_id_detalle_factura_compra_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.detalle_factura_compra_id_detalle_factura_compra_seq OWNER TO posuser;

--
-- TOC entry 3919 (class 0 OID 0)
-- Dependencies: 265
-- Name: detalle_factura_compra_id_detalle_factura_compra_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: posuser
--

ALTER SEQUENCE public.detalle_factura_compra_id_detalle_factura_compra_seq OWNED BY public.detalle_factura_compra.id_detalle_factura_compra;


--
-- TOC entry 259 (class 1259 OID 17160)
-- Name: detalle_factura_id_detalle_factura_seq; Type: SEQUENCE; Schema: public; Owner: posuser
--

CREATE SEQUENCE public.detalle_factura_id_detalle_factura_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.detalle_factura_id_detalle_factura_seq OWNER TO posuser;

--
-- TOC entry 3920 (class 0 OID 0)
-- Dependencies: 259
-- Name: detalle_factura_id_detalle_factura_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: posuser
--

ALTER SEQUENCE public.detalle_factura_id_detalle_factura_seq OWNED BY public.detalle_factura.id_detalle_factura;


--
-- TOC entry 244 (class 1259 OID 17035)
-- Name: detalle_inventario; Type: TABLE; Schema: public; Owner: posuser
--

CREATE TABLE public.detalle_inventario (
    id_detalle_inventario bigint NOT NULL,
    id_inventario bigint,
    id_producto bigint,
    cantidad_sistema numeric(10,2),
    cantidad_fisica numeric(10,2),
    diferencia numeric(10,2)
);


ALTER TABLE public.detalle_inventario OWNER TO posuser;

--
-- TOC entry 243 (class 1259 OID 17034)
-- Name: detalle_inventario_id_detalle_inventario_seq; Type: SEQUENCE; Schema: public; Owner: posuser
--

CREATE SEQUENCE public.detalle_inventario_id_detalle_inventario_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.detalle_inventario_id_detalle_inventario_seq OWNER TO posuser;

--
-- TOC entry 3921 (class 0 OID 0)
-- Dependencies: 243
-- Name: detalle_inventario_id_detalle_inventario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: posuser
--

ALTER SEQUENCE public.detalle_inventario_id_detalle_inventario_seq OWNED BY public.detalle_inventario.id_detalle_inventario;


--
-- TOC entry 280 (class 1259 OID 17301)
-- Name: detalle_orden_compra; Type: TABLE; Schema: public; Owner: posuser
--

CREATE TABLE public.detalle_orden_compra (
    id_detalle_orden_compra bigint NOT NULL,
    id_orden_compra bigint NOT NULL,
    id_producto bigint NOT NULL,
    cantidad numeric(38,2) NOT NULL,
    precio_unitario numeric(38,2) NOT NULL
);


ALTER TABLE public.detalle_orden_compra OWNER TO posuser;

--
-- TOC entry 279 (class 1259 OID 17300)
-- Name: detalle_orden_compra_id_detalle_orden_compra_seq; Type: SEQUENCE; Schema: public; Owner: posuser
--

CREATE SEQUENCE public.detalle_orden_compra_id_detalle_orden_compra_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.detalle_orden_compra_id_detalle_orden_compra_seq OWNER TO posuser;

--
-- TOC entry 3922 (class 0 OID 0)
-- Dependencies: 279
-- Name: detalle_orden_compra_id_detalle_orden_compra_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: posuser
--

ALTER SEQUENCE public.detalle_orden_compra_id_detalle_orden_compra_seq OWNED BY public.detalle_orden_compra.id_detalle_orden_compra;


--
-- TOC entry 256 (class 1259 OID 17126)
-- Name: detalle_pedido; Type: TABLE; Schema: public; Owner: posuser
--

CREATE TABLE public.detalle_pedido (
    id_detalle_pedido bigint NOT NULL,
    id_pedido bigint,
    id_producto bigint,
    cantidad numeric(10,2),
    precio_unitario numeric(10,2)
);


ALTER TABLE public.detalle_pedido OWNER TO posuser;

--
-- TOC entry 255 (class 1259 OID 17125)
-- Name: detalle_pedido_id_detalle_pedido_seq; Type: SEQUENCE; Schema: public; Owner: posuser
--

CREATE SEQUENCE public.detalle_pedido_id_detalle_pedido_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.detalle_pedido_id_detalle_pedido_seq OWNER TO posuser;

--
-- TOC entry 3923 (class 0 OID 0)
-- Dependencies: 255
-- Name: detalle_pedido_id_detalle_pedido_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: posuser
--

ALTER SEQUENCE public.detalle_pedido_id_detalle_pedido_seq OWNED BY public.detalle_pedido.id_detalle_pedido;


--
-- TOC entry 222 (class 1259 OID 16887)
-- Name: empleado; Type: TABLE; Schema: public; Owner: posuser
--

CREATE TABLE public.empleado (
    id_empleado bigint NOT NULL,
    nombre character varying(100) NOT NULL,
    apellido character varying(100),
    documento character varying(20),
    telefono character varying(30),
    id_rol bigint,
    id_ciudad bigint
);


ALTER TABLE public.empleado OWNER TO posuser;

--
-- TOC entry 221 (class 1259 OID 16886)
-- Name: empleado_id_empleado_seq; Type: SEQUENCE; Schema: public; Owner: posuser
--

CREATE SEQUENCE public.empleado_id_empleado_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.empleado_id_empleado_seq OWNER TO posuser;

--
-- TOC entry 3924 (class 0 OID 0)
-- Dependencies: 221
-- Name: empleado_id_empleado_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: posuser
--

ALTER SEQUENCE public.empleado_id_empleado_seq OWNED BY public.empleado.id_empleado;


--
-- TOC entry 258 (class 1259 OID 17143)
-- Name: factura; Type: TABLE; Schema: public; Owner: posuser
--

CREATE TABLE public.factura (
    id_factura bigint NOT NULL,
    nro_factura character varying(50),
    fecha timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    id_pedido bigint,
    id_cliente bigint,
    total numeric(12,2)
);


ALTER TABLE public.factura OWNER TO posuser;

--
-- TOC entry 264 (class 1259 OID 17185)
-- Name: factura_compra; Type: TABLE; Schema: public; Owner: posuser
--

CREATE TABLE public.factura_compra (
    id_factura_compra bigint NOT NULL,
    fecha timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    id_proveedor bigint,
    total numeric(12,2)
);


ALTER TABLE public.factura_compra OWNER TO posuser;

--
-- TOC entry 263 (class 1259 OID 17184)
-- Name: factura_compra_id_factura_compra_seq; Type: SEQUENCE; Schema: public; Owner: posuser
--

CREATE SEQUENCE public.factura_compra_id_factura_compra_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.factura_compra_id_factura_compra_seq OWNER TO posuser;

--
-- TOC entry 3925 (class 0 OID 0)
-- Dependencies: 263
-- Name: factura_compra_id_factura_compra_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: posuser
--

ALTER SEQUENCE public.factura_compra_id_factura_compra_seq OWNED BY public.factura_compra.id_factura_compra;


--
-- TOC entry 257 (class 1259 OID 17142)
-- Name: factura_id_factura_seq; Type: SEQUENCE; Schema: public; Owner: posuser
--

CREATE SEQUENCE public.factura_id_factura_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.factura_id_factura_seq OWNER TO posuser;

--
-- TOC entry 3926 (class 0 OID 0)
-- Dependencies: 257
-- Name: factura_id_factura_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: posuser
--

ALTER SEQUENCE public.factura_id_factura_seq OWNED BY public.factura.id_factura;


--
-- TOC entry 262 (class 1259 OID 17178)
-- Name: forma_pago; Type: TABLE; Schema: public; Owner: posuser
--

CREATE TABLE public.forma_pago (
    id_forma_pago bigint NOT NULL,
    descripcion character varying(100)
);


ALTER TABLE public.forma_pago OWNER TO posuser;

--
-- TOC entry 261 (class 1259 OID 17177)
-- Name: forma_pago_id_forma_pago_seq; Type: SEQUENCE; Schema: public; Owner: posuser
--

CREATE SEQUENCE public.forma_pago_id_forma_pago_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.forma_pago_id_forma_pago_seq OWNER TO posuser;

--
-- TOC entry 3927 (class 0 OID 0)
-- Dependencies: 261
-- Name: forma_pago_id_forma_pago_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: posuser
--

ALTER SEQUENCE public.forma_pago_id_forma_pago_seq OWNED BY public.forma_pago.id_forma_pago;


--
-- TOC entry 240 (class 1259 OID 17001)
-- Name: historial_precio_producto; Type: TABLE; Schema: public; Owner: posuser
--

CREATE TABLE public.historial_precio_producto (
    id_historial_precio_producto bigint NOT NULL,
    id_producto bigint NOT NULL,
    id_proveedor bigint,
    precio_anterior numeric(10,2),
    precio_nuevo numeric(10,2) NOT NULL,
    precio_costo_anterior numeric(10,2),
    precio_costo_nuevo numeric(10,2),
    margen_anterior numeric(10,2),
    margen_nuevo numeric(10,2),
    margen_porcentaje_anterior numeric(5,2),
    margen_porcentaje_nuevo numeric(5,2),
    fecha_cambio timestamp without time zone DEFAULT now() NOT NULL,
    usuario_responsable character varying(100)
);


ALTER TABLE public.historial_precio_producto OWNER TO posuser;

--
-- TOC entry 239 (class 1259 OID 17000)
-- Name: historial_precio_producto_id_historial_precio_producto_seq; Type: SEQUENCE; Schema: public; Owner: posuser
--

CREATE SEQUENCE public.historial_precio_producto_id_historial_precio_producto_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.historial_precio_producto_id_historial_precio_producto_seq OWNER TO posuser;

--
-- TOC entry 3928 (class 0 OID 0)
-- Dependencies: 239
-- Name: historial_precio_producto_id_historial_precio_producto_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: posuser
--

ALTER SEQUENCE public.historial_precio_producto_id_historial_precio_producto_seq OWNED BY public.historial_precio_producto.id_historial_precio_producto;


--
-- TOC entry 242 (class 1259 OID 17019)
-- Name: inventario; Type: TABLE; Schema: public; Owner: posuser
--

CREATE TABLE public.inventario (
    id_inventario bigint NOT NULL,
    fecha date DEFAULT CURRENT_DATE NOT NULL,
    observaciones text,
    id_empleado bigint,
    ajuste_realizado boolean DEFAULT false
);


ALTER TABLE public.inventario OWNER TO posuser;

--
-- TOC entry 241 (class 1259 OID 17018)
-- Name: inventario_id_inventario_seq; Type: SEQUENCE; Schema: public; Owner: posuser
--

CREATE SEQUENCE public.inventario_id_inventario_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.inventario_id_inventario_seq OWNER TO posuser;

--
-- TOC entry 3929 (class 0 OID 0)
-- Dependencies: 241
-- Name: inventario_id_inventario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: posuser
--

ALTER SEQUENCE public.inventario_id_inventario_seq OWNED BY public.inventario.id_inventario;


--
-- TOC entry 274 (class 1259 OID 17250)
-- Name: movimiento_caja; Type: TABLE; Schema: public; Owner: posuser
--

CREATE TABLE public.movimiento_caja (
    id_mov_caja bigint NOT NULL,
    id_caja integer,
    id_tipo_mov_caja bigint,
    monto numeric(12,2),
    motivo text,
    fecha timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.movimiento_caja OWNER TO posuser;

--
-- TOC entry 273 (class 1259 OID 17249)
-- Name: movimiento_caja_id_mov_caja_seq; Type: SEQUENCE; Schema: public; Owner: posuser
--

CREATE SEQUENCE public.movimiento_caja_id_mov_caja_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.movimiento_caja_id_mov_caja_seq OWNER TO posuser;

--
-- TOC entry 3930 (class 0 OID 0)
-- Dependencies: 273
-- Name: movimiento_caja_id_mov_caja_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: posuser
--

ALTER SEQUENCE public.movimiento_caja_id_mov_caja_seq OWNED BY public.movimiento_caja.id_mov_caja;


--
-- TOC entry 248 (class 1259 OID 17059)
-- Name: movimiento_inventario; Type: TABLE; Schema: public; Owner: posuser
--

CREATE TABLE public.movimiento_inventario (
    id_movimiento bigint NOT NULL,
    id_producto bigint,
    id_tipo_movimiento bigint,
    cantidad numeric(10,2),
    fecha timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    referencia text
);


ALTER TABLE public.movimiento_inventario OWNER TO posuser;

--
-- TOC entry 247 (class 1259 OID 17058)
-- Name: movimiento_inventario_id_movimiento_seq; Type: SEQUENCE; Schema: public; Owner: posuser
--

CREATE SEQUENCE public.movimiento_inventario_id_movimiento_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.movimiento_inventario_id_movimiento_seq OWNER TO posuser;

--
-- TOC entry 3931 (class 0 OID 0)
-- Dependencies: 247
-- Name: movimiento_inventario_id_movimiento_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: posuser
--

ALTER SEQUENCE public.movimiento_inventario_id_movimiento_seq OWNED BY public.movimiento_inventario.id_movimiento;


--
-- TOC entry 278 (class 1259 OID 17280)
-- Name: orden_compra; Type: TABLE; Schema: public; Owner: posuser
--

CREATE TABLE public.orden_compra (
    id_orden_compra bigint NOT NULL,
    id_proveedor bigint NOT NULL,
    id_empleado bigint NOT NULL,
    fecha_emision date DEFAULT CURRENT_DATE NOT NULL,
    estado character varying(50) DEFAULT 'pendiente'::character varying NOT NULL,
    observaciones text
);


ALTER TABLE public.orden_compra OWNER TO posuser;

--
-- TOC entry 277 (class 1259 OID 17279)
-- Name: orden_compra_id_orden_compra_seq; Type: SEQUENCE; Schema: public; Owner: posuser
--

CREATE SEQUENCE public.orden_compra_id_orden_compra_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.orden_compra_id_orden_compra_seq OWNER TO posuser;

--
-- TOC entry 3932 (class 0 OID 0)
-- Dependencies: 277
-- Name: orden_compra_id_orden_compra_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: posuser
--

ALTER SEQUENCE public.orden_compra_id_orden_compra_seq OWNED BY public.orden_compra.id_orden_compra;


--
-- TOC entry 268 (class 1259 OID 17215)
-- Name: pago_proveedor; Type: TABLE; Schema: public; Owner: posuser
--

CREATE TABLE public.pago_proveedor (
    id_pago bigint NOT NULL,
    id_proveedor bigint,
    monto numeric(12,2),
    fecha timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    descripcion text
);


ALTER TABLE public.pago_proveedor OWNER TO posuser;

--
-- TOC entry 267 (class 1259 OID 17214)
-- Name: pago_proveedor_id_pago_seq; Type: SEQUENCE; Schema: public; Owner: posuser
--

CREATE SEQUENCE public.pago_proveedor_id_pago_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.pago_proveedor_id_pago_seq OWNER TO posuser;

--
-- TOC entry 3933 (class 0 OID 0)
-- Dependencies: 267
-- Name: pago_proveedor_id_pago_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: posuser
--

ALTER SEQUENCE public.pago_proveedor_id_pago_seq OWNED BY public.pago_proveedor.id_pago;


--
-- TOC entry 216 (class 1259 OID 16859)
-- Name: pais; Type: TABLE; Schema: public; Owner: posuser
--

CREATE TABLE public.pais (
    id_pais bigint NOT NULL,
    nombre character varying(100) NOT NULL
);


ALTER TABLE public.pais OWNER TO posuser;

--
-- TOC entry 215 (class 1259 OID 16858)
-- Name: pais_id_pais_seq; Type: SEQUENCE; Schema: public; Owner: posuser
--

CREATE SEQUENCE public.pais_id_pais_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.pais_id_pais_seq OWNER TO posuser;

--
-- TOC entry 3934 (class 0 OID 0)
-- Dependencies: 215
-- Name: pais_id_pais_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: posuser
--

ALTER SEQUENCE public.pais_id_pais_seq OWNED BY public.pais.id_pais;


--
-- TOC entry 254 (class 1259 OID 17108)
-- Name: pedido; Type: TABLE; Schema: public; Owner: posuser
--

CREATE TABLE public.pedido (
    id_pedido bigint NOT NULL,
    fecha timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    id_cliente bigint,
    id_empleado bigint,
    total numeric(12,2)
);


ALTER TABLE public.pedido OWNER TO posuser;

--
-- TOC entry 253 (class 1259 OID 17107)
-- Name: pedido_id_pedido_seq; Type: SEQUENCE; Schema: public; Owner: posuser
--

CREATE SEQUENCE public.pedido_id_pedido_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.pedido_id_pedido_seq OWNER TO posuser;

--
-- TOC entry 3935 (class 0 OID 0)
-- Dependencies: 253
-- Name: pedido_id_pedido_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: posuser
--

ALTER SEQUENCE public.pedido_id_pedido_seq OWNED BY public.pedido.id_pedido;


--
-- TOC entry 286 (class 1259 OID 17341)
-- Name: permiso; Type: TABLE; Schema: public; Owner: posuser
--

CREATE TABLE public.permiso (
    id_permiso bigint NOT NULL,
    nombre character varying(100) NOT NULL,
    descripcion text
);


ALTER TABLE public.permiso OWNER TO posuser;

--
-- TOC entry 285 (class 1259 OID 17340)
-- Name: permiso_id_permiso_seq; Type: SEQUENCE; Schema: public; Owner: posuser
--

CREATE SEQUENCE public.permiso_id_permiso_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.permiso_id_permiso_seq OWNER TO posuser;

--
-- TOC entry 3936 (class 0 OID 0)
-- Dependencies: 285
-- Name: permiso_id_permiso_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: posuser
--

ALTER SEQUENCE public.permiso_id_permiso_seq OWNED BY public.permiso.id_permiso;


--
-- TOC entry 236 (class 1259 OID 16961)
-- Name: producto; Type: TABLE; Schema: public; Owner: posuser
--

CREATE TABLE public.producto (
    id_producto bigint NOT NULL,
    nombre character varying(150) NOT NULL,
    descripcion text,
    precio numeric(10,2) NOT NULL,
    stock_actual numeric(10,2) DEFAULT 0,
    id_categoria bigint,
    id_unidad bigint,
    codigo_producto character varying(20) NOT NULL,
    CONSTRAINT chk_codigo_barras_numerico CHECK (((codigo_producto)::text ~ '^[0-9]+$'::text))
);


ALTER TABLE public.producto OWNER TO posuser;

--
-- TOC entry 235 (class 1259 OID 16960)
-- Name: producto_id_producto_seq; Type: SEQUENCE; Schema: public; Owner: posuser
--

CREATE SEQUENCE public.producto_id_producto_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.producto_id_producto_seq OWNER TO posuser;

--
-- TOC entry 3937 (class 0 OID 0)
-- Dependencies: 235
-- Name: producto_id_producto_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: posuser
--

ALTER SEQUENCE public.producto_id_producto_seq OWNED BY public.producto.id_producto;


--
-- TOC entry 238 (class 1259 OID 16981)
-- Name: producto_proveedor; Type: TABLE; Schema: public; Owner: posuser
--

CREATE TABLE public.producto_proveedor (
    id_producto_proveedor bigint NOT NULL,
    id_producto bigint NOT NULL,
    id_proveedor bigint NOT NULL,
    precio_costo numeric(10,2),
    codigo_proveedor character varying(100),
    activo boolean DEFAULT true NOT NULL
);


ALTER TABLE public.producto_proveedor OWNER TO posuser;

--
-- TOC entry 237 (class 1259 OID 16980)
-- Name: producto_proveedor_id_producto_proveedor_seq; Type: SEQUENCE; Schema: public; Owner: posuser
--

CREATE SEQUENCE public.producto_proveedor_id_producto_proveedor_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.producto_proveedor_id_producto_proveedor_seq OWNER TO posuser;

--
-- TOC entry 3938 (class 0 OID 0)
-- Dependencies: 237
-- Name: producto_proveedor_id_producto_proveedor_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: posuser
--

ALTER SEQUENCE public.producto_proveedor_id_producto_proveedor_seq OWNED BY public.producto_proveedor.id_producto_proveedor;


--
-- TOC entry 232 (class 1259 OID 16942)
-- Name: proveedor; Type: TABLE; Schema: public; Owner: posuser
--

CREATE TABLE public.proveedor (
    id_proveedor bigint NOT NULL,
    nombre character varying(150) NOT NULL
);


ALTER TABLE public.proveedor OWNER TO posuser;

--
-- TOC entry 231 (class 1259 OID 16941)
-- Name: proveedor_id_proveedor_seq; Type: SEQUENCE; Schema: public; Owner: posuser
--

CREATE SEQUENCE public.proveedor_id_proveedor_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.proveedor_id_proveedor_seq OWNER TO posuser;

--
-- TOC entry 3939 (class 0 OID 0)
-- Dependencies: 231
-- Name: proveedor_id_proveedor_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: posuser
--

ALTER SEQUENCE public.proveedor_id_proveedor_seq OWNED BY public.proveedor.id_proveedor;


--
-- TOC entry 284 (class 1259 OID 17332)
-- Name: rol; Type: TABLE; Schema: public; Owner: posuser
--

CREATE TABLE public.rol (
    id_rol bigint NOT NULL,
    nombre character varying(50) NOT NULL
);


ALTER TABLE public.rol OWNER TO posuser;

--
-- TOC entry 220 (class 1259 OID 16878)
-- Name: rol_empleado; Type: TABLE; Schema: public; Owner: posuser
--

CREATE TABLE public.rol_empleado (
    id_rol bigint NOT NULL,
    nombre character varying(100) NOT NULL,
    descripcion text
);


ALTER TABLE public.rol_empleado OWNER TO posuser;

--
-- TOC entry 219 (class 1259 OID 16877)
-- Name: rol_empleado_id_rol_seq; Type: SEQUENCE; Schema: public; Owner: posuser
--

CREATE SEQUENCE public.rol_empleado_id_rol_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.rol_empleado_id_rol_seq OWNER TO posuser;

--
-- TOC entry 3940 (class 0 OID 0)
-- Dependencies: 219
-- Name: rol_empleado_id_rol_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: posuser
--

ALTER SEQUENCE public.rol_empleado_id_rol_seq OWNED BY public.rol_empleado.id_rol;


--
-- TOC entry 283 (class 1259 OID 17331)
-- Name: rol_id_rol_seq; Type: SEQUENCE; Schema: public; Owner: posuser
--

CREATE SEQUENCE public.rol_id_rol_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.rol_id_rol_seq OWNER TO posuser;

--
-- TOC entry 3941 (class 0 OID 0)
-- Dependencies: 283
-- Name: rol_id_rol_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: posuser
--

ALTER SEQUENCE public.rol_id_rol_seq OWNED BY public.rol.id_rol;


--
-- TOC entry 288 (class 1259 OID 17366)
-- Name: rol_permiso; Type: TABLE; Schema: public; Owner: posuser
--

CREATE TABLE public.rol_permiso (
    id_rol bigint NOT NULL,
    id_permiso bigint NOT NULL
);


ALTER TABLE public.rol_permiso OWNER TO posuser;

--
-- TOC entry 289 (class 1259 OID 18296)
-- Name: rol_permiso_id_rol_seq; Type: SEQUENCE; Schema: public; Owner: posuser
--

ALTER TABLE public.rol_permiso ALTER COLUMN id_rol ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.rol_permiso_id_rol_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 228 (class 1259 OID 16923)
-- Name: subcategoria_producto; Type: TABLE; Schema: public; Owner: posuser
--

CREATE TABLE public.subcategoria_producto (
    id_subcategoria bigint NOT NULL,
    nombre character varying(100) NOT NULL,
    descripcion character varying(255),
    id_categoria bigint NOT NULL
);


ALTER TABLE public.subcategoria_producto OWNER TO posuser;

--
-- TOC entry 227 (class 1259 OID 16922)
-- Name: subcategoria_producto_id_subcategoria_seq; Type: SEQUENCE; Schema: public; Owner: posuser
--

CREATE SEQUENCE public.subcategoria_producto_id_subcategoria_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.subcategoria_producto_id_subcategoria_seq OWNER TO posuser;

--
-- TOC entry 3942 (class 0 OID 0)
-- Dependencies: 227
-- Name: subcategoria_producto_id_subcategoria_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: posuser
--

ALTER SEQUENCE public.subcategoria_producto_id_subcategoria_seq OWNED BY public.subcategoria_producto.id_subcategoria;


--
-- TOC entry 290 (class 1259 OID 18505)
-- Name: tablas; Type: TABLE; Schema: public; Owner: posuser
--

CREATE TABLE public.tablas (
);


ALTER TABLE public.tablas OWNER TO posuser;

--
-- TOC entry 291 (class 1259 OID 18508)
-- Name: tan; Type: TABLE; Schema: public; Owner: posuser
--

CREATE TABLE public.tan (
);


ALTER TABLE public.tan OWNER TO posuser;

--
-- TOC entry 246 (class 1259 OID 17052)
-- Name: tipo_movimiento; Type: TABLE; Schema: public; Owner: posuser
--

CREATE TABLE public.tipo_movimiento (
    id_tipo_movimiento bigint NOT NULL,
    nombre character varying(100) NOT NULL,
    descripcion character varying(200)
);


ALTER TABLE public.tipo_movimiento OWNER TO posuser;

--
-- TOC entry 272 (class 1259 OID 17243)
-- Name: tipo_movimiento_caja; Type: TABLE; Schema: public; Owner: posuser
--

CREATE TABLE public.tipo_movimiento_caja (
    id_tipo_mov_caja bigint NOT NULL,
    nombre character varying(100)
);


ALTER TABLE public.tipo_movimiento_caja OWNER TO posuser;

--
-- TOC entry 271 (class 1259 OID 17242)
-- Name: tipo_movimiento_caja_id_tipo_mov_caja_seq; Type: SEQUENCE; Schema: public; Owner: posuser
--

CREATE SEQUENCE public.tipo_movimiento_caja_id_tipo_mov_caja_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tipo_movimiento_caja_id_tipo_mov_caja_seq OWNER TO posuser;

--
-- TOC entry 3943 (class 0 OID 0)
-- Dependencies: 271
-- Name: tipo_movimiento_caja_id_tipo_mov_caja_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: posuser
--

ALTER SEQUENCE public.tipo_movimiento_caja_id_tipo_mov_caja_seq OWNED BY public.tipo_movimiento_caja.id_tipo_mov_caja;


--
-- TOC entry 245 (class 1259 OID 17051)
-- Name: tipo_movimiento_id_tipo_movimiento_seq; Type: SEQUENCE; Schema: public; Owner: posuser
--

CREATE SEQUENCE public.tipo_movimiento_id_tipo_movimiento_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tipo_movimiento_id_tipo_movimiento_seq OWNER TO posuser;

--
-- TOC entry 3944 (class 0 OID 0)
-- Dependencies: 245
-- Name: tipo_movimiento_id_tipo_movimiento_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: posuser
--

ALTER SEQUENCE public.tipo_movimiento_id_tipo_movimiento_seq OWNED BY public.tipo_movimiento.id_tipo_movimiento;


--
-- TOC entry 276 (class 1259 OID 17270)
-- Name: transferencia_informacion; Type: TABLE; Schema: public; Owner: posuser
--

CREATE TABLE public.transferencia_informacion (
    id_transferencia bigint NOT NULL,
    descripcion text,
    fecha timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    origen character varying(100),
    destino character varying(100)
);


ALTER TABLE public.transferencia_informacion OWNER TO posuser;

--
-- TOC entry 275 (class 1259 OID 17269)
-- Name: transferencia_informacion_id_transferencia_seq; Type: SEQUENCE; Schema: public; Owner: posuser
--

CREATE SEQUENCE public.transferencia_informacion_id_transferencia_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.transferencia_informacion_id_transferencia_seq OWNER TO posuser;

--
-- TOC entry 3945 (class 0 OID 0)
-- Dependencies: 275
-- Name: transferencia_informacion_id_transferencia_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: posuser
--

ALTER SEQUENCE public.transferencia_informacion_id_transferencia_seq OWNED BY public.transferencia_informacion.id_transferencia;


--
-- TOC entry 224 (class 1259 OID 16904)
-- Name: turno; Type: TABLE; Schema: public; Owner: posuser
--

CREATE TABLE public.turno (
    id_turno bigint NOT NULL,
    descripcion character varying(100),
    hora_inicio time without time zone,
    hora_fin time without time zone,
    id_empleado bigint
);


ALTER TABLE public.turno OWNER TO posuser;

--
-- TOC entry 223 (class 1259 OID 16903)
-- Name: turno_id_turno_seq; Type: SEQUENCE; Schema: public; Owner: posuser
--

CREATE SEQUENCE public.turno_id_turno_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.turno_id_turno_seq OWNER TO posuser;

--
-- TOC entry 3946 (class 0 OID 0)
-- Dependencies: 223
-- Name: turno_id_turno_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: posuser
--

ALTER SEQUENCE public.turno_id_turno_seq OWNED BY public.turno.id_turno;


--
-- TOC entry 230 (class 1259 OID 16935)
-- Name: unidad_medida; Type: TABLE; Schema: public; Owner: posuser
--

CREATE TABLE public.unidad_medida (
    id_unidad bigint NOT NULL,
    nombre character varying(50) NOT NULL,
    abreviatura character varying(10)
);


ALTER TABLE public.unidad_medida OWNER TO posuser;

--
-- TOC entry 229 (class 1259 OID 16934)
-- Name: unidad_medida_id_unidad_seq; Type: SEQUENCE; Schema: public; Owner: posuser
--

CREATE SEQUENCE public.unidad_medida_id_unidad_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.unidad_medida_id_unidad_seq OWNER TO posuser;

--
-- TOC entry 3947 (class 0 OID 0)
-- Dependencies: 229
-- Name: unidad_medida_id_unidad_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: posuser
--

ALTER SEQUENCE public.unidad_medida_id_unidad_seq OWNED BY public.unidad_medida.id_unidad;


--
-- TOC entry 282 (class 1259 OID 17320)
-- Name: usuario; Type: TABLE; Schema: public; Owner: posuser
--

CREATE TABLE public.usuario (
    id_usuario bigint NOT NULL,
    nombre character varying(100),
    username character varying(50) NOT NULL,
    password_hash text NOT NULL,
    activo boolean DEFAULT true NOT NULL
);


ALTER TABLE public.usuario OWNER TO posuser;

--
-- TOC entry 281 (class 1259 OID 17319)
-- Name: usuario_id_usuario_seq; Type: SEQUENCE; Schema: public; Owner: posuser
--

CREATE SEQUENCE public.usuario_id_usuario_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.usuario_id_usuario_seq OWNER TO posuser;

--
-- TOC entry 3948 (class 0 OID 0)
-- Dependencies: 281
-- Name: usuario_id_usuario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: posuser
--

ALTER SEQUENCE public.usuario_id_usuario_seq OWNED BY public.usuario.id_usuario;


--
-- TOC entry 287 (class 1259 OID 17351)
-- Name: usuario_rol; Type: TABLE; Schema: public; Owner: posuser
--

CREATE TABLE public.usuario_rol (
    id_usuario bigint NOT NULL,
    id_rol bigint NOT NULL
);


ALTER TABLE public.usuario_rol OWNER TO posuser;

--
-- TOC entry 3496 (class 2604 OID 17233)
-- Name: caja id_caja; Type: DEFAULT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.caja ALTER COLUMN id_caja SET DEFAULT nextval('public.caja_id_caja_seq'::regclass);


--
-- TOC entry 3464 (class 2604 OID 17391)
-- Name: categoria_producto id_categoria; Type: DEFAULT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.categoria_producto ALTER COLUMN id_categoria SET DEFAULT nextval('public.categoria_producto_id_categoria_seq'::regclass);


--
-- TOC entry 3460 (class 2604 OID 17408)
-- Name: ciudad id_ciudad; Type: DEFAULT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.ciudad ALTER COLUMN id_ciudad SET DEFAULT nextval('public.ciudad_id_ciudad_seq'::regclass);


--
-- TOC entry 3482 (class 2604 OID 17434)
-- Name: cliente id_cliente; Type: DEFAULT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.cliente ALTER COLUMN id_cliente SET DEFAULT nextval('public.cliente_id_cliente_seq'::regclass);


--
-- TOC entry 3483 (class 2604 OID 17484)
-- Name: contacto_cliente id_contacto_cliente; Type: DEFAULT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.contacto_cliente ALTER COLUMN id_contacto_cliente SET DEFAULT nextval('public.contacto_cliente_id_contacto_cliente_seq'::regclass);


--
-- TOC entry 3468 (class 2604 OID 17500)
-- Name: contacto_proveedor id_contacto_proveedor; Type: DEFAULT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.contacto_proveedor ALTER COLUMN id_contacto_proveedor SET DEFAULT nextval('public.contacto_proveedor_id_contacto_proveedor_seq'::regclass);


--
-- TOC entry 3489 (class 2604 OID 17516)
-- Name: detalle_factura id_detalle_factura; Type: DEFAULT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.detalle_factura ALTER COLUMN id_detalle_factura SET DEFAULT nextval('public.detalle_factura_id_detalle_factura_seq'::regclass);


--
-- TOC entry 3493 (class 2604 OID 17541)
-- Name: detalle_factura_compra id_detalle_factura_compra; Type: DEFAULT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.detalle_factura_compra ALTER COLUMN id_detalle_factura_compra SET DEFAULT nextval('public.detalle_factura_compra_id_detalle_factura_compra_seq'::regclass);


--
-- TOC entry 3478 (class 2604 OID 17566)
-- Name: detalle_inventario id_detalle_inventario; Type: DEFAULT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.detalle_inventario ALTER COLUMN id_detalle_inventario SET DEFAULT nextval('public.detalle_inventario_id_detalle_inventario_seq'::regclass);


--
-- TOC entry 3506 (class 2604 OID 17591)
-- Name: detalle_orden_compra id_detalle_orden_compra; Type: DEFAULT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.detalle_orden_compra ALTER COLUMN id_detalle_orden_compra SET DEFAULT nextval('public.detalle_orden_compra_id_detalle_orden_compra_seq'::regclass);


--
-- TOC entry 3486 (class 2604 OID 17628)
-- Name: detalle_pedido id_detalle_pedido; Type: DEFAULT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.detalle_pedido ALTER COLUMN id_detalle_pedido SET DEFAULT nextval('public.detalle_pedido_id_detalle_pedido_seq'::regclass);


--
-- TOC entry 3462 (class 2604 OID 17653)
-- Name: empleado id_empleado; Type: DEFAULT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.empleado ALTER COLUMN id_empleado SET DEFAULT nextval('public.empleado_id_empleado_seq'::regclass);


--
-- TOC entry 3487 (class 2604 OID 17703)
-- Name: factura id_factura; Type: DEFAULT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.factura ALTER COLUMN id_factura SET DEFAULT nextval('public.factura_id_factura_seq'::regclass);


--
-- TOC entry 3491 (class 2604 OID 17733)
-- Name: factura_compra id_factura_compra; Type: DEFAULT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.factura_compra ALTER COLUMN id_factura_compra SET DEFAULT nextval('public.factura_compra_id_factura_compra_seq'::regclass);


--
-- TOC entry 3490 (class 2604 OID 17754)
-- Name: forma_pago id_forma_pago; Type: DEFAULT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.forma_pago ALTER COLUMN id_forma_pago SET DEFAULT nextval('public.forma_pago_id_forma_pago_seq'::regclass);


--
-- TOC entry 3473 (class 2604 OID 17761)
-- Name: historial_precio_producto id_historial_precio_producto; Type: DEFAULT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.historial_precio_producto ALTER COLUMN id_historial_precio_producto SET DEFAULT nextval('public.historial_precio_producto_id_historial_precio_producto_seq'::regclass);


--
-- TOC entry 3475 (class 2604 OID 17786)
-- Name: inventario id_inventario; Type: DEFAULT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.inventario ALTER COLUMN id_inventario SET DEFAULT nextval('public.inventario_id_inventario_seq'::regclass);


--
-- TOC entry 3499 (class 2604 OID 17811)
-- Name: movimiento_caja id_mov_caja; Type: DEFAULT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.movimiento_caja ALTER COLUMN id_mov_caja SET DEFAULT nextval('public.movimiento_caja_id_mov_caja_seq'::regclass);


--
-- TOC entry 3480 (class 2604 OID 17831)
-- Name: movimiento_inventario id_movimiento; Type: DEFAULT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.movimiento_inventario ALTER COLUMN id_movimiento SET DEFAULT nextval('public.movimiento_inventario_id_movimiento_seq'::regclass);


--
-- TOC entry 3503 (class 2604 OID 17862)
-- Name: orden_compra id_orden_compra; Type: DEFAULT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.orden_compra ALTER COLUMN id_orden_compra SET DEFAULT nextval('public.orden_compra_id_orden_compra_seq'::regclass);


--
-- TOC entry 3494 (class 2604 OID 17898)
-- Name: pago_proveedor id_pago; Type: DEFAULT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.pago_proveedor ALTER COLUMN id_pago SET DEFAULT nextval('public.pago_proveedor_id_pago_seq'::regclass);


--
-- TOC entry 3459 (class 2604 OID 17918)
-- Name: pais id_pais; Type: DEFAULT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.pais ALTER COLUMN id_pais SET DEFAULT nextval('public.pais_id_pais_seq'::regclass);


--
-- TOC entry 3484 (class 2604 OID 17935)
-- Name: pedido id_pedido; Type: DEFAULT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.pedido ALTER COLUMN id_pedido SET DEFAULT nextval('public.pedido_id_pedido_seq'::regclass);


--
-- TOC entry 3510 (class 2604 OID 17970)
-- Name: permiso id_permiso; Type: DEFAULT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.permiso ALTER COLUMN id_permiso SET DEFAULT nextval('public.permiso_id_permiso_seq'::regclass);


--
-- TOC entry 3469 (class 2604 OID 17985)
-- Name: producto id_producto; Type: DEFAULT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.producto ALTER COLUMN id_producto SET DEFAULT nextval('public.producto_id_producto_seq'::regclass);


--
-- TOC entry 3471 (class 2604 OID 18056)
-- Name: producto_proveedor id_producto_proveedor; Type: DEFAULT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.producto_proveedor ALTER COLUMN id_producto_proveedor SET DEFAULT nextval('public.producto_proveedor_id_producto_proveedor_seq'::regclass);


--
-- TOC entry 3467 (class 2604 OID 18088)
-- Name: proveedor id_proveedor; Type: DEFAULT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.proveedor ALTER COLUMN id_proveedor SET DEFAULT nextval('public.proveedor_id_proveedor_seq'::regclass);


--
-- TOC entry 3509 (class 2604 OID 18125)
-- Name: rol id_rol; Type: DEFAULT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.rol ALTER COLUMN id_rol SET DEFAULT nextval('public.rol_id_rol_seq'::regclass);


--
-- TOC entry 3461 (class 2604 OID 18143)
-- Name: rol_empleado id_rol; Type: DEFAULT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.rol_empleado ALTER COLUMN id_rol SET DEFAULT nextval('public.rol_empleado_id_rol_seq'::regclass);


--
-- TOC entry 3465 (class 2604 OID 18179)
-- Name: subcategoria_producto id_subcategoria; Type: DEFAULT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.subcategoria_producto ALTER COLUMN id_subcategoria SET DEFAULT nextval('public.subcategoria_producto_id_subcategoria_seq'::regclass);


--
-- TOC entry 3479 (class 2604 OID 18195)
-- Name: tipo_movimiento id_tipo_movimiento; Type: DEFAULT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.tipo_movimiento ALTER COLUMN id_tipo_movimiento SET DEFAULT nextval('public.tipo_movimiento_id_tipo_movimiento_seq'::regclass);


--
-- TOC entry 3498 (class 2604 OID 18207)
-- Name: tipo_movimiento_caja id_tipo_mov_caja; Type: DEFAULT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.tipo_movimiento_caja ALTER COLUMN id_tipo_mov_caja SET DEFAULT nextval('public.tipo_movimiento_caja_id_tipo_mov_caja_seq'::regclass);


--
-- TOC entry 3501 (class 2604 OID 18219)
-- Name: transferencia_informacion id_transferencia; Type: DEFAULT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.transferencia_informacion ALTER COLUMN id_transferencia SET DEFAULT nextval('public.transferencia_informacion_id_transferencia_seq'::regclass);


--
-- TOC entry 3463 (class 2604 OID 18228)
-- Name: turno id_turno; Type: DEFAULT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.turno ALTER COLUMN id_turno SET DEFAULT nextval('public.turno_id_turno_seq'::regclass);


--
-- TOC entry 3466 (class 2604 OID 18244)
-- Name: unidad_medida id_unidad; Type: DEFAULT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.unidad_medida ALTER COLUMN id_unidad SET DEFAULT nextval('public.unidad_medida_id_unidad_seq'::regclass);


--
-- TOC entry 3507 (class 2604 OID 18256)
-- Name: usuario id_usuario; Type: DEFAULT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.usuario ALTER COLUMN id_usuario SET DEFAULT nextval('public.usuario_id_usuario_seq'::regclass);


--
-- TOC entry 3885 (class 0 OID 17230)
-- Dependencies: 270
-- Data for Name: caja; Type: TABLE DATA; Schema: public; Owner: posuser
--

COPY public.caja (id_caja, fecha_apertura, fecha_cierre, monto_inicial, monto_final, estado, id_empleado) FROM stdin;
\.


--
-- TOC entry 3841 (class 0 OID 16916)
-- Dependencies: 226
-- Data for Name: categoria_producto; Type: TABLE DATA; Schema: public; Owner: posuser
--

COPY public.categoria_producto (id_categoria, nombre) FROM stdin;
1	Comestibles
2	Bebidas
\.


--
-- TOC entry 3833 (class 0 OID 16866)
-- Dependencies: 218
-- Data for Name: ciudad; Type: TABLE DATA; Schema: public; Owner: posuser
--

COPY public.ciudad (id_ciudad, nombre, id_pais) FROM stdin;
1	Capiata	1
\.


--
-- TOC entry 3865 (class 0 OID 17079)
-- Dependencies: 250
-- Data for Name: cliente; Type: TABLE DATA; Schema: public; Owner: posuser
--

COPY public.cliente (id_cliente, phone, id_ciudad, document_type, document_number, first_name, last_name, date_birth, nationality_id_pais, gender) FROM stdin;
1	09913654789	1	CI	5978456	carlos	zarate	1999-02-27 21:00:00	1	M
\.


--
-- TOC entry 3867 (class 0 OID 17096)
-- Dependencies: 252
-- Data for Name: contacto_cliente; Type: TABLE DATA; Schema: public; Owner: posuser
--

COPY public.contacto_cliente (id_contacto_cliente, id_cliente, nombre_contacto, telefono, email) FROM stdin;
\.


--
-- TOC entry 3849 (class 0 OID 16949)
-- Dependencies: 234
-- Data for Name: contacto_proveedor; Type: TABLE DATA; Schema: public; Owner: posuser
--

COPY public.contacto_proveedor (id_contacto_proveedor, id_proveedor, nombre_contacto, telefono, email) FROM stdin;
\.


--
-- TOC entry 3875 (class 0 OID 17161)
-- Dependencies: 260
-- Data for Name: detalle_factura; Type: TABLE DATA; Schema: public; Owner: posuser
--

COPY public.detalle_factura (id_detalle_factura, id_factura, id_producto, cantidad, precio_unitario) FROM stdin;
\.


--
-- TOC entry 3881 (class 0 OID 17198)
-- Dependencies: 266
-- Data for Name: detalle_factura_compra; Type: TABLE DATA; Schema: public; Owner: posuser
--

COPY public.detalle_factura_compra (id_detalle_factura_compra, id_factura_compra, id_producto, cantidad, precio_unitario) FROM stdin;
\.


--
-- TOC entry 3859 (class 0 OID 17035)
-- Dependencies: 244
-- Data for Name: detalle_inventario; Type: TABLE DATA; Schema: public; Owner: posuser
--

COPY public.detalle_inventario (id_detalle_inventario, id_inventario, id_producto, cantidad_sistema, cantidad_fisica, diferencia) FROM stdin;
\.


--
-- TOC entry 3895 (class 0 OID 17301)
-- Dependencies: 280
-- Data for Name: detalle_orden_compra; Type: TABLE DATA; Schema: public; Owner: posuser
--

COPY public.detalle_orden_compra (id_detalle_orden_compra, id_orden_compra, id_producto, cantidad, precio_unitario) FROM stdin;
\.


--
-- TOC entry 3871 (class 0 OID 17126)
-- Dependencies: 256
-- Data for Name: detalle_pedido; Type: TABLE DATA; Schema: public; Owner: posuser
--

COPY public.detalle_pedido (id_detalle_pedido, id_pedido, id_producto, cantidad, precio_unitario) FROM stdin;
\.


--
-- TOC entry 3837 (class 0 OID 16887)
-- Dependencies: 222
-- Data for Name: empleado; Type: TABLE DATA; Schema: public; Owner: posuser
--

COPY public.empleado (id_empleado, nombre, apellido, documento, telefono, id_rol, id_ciudad) FROM stdin;
\.


--
-- TOC entry 3873 (class 0 OID 17143)
-- Dependencies: 258
-- Data for Name: factura; Type: TABLE DATA; Schema: public; Owner: posuser
--

COPY public.factura (id_factura, nro_factura, fecha, id_pedido, id_cliente, total) FROM stdin;
\.


--
-- TOC entry 3879 (class 0 OID 17185)
-- Dependencies: 264
-- Data for Name: factura_compra; Type: TABLE DATA; Schema: public; Owner: posuser
--

COPY public.factura_compra (id_factura_compra, fecha, id_proveedor, total) FROM stdin;
\.


--
-- TOC entry 3877 (class 0 OID 17178)
-- Dependencies: 262
-- Data for Name: forma_pago; Type: TABLE DATA; Schema: public; Owner: posuser
--

COPY public.forma_pago (id_forma_pago, descripcion) FROM stdin;
\.


--
-- TOC entry 3855 (class 0 OID 17001)
-- Dependencies: 240
-- Data for Name: historial_precio_producto; Type: TABLE DATA; Schema: public; Owner: posuser
--

COPY public.historial_precio_producto (id_historial_precio_producto, id_producto, id_proveedor, precio_anterior, precio_nuevo, precio_costo_anterior, precio_costo_nuevo, margen_anterior, margen_nuevo, margen_porcentaje_anterior, margen_porcentaje_nuevo, fecha_cambio, usuario_responsable) FROM stdin;
\.


--
-- TOC entry 3857 (class 0 OID 17019)
-- Dependencies: 242
-- Data for Name: inventario; Type: TABLE DATA; Schema: public; Owner: posuser
--

COPY public.inventario (id_inventario, fecha, observaciones, id_empleado, ajuste_realizado) FROM stdin;
\.


--
-- TOC entry 3889 (class 0 OID 17250)
-- Dependencies: 274
-- Data for Name: movimiento_caja; Type: TABLE DATA; Schema: public; Owner: posuser
--

COPY public.movimiento_caja (id_mov_caja, id_caja, id_tipo_mov_caja, monto, motivo, fecha) FROM stdin;
\.


--
-- TOC entry 3863 (class 0 OID 17059)
-- Dependencies: 248
-- Data for Name: movimiento_inventario; Type: TABLE DATA; Schema: public; Owner: posuser
--

COPY public.movimiento_inventario (id_movimiento, id_producto, id_tipo_movimiento, cantidad, fecha, referencia) FROM stdin;
\.


--
-- TOC entry 3893 (class 0 OID 17280)
-- Dependencies: 278
-- Data for Name: orden_compra; Type: TABLE DATA; Schema: public; Owner: posuser
--

COPY public.orden_compra (id_orden_compra, id_proveedor, id_empleado, fecha_emision, estado, observaciones) FROM stdin;
\.


--
-- TOC entry 3883 (class 0 OID 17215)
-- Dependencies: 268
-- Data for Name: pago_proveedor; Type: TABLE DATA; Schema: public; Owner: posuser
--

COPY public.pago_proveedor (id_pago, id_proveedor, monto, fecha, descripcion) FROM stdin;
\.


--
-- TOC entry 3831 (class 0 OID 16859)
-- Dependencies: 216
-- Data for Name: pais; Type: TABLE DATA; Schema: public; Owner: posuser
--

COPY public.pais (id_pais, nombre) FROM stdin;
1	Paraguay
\.


--
-- TOC entry 3869 (class 0 OID 17108)
-- Dependencies: 254
-- Data for Name: pedido; Type: TABLE DATA; Schema: public; Owner: posuser
--

COPY public.pedido (id_pedido, fecha, id_cliente, id_empleado, total) FROM stdin;
\.


--
-- TOC entry 3901 (class 0 OID 17341)
-- Dependencies: 286
-- Data for Name: permiso; Type: TABLE DATA; Schema: public; Owner: posuser
--

COPY public.permiso (id_permiso, nombre, descripcion) FROM stdin;
\.


--
-- TOC entry 3851 (class 0 OID 16961)
-- Dependencies: 236
-- Data for Name: producto; Type: TABLE DATA; Schema: public; Owner: posuser
--

COPY public.producto (id_producto, nombre, descripcion, precio, stock_actual, id_categoria, id_unidad, codigo_producto) FROM stdin;
4	Arroz Mariela 500Gr	\N	3500.00	20.00	1	1	78415161313232
3	coca	gaseosa coca cola 1L	12500.00	15.00	2	2	784123654789
6	pipa	\N	500.00	10.00	1	1	78412546945
7	prueba		1000.00	10.00	1	1	784741546945
\.


--
-- TOC entry 3853 (class 0 OID 16981)
-- Dependencies: 238
-- Data for Name: producto_proveedor; Type: TABLE DATA; Schema: public; Owner: posuser
--

COPY public.producto_proveedor (id_producto_proveedor, id_producto, id_proveedor, precio_costo, codigo_proveedor, activo) FROM stdin;
\.


--
-- TOC entry 3847 (class 0 OID 16942)
-- Dependencies: 232
-- Data for Name: proveedor; Type: TABLE DATA; Schema: public; Owner: posuser
--

COPY public.proveedor (id_proveedor, nombre) FROM stdin;
1	Conti
2	Paresa
\.


--
-- TOC entry 3899 (class 0 OID 17332)
-- Dependencies: 284
-- Data for Name: rol; Type: TABLE DATA; Schema: public; Owner: posuser
--

COPY public.rol (id_rol, nombre) FROM stdin;
\.


--
-- TOC entry 3835 (class 0 OID 16878)
-- Dependencies: 220
-- Data for Name: rol_empleado; Type: TABLE DATA; Schema: public; Owner: posuser
--

COPY public.rol_empleado (id_rol, nombre, descripcion) FROM stdin;
\.


--
-- TOC entry 3903 (class 0 OID 17366)
-- Dependencies: 288
-- Data for Name: rol_permiso; Type: TABLE DATA; Schema: public; Owner: posuser
--

COPY public.rol_permiso (id_rol, id_permiso) FROM stdin;
\.


--
-- TOC entry 3843 (class 0 OID 16923)
-- Dependencies: 228
-- Data for Name: subcategoria_producto; Type: TABLE DATA; Schema: public; Owner: posuser
--

COPY public.subcategoria_producto (id_subcategoria, nombre, descripcion, id_categoria) FROM stdin;
\.


--
-- TOC entry 3905 (class 0 OID 18505)
-- Dependencies: 290
-- Data for Name: tablas; Type: TABLE DATA; Schema: public; Owner: posuser
--

COPY public.tablas  FROM stdin;
\.


--
-- TOC entry 3906 (class 0 OID 18508)
-- Dependencies: 291
-- Data for Name: tan; Type: TABLE DATA; Schema: public; Owner: posuser
--

COPY public.tan  FROM stdin;
\.


--
-- TOC entry 3861 (class 0 OID 17052)
-- Dependencies: 246
-- Data for Name: tipo_movimiento; Type: TABLE DATA; Schema: public; Owner: posuser
--

COPY public.tipo_movimiento (id_tipo_movimiento, nombre, descripcion) FROM stdin;
\.


--
-- TOC entry 3887 (class 0 OID 17243)
-- Dependencies: 272
-- Data for Name: tipo_movimiento_caja; Type: TABLE DATA; Schema: public; Owner: posuser
--

COPY public.tipo_movimiento_caja (id_tipo_mov_caja, nombre) FROM stdin;
\.


--
-- TOC entry 3891 (class 0 OID 17270)
-- Dependencies: 276
-- Data for Name: transferencia_informacion; Type: TABLE DATA; Schema: public; Owner: posuser
--

COPY public.transferencia_informacion (id_transferencia, descripcion, fecha, origen, destino) FROM stdin;
\.


--
-- TOC entry 3839 (class 0 OID 16904)
-- Dependencies: 224
-- Data for Name: turno; Type: TABLE DATA; Schema: public; Owner: posuser
--

COPY public.turno (id_turno, descripcion, hora_inicio, hora_fin, id_empleado) FROM stdin;
\.


--
-- TOC entry 3845 (class 0 OID 16935)
-- Dependencies: 230
-- Data for Name: unidad_medida; Type: TABLE DATA; Schema: public; Owner: posuser
--

COPY public.unidad_medida (id_unidad, nombre, abreviatura) FROM stdin;
1	Gramos	gr
2	Litros	Ltrs
3	Kilogramos	Kg
4	Unidad	U
\.


--
-- TOC entry 3897 (class 0 OID 17320)
-- Dependencies: 282
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: posuser
--

COPY public.usuario (id_usuario, nombre, username, password_hash, activo) FROM stdin;
\.


--
-- TOC entry 3902 (class 0 OID 17351)
-- Dependencies: 287
-- Data for Name: usuario_rol; Type: TABLE DATA; Schema: public; Owner: posuser
--

COPY public.usuario_rol (id_usuario, id_rol) FROM stdin;
\.


--
-- TOC entry 3949 (class 0 OID 0)
-- Dependencies: 269
-- Name: caja_id_caja_seq; Type: SEQUENCE SET; Schema: public; Owner: posuser
--

SELECT pg_catalog.setval('public.caja_id_caja_seq', 1, false);


--
-- TOC entry 3950 (class 0 OID 0)
-- Dependencies: 225
-- Name: categoria_producto_id_categoria_seq; Type: SEQUENCE SET; Schema: public; Owner: posuser
--

SELECT pg_catalog.setval('public.categoria_producto_id_categoria_seq', 2, true);


--
-- TOC entry 3951 (class 0 OID 0)
-- Dependencies: 217
-- Name: ciudad_id_ciudad_seq; Type: SEQUENCE SET; Schema: public; Owner: posuser
--

SELECT pg_catalog.setval('public.ciudad_id_ciudad_seq', 1, true);


--
-- TOC entry 3952 (class 0 OID 0)
-- Dependencies: 249
-- Name: cliente_id_cliente_seq; Type: SEQUENCE SET; Schema: public; Owner: posuser
--

SELECT pg_catalog.setval('public.cliente_id_cliente_seq', 1, true);


--
-- TOC entry 3953 (class 0 OID 0)
-- Dependencies: 251
-- Name: contacto_cliente_id_contacto_cliente_seq; Type: SEQUENCE SET; Schema: public; Owner: posuser
--

SELECT pg_catalog.setval('public.contacto_cliente_id_contacto_cliente_seq', 1, false);


--
-- TOC entry 3954 (class 0 OID 0)
-- Dependencies: 233
-- Name: contacto_proveedor_id_contacto_proveedor_seq; Type: SEQUENCE SET; Schema: public; Owner: posuser
--

SELECT pg_catalog.setval('public.contacto_proveedor_id_contacto_proveedor_seq', 1, false);


--
-- TOC entry 3955 (class 0 OID 0)
-- Dependencies: 265
-- Name: detalle_factura_compra_id_detalle_factura_compra_seq; Type: SEQUENCE SET; Schema: public; Owner: posuser
--

SELECT pg_catalog.setval('public.detalle_factura_compra_id_detalle_factura_compra_seq', 1, false);


--
-- TOC entry 3956 (class 0 OID 0)
-- Dependencies: 259
-- Name: detalle_factura_id_detalle_factura_seq; Type: SEQUENCE SET; Schema: public; Owner: posuser
--

SELECT pg_catalog.setval('public.detalle_factura_id_detalle_factura_seq', 1, false);


--
-- TOC entry 3957 (class 0 OID 0)
-- Dependencies: 243
-- Name: detalle_inventario_id_detalle_inventario_seq; Type: SEQUENCE SET; Schema: public; Owner: posuser
--

SELECT pg_catalog.setval('public.detalle_inventario_id_detalle_inventario_seq', 1, false);


--
-- TOC entry 3958 (class 0 OID 0)
-- Dependencies: 279
-- Name: detalle_orden_compra_id_detalle_orden_compra_seq; Type: SEQUENCE SET; Schema: public; Owner: posuser
--

SELECT pg_catalog.setval('public.detalle_orden_compra_id_detalle_orden_compra_seq', 1, false);


--
-- TOC entry 3959 (class 0 OID 0)
-- Dependencies: 255
-- Name: detalle_pedido_id_detalle_pedido_seq; Type: SEQUENCE SET; Schema: public; Owner: posuser
--

SELECT pg_catalog.setval('public.detalle_pedido_id_detalle_pedido_seq', 1, false);


--
-- TOC entry 3960 (class 0 OID 0)
-- Dependencies: 221
-- Name: empleado_id_empleado_seq; Type: SEQUENCE SET; Schema: public; Owner: posuser
--

SELECT pg_catalog.setval('public.empleado_id_empleado_seq', 1, false);


--
-- TOC entry 3961 (class 0 OID 0)
-- Dependencies: 263
-- Name: factura_compra_id_factura_compra_seq; Type: SEQUENCE SET; Schema: public; Owner: posuser
--

SELECT pg_catalog.setval('public.factura_compra_id_factura_compra_seq', 1, false);


--
-- TOC entry 3962 (class 0 OID 0)
-- Dependencies: 257
-- Name: factura_id_factura_seq; Type: SEQUENCE SET; Schema: public; Owner: posuser
--

SELECT pg_catalog.setval('public.factura_id_factura_seq', 1, false);


--
-- TOC entry 3963 (class 0 OID 0)
-- Dependencies: 261
-- Name: forma_pago_id_forma_pago_seq; Type: SEQUENCE SET; Schema: public; Owner: posuser
--

SELECT pg_catalog.setval('public.forma_pago_id_forma_pago_seq', 1, false);


--
-- TOC entry 3964 (class 0 OID 0)
-- Dependencies: 239
-- Name: historial_precio_producto_id_historial_precio_producto_seq; Type: SEQUENCE SET; Schema: public; Owner: posuser
--

SELECT pg_catalog.setval('public.historial_precio_producto_id_historial_precio_producto_seq', 1, false);


--
-- TOC entry 3965 (class 0 OID 0)
-- Dependencies: 241
-- Name: inventario_id_inventario_seq; Type: SEQUENCE SET; Schema: public; Owner: posuser
--

SELECT pg_catalog.setval('public.inventario_id_inventario_seq', 1, false);


--
-- TOC entry 3966 (class 0 OID 0)
-- Dependencies: 273
-- Name: movimiento_caja_id_mov_caja_seq; Type: SEQUENCE SET; Schema: public; Owner: posuser
--

SELECT pg_catalog.setval('public.movimiento_caja_id_mov_caja_seq', 1, false);


--
-- TOC entry 3967 (class 0 OID 0)
-- Dependencies: 247
-- Name: movimiento_inventario_id_movimiento_seq; Type: SEQUENCE SET; Schema: public; Owner: posuser
--

SELECT pg_catalog.setval('public.movimiento_inventario_id_movimiento_seq', 1, false);


--
-- TOC entry 3968 (class 0 OID 0)
-- Dependencies: 277
-- Name: orden_compra_id_orden_compra_seq; Type: SEQUENCE SET; Schema: public; Owner: posuser
--

SELECT pg_catalog.setval('public.orden_compra_id_orden_compra_seq', 1, false);


--
-- TOC entry 3969 (class 0 OID 0)
-- Dependencies: 267
-- Name: pago_proveedor_id_pago_seq; Type: SEQUENCE SET; Schema: public; Owner: posuser
--

SELECT pg_catalog.setval('public.pago_proveedor_id_pago_seq', 1, false);


--
-- TOC entry 3970 (class 0 OID 0)
-- Dependencies: 215
-- Name: pais_id_pais_seq; Type: SEQUENCE SET; Schema: public; Owner: posuser
--

SELECT pg_catalog.setval('public.pais_id_pais_seq', 1, true);


--
-- TOC entry 3971 (class 0 OID 0)
-- Dependencies: 253
-- Name: pedido_id_pedido_seq; Type: SEQUENCE SET; Schema: public; Owner: posuser
--

SELECT pg_catalog.setval('public.pedido_id_pedido_seq', 1, false);


--
-- TOC entry 3972 (class 0 OID 0)
-- Dependencies: 285
-- Name: permiso_id_permiso_seq; Type: SEQUENCE SET; Schema: public; Owner: posuser
--

SELECT pg_catalog.setval('public.permiso_id_permiso_seq', 1, false);


--
-- TOC entry 3973 (class 0 OID 0)
-- Dependencies: 235
-- Name: producto_id_producto_seq; Type: SEQUENCE SET; Schema: public; Owner: posuser
--

SELECT pg_catalog.setval('public.producto_id_producto_seq', 8, true);


--
-- TOC entry 3974 (class 0 OID 0)
-- Dependencies: 237
-- Name: producto_proveedor_id_producto_proveedor_seq; Type: SEQUENCE SET; Schema: public; Owner: posuser
--

SELECT pg_catalog.setval('public.producto_proveedor_id_producto_proveedor_seq', 1, false);


--
-- TOC entry 3975 (class 0 OID 0)
-- Dependencies: 231
-- Name: proveedor_id_proveedor_seq; Type: SEQUENCE SET; Schema: public; Owner: posuser
--

SELECT pg_catalog.setval('public.proveedor_id_proveedor_seq', 2, true);


--
-- TOC entry 3976 (class 0 OID 0)
-- Dependencies: 219
-- Name: rol_empleado_id_rol_seq; Type: SEQUENCE SET; Schema: public; Owner: posuser
--

SELECT pg_catalog.setval('public.rol_empleado_id_rol_seq', 1, false);


--
-- TOC entry 3977 (class 0 OID 0)
-- Dependencies: 283
-- Name: rol_id_rol_seq; Type: SEQUENCE SET; Schema: public; Owner: posuser
--

SELECT pg_catalog.setval('public.rol_id_rol_seq', 1, false);


--
-- TOC entry 3978 (class 0 OID 0)
-- Dependencies: 289
-- Name: rol_permiso_id_rol_seq; Type: SEQUENCE SET; Schema: public; Owner: posuser
--

SELECT pg_catalog.setval('public.rol_permiso_id_rol_seq', 1, false);


--
-- TOC entry 3979 (class 0 OID 0)
-- Dependencies: 227
-- Name: subcategoria_producto_id_subcategoria_seq; Type: SEQUENCE SET; Schema: public; Owner: posuser
--

SELECT pg_catalog.setval('public.subcategoria_producto_id_subcategoria_seq', 1, false);


--
-- TOC entry 3980 (class 0 OID 0)
-- Dependencies: 271
-- Name: tipo_movimiento_caja_id_tipo_mov_caja_seq; Type: SEQUENCE SET; Schema: public; Owner: posuser
--

SELECT pg_catalog.setval('public.tipo_movimiento_caja_id_tipo_mov_caja_seq', 1, false);


--
-- TOC entry 3981 (class 0 OID 0)
-- Dependencies: 245
-- Name: tipo_movimiento_id_tipo_movimiento_seq; Type: SEQUENCE SET; Schema: public; Owner: posuser
--

SELECT pg_catalog.setval('public.tipo_movimiento_id_tipo_movimiento_seq', 1, false);


--
-- TOC entry 3982 (class 0 OID 0)
-- Dependencies: 275
-- Name: transferencia_informacion_id_transferencia_seq; Type: SEQUENCE SET; Schema: public; Owner: posuser
--

SELECT pg_catalog.setval('public.transferencia_informacion_id_transferencia_seq', 1, false);


--
-- TOC entry 3983 (class 0 OID 0)
-- Dependencies: 223
-- Name: turno_id_turno_seq; Type: SEQUENCE SET; Schema: public; Owner: posuser
--

SELECT pg_catalog.setval('public.turno_id_turno_seq', 1, false);


--
-- TOC entry 3984 (class 0 OID 0)
-- Dependencies: 229
-- Name: unidad_medida_id_unidad_seq; Type: SEQUENCE SET; Schema: public; Owner: posuser
--

SELECT pg_catalog.setval('public.unidad_medida_id_unidad_seq', 4, true);


--
-- TOC entry 3985 (class 0 OID 0)
-- Dependencies: 281
-- Name: usuario_id_usuario_seq; Type: SEQUENCE SET; Schema: public; Owner: posuser
--

SELECT pg_catalog.setval('public.usuario_id_usuario_seq', 1, false);


--
-- TOC entry 3571 (class 2606 OID 17236)
-- Name: caja caja_pkey; Type: CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.caja
    ADD CONSTRAINT caja_pkey PRIMARY KEY (id_caja);


--
-- TOC entry 3523 (class 2606 OID 17393)
-- Name: categoria_producto categoria_producto_pkey; Type: CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.categoria_producto
    ADD CONSTRAINT categoria_producto_pkey PRIMARY KEY (id_categoria);


--
-- TOC entry 3515 (class 2606 OID 17410)
-- Name: ciudad ciudad_pkey; Type: CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.ciudad
    ADD CONSTRAINT ciudad_pkey PRIMARY KEY (id_ciudad);


--
-- TOC entry 3551 (class 2606 OID 17436)
-- Name: cliente cliente_pkey; Type: CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (id_cliente);


--
-- TOC entry 3553 (class 2606 OID 17486)
-- Name: contacto_cliente contacto_cliente_pkey; Type: CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.contacto_cliente
    ADD CONSTRAINT contacto_cliente_pkey PRIMARY KEY (id_contacto_cliente);


--
-- TOC entry 3531 (class 2606 OID 17502)
-- Name: contacto_proveedor contacto_proveedor_pkey; Type: CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.contacto_proveedor
    ADD CONSTRAINT contacto_proveedor_pkey PRIMARY KEY (id_contacto_proveedor);


--
-- TOC entry 3567 (class 2606 OID 17543)
-- Name: detalle_factura_compra detalle_factura_compra_pkey; Type: CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.detalle_factura_compra
    ADD CONSTRAINT detalle_factura_compra_pkey PRIMARY KEY (id_detalle_factura_compra);


--
-- TOC entry 3561 (class 2606 OID 17518)
-- Name: detalle_factura detalle_factura_pkey; Type: CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.detalle_factura
    ADD CONSTRAINT detalle_factura_pkey PRIMARY KEY (id_detalle_factura);


--
-- TOC entry 3545 (class 2606 OID 17568)
-- Name: detalle_inventario detalle_inventario_pkey; Type: CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.detalle_inventario
    ADD CONSTRAINT detalle_inventario_pkey PRIMARY KEY (id_detalle_inventario);


--
-- TOC entry 3581 (class 2606 OID 17593)
-- Name: detalle_orden_compra detalle_orden_compra_pkey; Type: CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.detalle_orden_compra
    ADD CONSTRAINT detalle_orden_compra_pkey PRIMARY KEY (id_detalle_orden_compra);


--
-- TOC entry 3557 (class 2606 OID 17630)
-- Name: detalle_pedido detalle_pedido_pkey; Type: CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.detalle_pedido
    ADD CONSTRAINT detalle_pedido_pkey PRIMARY KEY (id_detalle_pedido);


--
-- TOC entry 3519 (class 2606 OID 17655)
-- Name: empleado empleado_pkey; Type: CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.empleado
    ADD CONSTRAINT empleado_pkey PRIMARY KEY (id_empleado);


--
-- TOC entry 3565 (class 2606 OID 17735)
-- Name: factura_compra factura_compra_pkey; Type: CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.factura_compra
    ADD CONSTRAINT factura_compra_pkey PRIMARY KEY (id_factura_compra);


--
-- TOC entry 3559 (class 2606 OID 17705)
-- Name: factura factura_pkey; Type: CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.factura
    ADD CONSTRAINT factura_pkey PRIMARY KEY (id_factura);


--
-- TOC entry 3563 (class 2606 OID 17756)
-- Name: forma_pago forma_pago_pkey; Type: CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.forma_pago
    ADD CONSTRAINT forma_pago_pkey PRIMARY KEY (id_forma_pago);


--
-- TOC entry 3541 (class 2606 OID 17763)
-- Name: historial_precio_producto historial_precio_producto_pkey; Type: CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.historial_precio_producto
    ADD CONSTRAINT historial_precio_producto_pkey PRIMARY KEY (id_historial_precio_producto);


--
-- TOC entry 3543 (class 2606 OID 17788)
-- Name: inventario inventario_pkey; Type: CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.inventario
    ADD CONSTRAINT inventario_pkey PRIMARY KEY (id_inventario);


--
-- TOC entry 3575 (class 2606 OID 17813)
-- Name: movimiento_caja movimiento_caja_pkey; Type: CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.movimiento_caja
    ADD CONSTRAINT movimiento_caja_pkey PRIMARY KEY (id_mov_caja);


--
-- TOC entry 3549 (class 2606 OID 17833)
-- Name: movimiento_inventario movimiento_inventario_pkey; Type: CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.movimiento_inventario
    ADD CONSTRAINT movimiento_inventario_pkey PRIMARY KEY (id_movimiento);


--
-- TOC entry 3579 (class 2606 OID 17864)
-- Name: orden_compra orden_compra_pkey; Type: CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.orden_compra
    ADD CONSTRAINT orden_compra_pkey PRIMARY KEY (id_orden_compra);


--
-- TOC entry 3569 (class 2606 OID 17900)
-- Name: pago_proveedor pago_proveedor_pkey; Type: CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.pago_proveedor
    ADD CONSTRAINT pago_proveedor_pkey PRIMARY KEY (id_pago);


--
-- TOC entry 3513 (class 2606 OID 17920)
-- Name: pais pais_pkey; Type: CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.pais
    ADD CONSTRAINT pais_pkey PRIMARY KEY (id_pais);


--
-- TOC entry 3555 (class 2606 OID 17937)
-- Name: pedido pedido_pkey; Type: CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.pedido
    ADD CONSTRAINT pedido_pkey PRIMARY KEY (id_pedido);


--
-- TOC entry 3595 (class 2606 OID 17350)
-- Name: permiso permiso_nombre_key; Type: CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.permiso
    ADD CONSTRAINT permiso_nombre_key UNIQUE (nombre);


--
-- TOC entry 3597 (class 2606 OID 17972)
-- Name: permiso permiso_pkey; Type: CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.permiso
    ADD CONSTRAINT permiso_pkey PRIMARY KEY (id_permiso);


--
-- TOC entry 3533 (class 2606 OID 17987)
-- Name: producto producto_pkey; Type: CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.producto
    ADD CONSTRAINT producto_pkey PRIMARY KEY (id_producto);


--
-- TOC entry 3537 (class 2606 OID 18058)
-- Name: producto_proveedor producto_proveedor_pkey; Type: CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.producto_proveedor
    ADD CONSTRAINT producto_proveedor_pkey PRIMARY KEY (id_producto_proveedor);


--
-- TOC entry 3529 (class 2606 OID 18090)
-- Name: proveedor proveedor_pkey; Type: CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.proveedor
    ADD CONSTRAINT proveedor_pkey PRIMARY KEY (id_proveedor);


--
-- TOC entry 3517 (class 2606 OID 18145)
-- Name: rol_empleado rol_empleado_pkey; Type: CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.rol_empleado
    ADD CONSTRAINT rol_empleado_pkey PRIMARY KEY (id_rol);


--
-- TOC entry 3589 (class 2606 OID 17339)
-- Name: rol rol_nombre_key; Type: CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.rol
    ADD CONSTRAINT rol_nombre_key UNIQUE (nombre);


--
-- TOC entry 3603 (class 2606 OID 18169)
-- Name: rol_permiso rol_permiso_pkey; Type: CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.rol_permiso
    ADD CONSTRAINT rol_permiso_pkey PRIMARY KEY (id_rol, id_permiso);


--
-- TOC entry 3591 (class 2606 OID 18127)
-- Name: rol rol_pkey; Type: CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.rol
    ADD CONSTRAINT rol_pkey PRIMARY KEY (id_rol);


--
-- TOC entry 3525 (class 2606 OID 18181)
-- Name: subcategoria_producto subcategoria_producto_pkey; Type: CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.subcategoria_producto
    ADD CONSTRAINT subcategoria_producto_pkey PRIMARY KEY (id_subcategoria);


--
-- TOC entry 3573 (class 2606 OID 18209)
-- Name: tipo_movimiento_caja tipo_movimiento_caja_pkey; Type: CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.tipo_movimiento_caja
    ADD CONSTRAINT tipo_movimiento_caja_pkey PRIMARY KEY (id_tipo_mov_caja);


--
-- TOC entry 3547 (class 2606 OID 18197)
-- Name: tipo_movimiento tipo_movimiento_pkey; Type: CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.tipo_movimiento
    ADD CONSTRAINT tipo_movimiento_pkey PRIMARY KEY (id_tipo_movimiento);


--
-- TOC entry 3577 (class 2606 OID 18221)
-- Name: transferencia_informacion transferencia_informacion_pkey; Type: CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.transferencia_informacion
    ADD CONSTRAINT transferencia_informacion_pkey PRIMARY KEY (id_transferencia);


--
-- TOC entry 3521 (class 2606 OID 18230)
-- Name: turno turno_pkey; Type: CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.turno
    ADD CONSTRAINT turno_pkey PRIMARY KEY (id_turno);


--
-- TOC entry 3593 (class 2606 OID 18298)
-- Name: rol uk43kr6s7bts1wqfv43f7jd87kp; Type: CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.rol
    ADD CONSTRAINT uk43kr6s7bts1wqfv43f7jd87kp UNIQUE (nombre);


--
-- TOC entry 3583 (class 2606 OID 18300)
-- Name: usuario uk863n1y3x0jalatoir4325ehal; Type: CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT uk863n1y3x0jalatoir4325ehal UNIQUE (username);


--
-- TOC entry 3599 (class 2606 OID 18302)
-- Name: permiso uknwe6lkk7x7sbw94xcmbwgvycu; Type: CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.permiso
    ADD CONSTRAINT uknwe6lkk7x7sbw94xcmbwgvycu UNIQUE (nombre);


--
-- TOC entry 3535 (class 2606 OID 18294)
-- Name: producto ukprg65xyo6blunyqxq80xaqhlo; Type: CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.producto
    ADD CONSTRAINT ukprg65xyo6blunyqxq80xaqhlo UNIQUE (codigo_producto);


--
-- TOC entry 3527 (class 2606 OID 18246)
-- Name: unidad_medida unidad_medida_pkey; Type: CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.unidad_medida
    ADD CONSTRAINT unidad_medida_pkey PRIMARY KEY (id_unidad);


--
-- TOC entry 3539 (class 2606 OID 18077)
-- Name: producto_proveedor uq_producto_proveedor; Type: CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.producto_proveedor
    ADD CONSTRAINT uq_producto_proveedor UNIQUE (id_producto, id_proveedor);


--
-- TOC entry 3585 (class 2606 OID 18258)
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id_usuario);


--
-- TOC entry 3601 (class 2606 OID 18283)
-- Name: usuario_rol usuario_rol_pkey; Type: CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.usuario_rol
    ADD CONSTRAINT usuario_rol_pkey PRIMARY KEY (id_usuario, id_rol);


--
-- TOC entry 3587 (class 2606 OID 17330)
-- Name: usuario usuario_username_key; Type: CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_username_key UNIQUE (username);


--
-- TOC entry 3665 (class 2606 OID 17676)
-- Name: caja caja_id_empleado_fkey; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.caja
    ADD CONSTRAINT caja_id_empleado_fkey FOREIGN KEY (id_empleado) REFERENCES public.empleado(id_empleado);


--
-- TOC entry 3604 (class 2606 OID 17921)
-- Name: ciudad ciudad_id_pais_fkey; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.ciudad
    ADD CONSTRAINT ciudad_id_pais_fkey FOREIGN KEY (id_pais) REFERENCES public.pais(id_pais);


--
-- TOC entry 3635 (class 2606 OID 17473)
-- Name: cliente cliente_id_ciudad_fkey; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_id_ciudad_fkey FOREIGN KEY (id_ciudad) REFERENCES public.ciudad(id_ciudad);


--
-- TOC entry 3636 (class 2606 OID 17926)
-- Name: cliente cliente_nationality_id_pais_fkey; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_nationality_id_pais_fkey FOREIGN KEY (nationality_id_pais) REFERENCES public.pais(id_pais);


--
-- TOC entry 3639 (class 2606 OID 17491)
-- Name: contacto_cliente contacto_cliente_id_cliente_fkey; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.contacto_cliente
    ADD CONSTRAINT contacto_cliente_id_cliente_fkey FOREIGN KEY (id_cliente) REFERENCES public.cliente(id_cliente);


--
-- TOC entry 3614 (class 2606 OID 18091)
-- Name: contacto_proveedor contacto_proveedor_id_proveedor_fkey; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.contacto_proveedor
    ADD CONSTRAINT contacto_proveedor_id_proveedor_fkey FOREIGN KEY (id_proveedor) REFERENCES public.proveedor(id_proveedor);


--
-- TOC entry 3659 (class 2606 OID 17736)
-- Name: detalle_factura_compra detalle_factura_compra_id_factura_compra_fkey; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.detalle_factura_compra
    ADD CONSTRAINT detalle_factura_compra_id_factura_compra_fkey FOREIGN KEY (id_factura_compra) REFERENCES public.factura_compra(id_factura_compra);


--
-- TOC entry 3660 (class 2606 OID 17998)
-- Name: detalle_factura_compra detalle_factura_compra_id_producto_fkey; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.detalle_factura_compra
    ADD CONSTRAINT detalle_factura_compra_id_producto_fkey FOREIGN KEY (id_producto) REFERENCES public.producto(id_producto);


--
-- TOC entry 3653 (class 2606 OID 17706)
-- Name: detalle_factura detalle_factura_id_factura_fkey; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.detalle_factura
    ADD CONSTRAINT detalle_factura_id_factura_fkey FOREIGN KEY (id_factura) REFERENCES public.factura(id_factura);


--
-- TOC entry 3654 (class 2606 OID 17993)
-- Name: detalle_factura detalle_factura_id_producto_fkey; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.detalle_factura
    ADD CONSTRAINT detalle_factura_id_producto_fkey FOREIGN KEY (id_producto) REFERENCES public.producto(id_producto);


--
-- TOC entry 3627 (class 2606 OID 17789)
-- Name: detalle_inventario detalle_inventario_id_inventario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.detalle_inventario
    ADD CONSTRAINT detalle_inventario_id_inventario_fkey FOREIGN KEY (id_inventario) REFERENCES public.inventario(id_inventario);


--
-- TOC entry 3628 (class 2606 OID 18003)
-- Name: detalle_inventario detalle_inventario_id_producto_fkey; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.detalle_inventario
    ADD CONSTRAINT detalle_inventario_id_producto_fkey FOREIGN KEY (id_producto) REFERENCES public.producto(id_producto);


--
-- TOC entry 3675 (class 2606 OID 17865)
-- Name: detalle_orden_compra detalle_orden_compra_id_orden_compra_fkey; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.detalle_orden_compra
    ADD CONSTRAINT detalle_orden_compra_id_orden_compra_fkey FOREIGN KEY (id_orden_compra) REFERENCES public.orden_compra(id_orden_compra);


--
-- TOC entry 3676 (class 2606 OID 18008)
-- Name: detalle_orden_compra detalle_orden_compra_id_producto_fkey; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.detalle_orden_compra
    ADD CONSTRAINT detalle_orden_compra_id_producto_fkey FOREIGN KEY (id_producto) REFERENCES public.producto(id_producto);


--
-- TOC entry 3645 (class 2606 OID 17938)
-- Name: detalle_pedido detalle_pedido_id_pedido_fkey; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.detalle_pedido
    ADD CONSTRAINT detalle_pedido_id_pedido_fkey FOREIGN KEY (id_pedido) REFERENCES public.pedido(id_pedido);


--
-- TOC entry 3646 (class 2606 OID 18013)
-- Name: detalle_pedido detalle_pedido_id_producto_fkey; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.detalle_pedido
    ADD CONSTRAINT detalle_pedido_id_producto_fkey FOREIGN KEY (id_producto) REFERENCES public.producto(id_producto);


--
-- TOC entry 3606 (class 2606 OID 17685)
-- Name: empleado empleado_id_ciudad_fkey; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.empleado
    ADD CONSTRAINT empleado_id_ciudad_fkey FOREIGN KEY (id_ciudad) REFERENCES public.ciudad(id_ciudad);


--
-- TOC entry 3607 (class 2606 OID 18146)
-- Name: empleado empleado_id_rol_fkey; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.empleado
    ADD CONSTRAINT empleado_id_rol_fkey FOREIGN KEY (id_rol) REFERENCES public.rol_empleado(id_rol);


--
-- TOC entry 3657 (class 2606 OID 18096)
-- Name: factura_compra factura_compra_id_proveedor_fkey; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.factura_compra
    ADD CONSTRAINT factura_compra_id_proveedor_fkey FOREIGN KEY (id_proveedor) REFERENCES public.proveedor(id_proveedor);


--
-- TOC entry 3649 (class 2606 OID 17715)
-- Name: factura factura_id_cliente_fkey; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.factura
    ADD CONSTRAINT factura_id_cliente_fkey FOREIGN KEY (id_cliente) REFERENCES public.cliente(id_cliente);


--
-- TOC entry 3650 (class 2606 OID 17943)
-- Name: factura factura_id_pedido_fkey; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.factura
    ADD CONSTRAINT factura_id_pedido_fkey FOREIGN KEY (id_pedido) REFERENCES public.pedido(id_pedido);


--
-- TOC entry 3661 (class 2606 OID 18303)
-- Name: detalle_factura_compra fk1lxqiyipo5lckh05803rb9l0v; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.detalle_factura_compra
    ADD CONSTRAINT fk1lxqiyipo5lckh05803rb9l0v FOREIGN KEY (id_producto) REFERENCES public.producto(id_producto);


--
-- TOC entry 3658 (class 2606 OID 18308)
-- Name: factura_compra fk1vcex6riw9nnov5jje9b8wnjr; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.factura_compra
    ADD CONSTRAINT fk1vcex6riw9nnov5jje9b8wnjr FOREIGN KEY (id_proveedor) REFERENCES public.proveedor(id_proveedor);


--
-- TOC entry 3666 (class 2606 OID 18313)
-- Name: caja fk1yfvnv9pdanq5r2u2sknfn0as; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.caja
    ADD CONSTRAINT fk1yfvnv9pdanq5r2u2sknfn0as FOREIGN KEY (id_empleado) REFERENCES public.empleado(id_empleado);


--
-- TOC entry 3662 (class 2606 OID 18318)
-- Name: detalle_factura_compra fk2fnd14ep5ada0evxn25mxi1ko; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.detalle_factura_compra
    ADD CONSTRAINT fk2fnd14ep5ada0evxn25mxi1ko FOREIGN KEY (id_factura_compra) REFERENCES public.factura_compra(id_factura_compra);


--
-- TOC entry 3608 (class 2606 OID 18323)
-- Name: empleado fk32iir1s9ju6883156bp2n4b0x; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.empleado
    ADD CONSTRAINT fk32iir1s9ju6883156bp2n4b0x FOREIGN KEY (id_ciudad) REFERENCES public.ciudad(id_ciudad);


--
-- TOC entry 3679 (class 2606 OID 18328)
-- Name: usuario_rol fk3ftpt75ebughsiy5g03b11akt; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.usuario_rol
    ADD CONSTRAINT fk3ftpt75ebughsiy5g03b11akt FOREIGN KEY (id_usuario) REFERENCES public.usuario(id_usuario);


--
-- TOC entry 3616 (class 2606 OID 18333)
-- Name: producto fk3p043787h0xuxlysix3m276bt; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.producto
    ADD CONSTRAINT fk3p043787h0xuxlysix3m276bt FOREIGN KEY (id_categoria) REFERENCES public.categoria_producto(id_categoria);


--
-- TOC entry 3617 (class 2606 OID 18338)
-- Name: producto fk40xg6hxacskw94ff60dw82r77; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.producto
    ADD CONSTRAINT fk40xg6hxacskw94ff60dw82r77 FOREIGN KEY (id_unidad) REFERENCES public.unidad_medida(id_unidad);


--
-- TOC entry 3671 (class 2606 OID 18343)
-- Name: orden_compra fk61e8h7c7gw7dahyyixbf7ltho; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.orden_compra
    ADD CONSTRAINT fk61e8h7c7gw7dahyyixbf7ltho FOREIGN KEY (id_empleado) REFERENCES public.empleado(id_empleado);


--
-- TOC entry 3615 (class 2606 OID 18348)
-- Name: contacto_proveedor fk7jgh0fa8mvtmlcdhe8qtcvqrn; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.contacto_proveedor
    ADD CONSTRAINT fk7jgh0fa8mvtmlcdhe8qtcvqrn FOREIGN KEY (id_proveedor) REFERENCES public.proveedor(id_proveedor);


--
-- TOC entry 3647 (class 2606 OID 18353)
-- Name: detalle_pedido fk7n9hdifr08joboojejveby1vr; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.detalle_pedido
    ADD CONSTRAINT fk7n9hdifr08joboojejveby1vr FOREIGN KEY (id_pedido) REFERENCES public.pedido(id_pedido);


--
-- TOC entry 3629 (class 2606 OID 18358)
-- Name: detalle_inventario fk85huo6ft82eo2b2u7u9qopjfe; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.detalle_inventario
    ADD CONSTRAINT fk85huo6ft82eo2b2u7u9qopjfe FOREIGN KEY (id_producto) REFERENCES public.producto(id_producto);


--
-- TOC entry 3612 (class 2606 OID 18363)
-- Name: subcategoria_producto fk86k8to0r8ecgq4ieo2tr1amkf; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.subcategoria_producto
    ADD CONSTRAINT fk86k8to0r8ecgq4ieo2tr1amkf FOREIGN KEY (id_categoria) REFERENCES public.categoria_producto(id_categoria);


--
-- TOC entry 3641 (class 2606 OID 18368)
-- Name: pedido fk9y4jnyp1hxqa386cnly0ay9uw; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.pedido
    ADD CONSTRAINT fk9y4jnyp1hxqa386cnly0ay9uw FOREIGN KEY (id_cliente) REFERENCES public.cliente(id_cliente);


--
-- TOC entry 3622 (class 2606 OID 18018)
-- Name: historial_precio_producto fk_historial_producto; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.historial_precio_producto
    ADD CONSTRAINT fk_historial_producto FOREIGN KEY (id_producto) REFERENCES public.producto(id_producto) ON DELETE CASCADE;


--
-- TOC entry 3623 (class 2606 OID 18101)
-- Name: historial_precio_producto fk_historial_proveedor; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.historial_precio_producto
    ADD CONSTRAINT fk_historial_proveedor FOREIGN KEY (id_proveedor) REFERENCES public.proveedor(id_proveedor) ON DELETE SET NULL;


--
-- TOC entry 3613 (class 2606 OID 18186)
-- Name: subcategoria_producto fk_subcategoria_categoria; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.subcategoria_producto
    ADD CONSTRAINT fk_subcategoria_categoria FOREIGN KEY (id_categoria) REFERENCES public.categoria_producto(id_categoria);


--
-- TOC entry 3631 (class 2606 OID 18373)
-- Name: movimiento_inventario fkbjxhsg43krfbchjotdasekqi; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.movimiento_inventario
    ADD CONSTRAINT fkbjxhsg43krfbchjotdasekqi FOREIGN KEY (id_tipo_movimiento) REFERENCES public.tipo_movimiento(id_tipo_movimiento);


--
-- TOC entry 3655 (class 2606 OID 18378)
-- Name: detalle_factura fkbtcmmj5awxvxq2gj65kx1v7ly; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.detalle_factura
    ADD CONSTRAINT fkbtcmmj5awxvxq2gj65kx1v7ly FOREIGN KEY (id_producto) REFERENCES public.producto(id_producto);


--
-- TOC entry 3677 (class 2606 OID 18383)
-- Name: detalle_orden_compra fkcnt8ik8ono53rnnqmo6fuhh0h; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.detalle_orden_compra
    ADD CONSTRAINT fkcnt8ik8ono53rnnqmo6fuhh0h FOREIGN KEY (id_orden_compra) REFERENCES public.orden_compra(id_orden_compra);


--
-- TOC entry 3637 (class 2606 OID 18388)
-- Name: cliente fkddv8tueu64g1r8qjt8bpeu5qv; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT fkddv8tueu64g1r8qjt8bpeu5qv FOREIGN KEY (nationality_id_pais) REFERENCES public.pais(id_pais);


--
-- TOC entry 3630 (class 2606 OID 18393)
-- Name: detalle_inventario fkf5xtgaoqmrglydwfiaae2wkuw; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.detalle_inventario
    ADD CONSTRAINT fkf5xtgaoqmrglydwfiaae2wkuw FOREIGN KEY (id_inventario) REFERENCES public.inventario(id_inventario);


--
-- TOC entry 3640 (class 2606 OID 18398)
-- Name: contacto_cliente fkf6faiqbvy36nka0vdmqkctg1l; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.contacto_cliente
    ADD CONSTRAINT fkf6faiqbvy36nka0vdmqkctg1l FOREIGN KEY (id_cliente) REFERENCES public.cliente(id_cliente);


--
-- TOC entry 3638 (class 2606 OID 18403)
-- Name: cliente fkgmoeiwcrhhxkicpiuldlmhn0x; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT fkgmoeiwcrhhxkicpiuldlmhn0x FOREIGN KEY (id_ciudad) REFERENCES public.ciudad(id_ciudad);


--
-- TOC entry 3605 (class 2606 OID 18408)
-- Name: ciudad fkh96uk5etvw529uc18kdjhvdq9; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.ciudad
    ADD CONSTRAINT fkh96uk5etvw529uc18kdjhvdq9 FOREIGN KEY (id_pais) REFERENCES public.pais(id_pais);


--
-- TOC entry 3656 (class 2606 OID 18413)
-- Name: detalle_factura fkhc4e7swi6aa3e6uom3bwm2x6x; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.detalle_factura
    ADD CONSTRAINT fkhc4e7swi6aa3e6uom3bwm2x6x FOREIGN KEY (id_factura) REFERENCES public.factura(id_factura);


--
-- TOC entry 3678 (class 2606 OID 18418)
-- Name: detalle_orden_compra fkhcx4m5eplu6q1lht0o4y0ht2f; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.detalle_orden_compra
    ADD CONSTRAINT fkhcx4m5eplu6q1lht0o4y0ht2f FOREIGN KEY (id_producto) REFERENCES public.producto(id_producto);


--
-- TOC entry 3651 (class 2606 OID 18423)
-- Name: factura fkhf4qd2e07en591ymcp74t1ebj; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.factura
    ADD CONSTRAINT fkhf4qd2e07en591ymcp74t1ebj FOREIGN KEY (id_pedido) REFERENCES public.pedido(id_pedido);


--
-- TOC entry 3667 (class 2606 OID 18428)
-- Name: movimiento_caja fkhw08ftkmr79jqg9wsv7rrptqd; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.movimiento_caja
    ADD CONSTRAINT fkhw08ftkmr79jqg9wsv7rrptqd FOREIGN KEY (id_caja) REFERENCES public.caja(id_caja);


--
-- TOC entry 3610 (class 2606 OID 18433)
-- Name: turno fkhyyo0e4asmovio3x8jmma8vbi; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.turno
    ADD CONSTRAINT fkhyyo0e4asmovio3x8jmma8vbi FOREIGN KEY (id_empleado) REFERENCES public.empleado(id_empleado);


--
-- TOC entry 3668 (class 2606 OID 18438)
-- Name: movimiento_caja fkio4o2yhwk9ptlb0v020va45ng; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.movimiento_caja
    ADD CONSTRAINT fkio4o2yhwk9ptlb0v020va45ng FOREIGN KEY (id_tipo_mov_caja) REFERENCES public.tipo_movimiento_caja(id_tipo_mov_caja);


--
-- TOC entry 3648 (class 2606 OID 18443)
-- Name: detalle_pedido fkjfm9pk0w2eag8tx8lu6pbego6; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.detalle_pedido
    ADD CONSTRAINT fkjfm9pk0w2eag8tx8lu6pbego6 FOREIGN KEY (id_producto) REFERENCES public.producto(id_producto);


--
-- TOC entry 3680 (class 2606 OID 18448)
-- Name: usuario_rol fkkxcv7htfnm9x1wkofnud0ewql; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.usuario_rol
    ADD CONSTRAINT fkkxcv7htfnm9x1wkofnud0ewql FOREIGN KEY (id_rol) REFERENCES public.rol(id_rol);


--
-- TOC entry 3625 (class 2606 OID 18453)
-- Name: inventario fkldr1ejc7iue77wr5qnsgaj7d2; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.inventario
    ADD CONSTRAINT fkldr1ejc7iue77wr5qnsgaj7d2 FOREIGN KEY (id_empleado) REFERENCES public.empleado(id_empleado);


--
-- TOC entry 3609 (class 2606 OID 18458)
-- Name: empleado fklftw457251pnh2wpiw8pn1md0; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.empleado
    ADD CONSTRAINT fklftw457251pnh2wpiw8pn1md0 FOREIGN KEY (id_rol) REFERENCES public.rol_empleado(id_rol);


--
-- TOC entry 3632 (class 2606 OID 18463)
-- Name: movimiento_inventario fklkde1yc6teblf62ro9slw9qdw; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.movimiento_inventario
    ADD CONSTRAINT fklkde1yc6teblf62ro9slw9qdw FOREIGN KEY (id_producto) REFERENCES public.producto(id_producto);


--
-- TOC entry 3672 (class 2606 OID 18468)
-- Name: orden_compra fkpgp1ooe3wmycq29kdj6qydybp; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.orden_compra
    ADD CONSTRAINT fkpgp1ooe3wmycq29kdj6qydybp FOREIGN KEY (id_proveedor) REFERENCES public.proveedor(id_proveedor);


--
-- TOC entry 3642 (class 2606 OID 18473)
-- Name: pedido fkqbl4adl78gxdv0wbx3nq03awv; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.pedido
    ADD CONSTRAINT fkqbl4adl78gxdv0wbx3nq03awv FOREIGN KEY (id_empleado) REFERENCES public.empleado(id_empleado);


--
-- TOC entry 3683 (class 2606 OID 18478)
-- Name: rol_permiso fkrhxhgw05bdvokfrpppumlfh5d; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.rol_permiso
    ADD CONSTRAINT fkrhxhgw05bdvokfrpppumlfh5d FOREIGN KEY (id_permiso) REFERENCES public.permiso(id_permiso);


--
-- TOC entry 3652 (class 2606 OID 18483)
-- Name: factura fks1vkgwvkxrt5hrmt0c50auyug; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.factura
    ADD CONSTRAINT fks1vkgwvkxrt5hrmt0c50auyug FOREIGN KEY (id_cliente) REFERENCES public.cliente(id_cliente);


--
-- TOC entry 3663 (class 2606 OID 18488)
-- Name: pago_proveedor fkswair8x6qr20rbrqj9vdupa6d; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.pago_proveedor
    ADD CONSTRAINT fkswair8x6qr20rbrqj9vdupa6d FOREIGN KEY (id_proveedor) REFERENCES public.proveedor(id_proveedor);


--
-- TOC entry 3684 (class 2606 OID 18493)
-- Name: rol_permiso fksxc3d8lmtj7em6n8j0wl4jwco; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.rol_permiso
    ADD CONSTRAINT fksxc3d8lmtj7em6n8j0wl4jwco FOREIGN KEY (id_rol) REFERENCES public.rol(id_rol);


--
-- TOC entry 3624 (class 2606 OID 18498)
-- Name: historial_precio_producto fktekqwx5wfve7xmxln2ac43c25; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.historial_precio_producto
    ADD CONSTRAINT fktekqwx5wfve7xmxln2ac43c25 FOREIGN KEY (id_producto) REFERENCES public.producto(id_producto);


--
-- TOC entry 3626 (class 2606 OID 17800)
-- Name: inventario inventario_id_empleado_fkey; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.inventario
    ADD CONSTRAINT inventario_id_empleado_fkey FOREIGN KEY (id_empleado) REFERENCES public.empleado(id_empleado);


--
-- TOC entry 3669 (class 2606 OID 17259)
-- Name: movimiento_caja movimiento_caja_id_caja_fkey; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.movimiento_caja
    ADD CONSTRAINT movimiento_caja_id_caja_fkey FOREIGN KEY (id_caja) REFERENCES public.caja(id_caja);


--
-- TOC entry 3670 (class 2606 OID 18210)
-- Name: movimiento_caja movimiento_caja_id_tipo_mov_caja_fkey; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.movimiento_caja
    ADD CONSTRAINT movimiento_caja_id_tipo_mov_caja_fkey FOREIGN KEY (id_tipo_mov_caja) REFERENCES public.tipo_movimiento_caja(id_tipo_mov_caja);


--
-- TOC entry 3633 (class 2606 OID 18023)
-- Name: movimiento_inventario movimiento_inventario_id_producto_fkey; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.movimiento_inventario
    ADD CONSTRAINT movimiento_inventario_id_producto_fkey FOREIGN KEY (id_producto) REFERENCES public.producto(id_producto);


--
-- TOC entry 3634 (class 2606 OID 18198)
-- Name: movimiento_inventario movimiento_inventario_id_tipo_movimiento_fkey; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.movimiento_inventario
    ADD CONSTRAINT movimiento_inventario_id_tipo_movimiento_fkey FOREIGN KEY (id_tipo_movimiento) REFERENCES public.tipo_movimiento(id_tipo_movimiento);


--
-- TOC entry 3673 (class 2606 OID 17876)
-- Name: orden_compra orden_compra_id_empleado_fkey; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.orden_compra
    ADD CONSTRAINT orden_compra_id_empleado_fkey FOREIGN KEY (id_empleado) REFERENCES public.empleado(id_empleado);


--
-- TOC entry 3674 (class 2606 OID 18106)
-- Name: orden_compra orden_compra_id_proveedor_fkey; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.orden_compra
    ADD CONSTRAINT orden_compra_id_proveedor_fkey FOREIGN KEY (id_proveedor) REFERENCES public.proveedor(id_proveedor);


--
-- TOC entry 3664 (class 2606 OID 18111)
-- Name: pago_proveedor pago_proveedor_id_proveedor_fkey; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.pago_proveedor
    ADD CONSTRAINT pago_proveedor_id_proveedor_fkey FOREIGN KEY (id_proveedor) REFERENCES public.proveedor(id_proveedor);


--
-- TOC entry 3643 (class 2606 OID 17952)
-- Name: pedido pedido_id_cliente_fkey; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.pedido
    ADD CONSTRAINT pedido_id_cliente_fkey FOREIGN KEY (id_cliente) REFERENCES public.cliente(id_cliente);


--
-- TOC entry 3644 (class 2606 OID 17961)
-- Name: pedido pedido_id_empleado_fkey; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.pedido
    ADD CONSTRAINT pedido_id_empleado_fkey FOREIGN KEY (id_empleado) REFERENCES public.empleado(id_empleado);


--
-- TOC entry 3618 (class 2606 OID 18034)
-- Name: producto producto_id_categoria_fkey; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.producto
    ADD CONSTRAINT producto_id_categoria_fkey FOREIGN KEY (id_categoria) REFERENCES public.categoria_producto(id_categoria);


--
-- TOC entry 3619 (class 2606 OID 18247)
-- Name: producto producto_id_unidad_fkey; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.producto
    ADD CONSTRAINT producto_id_unidad_fkey FOREIGN KEY (id_unidad) REFERENCES public.unidad_medida(id_unidad);


--
-- TOC entry 3620 (class 2606 OID 18066)
-- Name: producto_proveedor producto_proveedor_id_producto_fkey; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.producto_proveedor
    ADD CONSTRAINT producto_proveedor_id_producto_fkey FOREIGN KEY (id_producto) REFERENCES public.producto(id_producto);


--
-- TOC entry 3621 (class 2606 OID 18116)
-- Name: producto_proveedor producto_proveedor_id_proveedor_fkey; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.producto_proveedor
    ADD CONSTRAINT producto_proveedor_id_proveedor_fkey FOREIGN KEY (id_proveedor) REFERENCES public.proveedor(id_proveedor);


--
-- TOC entry 3685 (class 2606 OID 18170)
-- Name: rol_permiso rol_permiso_id_permiso_fkey; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.rol_permiso
    ADD CONSTRAINT rol_permiso_id_permiso_fkey FOREIGN KEY (id_permiso) REFERENCES public.permiso(id_permiso);


--
-- TOC entry 3686 (class 2606 OID 18159)
-- Name: rol_permiso rol_permiso_id_rol_fkey; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.rol_permiso
    ADD CONSTRAINT rol_permiso_id_rol_fkey FOREIGN KEY (id_rol) REFERENCES public.rol(id_rol);


--
-- TOC entry 3611 (class 2606 OID 18235)
-- Name: turno turno_id_empleado_fkey; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.turno
    ADD CONSTRAINT turno_id_empleado_fkey FOREIGN KEY (id_empleado) REFERENCES public.empleado(id_empleado);


--
-- TOC entry 3681 (class 2606 OID 18284)
-- Name: usuario_rol usuario_rol_id_rol_fkey; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.usuario_rol
    ADD CONSTRAINT usuario_rol_id_rol_fkey FOREIGN KEY (id_rol) REFERENCES public.rol(id_rol);


--
-- TOC entry 3682 (class 2606 OID 18273)
-- Name: usuario_rol usuario_rol_id_usuario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: posuser
--

ALTER TABLE ONLY public.usuario_rol
    ADD CONSTRAINT usuario_rol_id_usuario_fkey FOREIGN KEY (id_usuario) REFERENCES public.usuario(id_usuario);


--
-- TOC entry 3912 (class 0 OID 0)
-- Dependencies: 5
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: posuser
--

REVOKE USAGE ON SCHEMA public FROM PUBLIC;


-- Completed on 2026-04-06 17:30:57

--
-- PostgreSQL database dump complete
--

