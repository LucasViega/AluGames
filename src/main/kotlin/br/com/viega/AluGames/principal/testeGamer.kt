import br.com.viega.AluGames.modelo.Gamer

fun main () {
    val gamer1 = Gamer("Lucas", "lucas@email.com")
    println(gamer1)

    val gamer2 = Gamer(
        "Gabriel",
        "gabriel@email.com",
        "23/02/2002",
        "Luffy")

    println(gamer2)

    gamer1.let {
        it.dataNascimento = "25/05/1998"
        it.usuario = "LordVaider"
    }.also {
        println(gamer1.idInterno)
    }

    println(gamer1)
    gamer1.usuario = "Jujubinha"
    println(gamer1)
}