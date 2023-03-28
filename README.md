# Java-Studies
Repositório para projetos de estudo da linguagem Java seguindo os passos do curso **Java COMPLETO 2023**
do professor Nelio Alves na plataforma Undemy.

O repositório será dividido em vários módulos (um para cada seção), onde serão salvos os projetos desenvolvidos.


---
## Seção 3 - Itrodução à Linguagem Java
*Hello World* na linguagem.


---
## Seção 13 - Enumeração e composição
Exercícios de enumeração e implementações de métodos de classes


---
## Seção 15 - Tratamento de exceções 
Utilização da estrutura *try-catch* para tratamento de exceções, estudando diferentes métodos de aplicações.
Foram implementadas três soluções diferentes, todas efetivas, mas utilizando más e boas prátiacas.

### VERY BAD Solution
- Tratamento de erros é feito **dentro da classe**
- Não utiliza try-catch
- Utiliza *throws* na assinatura da função, delegando o tratamento para uma classe superior (que
não existe)
- Delegação da responsabilidade de erros da classe *Reservation* para o método que a
utiliza


### BAD Solution
- Delegamos a lógica de validação da atualização dos dados para classe *Reservation*
- Utilizamos *Strings* retornadas pelos métodos para identificar se houve algum erro
- Imprime o erro retornado na tela
- Ainda utiliza uma lógica de *if-else*


### GOOD Solution
- Adição de um bloco *try-catch* entre as chamadas das funções que podem acarretar erros
- Adição de *throws* de exceções dentro da classe *Reservation* e propagação das exceções por
meio da adição das mesmas na assinatura do método
- Tratamento dos diferentes tipos de exceção dentro do bloco
- Criação de uma classe de exceção customisada *DomainException* que "extende"
*Exception*, ou seja, exige tratamento