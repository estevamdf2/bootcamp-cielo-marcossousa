# Desafio Bootcamp Cielo

## Objetivo

Repositório do candidato Marcos Sousa cujo objetivo e mostrar aos avaliadores os desafios solicitados implementados.

* [Aplicação](#aplicação)
* [Desafios](#desafios)
* [Desafio 1](#desafio-1)
* [Desafio 2](#desafio-2)
* [Desafio 3](#desafio-3)
* [Desafio 4](#desafio-4)


## Aplicação


### Autenticação

Para utilizar os endpoints da aplicação e necessário realizar o login na aplicação. Use um dos seguintes usuários cadastrados para esta finalidade:

| username     | password | role  |
|--------------|----------|-------|
| ayrton.senna | ehTetra  | ADMIN |
| alain.prost  | Balestri | USER  |
## Desafios

### Desafio 1 
_Modelar uma API REST com operações que possibilitem a **criação, alteração,
exclusão e consulta** de pré-cadastros de clientes. O entregável deverá ser um
documento **swagger**_.


### Desafio 2

_a) incluir na API criada no desafio “1” uma nova operação que possibilite a **retirada do
próximo cliente** da fila de atendimento e retorne os dados disponíveis_

_b) implementar na linguagem java uma estrutura de dados para uma fila utilizando
apenas tipos de dados primitivos (sem utilizar classes java.util.*), onde seja possível
acrescentar e retirar clientes na fila no modelo FIFO (First In, First Out)._

_c) contemplar as regras da história de usuário através da implementação da operação
modelada no item “a”, utilizando a estrutura de fila criada no item “b”_

_d) Implementar cobertura de 70% de testes unitários_

Os seguintes endpoints foram criados para esta finalidade;

* `GET /clientes`: retorna a lista de clientes na fila de atendimento.
* `GET /clientes/retirar`: retira o primeiro cliente na fila para atendimento.

Para testar a funcionalidade compartilhamos os seguintes dados para cadastro:

**Pessoa Física**

Cadastre os seguintes dados utilizando o endpoint `POST /clientes/pessoasfisicas`:

```json
{
  "mcc": "0763",
  "cpf": "84122878004",
  "nomePessoa": "Ana Moreira Froes",
  "emailPessoa": "ana.moreira@ada.tech"
}
```

**Pessoa Jurídica**

Cadastre os seguintes dados utilizando o endpoint `POST /clientes/pessoasjuridicas`:

```json
{
  "mcc": "1711",
  "cpf": "63944134028",
  "cnpj": "88824062000169",
  "razaoSocial": "Tudo frio Ar condicionado",
  "nomeContato": "Marcelino Freitas",
  "emailContato": "marcelino.freitas@ada.tech"
}
```

```json
{
  "mcc": "5814",
  "cpf": "93885056038",
  "cnpj": "55064933000134",
  "razaoSocial": "Bolos fofos bolos confeitados",
  "nomeContato": "Gabriela Lemos",
  "emailContato": "gabi.fofos@ada.tech"
}
```

Após realizar o cadastro chame o endpoint `GET /clientes` para visualizar a lista de clientes na fila de atendimento.

![Resposta endpoint get /clientes](docs/imagens/1-get-clientes.PNG "Resposta endpoint GET /clientes")

#### Simulando uma alteração
Vamos simular uma alteração no cadastro do cliente 2, **Tudo frio Ar condicionado** para colocar este cliente em último na fila.

Vamos utilizar o endpoint `PUT /clientes/pessoasjuridicas`
segue um json com a sugestão de alteração:
```json
{
  "mcc": "1711",
  "cpf": "63944134028",
  "cnpj": "88824062000169",
  "razaoSocial": "Tudo frio Ar condicionado - LTDA",
  "nomeContato": "Marcelino Freitas",
  "emailContato": "marcelino.freitas@ada.tech"
}
```

Após a alteração o cliente aparecerá em último na fila de atendimento.

![Resposta endpoint get /clientes após alterar cliente](docs/imagens/2-get-clientes.PNG "Resposta endpoint GET /clientes após alterar cliente")

#### Retirando um cliente para atendimento

Conforme demonstrado pelos passos anteriores. Quando for realizada a chamada ao endpoint `GET /clientes/retirar`. Iremos receber o seguinte cliente da fila:
```json
{
  "mcc": "0763",
  "cpf": "84122878004",
  "nomePessoa": "Ana Moreira Froes",
  "emailPessoa": "ana.moreira@ada.tech"
}
```

Faça a chamada ao endpoint e obtenha a seguinte resposta

![Resposta endpoint get /clientes/retirar](docs/imagens/3-get-clientes-retirar.PNG "Resposta endpoint GET /clientes/retirar")

Ao chamar novamente o endpoint `GET /clientes` teremos somente dois clientes na fila de atendimento.

![Resposta endpoint get /clientes após retirar cliente](docs/imagens/4-get-clientes-apos-retirada.PNG "Resposta endpoint GET /clientes após alterar cliente")

### Desafio 3

_Desenhe e implemente uma nova solução para a fila de atendimento, utilizando a
solução de mensageria SQS da AWS._

Implementado junto a aplicação um serviço que se comunica com a Amazon SQS cujo objetivo e gerenciar a fila de atendimento de novos clientes cadastrados no **Prospect clientes**.

Segue abaixo o desenho da arquitetura para o atendimento do desafio.

![Arquitetura Amazon SQS](docs/imagens/5-arquitetura-desafio3.png "Arquitetura Amazon SQS")

### Desafio 4

_a) identifique um débito técnico de Segurança da Informação na aplicação;_

_b) detalhe o débito técnico identificado, informando a criticidade e possíveis
consequências;_

