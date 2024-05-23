package com.example.demo.business

import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class AbastecimentoBusiness {
    fun calcularValorTotal(vlUnitario: BigDecimal, litros: BigDecimal): BigDecimal {
        return vlUnitario * litros
    }

    fun getvlUnitario(codProduto: String): BigDecimal {
        return when(codProduto){
            "Gasolina" ->BigDecimal(5.10)
            "Alcool" ->BigDecimal(4.90)
            "Diesel" ->BigDecimal(4.80)
             else ->{
                 return (error("Produto não encontrado"))
             }
        }
    }
}
