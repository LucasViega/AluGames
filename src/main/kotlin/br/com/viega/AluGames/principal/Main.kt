package br.com.viega.AluGames.principal

import br.com.viega.AluGames.modelo.Jogo
import br.com.viega.AluGames.servicos.ConsumoApi
import java.util.*


fun main() {

    val leitura = Scanner(System.`in`)
    println("Digite o codigo do jogo que deseja buscar: ")
    val busca =leitura.nextLine()

    val buscaApi = ConsumoApi()
    val informacaoJogo = buscaApi.buscaJogo(busca)

    var meuJogo: Jogo? = null

    val resultado = runCatching {
        meuJogo = Jogo(
            informacaoJogo.info.title,
            informacaoJogo.info.thumb
        )
    }

    resultado.onFailure {
        println("br.com.viega.AluGames.modelo.Jogo inexistente. Tente outro ID.")
    }

    resultado.onSuccess {
        println("Deseja inserir uma descricao personalizada? S/N")
        val opcao = leitura.nextLine()
        if (opcao.equals("s", true)) {
            println("Insira a opcao personalizada para o jogo: ")
            val descricaoPersonalizada = leitura.nextLine()
            meuJogo?.descricao = descricaoPersonalizada
        } else {
            meuJogo?.descricao = meuJogo?.titulo

        }
    }
    println(meuJogo)

    resultado.onSuccess {
        println("Busca finalizada com sucesso. ")
    }
}