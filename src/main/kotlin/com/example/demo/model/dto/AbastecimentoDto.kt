package com.example.demo.model.dto

import java.math.BigDecimal

data class AbastecimentoDto(
    val codProduto: String,
    val codBico: String,
    val idFrentista: String,
    val litros: BigDecimal
) {
}