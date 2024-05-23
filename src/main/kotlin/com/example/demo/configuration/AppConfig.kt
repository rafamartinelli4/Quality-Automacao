package com.example.demo.configuration

import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.JdbcTemplate

@Configuration
class AppConfig(private val jdbcTemplate: JdbcTemplate) {

    @PostConstruct
    fun initDataBase() {
        listOf(
            """
                CREATE TABLE IF NOT EXISTS abastecimentos (
                  controle INTEGER NOT NULL,
                   bomba VARCHAR(3),
                   total DECIMAL(18, 2),
                   codpro VARCHAR(5),
                   idfrentista VARCHAR(5),
                   imprimiu VARCHAR(1),
                   litros DECIMAL(18, 3),
                   data date,
                   pu DECIMAL(18, 3),
                   CONSTRAINT pk_abastecimentos PRIMARY KEY (controle)
                )
            """.trimIndent(),
            "CREATE SEQUENCE IF NOT EXISTS abastecimentos_controle_seq INCREMENT BY 1 START WITH 1;",
            "ALTER TABLE public.abastecimentos ALTER COLUMN controle SET DEFAULT nextval('abastecimentos_controle_seq'::regclass);",

        ).forEach { sql ->
            jdbcTemplate.execute(sql)
        }
    }
}