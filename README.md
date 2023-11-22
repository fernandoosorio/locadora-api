# locadora-api


git clone https://github.com/fernandoosorio/locadora-api

cd locadora-api

Verificar versão do java ( java -version) deve estar na versão 17

Noo linux para escolher diferentes versões do java é preciso fazer sudo update-alternatives --config java

mvn dependency:resolve  (precisa estar na vesão 17 do Java)

Se tudo ocorreu como esperado: [INFO] BUILD SUCCESS


Abrir o projeto na sua IDE de preferência.
Escolher o arquivo application.properties e configurar o banco de dados.

Criar um banco de dados chamado locadora ( o esquema será gerado via JPA)

Executar o projeto ( executar/debugar o arquivo LocadoraApiApplication.java)

Se tudo ocorreu conforme orientado, as tabelas do banco terão sido criadas.
