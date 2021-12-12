Tabela de Ferramentas:

![](tabelaFerramentas.png?raw=true "Tabela de Ferramentas")

Diagrama UML:

![](diagrama.png?raw=true "Diagrama UML")

Decidimos modelar a classe Abismos e Ferramentas através da Herança, pois considerámos que seria mais fácil organizar o nosso código, mas também porque deste modo fica muito mais simples armazenar os dados relativos aos Abismos e as Ferramentas na função createInitialBoard().
Para conseguir armazenar os nomes dos Abismos e da Ferramentas, desenvolvemos os métodos abismoPorId() e ferramentaPorId().

Acrescentamos na classe Programmer um ArrayList de ferramentas a cada jogador para conseguirmos saber quais a ferramentas que cada jogador possuí. Posteriormente no método verificaAbismos() verificamos se o jogador do turno atual possui a ferramenta útil para que este se salve do abismo, caso este não a possua sofre a respetiva consequência.
Implementamos também o método removeTools(), para remover as ferramentas dos jogadores após estes as utilizarem para se salvarem dos respetivos abismos.

Ao desenvolver o Abismo Efeitos secundários, acrescentámos um ArrayList na classe Programmer para deste modo conseguirmos gravar todas as posições do tabuleiro pelas quais o nosso jogador passou, pois este Abismo faz com que o jogador recue duas jogadas atrás.
 
Vídeo : https://youtu.be/4ZpyQg94GSs
