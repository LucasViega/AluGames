package br.com.viega.AluGames.principal

import br.com.viega.AluGames.modelo.Gamer
import br.com.viega.AluGames.modelo.Jogo
import br.com.viega.AluGames.servicos.ConsumoApi
import transformarEmIdade
import java.util.*


fun main() {

    val leitura = Scanner(System.`in`)
    val gamer = Gamer.criarGamer(leitura)
    println("Cadastro concluido com sucesso. Dados do Gamer:")
    println(gamer)
    println("Idade do gamer: " + gamer.dataNascimento?.transformarEmIdade())


    do {
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

            gamer.jogosBuscados.add(meuJogo)
        }
        println("Deseja buscar um novo jogo? S/N")
        val resposta = leitura.nextLine()

    } while (resposta.equals("s", true))

    println("Jogos Buscados:")
    println(gamer.jogosBuscados)

    println("\n Jogos ordenados por titulo: ")
    gamer.jogosBuscados.sortBy {
        it?.titulo
    }

    gamer.jogosBuscados.forEach {
        println("Titulo: ${it?.titulo}")
    }

    val jogosFiltrados = gamer.jogosBuscados.filter {
        it?.titulo?.contains("batman", true) ?: false
    }
    println("Jogos filtrados: ")
    println(jogosFiltrados)

    println("Deseja excluir algum jogo da lista original? S/N")
    val opcao = leitura.nextLine()
    if (opcao.equals("s", true)){
        println(gamer.jogosBuscados)
        println("\nInforme a posicao do jogo que deseja excluir: ")
        val posicao = leitura.nextInt()
        gamer.jogosBuscados.removeAt(posicao)
    }

    println("\nLista atualizada:")
    println(gamer.jogosBuscados)

    println("Busca finalizada com sucesso.")

}