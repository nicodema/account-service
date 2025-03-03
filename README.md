# account-service
Microservicio 2 que corresponde a Cuenta y Movimiento

URL de GitHub:
https://github.com/nicodema/account-service

Se utilizo como base de datos H2, no tiene script ya que se creo la estructura desde la aplicacion y se ingreso Clientes de prueba

Para probar los servicios en POSTMAN:

- Consulta los Clientes en el microservicio 1 por id de persona: 
GET - http://localhost:8082/api/cuentas/1

- Crear una nueva Cuenta
POST - http://localhost:8082/api/cuentas
JSON:
{
  "numero": "478759",
  "tipo": "Corriente",
  "saldoInicial": 3000,
  "estado": true,
  "clienteId": 1
}

- Crear Movimientos ingresando el valor y numero de cuenta
POST - http://localhost:8082/api/movimientos

{
  "numeroCuenta":"496825",
  "valor": 800
}

{
  "numeroCuenta":"496825",
  "valor": -200
}

- Reporte de Estado de cuenta por cliente y fechas
GET - http://localhost:8082/api/reportes?fechaInicio=2025-02-01&fechaFin=2025-03-31&clienteId=2



