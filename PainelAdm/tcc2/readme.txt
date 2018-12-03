HOOKAH_APP_LOUNGES

O sistema desenvolvido como trabalho de conclusão de curso da Universidade Federal do Paraná foi motivado pela crescente demanda de lugares específicos para fumar Arguile (assim chamados os instrumentos de fumo a base de água e carvão).
O projeto consiste em um servidor Web local que irá disponibilizar diversos serviços tais como armazenamento do banco de dados e o gerenciamento de toda comunicação(Http) entre o sistema Web (Painel Administrativo) e o aplicativo mobile.

Pré requisitos: 

Para correta utilização do sistema se faz necessário o deploy do servidor Web em um computador local com configurações padrões, ou seja, . Os requisitos do computador que irá hospedar o servidor: 

Sistema operacional Windows

De acordo com o site WampServer "O WampServer instala automaticamente tudo o que você precisa para começar a desenvolver aplicativos web e é muito intuitivo de usar. Você poderá ajustar seu servidor sem tocar nos arquivos de configuração."

Para a instalação e utilização do serviço será necessário o download de um servidor de aplicação Web, recomenda-se a utilização do WampServer e, para instalação do aplicativo em um dispositivo móvel, o sistema operacional Android. Todo processo de instalação e configuração do ambiente de trabalho será fornecido pela empresa contratada e o valor já estará estipulado no contrato. 

Para a instalação do servidor basta acessar o site oficial do Wampserver http://www.wampserver.com/en/ , clicar no botão ‘START USING WAMPSERVER’ e instalar a versão de acordo com o tamanho do registro do processador do seu sistema operacional.

Para verificar o tamanho do registro do processador basta clicar com o botão direito do mouse na opção ‘Computador’ localizado no menu ‘Iniciar’ do Windows e selecionar a opção ‘Propriedades’.

Após o download do servidor, seguir o passo a passo que o Wampserver instruir e, após a conclusão do mesmo, verificar qual o IPv4 da máquina em que está se instalando. A verificação do IPv4 será necessário para realizar a comunicação do aplicativo com o Painel Administrativo, para tanto, será necessário um compartilhador de rede Internet, seja via cabo (Ethernet) ou roteador Wifi. 
Para verificar qual IP foi fornecido de acordo com o protocolo DHCP do roteador, basta digitar ‘ipconfig’ no prompt de comando do Windows. 

Para permitir a transferência de imagens entre os dois dispositivos, deverá ser alterado manualmente no arquivo de instalação do interpretador PHP. Para isso, basta clicar no ícone do Wampserver e abrir o arquivo PHP.ini na opção ‘PHP’. Ao acessar a funcionalidade de busca no código teclando CTRL+F digitar a palavra ‘file_uploads’ e alterar para ‘file_uploads = on’ assim, você está dizendo que está ativo a transferências de arquivos pelo servidor por qualquer protocolo de comunicação. 

Após a alteração do arquivo será necessário verificar qual porta o servidor de aplicação está escutando as requisições. Por padrão, as portas de comunicação Cliente-Servidor ocorrem na 8080 e ,para verificar isso basta clicar na opção Apache, no ícone do Wampserver. O Wamp fornece funcionalidades para testar a porta que está sendo utilizada para confirmar se o serviço utilizado na porta é realmente o servidor.

Ao confirmar a porta do servidor, realiza-se um teste digitando a URL no browser do navegador,  combinando o ip da máquina e a porta ativa, por exemplo, http://192.168.0.1:8080/login.php . O sistema web roda localmente em qualquer browser, porém todo o desenvolvimento foi realizado com o Google Chrome, por isso recomenda-se a utilização do mesmo. Com este link, altera-se também o link de cada serviço WebService no código do aplicativo, uma vez que para cada tipo de serviço, é iniciado pelo aplicativo realizando uma requisição ao servidor, e o servidor retornando uma resposta. 

Para permitir a comunicação, também deverá ser alterado o arquivo de configuração ‘httpd-vhosts.conf’ localizado também no menu ‘Apache’ no ícone do Wampserver. Com o arquivo aberto basta incluir o código abaixo entre as tags <VirtualHost>:

Este trecho de código realiza a permissão de outros hosts (dispositivos) para acessar o servidor via rede local. Também será necessário a verificação do Firewall do windows, que configurado de maneira incorreta, irá bloquear a comunicação da sua máquina com outros dispositivos.

Para isso, basta digitar ‘Firewall’ na pesquisa do Windows e clicar na opção ‘Propriedades do Windows Defender Firewall’. Com a janela aberta basta selecionar a aba ‘Perfil Particular’ e selecionar a opção ‘Permitir’ nos campos ‘Conexões de entrada’ e ‘Conexões de saída’.

Após todas as verificações, será necessário a instalação do software na pasta ‘www’ do Wampserver, esta é a única pasta que o Wamp executa no browser, sendo necessário todos os arquivos localizados neste local. 
O conjunto de softwares Wampserver também oferece um banco de dados nativo do php, o phpmyadmin, porém para execução do projeto utilizamos outro SGBD, o MYSQL WorkBench.
Para instalar o Mysql Workbench, basta acessar a página oficial em https://www.mysql.com/products/workbench/ e clicar na opção ‘Downloads’. Selecionar a opção ‘Community (GPL) Downloads’, que é a versão gratuita do SGBD e realizar o download do software MySQL Workbench (GPL). Após o download, o SGBD apresentará um passo a passo fácil e intuitivo para a instalação assim como a instalação do software Wampserver.
Após a instalação, executar o programa ‘MySQL Workbench’ e verificar em qual porta o servidor do banco de dados está escutando as requisições. Por padrão a porta para serviços em banco de dados é a 3306. Para verificar/alterar a porta, basta clicar com o botão direito do mouse na conexão do banco, que é uma funcionalidade da aplicação do SGBD e selecionar a opção ‘Edit Connection’. 

Após a verificação da porta, o usuário deverá configurar a conexão servidor - banco de dados informando o nome do usuário de acesso, a senha de acesso e o nome do banco de dados em que a aplicação executará.

Feito isso, basta o informar os dados de configuração no arquivo de conexão ‘db_credentials.php’, localizado na pasta que foi realizado o download do software. 

Para a instalação do aplicativo android, o dono poderá baixar o App do Google Store (Projetos futuros) ou fornecer o dispositivo (celular ou tablet) para a empresa contratada, para que o mesmo instale o aplicativo manualmente e disponibilize com as devidas configurações.
Realizado os passos anteriores, o servidor Web está pronto para ser executado e a comunicação com o aplicativo também está pronta para ser utilizada.
