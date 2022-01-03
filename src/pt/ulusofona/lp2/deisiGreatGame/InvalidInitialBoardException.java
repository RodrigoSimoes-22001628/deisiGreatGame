package pt.ulusofona.lp2.deisiGreatGame;

public class InvalidInitialBoardException  extends  Exception {
    String messagem;
    public InvalidInitialBoardException(String messagem) {
        this.messagem = messagem;
    }
    public String getMessage() {
        return messagem;
    }
    public boolean isInvalidAbyss(){
        return this.messagem.contains("Erro : Abismo Inválido");
    }
    public boolean isInvalidTool() {
        return this.messagem.contains("Erro : Ferramenta Inválida");
    }
    public String getTypeId(){
        return messagem.substring(messagem.length()-1);
    }
}
