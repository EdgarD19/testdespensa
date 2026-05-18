@echo off
echo ===============================
echo Restaurando base de datos...
echo ===============================

docker exec -i pos_postgres psql -U posuser -d posdb < backup\backup.sql

echo.
echo ✅ Base restaurada
pause
