## Requisitos de Uso:
- Possuir o **Docker** instalado na máquina;
- Possuir **git** instalado na máquina;
- Possuir conta no https://mailtrap.io/ e o **Token** da API deles;
- Possuir um **cliente** HTTP.
## Como Usar:
- Utilize **git clone** para clonar este repositório;
- Entre na pasta do repositório clonado e crie um arquivo **.env** com base no arquivo **.env-exemple**;
- Utilize **git clone** para clonar o repositório: https://github.com/joaoAaf/api-service-mail;
- Execute o comando **docker compose up -d**, para para subir os containers;
- Se tudo ocorrer com sucesso, 03 container devem estar ativos: **db, api-email e api-email**;
- Para acessar os **endpoints** da api de usuários utilize qualquer cliente HTTP com a seguinte URL: **localhost:8000/{RECURSO}**;
- Existem dois **Recursos** nesta API: "**/users**" e "**/login**"
- Modelo de **body** para uma requisição **POST** em "**/users**":<br>
{
    "name": "Fulano de Tal",
    "email": "fulano@gmail.com",
    "pass": "123456"
};<br>
Obs1: Para rquisições **PUT** deve ser usado o mesmo modelo de **body**, passando o **Token** no cabeçalho autorization;<br>
Obs2: Para rquisições **GET** e **DELETE**, não há **body**, deve ser passado somente o **Token** no cabeçalho autorization;
- Modelo para uma requisição **POST** em "**/login**":<br>
{
    "login": "fulano@gmail.com",
    "pass": "123456"
};<br>
Obs3: Esta requisição irá retornar um **Token**.