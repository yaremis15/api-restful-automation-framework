# language: es

@updateBooking
Necesidad del negocio: Actualizar una reserva
  Como cliente
  Quiero actualizar la información de mi reserva
  Para poder actualizar y eliminar reservas

  Regla de negocio: Para actualizar la información de la reserva, el cliente debe estar autenticado.

    @integrationTest
    Regla: Se deben enviar todos los campos de la reservación
    Esquema del escenario: Validar la actualización de una reserva actual
      Dado que la cliente desea actualizar la reservación de su próximo viaje
      Cuando ella ingrese todos los datos de su reserva con las actualizaciones deseadas
        | firstname   | lastname   | totalprice   | depositpaid   | checkinDate   | checkoutDate   | additionalneeds   |
        | <firstname> | <lastname> | <totalprice> | <depositpaid> | <checkinDate> | <checkoutDate> | <additionalneeds> |
      Entonces visualizará los detalles de la reserva con su nueva información
      Ejemplos:
        | firstname | lastname | totalprice | depositpaid | checkinDate | checkoutDate | additionalneeds  |
        | Jose     | Gutierrez| 100        | true        | 2023-05-12  | 2023-06-28   | Comics           |
        | Javier   | Mora     | 356        | true        | 2023-06-20  | 2023-07-20   | Terror           |