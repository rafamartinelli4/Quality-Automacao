package com.example.demo.repositories

import com.example.demo.model.Abastecimento
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface AbastecimentoRepository : JpaRepository<Abastecimento, Int> {
    @Query("SELECT imprimiu FROM abastecimento a WHERE a.controle = ?1", nativeQuery = true)
    fun imprimiuById(controle: Int): String
}