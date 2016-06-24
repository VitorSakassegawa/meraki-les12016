# Meraki
Projeto de E-Commerce para a matéria de Laboratório de Engenharia de Software.

## Configurando o ambiente
Os passos básicos são:

1. Importe o projeto no [Eclipse IDE for Java EE Developers](http://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/neonr);
2. Adicione o JDBC Driver no diretório /WebContent/WEB-INF/lib caso não pretenda utilizar o PostgreSQL;
3. Crie o banco de dados MerakiBD com a ferramenta de sua preferência (como o PGAdmin, no caso do PostgreSQL) - O Dump encontra-se no diretório `EspecificacoesDeSoftware`;
4. Faça o deploy no Apache Tomcat 8.0 e inicie o servidor;
5. Insira um novo usuário no banco (tabela `clientes`) ou através da página de login da aplicação para que seja possível logar na aplicação;
6. Acesse a aplicação através da url http://localhost:8080/Meraki;
7. Faça o login com o usuário criado;

>Obs: Por padrão o projeto está configurado para o banco de dados PostgreSQL, mas você pode simplesmente configura-lo para trabalhar com qualquer outro banco de dados.

## Gerando .war da aplicação
* Modo 1: Para gerar o .war da aplicação dentro do Eclipse execute `FILE` -> `EXPORT` -> `WEB` -> `WAR FILE`. 
* ou
* Modo 2: Executar o ant script (build.xml) no Eclipse ou na linha de comando:
```
$ ant
```
* Após ter executado o ant script o .war será gerado em /target/war/snapshot/Meraki.war;

## Informações adicionais
* Dentro do diretório `/lib/app` você encontra todas as libs utilizadas no projeto;
* Dentro do diretório `/lib/app/jdbc` é possível encontrar alguns drivers já disponíveis, como MySQL e PostgreSQL;
* Foram usados no design da aplicação o Bootstrap e o tema `Smarty - Website + Admin + RTL` que pode ser encontrado [aqui](https://wrapbootstrap.com/theme/smarty-website-admin-rtl-WB02DSN1B);

## Informações importantes sobre o projeto
* Caso precise, faça o download do zip do projeto, basta clicar nesse link: https://github.com/VitorSakassegawa/meraki-les12016/archive/master.zip
* O projeto zip, aponta para um banco PostgreSQL chamado `MerakiBD`, fique atento a isso.
* A documentação `PT-BR` do projeto encontra-se no diretório `EspecificacoesDeSoftware`.

