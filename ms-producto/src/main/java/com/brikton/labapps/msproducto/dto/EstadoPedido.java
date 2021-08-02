package com.brikton.labapps.msproducto.dto;

import java.io.Serializable;

public enum EstadoPedido  implements Serializable {
    NUEVO,CONFIRMADO,PENDIENTE,CANCELADO,ACEPTADO,RECHAZADO,EN_PREPARACION,ENTREGADO
}
