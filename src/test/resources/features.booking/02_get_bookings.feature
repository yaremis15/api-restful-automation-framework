# language: es

@getBookings
Necesidad del negocio: Consultar reservas
  Como usuario
  Quiero consultar la información de las reservas
  Para validar que las reservaciones se realizó correctamente

  Regla: Los parámetros de búsqueda permitidos son: idBooking, firstname & lastname, Checkin to Checkout.

    Antecedentes:
      Dado que la cliente desea consultar la reservación de su próximo viaje

    @getBookingDetails @integrationTest
    Escenario: Validar que se pueda consultar una reserva por su BookingId
      Cuando ella filtre la consulta por el parámetro "ID Booking"
      Entonces visualizará los detalles de la reserva