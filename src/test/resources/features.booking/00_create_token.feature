# language: es

@createToken
@integrationTest
Necesidad del negocio: Generar token de autenticación
  Como cliente
  Quiero autenticarme en el sistema
  Para poder actualizar y eliminar reservas

  Regla: El token expira cada 10 minutos

    @token
    Escenario: Validar la creación de token de autenticación
      Dado que la cliente desea crear la reservación de su próximo viaje
      Cuando ella ingrese sus credenciales de acceso
      Entonces se autenticará en el sistema