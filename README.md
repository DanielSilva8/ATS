Notas
--------------------------------------------------------------

É necessário Maven instalado para fazer build
A versão do jRAPL foi alterada de forma a ser compatível com as nossas necessidades:
     1 - Foi adaptada à arquitetura Kaby Lake da Intel
     2 - Os ficheiros do CPUScaler foram alterados de forma a permitir utilizar dentro de um package a classe EnergyCheckUtils.
     3 - Estas alterações requerem que se faça make desta nova versão.
     4 - A nova versão está aqui incluida para poder fazer make.

NOTA: O mecanismo de report foi desenvolvido para CPUs com apenas 1 socket.

Após build os resultados podem ser encontrados nas seguintes diretorias:

     Testes unitáros, integração e sistema (TestNG) :
           target/surefire-reports/
           index.html

     Testes de estrutura (JaCoCo) :
           target/site/jacoco/
           index.html

     Testes de performance (jRAPL)* :
           target/jrapl-reports/
           index.html

*O sistema de report do jRAPL, html inclusive, foi totalmente desenvolvido pelo grupo.


Build
--------------------------------------------------------------

Ir -> ATS/src/test/resources/jRAPL/
      
      sudo make
      sudo modprobe msr

Ir -> ATS/
       
      sudo mvn test
      
