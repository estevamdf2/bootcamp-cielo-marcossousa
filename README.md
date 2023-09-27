# Desafio Bootcamp Cielo

## Objetivo

Repositório do candidato Marcos Sousa cujo objetivo e mostrar aos avaliadores os desafios solicitados implementados.

## Desafios

### Desafio 1 
modelar uma API REST com operações que possibilitem a **criação, alteração,
exclusão e consulta** de pré-cadastros de clientes. O entregável deverá ser um
documento **swagger**.

### Desafio 2
a) incluir na API criada no desafio “1” uma nova operação que possibilite a **retirada do
próximo cliente** da fila de atendimento e retorne os dados disponíveis:

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



