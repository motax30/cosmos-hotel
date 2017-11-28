# Cosmos Hotel

Projeto de uma aplicação web para um hotel fictício, apropriado ao projeto da disciplina **Laboratório de Banco de Dados III** do curso **Banco de Dados** da FATEC de São José dos Campos.

## Desenvolvimento
O desenvolvimento deste projeto consiste nas seguintes ferramentas:

### Backend (API RESTful)
- Gradle
- Db4o
- Spark

### Frontend
- EmberJS
- Bootstrap

## Integrantes
- Adriano Mota ([@motax30](https://github.com/motax30))
- Denis Soares ([@denissoares](https://github.com/denissoares))
- Felipe Koblinger ([@felipekoblinger](https://github.com/felipekoblinger))

## Professor
- Giuliano Bertoti ([@giulianobertoti](https://github.com/giulianobertoti))

## Deploy
Para rodar a aplicação é necessário:
- NodeJS
- Java 8

### Instalando o EmberJS e Bower
- `npm install -g ember-cli`
- `npm install -g bower`
- `npm install -g yarn`

### Configuração Inicial
Na pasta do projeto, faça:
- `cd frontend`
- `yarn install` (baixa os arquivos necessários do Ember)
- `bower install` (baixa os arquivos do front-end)
- `ember s` (inicia o servidor EmberJS no endereço http://localhost:4200)

### Configuração Proxy do Ember

Editar o arquivo .bowerrcc, caso não exista, criá-lo na pasta frontend do projeto onde o bower foi instalado:
Inserir os dados correspondentes ao proxy, coforme abaixo
  - Com dados de servidor e porta
  {
  "proxy": "http://<url>:<port>",
  "https-proxy": "http://<url>:<port>"
  }
  Exemplo:
  {
  "proxy": "http://proxy.adrianomota.com:1234",
  "https-proxy": "http://proxy.adrianomota.com:1234"
  }
  --------------------------------------------------------------------------------------------------------------
  - Com dados de usuário,senha, servidor e porta
  "proxy": "http://<username>:<password>@<url>:<port>",
  "https-proxy": "http://<username>:<password>@<url>:<port>"
  }
  Exemplo:
  {
    "proxy": "http://adrianoams:123456789@192.168.132.55:8080",
  "https-proxy": "http://adrianoams:123456789@192.168.132.55:8080"
  }
http://laurenthinoul.com/how-to-enable-bower-to-work-behind-a-proxy/
