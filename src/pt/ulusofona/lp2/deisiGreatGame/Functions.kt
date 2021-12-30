package pt.ulusofona.lp2.deisiGreatGame

class Functions {
    enum class CommandType( var comando : String) {
        GET_PLAYER("getPlayer"),
        GET_PLAYERS_BY_LANGUAGE("getPlayersLanguage"),
        GET_POLYGLOTS("getPolyGlots"),
        GET_MOST_USED_POSITIONS("getMostUsedPositions"),
        GET_MOST_USED_ABYSSES("getMostUsedAbysses"),
        POST_MOVE("postMOVE"),
        POST_ABYSS("postABYSS")
    }

    fun router(comando: CommandType) : ((GameManager, List<String>) -> String?)? {
        return when(comando.toString()){
            "getPlayer" -> ::getPlayer
            "getPlayersLanguage" -> ::getPlayersLanguage
            "getPolyGlots" -> ::getPolyGlots
            "getMostUsedPositions" -> ::getMostUsedPositions
            "getMostUsedAbysses" -> ::getMostUsedAbysses
            "postMOVE" -> ::postMOVE
            "postMove" -> ::postABYSS
            else ->  null
        }
    }

    fun getPlayer(manager: GameManager, args: List<String>): String ? {
        val jogador : String ? = manager.jogadoresEmJogo.filter { it.getName().split(" ")[0] == args[1]}.toString()
        if (jogador != "[]"){ //Se a lista não for vazia
            return jogador;
        }
        return "Inexistent player";
    }

    fun getPlayersLanguage(manager: GameManager ,args: List<String> ):String ? {
        val jogadoresComEstaLinguagem : String = manager.jogadoresEmJogo.filter { it.languages.contains(args[1])}.joinToString(","){it.getName()}
        return if (jogadoresComEstaLinguagem != "[]"){
            jogadoresComEstaLinguagem;
        }else{
            "[]";
        }
    }

    fun getPolyGlots(manager: GameManager,args: List<String>): String ? {
        val retornaLinguagens : String ? = manager.jogadoresEmJogo.filter { it.languages.size > 1 }.sortedBy{it.languages.size}.joinToString("\n"){it.getName() + ":" + it.languages.size}
        return retornaLinguagens;
    }

    fun getMostUsedPositions(manager: GameManager , args: List<String> ): String ? {
        return ""
    }

    fun getMostUsedAbysses(manager: GameManager , args: List<String> ): String ? {
        return ""
    }

    fun postMOVE(manager: GameManager , args: List<String> ) : String ?{
        var moveJogador = manager.jogadoresEmJogo[manager.turnoAtual-1].incrementaPosicao(args[1].toInt(), manager.tamanhoTabuleiro)
       // moveJogador = manager.jogadoresEmJogo.get(manager.turnoAtual-1).getPosicao()
        return "";
    }
    fun postABYSS(manager: GameManager, args1: List<String>) : String ?{
        val adicionaAbismo = manager.abismos.filter {(it.posicao == args1[2].toInt())}.joinToString { "Position is occupied" }
        return if (adicionaAbismo == "Position is occupied"){
            adicionaAbismo
        }else{
            "OK"
        }
    }
}