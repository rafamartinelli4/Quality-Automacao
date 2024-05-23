package com.example.demo.controllers

import com.example.demo.model.Abastecimento
import com.example.demo.model.dto.AbastecimentoDto
import com.example.demo.repositories.AbastecimentoRepository
import org.springframework.context.annotation.Bean
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import java.time.LocalDate
import kotlin.jvm.optionals.getOrNull

@RestController
@RequestMapping("abastecimento")
class AbastecimentoController(
    private val abastecimentoRepository: AbastecimentoRepository
) {

    @GetMapping
    fun getAll() = abastecimentoRepository.findAll()

    @GetMapping("{id}")
    fun getById(@PathVariable id: Int) = abastecimentoRepository.findById(id).getOrNull()?:"O abastecimento nao existe"


    @PostMapping("inserir")
    fun inserirAbastecimento(@RequestBody body: AbastecimentoDto): String {
        val abastecimento = Abastecimento(
            valorAbastecimento = BigDecimal.TEN,
            volumeAbst = body.litros,
            codigoProduto = body.codProduto,
            codigoBico = body.codBico ,
            dataAbst = LocalDate.now(),
            imprimiu = "N",
            precoUnitario = BigDecimal(5),
            matrFuncionario = body.idFrentista
        )

        abastecimentoRepository.save(abastecimento)

        return "Abastecimento concluído com sucesso!"

    }

    @DeleteMapping("excluir/{id}")
    fun deleteAbastecimento(@PathVariable id: Int): String {

        return if (abastecimentoRepository.imprimiuById(id) == "N") "bastecimento ainda não foi pago, não é possível excluir"
        else{
            val abastecimento = abastecimentoRepository.findById(id).get()
            abastecimentoRepository.delete(abastecimento)
            "Abastecimento excluído com sucesso!"
        }
    }

}
