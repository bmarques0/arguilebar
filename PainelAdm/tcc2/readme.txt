HOOKAH_APP_LOUNGES

O sistema desenvolvido como trabalho de conclus�o de curso da Universidade Federal do Paran� foi motivado pela crescente demanda de lugares espec�ficos para fumar Arguile (assim chamados os instrumentos de fumo a base de �gua e carv�o).
O projeto consiste em um servidor Web local que ir� disponibilizar diversos servi�os tais como armazenamento do banco de dados e o gerenciamento de toda comunica��o(Http) entre o sistema Web (Painel Administrativo) e o aplicativo mobile.

Pr� requisitos: 

Para correta utiliza��o do sistema se faz necess�rio o deploy do servidor Web em um computador local com configura��es padr�es, ou seja, . Os requisitos do computador que ir� hospedar o servidor: 

Sistema operacional Windows

De acordo com o site WampServer "O WampServer instala automaticamente tudo o que voc� precisa para come�ar a desenvolver aplicativos web e � muito intuitivo de usar. Voc� poder� ajustar seu servidor sem tocar nos arquivos de configura��o."

Para a instala��o e utiliza��o do servi�o ser� necess�rio o download de um servidor de aplica��o Web, recomenda-se a utiliza��o do WampServer e, para instala��o do aplicativo em um dispositivo m�vel, o sistema operacional Android. Todo processo de instala��o e configura��o do ambiente de trabalho ser� fornecido pela empresa contratada e o valor j� estar� estipulado no contrato. 

Para a instala��o do servidor basta acessar o site oficial do Wampserver http://www.wampserver.com/en/ , clicar no bot�o �START USING WAMPSERVER� e instalar a vers�o de acordo com o tamanho do registro do processador do seu sistema operacional.

Para verificar o tamanho do registro do processador basta clicar com o bot�o direito do mouse na op��o �Computador� localizado no menu �Iniciar� do Windows e selecionar a op��o �Propriedades�.

Ap�s o download do servidor, seguir o passo a passo que o Wampserver instruir e, ap�s a conclus�o do mesmo, verificar qual o IPv4 da m�quina em que est� se instalando. A verifica��o do IPv4 ser� necess�rio para realizar a comunica��o do aplicativo com o Painel Administrativo, para tanto, ser� necess�rio um compartilhador de rede Internet, seja via cabo (Ethernet) ou roteador Wifi. 
Para verificar qual IP foi fornecido de acordo com o protocolo DHCP do roteador, basta digitar �ipconfig� no prompt de comando do Windows. 

Para permitir a transfer�ncia de imagens entre os dois dispositivos, dever� ser alterado manualmente no arquivo de instala��o do interpretador PHP. Para isso, basta clicar no �cone do Wampserver e abrir o arquivo PHP.ini na op��o �PHP�. Ao acessar a funcionalidade de busca no c�digo teclando CTRL+F digitar a palavra �file_uploads� e alterar para �file_uploads = on� assim, voc� est� dizendo que est� ativo a transfer�ncias de arquivos pelo servidor por qualquer protocolo de comunica��o. 

Ap�s a altera��o do arquivo ser� necess�rio verificar qual porta o servidor de aplica��o est� escutando as requisi��es. Por padr�o, as portas de comunica��o Cliente-Servidor ocorrem na 8080 e ,para verificar isso basta clicar na op��o Apache, no �cone do Wampserver. O Wamp fornece funcionalidades para testar a porta que est� sendo utilizada para confirmar se o servi�o utilizado na porta � realmente o servidor.

Ao confirmar a porta do servidor, realiza-se um teste digitando a URL no browser do navegador,  combinando o ip da m�quina e a porta ativa, por exemplo, http://192.168.0.1:8080/login.php . O sistema web roda localmente em qualquer browser, por�m todo o desenvolvimento foi realizado com o Google Chrome, por isso recomenda-se a utiliza��o do mesmo. Com este link, altera-se tamb�m o link de cada servi�o WebService no c�digo do aplicativo, uma vez que para cada tipo de servi�o, � iniciado pelo aplicativo realizando uma requisi��o ao servidor, e o servidor retornando uma resposta. 

Para permitir a comunica��o, tamb�m dever� ser alterado o arquivo de configura��o �httpd-vhosts.conf� localizado tamb�m no menu �Apache� no �cone do Wampserver. Com o arquivo aberto basta incluir o c�digo abaixo entre as tags <VirtualHost>:

Este trecho de c�digo realiza a permiss�o de outros hosts (dispositivos) para acessar o servidor via rede local. Tamb�m ser� necess�rio a verifica��o do Firewall do windows, que configurado de maneira incorreta, ir� bloquear a comunica��o da sua m�quina com outros dispositivos.

Para isso, basta digitar �Firewall� na pesquisa do Windows e clicar na op��o �Propriedades do Windows Defender Firewall�. Com a janela aberta basta selecionar a aba �Perfil Particular� e selecionar a op��o �Permitir� nos campos �Conex�es de entrada� e �Conex�es de sa�da�.

Ap�s todas as verifica��es, ser� necess�rio a instala��o do software na pasta �www� do Wampserver, esta � a �nica pasta que o Wamp executa no browser, sendo necess�rio todos os arquivos localizados neste local. 
O conjunto de softwares Wampserver tamb�m oferece um banco de dados nativo do php, o phpmyadmin, por�m para execu��o do projeto utilizamos outro SGBD, o MYSQL WorkBench.
Para instalar o Mysql Workbench, basta acessar a p�gina oficial em https://www.mysql.com/products/workbench/ e clicar na op��o �Downloads�. Selecionar a op��o �Community (GPL) Downloads�, que � a vers�o gratuita do SGBD e realizar o download do software MySQL Workbench (GPL). Ap�s o download, o SGBD apresentar� um passo a passo f�cil e intuitivo para a instala��o assim como a instala��o do software Wampserver.
Ap�s a instala��o, executar o programa �MySQL Workbench� e verificar em qual porta o servidor do banco de dados est� escutando as requisi��es. Por padr�o a porta para servi�os em banco de dados � a 3306. Para verificar/alterar a porta, basta clicar com o bot�o direito do mouse na conex�o do banco, que � uma funcionalidade da aplica��o do SGBD e selecionar a op��o �Edit Connection�. 

Ap�s a verifica��o da porta, o usu�rio dever� configurar a conex�o servidor - banco de dados informando o nome do usu�rio de acesso, a senha de acesso e o nome do banco de dados em que a aplica��o executar�.

Feito isso, basta o informar os dados de configura��o no arquivo de conex�o �db_credentials.php�, localizado na pasta que foi realizado o download do software. 

Para a instala��o do aplicativo android, o dono poder� baixar o App do Google Store (Projetos futuros) ou fornecer o dispositivo (celular ou tablet) para a empresa contratada, para que o mesmo instale o aplicativo manualmente e disponibilize com as devidas configura��es.
Realizado os passos anteriores, o servidor Web est� pronto para ser executado e a comunica��o com o aplicativo tamb�m est� pronta para ser utilizada.
