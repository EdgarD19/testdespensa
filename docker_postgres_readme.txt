DOCKER POSTGRESQL - GUIA RAPIDA DE INICIO Proyecto POS - Spring Boot +
PostgreSQL

  --------------------------------------------------
  ¿QUE SIGNIFICA “ESTRUCTURA LISTA AUTOMATICAMENTE”?
  --------------------------------------------------
  Significa que cuando alguien ejecuta:

  docker compose up

  Docker crea automaticamente: - La base de datos -
  Las tablas - Relaciones - Datos iniciales
  (opcional)

  Sin tener que crear tablas manualmente.

  Esto se logra usando scripts SQL que Docker
  ejecuta solo la primera vez que crea la base de
  datos.
  --------------------------------------------------

SCRIPTS SQL AUTOMATICOS

Debes crear una carpeta en tu proyecto:

    database/

Dentro puedes agregar archivos SQL, por ejemplo:

    database/init.sql

Docker ejecuta automaticamente cualquier archivo ubicado en:

    /docker-entrypoint-initdb.d/

Ejemplo docker-compose.yml:

services: postgres: image: postgres:16 environment: POSTGRES_DB: posdb
POSTGRES_USER: posuser POSTGRES_PASSWORD: pospass ports: - “5432:5432”
volumes: - postgres_data:/var/lib/postgresql/data -
./database:/docker-entrypoint-initdb.d

volumes: postgres_data:

IMPORTANTE: Estos scripts solo se ejecutan la PRIMERA vez cuando el
volumen aun no existe.

  ------------------------------------------
  PASOS PARA INICIAR DOCKER (CUALQUIER PC)
  ------------------------------------------

1)  Instalar Docker Desktop

2)  Clonar el proyecto desde Git

3)  Abrir terminal en la carpeta del proyecto

4)  Ejecutar:

    docker compose up -d

5)  Verificar contenedor:

    docker ps

6)  Ejecutar el backend Spring Boot normalmente.

  -----------------
  COMANDOS UTILES
  -----------------

Iniciar contenedores: docker compose up -d

Detener contenedores: docker compose down

Ver logs: docker logs pos_postgres

Reiniciar PostgreSQL: docker restart pos_postgres

  ---------------------
  ATENCION IMPORTANTE
  ---------------------

NO ejecutar: docker compose down -v

Esto elimina el volumen y BORRA toda la base de datos.

  ---------------------------------
  MIGRAR DATOS ENTRE COMPUTADORAS
  ---------------------------------

Exportar backup: pg_dump -U posuser posdb > backup.sql

Restaurar backup: psql -U posuser -d posdb < backup.sql

  -----
  FIN
  -----
