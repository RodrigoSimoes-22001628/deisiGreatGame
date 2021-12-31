package pt.ulusofona.lp2.deisiGreatGame
    enum class CommandType {
        GET,
        POST
    }

    fun router() : (CommandType) -> (GameManager,List<String>) -> String? {
        return { commandType -> comandos(commandType)}
    }

     fun comandos(comando : CommandType) : (GameManager, List<String>) -> String? {
         when(comando){
             CommandType.GET-> return {i1,i2 -> getFunctions(i1,i2) }
             CommandType.POST -> return {i1,i2 -> postFunctions(i1,i2) }
        }
    }

     fun getFunctions(jogo : GameManager, lista : List<String>): String?{
         return when(lista[0]){
            "PLAYER" -> getPlayer(jogo, lista)
            "PLAYERS_BY_LANGUAGE" -> getPlayersLanguage(jogo, lista)
            "POLYGLOTS" -> getPolyGlots(jogo, lista)
            "MOST_USED_POSITIONS" -> getMostUsedPositions(jogo, lista)
            "MOST_USED_ABYSSES" -> getMostUsedAbysses(jogo, lista)
             else -> null
         }
    }

     fun postFunctions(jogo : GameManager, lista : List<String>): String?{
       return when(lista[0]){
            "ABYSS" -> postABYSS(jogo, lista)
            "MOVE" -> postMOVE(jogo, lista)
           else -> null
       }
    }

    fun getPlayer(manager: GameManager, args: List<String>): String  {
        val jogador : String  = manager.jogadoresEmJogo.filter { it.getName().split(" ")[0] == args[1]}.toString()
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
        manager.jogadoresEmJogo[manager.turnoAtual-1].incrementaPosicao(args[1].toInt(), manager.tamanhoTabuleiro)
        val mensagemRetornada = manager.reactToAbyssOrTool()
        return if (mensagemRetornada != null){
            "POST MOVE "+args[1]+"\n"+mensagemRetornada
        }else{
            "POST MOVE "+args[1]+"\n"+"OK"
        }
    }

    fun postABYSS(manager: GameManager, args1: List<String>) : String ? {
        val adicionaAbismo = manager.abismos.filter { (it.posicao == args1[2].toInt()) }
        val adicionaFerramenta = manager.ferramentas.filter { (it.posicao == args1[2].toInt()) }
        if (adicionaAbismo.size >= 1 || adicionaFerramenta.size >= 1) {
            return "Position is occupied"
        } else {
            val adicionaAbismo = Abismo()
            adicionaAbismo.setTitulo(manager.abismoPorId(args1[1].toInt()))
            adicionaAbismo.setId(args1[1].toInt())
            adicionaAbismo.setPosicao(args1[2].toInt())
            manager.abismos.add(adicionaAbismo)
            return "OK"
        }
    }