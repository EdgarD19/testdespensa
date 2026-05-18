@echo off
echo ===============================
echo Generando backup de PostgreSQL...
echo ===============================

docker exec -t pos_postgres pg_dump -U posuser posdb > backup\backup.sql

echo.
echo ✅ Backup creado en carpeta /backup
pause
