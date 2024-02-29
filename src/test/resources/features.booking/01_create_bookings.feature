# language: es

@createBooking
Necesidad del negocio: Crear una reserva
Como cliente
Quiero hacer una nueva reserva
Para poder programar mi próximo viaje

Regla: Los campos firstname, lastname, totalprice, depositPaid, checkIn, checkOut, additionalNeeds,
son obligatorios para crear una reserva

@integrationTest
Esquema del escenario: Validar la creación de una nueva reserva
Dado que la cliente desea crear la reservación de su próximo viaje
Y se carga su información al sistema
| firstname   | lastname   | totalprice   | depositpaid   | checkinDate   | checkoutDate   | additionalneeds   |
| <firstname> | <lastname> | <totalprice> | <depositpaid> | <checkinDate> | <checkoutDate> | <additionalneeds> |
Cuando ella solicita la creación de una reserva
Entonces su solicitud se creará en el sistema con su información y un número de registro único
Ejemplos:
| firstname | lastname  | totalprice | depositpaid | checkinDate | checkoutDate | additionalneeds |
| Pedro    | Gutierrez | 100        | true        | 2024-03-01  | 2024-04-02   | Comics          |
| Javier   | Jaramillo | 365        | true        | 2024-03-15  | 2024-04-15   | Terror          |