_c) planeje as atividades técnicas para o desenvolvimento da solução;_

_d) implemente a solução._


A aplicação atualmente encontra-se pública, ou seja, qualquer pessoa que tenha acesso a URL da API conseguirá realizar
requisições para Ela. Isto é um ponto critico pois desta forma podemos expor dados sigilosos, sensíveis da aplicação e dos clientes mantidos pela aplicação.

O Spring conta com um módulo que trata do controle de acesso e autenticação a aplicação. O modulo Spring Security conta com os seguintes recursos:
* Suporte e extensibilidade para autenticação e autorização;
* Proteção contra ataques como sessão fixa, clickjacking, cross site request forgery, etc;
* Integração a servlet API;
* Dentre outros;

#### Planejamento das atividades
Desta forma temos que inserir as seguintes bibliotecas ao projeto:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-test</artifactId>
    <scope>test</scope>
</dependency>
```

A segunda lib será utilizada para testes da aplicação.

Como a aplicação se comportara no modo _STATELESS_ onde a API não armazenará o estado da sessão. Com isso em cada requisição temos que confirmar qual usuário está
autenticado a fim de verificar se ele terá permissões para obter os recursos.
Para que não se envie os dados de login e senha em todas as requisições utilizaremos o JSON Web Token.

Quando o usuário se autenticar ele receberá um token de autorização e o utilizara nas chamadas aos endpoints. Para isso, faremos o uso desta outra biblioteca

```xml
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
    <version>0.11.5</version>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-impl</artifactId>
    <version>0.11.5</version>
    <scope>runtime</scope>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-jackson</artifactId>
    <version>0.11.5</version>
    <scope>runtime</scope>
</dependency>
```

- falar sobre o SecurityConfig
Descrever os passos realizados.



----
Para incluir a autenticação no Swagger e habilitar o cabeçalho para as requisições faça os seguintes passos.

1. Abra a classe **SpringDocConfigurations** é inclua os seguintes trecho de código

Aqui você definirá o SecurityScheme para JWT
```java 
private SecurityScheme createAPIKeyScheme() {
    return new SecurityScheme().type(SecurityScheme.Type.HTTP)
        .bearerFormat("JWT")
        .scheme("bearer");
}
```

Aqui você incluirá as configurações de segurança relacionada ao bean do OpenApi
```java
.components(new Components()
        .addSecuritySchemes("bearer-key",
        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT"))) 
```

Método completo e a variável criada com o SecurityScheme para melhorar a visualização:

```java 
private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
        .bearerFormat("JWT")
        .scheme("bearer");

@Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().
                        addList("Bearer Authentication"))
                .components(new Components().addSecuritySchemes
                        ("Bearer Authentication", createAPIKeyScheme()))
                .info(new Info()
                        .title(nomeProjeto)
                        .description(descricao)
                        .contact(new Contact()
                                .name("Marcos Sousa")
                                .email("backend@adatech.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://empresa/api/licenca")));
    }
```

Agora você terá a opção de se autenticar via swagger, obter o token e usar o token 



